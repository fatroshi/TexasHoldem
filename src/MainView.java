/**
 * Created by Farhad Atroshi on 12/10/15.
 * Start the game
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

        window = stage;
        // Set title for the stage
        window.setTitle("KING KONG POKER");

        // Create scene
        // Add nodes applied to the root to scene
        // Setup up dimension of the window
        Scene scene = new Scene(viewStart.getBorderPane(), 1000, 650);

        window.setScene(scene);
        window.show();
    }
}


