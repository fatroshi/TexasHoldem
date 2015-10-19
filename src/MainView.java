/**
 * Created by Farhad Atroshi on 12/10/15.
 */


import Poker.GameBackground;
import Poker.GameBackground_;
import highscore.AlertWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.awt.util.IdentityArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Pane paneRoot       = controller.getRootPane();

        // MENU BAR
        BorderPane root     = new BorderPane();
        MenuBar mb          = new MenuBar();
        VBox topVBox        = new VBox();
        VBox centerBox      = new VBox();

        Menu fileMenu       = new Menu("File");
        MenuItem openItem   = new MenuItem("Open");
        MenuItem closeItem  = new MenuItem("Close");
        MenuItem highscoreItem  = new MenuItem("Highscore");
        MenuItem exitItem   = new MenuItem("Exit");

        // END
        fileMenu.getItems().addAll(openItem, closeItem,highscoreItem, exitItem);
        
        // shows highscorelist
        highscoreItem.setOnAction(event -> AlertWindow.show("Highscore List", "send\nthree\nlist\nhere\nas\nList", 3000, 3000));
        //
        mb.getMenus().addAll(fileMenu);
        topVBox.getChildren().add(mb);
        paneRoot.getChildren().add(topVBox);
        // END MENU BAR

        // TableLogic
        GameBackground start = new GameBackground(GameBackground_.TABLE_BLACK.getImageSrc());

        // Add table to scene 
        root.getChildren().add(start.getImageView());

        // Create players, get user info, get chips for each player
        controller.createPlayers(paneRoot);

        /*
        // On click start!
        controller.game.dealTwoCards();

        // Get the first 2 cards for each player
        controller.getFirstTwoCards(paneRoot);

        // Deal 5 cards
        controller.game.dealCards(5);
        controller.getTableCards(1, 3);

        // Card 4 5
        controller.getTableCards(4, 5, paneRoot);
        */
        // Get user btn
        controller.getStartBtn();

        // Add to scene
        root.setTop(topVBox);
        root.setCenter(paneRoot);
        Scene scene = new Scene(root, 1000, 650);

        window.setScene(scene);
        window.show();
    }
}


