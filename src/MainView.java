/**
 * Created by Farhad on 12/10/15.
 */

import Layout.ButtonLayout;
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


public class MainView extends Application{
    List<ImageView> imgViews = new IdentityArrayList<>();
    Map<Pane,List<ImageView>> graphics = new HashMap<>();
    Stage window;



    public static void main(String[] args) {
        // Set program as a javaFx application
        // Then calls start(Stage primaryStage)
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("KING KONG POKER");

        Controller controller = new Controller();

        // Pane
        Pane paneRoot   = new Pane();

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
        root.getChildren().add(table.getImageView());

        // Create players
        controller.createPlayers(root);

        // On click start!
        controller.game.dealTwoCards();

        // Get the first 2 cards for each player
        controller.getFirstTwoCards(root);
        /*





        // Add first 2 cards for players
        game.dealTwoCards();
        // Get the first 2 cards for each player
        getFirstTwoCards(paneRoot);

        // Deal 5 cards
        game.dealCards(5);

        // Card 1 2 3
        getTableCards(1, 3, paneRoot);

        // Card 4 5
        getTableCards(4, 5, paneRoot);

        // Get user btn
        getUserBtn(paneRoot);

        // Add to scene
        root.setTop(topVBox);
        root.setCenter(paneRoot);

        */




        Scene scene = new Scene(root, 1000, 650);




        window.setScene(scene);
        window.show();

    }


}


