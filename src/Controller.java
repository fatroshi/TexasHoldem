import Dealer.Card;
import Dealer.Chip;
import Poker.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by Farhad Atroshi on 14/10/15.
 */
public class Controller {
    // Create game
    private Poker game;
    // Place holder for items
    private Pane root;
    // Round
    int round;

    public Controller() {
        game = new Poker();
        root = new Pane();
        round = 0;
    }

    public Pane getRootPane() {
        return this.root;
    }

    public Poker getGame() {
        return this.game;
    }

    public void addToPane(Picture o, Pane pane) {
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
    }

    public void createPlayers(Pane root) {
        game.addPlayer("Lawen", 1000);
        game.addPlayer("Farhad", 2130);
        game.addPlayer("Felicia", 4213);
        game.addPlayer("Elise", 4219);
    }

    public void getUserInfo() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            // Get user background
            Rectangle r = this.game.getPokerGraphic().getPlayerBG(i);
            // Get username
            Label username = this.game.getPokerGraphic().getUsernameLabel(i);
            // Get balance
            Label balance = this.game.getPokerGraphic().getBalanceLabel(i);
            // Add to root scene
            root.getChildren().addAll(r, username, balance);
        }
    }

    public void getUserChips() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            for (int j = 0; j < this.game.getPlayerChips(i).size(); j++) {
                Chip chip = this.game.getPlayerChips(i).get(j);
                // Add to scene
                this.root.getChildren().add(chip.getImageView());
            }
        }
    }

    public void getFirstTwoCards() {
        for (int i = 0; i < this.game.getPlayerCards().size(); i++) {
            Card card = this.game.getPlayerCards().get(i);
            // Set event handler when card clicked
            card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
            // Add to scene
            this.root.getChildren().add(card.getImageView());
        }
    }

    public void getTableCards(int from, int to) {

        for (int cardID = from; cardID < to; cardID++) {
            Card card = Poker.tableCards.get(cardID);
            for (Table_ t : Table_.values()) {
                if (t.getCardId() == (cardID + 2)) {
                    card.getImageView().setX(150);
                    card.getImageView().setY(-100);
                    Animation.move(card, t.getX() * card.getImageView().getImage().getWidth() - 150, t.getY() + 100);
                    card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
                }
            }
            card.getImageView().setLayoutX(240);
            card.getImageView().setLayoutY(230);

            this.root.getChildren().add(card.getImageView());
        }

    }

    public void getUserBtn() {
        for (int i = 1; i < game.getPokerGraphic().getButtons().size(); i++) {
            Button btn = game.getPokerGraphic().getButton(i);
            // Assign EventHandler
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new BtnClickHandler(btn.getText(), this, btn));
            root.getChildren().add(btn);
        }
    }

    public void addPaneToPane(Pane p, Pane root) {
        root.getChildren().add(p);
    }

    public void getGameScene() {
        GameBackground table = new GameBackground(GameBackground_.TABLE.getImageSrc());
        Animation.fadeIn(table);
        this.root.getChildren().add(table.getImageView());
    }

    public void getTurn() {

    }

    public void getStartBtn() {
        Button btn = this.game.getPokerGraphic().getButtons().get(0);
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new BtnClickHandler(btn.getText(), this, btn));
        this.root.getChildren().add(btn);
    }

    public void getSlider() {
        root.getChildren().add(game.getPokerGraphic().getSliderLabel());
        root.getChildren().add(game.getPokerGraphic().getStatusLabel());
        root.getChildren().add(game.getPokerGraphic().getSlider());
    }

    public void getRound(){
        int round = this.game.round();

        if(round > this.round) {
            this.round = round;

            switch (this.round) {
                case 1:
                    this.getTableCards(0, 3);
                    break;
                case 2:
                    this.getTableCards(3, 4);
                    break;
                case 3:
                    this.getTableCards(4, 5);
                    break;
            }
        }
    }


}
