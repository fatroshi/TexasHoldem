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
    private Table game;
    // Place holder for items
    private Pane root;
    // Round
    int round;

    public Controller() {
        game = new Table();
        root = new Pane();
        round = 0;
    }

    public Pane getRootPane() {
        return this.root;
    }

    public Table getGame() {
        return this.game;
    }

    public void addToPane(Picture o, Pane pane) {
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
    }

    public void createPlayers(Pane root) {
        game.addPlayer("Mr Cohen", 50);
        game.addPlayer("Mr Atroshi", 82);
        //game.addPlayer("Felicia", 13);
        //game.addPlayer("Elise", 12);
    }

    public void getUserInfo() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            // Get user background
            Rectangle r = this.game.getPlayersBg().get(i);
            // Get username
            Label username = this.game.getUsernameLabels().get(i);
            // Get balance
            Label balance = this.game.getBalanceLabels().get(i);
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
            Card card = this.game.getTableCards().get(cardID);

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




    public void getGameScene() {
        GameBackground table = new GameBackground(GameBackground_.TABLE.getImageSrc());
        Animation.fadeIn(table);
        this.root.getChildren().add(table.getImageView());
    }

    public void getPlayBtn() {
        Button btn = game.getPlayBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PlayButtonHandler(this));
        root.getChildren().add(btn);

    }

    public void getFoldBtn() {
        Button btn = game.getFoldBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new FoldButtonHandler(this));
        root.getChildren().add(btn);

    }

    public void getStartBtn() {
        Button btn = game.getStartBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new StartButtonHandler(this));
        root.getChildren().add(btn);
    }

    public void getPotLabel() {
        Label potLabel = game.getPotLabel();
        // Add to scene
        root.getChildren().add(potLabel);
    }

    // This function should be written to 2
    // one for slider, one for labels
    public void getSlider() {
        root.getChildren().add(game.getSliderLabel());
        root.getChildren().add(game.getStatusLabel());
        root.getChildren().add(game.getSlider());
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
                case 4:

                    break;
            }
        }
    }
}
