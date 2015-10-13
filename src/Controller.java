/**
 * Created by Farhad on 12/10/15.
 */

import Layout.CardLayout;
import Layout.ChipLayout;
import Layout.UserLayout;
import Poker.*;
import Dealer.*;
import User.*;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.awt.util.IdentityArrayList;
import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.shape.*;

public class Controller extends Application{
    List<ImageView> imgViews = new IdentityArrayList<>();
    Map<Pane,List<ImageView>> graphics = new HashMap<>();
    Stage window;

    // Create game
    Poker game = new Poker();

    public static void main(String[] args) {
        // Set program as a javaFx application
        // Then calls start(Stage primaryStage)
        launch(args);
    }

    public void addToPane(Picture o,Pane pane){
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
    }

    public void createProfileBg(Pane root){

        Rectangle r;
        Label username, balance;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            r = new Rectangle();
            r.setFill(Color.BLACK);
            r.setStroke(Color.DARKGRAY);

            // Get username
            String user = game.getPlayer(i).getUsername();
            username = new Label(user);
            // Get balance
            String strBalance = "$ " + String.valueOf(game.getPlayer(i).getBalance());
            balance = new Label(strBalance);
            // Get your x,y layout
            for (Table_ t: Table_.values()){
                if(i == t.getUserId()){
                    r.setX(t.getXlayout() - 35);
                    r.setY(t.getYlayout() + 90);

                    // x,y for label
                    // username
                    username.setLayoutX(t.getXlayout() - 20);
                    username.setLayoutY(t.getYlayout() + 100);
                    username.setTextFill(Color.LIGHTGRAY);
                    username.setFont(Font.font(18));
                    // balance
                    balance.setLayoutX(t.getXlayout() - 20);
                    balance.setLayoutY(t.getYlayout() + 140);
                    balance.setTextFill(Color.GREEN);
                }
            }
            r.setWidth(140);
            r.setHeight(80);
            r.setArcWidth(20);
            r.setArcHeight(20);
            root.getChildren().addAll(r, username, balance);
        }
    }

    public void getUserChips(Pane root){
        int blackCounter = 0;
        int redCounter = 0;
        int blueCounter = 0;
        int greenCounter = 0;
        int whiteCounter = 0;
        int addY=0;
        for (int playerId = 0; playerId < game.getPlayers().size(); playerId++) {
            Player p = game.getPlayer(playerId);
            System.out.println("[CHIPS} Player ID" + playerId );
            for (int j = 0; j < p.getChips().size(); j++) {
                Chip chip = p.getChip(j);
                for (ChipLayout cl: ChipLayout.values()){
                    if(playerId == cl.getUserId()) {

                        if(chip.getChipValue() == Chip_.BLACK.getValue()){
                            blackCounter++;
                            addY = blackCounter;
                        }else if(chip.getChipValue() == Chip_.RED.getValue()){
                            redCounter++;
                            addY = redCounter;
                        }else if(chip.getChipValue() == Chip_.BLUE.getValue()){
                            blueCounter++;
                            addY = blueCounter;
                        }else if(chip.getChipValue() == Chip_.GREEN.getValue()){
                            greenCounter++;
                            addY = greenCounter;
                        }else if(chip.getChipValue() == Chip_.WHITE.getValue()){
                            whiteCounter++;
                            addY = whiteCounter;
                        }

                        if (chip.getChipValue() == cl.getChipValue()) {
                            chip.getImageView().setX(cl.getX());
                            chip.getImageView().setY(cl.getY() - addY);
                        }


                    }
                }

                // Set x,y for layout
                for (UserLayout ul: UserLayout.values()){
                    if( playerId == ul.getUserId()){
                        chip.getImageView().setLayoutX(ul.getLayoutX());
                        chip.getImageView().setLayoutY(ul.getLayoutY());
                    }
                }

                // Add to scene
                root.getChildren().add(chip.getImageView());
            }
            // Reset counters
            blackCounter    = 0;
            redCounter      = 0;
            blueCounter     = 0;
            greenCounter    = 0;
            whiteCounter    = 0;
        }
    }

    public void getFirstTwoCards(Pane root){
        for (int playerId = 0; playerId < game.getPlayers().size(); playerId++) {
            Hand hand = game.getPlayer(playerId).getHand();
            for (int j = 0; j < hand.getNoOfCards(); j++) {
                Card card = hand.getCard(j);
                for (CardLayout cl: CardLayout.values()){
                    // Set x,y for inside layout
                    if( j == cl.getCardId()){
                        card.getImageView().setX(cl.getX());
                        card.getImageView().setY(cl.getY());
                        card.getImageView().setRotate(cl.getRotation());
                        // Toggle card when clicked
                        card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new MousePressHandler(card));
                    }
                }

                // Set x,y for layout
                for (UserLayout ul: UserLayout.values()){
                    if( playerId == ul.getUserId()){
                        card.getImageView().setLayoutX(ul.getLayoutX() + 200);
                        card.getImageView().setLayoutY(ul.getLayoutY());
                    }
                }

                // Add to scene
                root.getChildren().add(card.getImageView());
            }

        }
    }

    public void addPaneToPane(Pane p, Pane root){
        root.getChildren().add(p);
    }

    public void fadeIn(Picture p){
        FadeTransition ft = new FadeTransition(Duration.millis(500), p.getImageView());
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    public void fadeOut(Picture p){
        FadeTransition ft = new FadeTransition(Duration.millis(500), p.getImageView());
        ft.setFromValue(0.0);
        ft.setToValue(1.);
        ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    public void move(Picture p, double x, double y){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), p.getImageView());
        tt.setByX(x);
        tt.setByY(y);

        //tt.setCycleCount(4);
        //tt.setAutoReverse(true);

        tt.play();
    }


    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("KING KONG POKER");


        // Pane
        Pane paneRoot   = new Pane();
        //List<Pane> paneTable  = new ArrayList<>();
        Pane paneTable = new Pane();

        List<Pane> panePlayers = new ArrayList<>();
        Pane paneDeck   = new Pane();

        // MENU BAR
        BorderPane root = new BorderPane();
        MenuBar mb = new MenuBar();
        VBox topVBox = new VBox();
        VBox centerBox = new VBox();

        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        MenuItem closeItem = new MenuItem("Close");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(openItem, closeItem, exitItem);

        mb.getMenus().addAll(fileMenu);
        topVBox.getChildren().add(mb);
        paneRoot.getChildren().add(topVBox);
        // END MENU BAR

        // Table
        GameBackground table = new GameBackground(GameBackground_.TABLE.getImageSrc());

        // Add table to scene 
        addToPane(table,paneRoot);


        // Create player
        game.addPlayer("Farhad", 1269);
        game.addPlayer("Johan", 3268);
        game.addPlayer("Felicia", 4694);
        game.addPlayer("Elise", 2343);

        // Add Profile BG to scene
        createProfileBg(paneRoot);
        // Add User Chips to scene
        getUserChips(paneRoot);

        // Add first 2 cards for players
        game.dealTwoCards();
        // Get the first 2 cards for each player
        getFirstTwoCards(paneRoot);

        // Add 5 card to table
        // Dessa fem borde sparas i en static lista !!!! sa att alla objekt kan dela pa dessa!!
        // far error eftersom javaFx vill inte lagga till exakt samma objekt flera ggr
        game.dealCards(5);

        for (int i = 0; i < Poker.tableCards.size(); i++) {
            Card card = Poker.tableCards.get(i);
            //System.out.println(Poker.tableCards.size());
            //System.out.println(card.getRank());

            for(Table_ t: Table_.values()){
                if(t.getCardId() == (i+2)){
                    card.getImageView().setX(t.getX() * card.getImageView().getImage().getWidth());                 // Set x
                    card.getImageView().setY(t.getY());                                                             // Set y
                    card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new MousePressHandler(card));
                }
            }

            addToPane(card, paneTable);
        }

        // Add to scene
        paneTable.setLayoutX(240);
        paneTable.setLayoutY(230);
        addPaneToPane(paneTable, paneRoot);


        root.setTop(topVBox);
        root.setCenter(paneRoot);

        Scene scene = new Scene(root, 1000, 650);

        window.setScene(scene);
        window.show();

    }

    private class MousePressHandler implements EventHandler<Event>{
        Card card;

        MousePressHandler(Card card){
            this.card = card;
        }

        @Override
        public void handle(Event evt) {
            this.card.toggleImage();
        }
    }
}


