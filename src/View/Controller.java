/**
 * ### FARHAD READ THIS ####
 *
 * Table should not have any graphic attached to it.
 *
 * Graphic should get values from table and be more like a viewModel
 * The graphic should be created by viewStart.
 * The buttons click events should be set also in viewStart.
 * When a button is clicked the it should call a function in the controller class.
 *
 * ### OBS!!!! Before doing all of this.. first read on Java util Subject and observable !!!!!
 * ### Don't do shit before u know this and how to implement it..
 */

package View;
import Dealer.Card;
import Dealer.Chip;
import Handler.CardClickHandler;
import Poker.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
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
    // Holder for player chips
    private Pane playerChips;
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
        this.game = viewStart.getGame();                            // init. the game
        this.createPlayers();                                       // Create players, get user info, get chips for each player
        this.round = 0;                                             // Counter for poker rounds
        this.tableCards =  new ArrayList<>();                       // Holder for table cards
        this.playerCards = new Pane();                              // Holder for player cards
        this.playerChips = new Pane();
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
     * gets the users chips to scene
     */
    public void showUserChips() {
        for (int i = 0; i < this.game.getPlayers().size(); i++) {
            for (int j = 0; j < this.game.getPlayerChips(i).size(); j++) {
                Chip chip = this.game.getPlayerChips(i).get(j);
                // Add to scene
                this.playerChips.getChildren().add(chip.getImageView());
            }
        }
        this.paneCenter.getChildren().add(playerChips);
    }

    /**
     * adds two cards per player for the first round
     */
    public void showFirstTwoCards() {
        for (int i = 0; i < this.game.getPlayerCards().size(); i++) {
            Card card = this.game.getPlayerCards().get(i);
            // Set event handler when card clicked
            card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
            // Att to player cards pan
            this.playerCards.getChildren().add(card.getImageView());
        }
        this.paneCenter.getChildren().add(playerCards);
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
            this.paneCenter.getChildren().add(tableCards.get(cardID));
        }
    }



    /**
     * restarts the game
    */
    public void restartGame(){
        // Game rounds
        this.getGame().resetRounds();

        // Controller round
        this.round = 0;

        //Remove user chips
        this.paneCenter.getChildren().remove(this.playerChips);

        // Remove player cards
        this.paneCenter.getChildren().remove(this.playerCards);

        // Remove table Cards
        for (Pane pane: tableCards){
            this.paneCenter.getChildren().remove(pane);
        }

        // Restart the game
        this.game.gameRestart();

    }

    public ViewStart getViewStart(){
        return this.viewStart;
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
                this.showFirstTwoCards();
                // Get user chips
                this.showUserChips();
                //Dealer deal 5 for the table
                this.getGame().dealCards(5);
            }

        }
    }
}
