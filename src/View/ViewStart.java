/**
 * Created by Farhad & Avi
 * This class is used for accessing stage and to be able to interact with the scene.
 *
 */

package View;

import Layout.ButtonLayout;
import Poker.GameBackground;
import Poker.GameBackground_;
import Poker.Table;
import Poker.Table_;
import User.Player;
import highscore.DB;
import highscore.HighScoreList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



public class ViewStart extends Pane{

    Stage stage;
    BorderPane root;
    Pane paneCenter;
    Pane  playerCards;
    List<Pane> tableCards;
    HighScoreList hsl = new HighScoreList();
    DB db = new DB("dbScoreList.bin");

    //FROM GRAPHIC
    private Slider slider;
    private Label sliderLabel;
    private Label statusLabel;
    private Label potLabel;
    private List<Label> usernameLabels = new ArrayList<>();
    private List<Label> blanceLabels = new ArrayList<>();

    // FROM TABLE
    private List<Rectangle> playersBg               = new ArrayList<>(); // Background for player profile
    private Button startBtn;                        // The start btn, Starts the game
    private Button playBtn;                         // Play btn. Call,Raise,Check
    private Button foldBtn;                         // Player fold

    private Table game;

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

        createGameGraphics();

        // Init scene elements
        this.startView();
        this.createGameMenu();

    }

    public Table getGame(){
        return this.game;
    }


    public void createGameGraphics(){
        // FROM GRAPHIC
        createTableLabes();

        // Game buttons
        createButtons();

        // Crate slider
        createSlider(0,100,0);
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

    public void createTableLabes(){
        slider      = this.createSlider(0,100,0);       // Slider fot betting (min,max,currentSliderValue)
        sliderLabel = createLabel(240, 465, 24);        // Label for the slider
        statusLabel = createLabel(400, 575, 24);        // Label for showing current status: bet/call/raise
        potLabel    = createLabel(443,340,24);
    }

    public void drawTableLabels(){
        this.paneCenter.getChildren().addAll(this.slider,this.sliderLabel,this.statusLabel,this.potLabel);
    }

    public void createButtons(){
        startBtn        = createButton("START");        // Start the game
        playBtn         = createButton("PLAY");         // Play: check,call,raise
        foldBtn         = createButton("FOLD");         // player folds
    }

    public Button getStartBtn(){
        return this.startBtn;
    }

    public Button getPlayBtn(){
        return this.playBtn;
    }

    public Button getFoldBtn(){
        return this.foldBtn;
    }

    public void displayUserinfo(){
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

            paneCenter.getChildren().addAll(r, username, balance);
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



    public Slider getSlider() {
        return slider;
    }

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
        return slider;
    }

    public Label getSliderLabel(){
        return this.sliderLabel;
    }

    public Label getStatusLabel(){
        return this.statusLabel;
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


    /*
    @Override
    public void updateSlider(double currentBet, double userBalance, String message) {
        // Update the slider
        //System.out.println("Slider got updated by the update method in graphic");
        this.slider.setValue(currentBet);
        this.slider.setMax(userBalance);

        double value = round(slider.getValue(), 0);
        String strValue = String.valueOf(value);
        // Slider label
        sliderLabel.setText("$ " + strValue);

        // Status label
        this.statusLabel.setText(message);
    }

    @Override
    public void decreaseUserBalance(int activeUser, double userBalance) {
        String strBalance = String.valueOf(userBalance);
        blanceLabels.get(activeUser).setText(strBalance);
    }

    @Override
    public void updateTablePotLabel(double pot) {
        pot = round(pot, 0);

        String strPot = "Pot $ " + String.valueOf(pot);

        this.potLabel.setTextFill(Color.LIGHTGREEN);

        this.potLabel.setText(strPot);
    }

    */


    /**
     * Found at: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}
