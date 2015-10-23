package View;

import Dealer.Card;
import Dealer.Chip;
import Handler.CardClickHandler;
import Handler.FoldButtonHandler;
import Handler.PlayButtonHandler;
import Handler.StartButtonHandler;
import Poker.*;
import View.ViewStart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * Created by Farhad Atroshi & Avi
 * controlls the game
 */
public class Controller {
    // Create game
    private Table game;
    // Root scene
    private BorderPane root;
    // Holder for game background, slider, play btn, fold btn, player username and balance;
    private Pane paneCenter;
    // Holder for table cards
    private List<Pane> tableCards;
    // Holder for table cards
    private Pane playerCards;
    // ViewStart
    ViewStart viewStart;
    // Round
    int round;

    /**
     * constructor
     *@param viewStart
     */
    public Controller(ViewStart viewStart) {
        this.viewStart = viewStart;                                 // Start view
        this.root      = viewStart.getRoot();                       // Root (holder for all Panes)
        this.paneCenter = this.viewStart.getPaneCenter();           // Holder for game play background (image = table)
        this.game = new Table();                                    // init. the game
        this.createPlayers();                                       // Create players, get user info, get chips for each player
        this.round = 0;                                             // Counter for poker rounds
        this.getStartBtn();                                         // Add start btn to the scene
        this.tableCards = viewStart.getTableCards();                // Holder for table cards
        this.playerCards = viewStart.getPlayerCards();              // Holder for player cards
    }

    public BorderPane getRootBorderPane(){
        return this.viewStart.getRoot();
    }

    public Table getGame() {
        return this.game;
    }

    /**
     * creates players for the game
     */
    public void createPlayers() {
        game.addPlayer("Mr Cohen", 2979);
        game.addPlayer("Mr Atroshi", 2979);
        //game.addPlayer("Felicia", 13);
        //game.addPlayer("Elise", 12);
    }

    /**
     * shows user info in a label
     */
    public void getUserInfo() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            // Get user background
            Rectangle r = this.game.getPlayersBg().get(i);
            // Get username
            Label username = this.game.getUsernameLabels().get(i);
            // Get balance
            Label balance = this.game.getBalanceLabels().get(i);
            // Add to paneCenter scene
            paneCenter.getChildren().addAll(r, username, balance);
        }
    }

    /**
     * gets the users chips to scene
     */
    public void getUserChips() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            for (int j = 0; j < this.game.getPlayerChips(i).size(); j++) {
                Chip chip = this.game.getPlayerChips(i).get(j);
                // Add to scene
                this.paneCenter.getChildren().add(chip.getImageView());
            }
        }
    }

    /**
     * adds two cards per player for the first round
     */
    public void getFirstTwoCards() {
        for (int i = 0; i < this.game.getPlayerCards().size(); i++) {
            Card card = this.game.getPlayerCards().get(i);
            // Set event handler when card clicked
            card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
            // Att to player cards pan
            this.playerCards.getChildren().add(card.getImageView());
        }
        this.root.getChildren().add(playerCards);
    }
    /**
     * gets the first three cards on table with animation
     * @param from
     * @param to
     */
    public void getTableCards(int from, int to) {

        for (int cardID = from; cardID < to; cardID++) {
            Card card = this.game.getTableCards().get(cardID);
            int space = 6;
            for (Table_ t : Table_.values()) {
                if (t.getCardId() == (cardID + 2)) {
                    card.getImageView().setX(130 + space * cardID) ;
                    card.getImageView().setY(-100);
                    Animation.move(card, t.getX() * card.getImageView().getImage().getWidth() - 150, t.getY() + 100);
                    card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
                }
            }
            card.getImageView().setLayoutX(240);
            card.getImageView().setLayoutY(230);

            // Add to pane tableCards
            Pane cPane = new Pane();
            cPane.getChildren().add(card.getImageView());

            // Add to tableCards
            this.tableCards.add(cPane);

            // Add to root
            this.root.getChildren().add(tableCards.get(cardID));
        }
    }


    public void getGameScene() {
        GameBackground table = new GameBackground(GameBackground_.TABLE.getImageSrc());
        Animation.fadeIn(table);
        this.paneCenter.getChildren().add(table.getImageView());
    }

    /**
     * adds play button to game
     */
    public void getPlayBtn() {
        Button btn = game.getPlayBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PlayButtonHandler(this));
        paneCenter.getChildren().add(btn);

    }

    /**
     * adds fold button to the game
     */
    public void getFoldBtn() {
        Button btn = game.getFoldBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new FoldButtonHandler(this));
        paneCenter.getChildren().add(btn);

    }

    /**
     * adds start button to the game
     */
    public void getStartBtn() {
        Button btn = game.getStartBtn();
        // Assign EventHandler
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new StartButtonHandler(this));
        paneCenter.getChildren().add(btn);
    }

    /**
     * adds pot label to the game
     */
    public void getPotLabel() {
        Label potLabel = game.getPotLabel();
        // Add to scene
        paneCenter.getChildren().add(potLabel);
    }

    // This function should be written to 2
    // one for slider, one for labels
    public void getSlider() {
        paneCenter.getChildren().add(game.getSliderLabel());
        paneCenter.getChildren().add(game.getStatusLabel());
        paneCenter.getChildren().add(game.getSlider());
    }

    /**
     * restarts the game
    */
    public void restartGame(){
        // Game rounds
        this.getGame().resetRounds();

        // Controller round
        this.round = 0;

        // Remove player cards
        this.root.getChildren().remove(this.playerCards);

        // Remove table Cards
        for (Pane pane: tableCards){
            this.root.getChildren().remove(pane);
        }

        // Restart the game
        this.game.gameRestart();

    }


    /**
     * decides what to do in every round
     */
    public void getRound(){
        int tableRound = this.game.round();

        if(tableRound > this.round) {
            this.round = tableRound;

            if(this.round == 1){
                System.out.println(" --- Controller round " + this.round + " (Should be 1)");
                this.getTableCards(0, 3);
            }else if(this.round == 2){
                System.out.println(" --- Controller round " + this.round + " (Should be 2)");
                this.getTableCards(3, 4);
            }else if(this.round == 3){
                System.out.println(" --- Controller round " + this.round + " (Should be 3)");
                this.getTableCards(4, 5);
            }else if(this.round == 4){
                System.out.println(" --- Controller round " + this.round + " (Should be 4)");

                // Restart game
                this.restartGame();
                // Dealer deal 2 cards for each player
                this.getGame().dealTwoCards();
                // Add to scene
                this.getFirstTwoCards();
                // Set active user
                //Dealer deal 5 for the table
                this.getGame().dealCards(5);
            }

        }
    }
}
