/**
 * Created by Farhad & Avi
 * This class is used for accessing stage and to be able to interact with the scene.
 * Also for creating the graphic elements in the game.
 *
 */

package View;

import Handler.FoldButtonHandler;
import Handler.PlayButtonHandler;
import Layout.ButtonLayout;
import Poker.*;
import User.Player;
import highscore.DB;
import highscore.HighScoreList;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class ViewStart extends Pane implements Observer{

    Stage stage;
    BorderPane root;
    Pane paneCenter;
    Pane  playerCards;
    List<Pane> tableCards;
    HighScoreList hsl = new HighScoreList();
    DB db = new DB("dbScoreList.bin");

    //FROM GRAPHIC
    private Slider slider;                                  // Slider is used for betting
    private Label sliderLabel;                              // Show current value of the slider
    private Label statusLabel;                              // Provide feedback for who checked,raised, folded or went all in
    private Label potLabel;                                 // Label for total pot
    private List<Label> usernameLabels = new ArrayList<>(); // Holder for all username labels
    private List<Label> blanceLabels = new ArrayList<>();   // Holder for all balance labels
    private Label winnerLabel;                              // Show who won the pot

    // FROM TABLE
    private List<Rectangle> playersBg               = new ArrayList<>(); // Background for player profile
    private Button startBtn;                        // The start btn, Starts the game
    private Button playBtn;                         // Play btn. Call,Raise,Check
    private Button foldBtn;                         // Player fold
    // THE GAME LOGIC
    private Table game;

    // Controller
    Controller controller;

    public ViewStart(Stage stage,BorderPane borderPane){

        // Create table object
        this.game =  new Table();
        this.stage = stage;
        this.root = borderPane;
        // Holder for game background
        this.paneCenter = new Pane();
        // Holder for player cards
        this.playerCards = new Pane();
        // Holder for table cards
        this.tableCards = new ArrayList<>();

        // Game Controller
        controller = new Controller(this);

        // Init scene elements
        this.startView();
        this.createGameMenu();
        this.createButtons();

        // Start btn
        this.drawStartBtn();
    }

    /**
     * Get the game controller
     * @return Controller
     */
    public Controller getController(){
        return this.controller;
    }

    /**
     * Get the root border pane
     * @return Borderpane
     */
    public BorderPane getBorderPane(){
        return  this.root;
    }


    /**
     * Get the game (Class Table)
     * @return game
     */
    public Table getGame(){
        return this.game;
    }


    /**
     * Adds the poker table image to the scene
     */
    public void showGameBg(){
        GameBackground table = new GameBackground(GameBackground_.TABLE.getImageSrc());
        //Animation.fadeIn(table);
        this.paneCenter.getChildren().add(table.getImageView());
    }

    /**
     * Change bg for the winner/s
     */
    public void setWinnerBG() {
        // Change color of player bg
        this.playersBg.get(this.game.getActiveUser()).setFill(Color.GOLD);
        this.usernameLabels.get(this.game.getActiveUser()).setTextFill(Color.BLACK);
        this.blanceLabels.get(this.game.getActiveUser()).setTextFill(Color.BLACK);
    }

    /**
     * Creates labels for the table and adds to the scene
     */
    public void showTableLabes(){
        slider      = this.createSlider(0,100,0);       // Slider fot betting (min,max,currentSliderValue)
        sliderLabel = createLabel(240, 465, 24);        // Label for the slider
        statusLabel = createLabel(400, 575, 24);        // Label for showing current status: bet/call/raise
        potLabel    = createLabel(443,340,24);
        // Add to pance center
        this.paneCenter.getChildren().addAll(this.slider,this.sliderLabel,this.statusLabel,this.potLabel);
    }

    /**
     * Creates all buttons and button handlers for the game
     */
    public void createButtons(){
        // Start btn
        startBtn        = createButton("START");        // Start the game
        //startBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new StartButtonHandler(this.controller));
        startBtn.setOnMouseClicked(event -> startBtnHandler());
        // Play btn
        playBtn         = createButton("PLAY");         // Play: check,call,raise
        playBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PlayButtonHandler(this));
        // Fold btn
        foldBtn         = createButton("FOLD");         // player folds
        foldBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new FoldButtonHandler(this.controller));
    }

    /**
     * Handles the start button click
     * Updates the scene by adding graphic elements to the scene
     */
    public void startBtnHandler(){
        this.showGameGraphics();
    }

    /**
     * Add play button to the scene
     */
    public void showPlayBtn(){
        this.paneCenter.getChildren().add(this.playBtn);
    }

    /**
     * Add fold button to the scene
     */
    public void showFoldBtn(){
        this.paneCenter.getChildren().add(this.foldBtn);
    }

    /**
     * Add graphic elements to the scene.
     */
    public void showGameGraphics(){
        this.showGameBg();
        this.showTableLabes();
        this.showUserinfo();
        // Btn
        this.showPlayBtn();
        this.showFoldBtn();
        // Controller
        this.controller.showUserChips();
        // Dealer deal 2 cards for each player
        this.controller.getGame().dealTwoCards();
        // Add to scene
        this.controller.showFirstTwoCards();
        // Set active user
        //Dealer deal 5 for the table
        this.controller.getGame().dealCards(5);
        // Winner label
        this.createWinnerLabel();

        // Show current active player by changing the bg
        updateBgUser();

    }

    /**
     * Add the start btn to the scene
     */
    public void drawStartBtn(){
        this.paneCenter.getChildren().add(this.startBtn);
    }

    /**
     * Add players information to the scene (except the chips)
     */
    public void showUserinfo(){
        Label username;
        Label balance;

        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            Player player = this.game.getPlayer(i);
            // Create bg for player
            Rectangle r = createPlayerBg(i);
            // Get username
            username = createUsernameLabel(player,i);
            // Get balance
            balance = createBalanceLabel(player,i);
            this.paneCenter.getChildren().addAll(r, username, balance);
        }
    }

    /**
     * Update the players label balances
     */
    public void updateLabelBalances(){
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            Player player = this.game.getPlayer(i);
            blanceLabels.get(i).setText("$ " + String.valueOf(player.getBalance()));
        }
    }

    /**
     * This is used as root, all nodes will be added to this.
     * @return the root BorderPane
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * The center part of the Border pane.
     * @return returns the center part.
     */
    public Pane getPaneCenter() {
        return paneCenter;
    }

    /**
     * Holder for players 2 cards
     * @return the pane
     */
    public Pane getPlayerCards() {
        return playerCards;
    }

    /**
     * Holder for the table cards, total of five
     * @return the pane
     */
    public List<Pane> getTableCards() {
        return tableCards;
    }


    /**
     * Creates the menu when starting the game
     * The user can check the high score list
     */
    public void createGameMenu(){
        // MENU BAR
        MenuBar mb          = new MenuBar();
        VBox topVBox        = new VBox();

        Menu fileMenu       = new Menu("Game");
        MenuItem highscoreItem  = new MenuItem("Top High Score");

        // shows highscore
         highscoreItem.setOnAction(event -> highscore.AlertWindow.show("High Score List",db.getData().toString(), 300, 400));
        //highscoreItem.setOnAction(event -> highScoreHanlder());

        // Add item to menu
        fileMenu.getItems().addAll(highscoreItem);

        // Get all menus
        mb.getMenus().addAll(fileMenu);

        // Add to top
        topVBox.getChildren().add(mb);

        // Add to paneCenter
        this.paneCenter.getChildren().add(topVBox);

        // Add to scene
        this.root.getChildren().add(this.paneCenter);
    }

    /**
     * Adds start image to the root border Pane
     */
    public void startView(){
        // TableLogic
        GameBackground start = new GameBackground(GameBackground_.TABLE_BLACK.getImageSrc());

        // Add table to scene 
        this.root.getChildren().add(start.getImageView());
    }


    /**
     * Get the slider
     * @return slider
     */
    public Slider getSlider() {
        return slider;
    }

    /**
     * The label for showing the total pot
     * @return label
     */
    public Label getPotLabel() {
        return potLabel;
    }

    /**
     * Create a slider set min,max and start value
     * @param setMin
     * @param setMax
     * @param setValue
     * @return
     */
    public Slider createSlider(int setMin, int setMax, int setValue) {
        Slider slider = new Slider();
        slider.setMin(setMin);
        slider.setMax(setMax);
        slider.setValue(setValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(500);
        slider.setMinWidth(520);
        slider.setLayoutX(240);
        slider.setLayoutY(510);
        slider.setTooltip(new Tooltip("Check or Raise"));
        slider.setStyle("-fx-color: RED;");

        slider.setOnMouseDragged(event -> sliderHandler());

        return slider;
    }

    /**
     * Updates the slider label when the slider is changed.
     */
    public void sliderHandler(){
        double value = round(slider.getValue(),0);
        this.sliderLabel.setText("$ " + String.valueOf(value));
        this.game.setSliderValue(value);
    }

    /**
     * Get the slider label
     * @return label
     */
    public Label getSliderLabel(){
        return this.sliderLabel;
    }


    /**
     * The status label is used for providing information to the players
     * check,raise all in;
     * @return
     */
    public Label getStatusLabel(){
        return this.statusLabel;
    }


    /**
     * Update the background for the current active user
     */
    public void updateBgUser(){
        int id = this.game.getActiveUser();
        Rectangle r;

        for (int i = 0; i < this.game.getPlayers().size(); i++) {

            r = this.playersBg.get(i);
            if(i != id){
                // Other player
                r.setFill(Color.BLACK);
                r.setStroke(Color.DARKGRAY);
                usernameLabels.get(i).setTextFill(Color.WHITESMOKE);
                blanceLabels.get(i).setTextFill(Color.DARKGREEN);

            }else{
                // Active user
                r.setFill(Color.DARKGREEN);
                r.setStroke(Color.LIGHTGREEN);
                usernameLabels.get(i).setTextFill(Color.WHITE);
                blanceLabels.get(i).setTextFill(Color.LIGHTYELLOW);
            }
        }

    }

    /**
     * Create label set x,y and fontsize
     * @param layoutX
     * @param layoutY
     * @param fontSize
     * @return the label
     */
    public Label createLabel(double layoutX, double layoutY, int fontSize){
        Label label = new Label();
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setFont(Font.font(fontSize));
        label.setTextFill(Color.WHITE);
        return label;
    }

    /**
     * Update label text
     * @param label
     * @param text
     */
    public void updateLabel(Label label, String text, Color color){
        label.setText(text);
        label.setTextFill(Color.WHITESMOKE);
    }

    /**
     * Create label for player, label text will be the username
     * Enum Table_ is used to set the layoutX, layoutY values.
     * @param player
     * @param playerIndex
     * @return
     */
    public Label createUsernameLabel(Player player, int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create label for username
        Label usernameLabel = new Label(player.getUsername());
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                usernameLabel.setLayoutX(t.getXlayout() - 20);
                usernameLabel.setLayoutY(t.getYlayout() + 95);
                usernameLabel.setTextFill(Color.LIGHTGRAY);
                usernameLabel.setFont(Font.font(18));
            }
        }

        usernameLabels.add(usernameLabel);
        return usernameLabel;

    }

    /**
     * Create label for player, label text will be the balance
     * Enum Table_ is used to set the layoutX, layoutY values.
     * @param player
     * @param playerIndex
     */
    public Label createBalanceLabel(Player player, int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create Label for balance
        Label label = new Label("$ " + String.valueOf(player.getBalance()));
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                // balance
                label.setLayoutX(t.getXlayout() - 20);
                label.setLayoutY(t.getYlayout() + 118);
                label.setTextFill(Color.GREEN);
            }
        }
        // Add to array
        this.blanceLabels.add(label);
        return label;
    }

    /**
     * Create background for user, and set layoutX, LayoutY
     * @param playerIndex
     * @return
     */
    public Rectangle createPlayerBg(int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create ractangle
        Rectangle r = new Rectangle();
        // Properties
        r.setFill(Color.BLACK);
        r.setStroke(Color.DARKGRAY);
        r.setWidth(140);
        r.setHeight(50);
        r.setArcWidth(10);
        r.setArcHeight(10);

        // Find the correct x,y position for the player
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                r.setX(t.getXlayout() - 35);
                r.setY(t.getYlayout() + 90);
            }
        }
        // Add to array
        this.playersBg.add(r);
        return r;
    }

    /**
     * Updade player background
     * @param rectangle
     * @param color
     */
    public void updatePlayerBg(Rectangle rectangle, Color color) {
        // Change color of player bg
        rectangle.setFill(color);
    }

    /**
     * creates button
     * @param name
     *
     */

    public Button createButton(String name){
        Button btn = new Button();
        for (ButtonLayout b : ButtonLayout.values()) {

            if(b.name() == name) {
                btn.setText(b.name());
                // Style btn
                String css = "-fx-stroke: #4e5b65; " +
                        "-fx-background-color:" + b.getColor() + ";" +
                        "-fx-stroke: green;";
                btn.setStyle(css);
                btn.setTextFill(Color.WHITESMOKE);
                // Set size
                btn.setMinWidth(90);
                btn.setMinHeight(40);

                // Set x,y layout
                btn.setLayoutX(b.getX());
                btn.setLayoutY(b.getY());
            }
        }
        return btn;
    }

    /**
     * Get players background
     * @return a list of rectangles with fill color black.
     */
    public List<Rectangle> getPlayersBg(){
        return this.playersBg;
    }

    /**
     * Found at: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     *
     * @param value Value that we want to round
     * @param places How many decimals
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Return the winner label
     * @return label
     */
    public Label getWinnerLabel(){
        return this.winnerLabel;
    }

    /**
     * Create the winner label and att to scene
     */
    public void createWinnerLabel(){
        this.winnerLabel = new Label();
        this.winnerLabel.setTextFill(Color.LIGHTGREEN);
        this.winnerLabel.setFont(Font.font(20));
        this.winnerLabel.setLayoutX(370);
        this.winnerLabel.setLayoutY(370);
        paneCenter.getChildren().add(winnerLabel);
    }

    /**
     * Update winner label with the username of the winners
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        String txt= "Winners: ";
        if(this.game.getWinner().size() > 1){

            for(Player p: this.game.getWinner()){
                txt += p.getUsername() + " ";
            }
            this.winnerLabel.setText(txt);

        }else{
            Player player = this.game.getWinner().get(0);
            this.winnerLabel.setText("The Winner is : " + player.getUsername());
        }

        Player player = this.game.getWinner().get(0);
        this.winnerLabel.setText("The Winner is : " + player.getUsername());
        Animation.fadeIn(winnerLabel);

    }


}
