import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.awt.util.IdentityArrayList;
import javafx.scene.shape.*;


import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Farhad on 07/10/15.
 */


public class UserInterface extends Application{

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

        Poker game = new Poker();

        Deck deck = new Deck();
        Player p1 = new Player("Farhad");
        Player p2 = new Player("Farhad");


        for (int i = 0; i < 7; i++) {
            p2.hand.addCard(deck.dealCard());
        }

        game.bestHand(p2.getHand());

        window = stage;
        window.setTitle("KING KONG POKER");

        Pane paneRoot   = new Pane();
        Pane paneTable  = new Pane();
        Pane paneCards  = new Pane();
        Pane paneP1     = new Pane();
        Pane paneP2     = new Pane();
        Pane paneDeck   = new Pane();

        // Table
        Background table = new Background(Background_.TABLE.getImageSrc());
        // Add table to scene
        addToPane(table,paneRoot);

        // P1 Add Card to scene
        Background bgP1 = new Background(Background_.PLAYER.getImageSrc());
        addToPane(bgP1, paneP1);

        // P1 username
        Text p1Username = new Text(p1.getUsername());
        p1Username.setScaleY(1.4);
        p1Username.setScaleX(1.4);
        p1Username.setX(40);
        p1Username.setY(25);
        paneP1.getChildren().add(p1Username);

        // P1 username
        Text p1Balance = new Text("$ " + String.valueOf(p1.getSaldo()));
        p1Balance.setScaleY(1.5);
        p1Balance.setScaleX(1.5);
        p1Balance.setX(40);
        p1Balance.setY(60);
        paneP1.getChildren().add(p1Balance);


        // P1 Cards
        // Create cards

        // card 1
        Card card1 = deck.dealCard();
        card1.getImageView().setX(140);
        // card 2
        Card card2 = deck.dealCard();
        card2.getImageView().setX(155);
        card2.getImageView().setRotate(10);

        // Card from deck (illusion)
        Card card3 = deck.dealCard();
        card3.getImageView().setX(260);
        card3.getImageView().setY(300);

        Card card4 = deck.dealCard();
        card4.getImageView().setX(340);
        card4.getImageView().setY(300);

        Card card5 = deck.dealCard();
        card5.getImageView().setX(420);
        card5.getImageView().setY(300);

        // Add cards to scene
        addToPane(card1,paneP1);
        addToPane(card2,paneP1);
        // From deck
        addToPane(card3,paneDeck);
        addToPane(card4,paneDeck);
        addToPane(card5,paneDeck);

        // Animation
        addPaneToPane(paneDeck,paneRoot);

        // Player 1 This is a holder JOHAN
        // So when u move this, all objects move...
        paneP1.setLayoutX(210);
        paneP1.setLayoutY(560);

        addPaneToPane(paneP1,paneRoot);

        Scene scene = new Scene(paneRoot, 1000, 700);
        window.setScene(scene);
        window.show();

    }

    // ADD TO SCENE
    //public void getChildren()

}
