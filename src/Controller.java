/**
 * Created by Farhad on 12/10/15.
 */




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
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.awt.util.IdentityArrayList;
import java.util.*;
import javafx.event.EventHandler;


public class Controller extends Application{
    List<ImageView> imgViews = new IdentityArrayList<>();
    Map<Pane,List<ImageView>> graphics = new HashMap<>();
    Stage window;

    public static void main(String[] args) {
        // Set program as a javaFx application
        // Then calls start(Stage primaryStage)
        launch(args);
    }

    public void addToPane(Picture o,Pane pane){
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
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

        // Create game
        Poker game = new Poker();

        //



        // Create player
        game.addPlayer("Farhad", 2000);
        game.addPlayer("Johan", 7000);
        game.addPlayer("Felicia", 6000);
        game.addPlayer("Elise", 8000);


        // Create pane for each player
        for (int i = 0; i < game.getPlayers().size(); i++) {
            // Create pane holder for player
            panePlayers.add(new Pane());
        }

        // Add first 2 cards for players
        game.dealTwoCards();
        for (int playerId = 0; playerId < game.getPlayers().size(); playerId++) {
            Hand hand = game.getPlayer(playerId).getHand();
            System.out.println("Total players " + game.getPlayers().size());
            for (int cardIndex = 0; cardIndex < hand.getNoOfCards(); cardIndex++) {
                Card card = hand.getCard(cardIndex);
                System.out.println("Player id: " + playerId + ", card index: " + cardIndex);
                for (Table_ p: Table_.values()){
                    if(cardIndex == p.getCardId()) {
                        // Set Card position in layout
                        card.getImageView().setX(p.getX());                 // Set x
                        card.getImageView().setY(p.getY());                 // Set y
                        card.getImageView().setRotate(p.getRotation());     // Set rotation
                        //card.setOnMouseClicked(event -> card.setImageFrontView());
                        card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new MousePressHandler(card));
                    }
                    if(playerId == p.getUserId()){
                        // Set Layout position
                        panePlayers.get(playerId).setLayoutX(p.getXlayout()); // Set layout x
                        panePlayers.get(playerId).setLayoutY(p.getYlayout()); // Set layout y
                    }
                }
                addToPane(card, panePlayers.get(playerId));
            }
        }

        // Add to scene panePlayers
        for (int i = 0; i < panePlayers.size(); i++) {
            addPaneToPane(panePlayers.get(i),paneRoot);
        }



        // Add 5 card to table
        // Dessa fem borde sparas i en static lista !!!! sa att alla objekt kan dela pa dessa!!
        // far error eftersom javaFx vill inte lagga till exakt samma objekt flera ggr
        game.dealCards(5);

        for (int i = 0; i < Poker.tableCards.size(); i++) {
            Card card = Poker.tableCards.get(i);
            System.out.println(Poker.tableCards.size());
            System.out.println(card.getRank());

            for(Table_ t: Table_.values()){
                if(t.getCardId() == (i=2)){
                    card.getImageView().setX(t.getX());                 // Set x
                    card.getImageView().setY(t.getY());                 // Set y
                }
            }

            addToPane(card, paneTable);
        }

        // Add to scene
        addPaneToPane(paneTable,paneRoot);



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


