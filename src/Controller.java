/**
 * Created by Farhad on 12/10/15.
 */




import Poker.*;
import Dealer.*;
import User.*;



import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.awt.util.IdentityArrayList;
import javafx.scene.shape.*;
import javafx.scene.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





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
        Pane paneTable  = new Pane();
        Pane paneCards  = new Pane();
        Pane paneP1     = new Pane();
        Pane paneP2     = new Pane();
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
        // Create player
        game.addPlayer("Farhad", 2000);
        game.addPlayer("Johan", 543210);
        game.dealCards(2);

        for (int i = 0; i < game.getPlayers().size(); i++) {
            System.out.println("Player");
            Hand hand = game.getPlayer(i).getHand();

            System.out.println("Cards in hand " + hand.getNoOfCards());
            for (int j = 0; j < hand.getNoOfCards(); j++) {
                System.out.println("cards");
                Card card = hand.getCard(j);
                System.out.println(card.getSuit());
                addToPane(card, paneP1);

            }
        }

        addPaneToPane(paneP1,paneRoot);
        root.setTop(topVBox);
        root.setCenter(paneRoot);

        Scene scene = new Scene(root, 1000, 700);
        window.setScene(scene);
        window.show();


    }
}


