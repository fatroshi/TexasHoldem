/**
 * Created by Farhad on 07/10/15.
 * This class is used for accessing stage and to be able to interact with the scene.
 *
 */

package View;

import Poker.GameBackground;
import Poker.GameBackground_;
import highscore.DB;
import highscore.HighScoreList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    public ViewStart(Stage stage,BorderPane borderPane){
        this.stage = stage;
        this.root = borderPane;
        // Holder for game background
        this.paneCenter = new Pane();
        // Holder for player cards
        this.playerCards = new Pane();
        // Holder for table cards
        this.tableCards = new ArrayList<>();

        // Init scene elements
        this.startView();
        this.createGameMenu();


    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Pane getPaneCenter() {
        return paneCenter;
    }

    public void setPaneCenter(Pane paneCenter) {
        this.paneCenter = paneCenter;
    }

    public Pane getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(Pane playerCards) {
        this.playerCards = playerCards;
    }

    public List<Pane> getTableCards() {
        return tableCards;
    }


    public void setTableCards(List<Pane> tableCards) {
        this.tableCards = tableCards;
    }

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

    public void startView(){
        // TableLogic
        GameBackground start = new GameBackground(GameBackground_.TABLE_BLACK.getImageSrc());

        // Add table to scene 
        this.root.getChildren().add(start.getImageView());
    }
    /*
    public void highScoreHanlder(){
        // AVI SKRIV VAD SOM SKA GORAS HAR
        System.out.println("High Score got clicked");
    }
    */



}
