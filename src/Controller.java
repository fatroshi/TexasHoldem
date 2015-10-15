import Dealer.Card;
import Dealer.Chip;
import Dealer.Chip_;
import Layout.ButtonLayout;
import Layout.CardLayout;
import Layout.ChipLayout;
import Layout.UserLayout;
import Poker.Picture;
import Poker.*;
import Poker.Table_;
import User.Hand;
import User.Player;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;

/**
 * Created by Farhad on 14/10/15.
 */
public class Controller {
    // Create game
    private Poker game;
    // Place holder for items
    private Pane root;

    public Controller(){
        game = new Poker();
        root = new Pane();
    }

    public Pane getRootPane(){
        return this.root;
    }

    public Poker getGame(){
        return  this.game;
    }

    public void addToPane(Picture o,Pane pane){
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
    }

    public void createPlayers(Pane root){
        game.addPlayer("Lawen", 1000);
        game.addPlayer("Farhad", 2130);
        game.addPlayer("Felicia", 4213);
        game.addPlayer("Elise", 4219);

        getUserInfo(root);
        getUserChips(root);
    }

    public void getUserInfo(Pane root){
        for (int i = 0; i < game.getPlayers().size(); i++) {
            // Get user background
            Rectangle r = getGame().getPlayerBG(i);
            // Get username
            Label username = getGame().getUsernameLabel(i);
            // Get balance
            Label balance = getGame().getBalanceLabel(i);
            // Add to root scene
            root.getChildren().addAll(r,username, balance);
        }
    }

    public void getUserChips(Pane root){
        for (int i = 0; i < game.getPlayers().size(); i++) {
            for (int j = 0; j < game.getPlayerChips(i).size(); j++) {
                Chip chip = game.getPlayerChips(i).get(j);
                // Add to scene
                root.getChildren().add(chip.getImageView());
            }
        }
    }

    public void getFirstTwoCards(){
        for (int i = 0; i < game.getPlayerCards().size(); i++) {
            Card card = game.getPlayerCards().get(i);
            // Set event handler when card clicked
            card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
            // Add to scene
            this.root.getChildren().add(card.getImageView());
        }
    }

    public Pane getTableCards(int from, int to,Pane root){
        from--;
        Pane pane = new Pane();
        for (int cardID = from; cardID < to; cardID++) {
            Card card = Poker.tableCards.get(cardID);
            for(Table_ t: Table_.values()){
                if(t.getCardId() == (cardID+2)){
                    card.getImageView().setX(150);
                    card.getImageView().setY(-100);
                    move(card,t.getX() * card.getImageView().getImage().getWidth()-150, t.getY()+100);
                    card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
                }
            }
            card.getImageView().setLayoutX(240);
            card.getImageView().setLayoutY(230);
            addToPane(card, root);
        }
        return  pane;
    }

    public void getUserBtn(Pane root){
        for (int i = 0; i < game.getButtons().size(); i++) {
            Button btn = game.getButton(i);
            // Assign EventHandler
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new BtnClickHandler(btn.getText(),this,btn));
            root.getChildren().add(btn);
        }
    }

    public void addPaneToPane(Pane p, Pane root){
        root.getChildren().add(p);
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
        SequentialTransition seqTransition = new SequentialTransition (
                new PauseTransition(Duration.millis(1000)), // wait a second
                tt);

        seqTransition.play();
    }

    public void rotation(Picture p){
        RotateTransition rt = new RotateTransition(Duration.millis(3000), p.getImageView());
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (
                new PauseTransition(Duration.millis(1000)), // wait a second
                rt
        );
        seqTransition.play();
    }

    public void getTurn(){

    }

}
