/**
 * Created by Farhad Atroshi on 12/10/15.
 */


import View.Controller;
import View.ViewStart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import highscore.*;


public class MainView extends Application{

    Stage window;

    HighScoreList hsl = new HighScoreList();
    DB db = new DB("dbScoreList.bin");
    // testing

    public static void main(String[] args) {
        // Set program as a javaFx application
        // Then calls start(Stage primaryStage)
        launch(args);
    }


    @Override
    public void start(Stage stage) {


        // Root
        BorderPane root     = new BorderPane();

        // View start
        ViewStart viewStart = new ViewStart(window,root);

        // Game controller
        Controller controller = new Controller(viewStart);

        /*
        // MENU BAR
        MenuBar mb          = new MenuBar();
        VBox topVBox        = new VBox();
        VBox centerBox      = new VBox();

        Menu fileMenu       = new Menu("File");
        MenuItem openItem   = new MenuItem("Open");
        MenuItem closeItem  = new MenuItem("Close");
        MenuItem highscoreItem  = new MenuItem("High Score");
        MenuItem exitItem   = new MenuItem("Exit");

        // END
        fileMenu.getItems().addAll(openItem, closeItem,highscoreItem, exitItem);
        
        // shows highscorelist
        highscoreItem.setOnAction(event -> highscore.AlertWindow.show("High Score List",db.getData().toString(), 3000, 3000));
        //
        mb.getMenus().addAll(fileMenu);
        topVBox.getChildren().add(mb);
        //paneRoot.getChildren().add(topVBox);
        // END MENU BAR

        // TableLogic
        GameBackground start = new GameBackground(GameBackground_.TABLE_BLACK.getImageSrc());

        */

        // Add table to scene 
        //root.getChildren().add(start.getImageView());

        //
        window = stage;
        // Set title for the stage
        window.setTitle("KING KONG POKER");
        // Add stuff to the stage
        Scene scene = new Scene(controller.getRootBorderPane(), 1000, 650);

        window.setScene(scene);
        window.show();
    }
}


