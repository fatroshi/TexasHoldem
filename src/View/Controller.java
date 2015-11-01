/**
 * Created by Farhad Atroshi
 * controller for the game
 */
package View;
import Dealer.Card;
import Dealer.Chip;
import Handler.CardClickHandler;
import Poker.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class Controller  extends Observable {
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
    private List<Pane> playerChips;
    // ViewStart
    ViewStart viewStart;
    // Winner label
    Label winnerLabel;
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
        this.playerChips = new ArrayList<>();                       // Holder for player chips
        this.winnerLabel = this.viewStart.getWinnerLabel();         // Display the winners name with this label

        //Add Observer
        addObserver(viewStart);
    }

    public Table getGame() {
        return this.game;
    }

    /**
     * creates players for the game
     */
    public void createPlayers() {
        game.addPlayer("Elise", 2979);
        game.addPlayer("Farhad", 2979);
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
                //this.playerChips.getChildren().add(chip.getImageView());

                // Add to pane tableCards
                Pane cPane = new Pane();
                cPane.getChildren().add(chip.getImageView());

                // Add to player chip to table
                this.playerChips.add(cPane);

                // Add to pane center

            }

        }

        for(Pane chip: playerChips){
            this.paneCenter.getChildren().add(chip);
        }
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

            System.out.println(card.getRank() + " " + card.getSuit());

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
     * Reset game elements
    */
    public void resetGame(){



        // Game rounds
        this.getGame().resetRounds();

        // Controller round
        this.round = 0;

        //Remove user chips
        for(Pane chip: playerChips){
            this.paneCenter.getChildren().remove(chip);
        }
        // clear list
        playerChips.clear();

        // Remove player cards
        this.paneCenter.getChildren().remove(this.playerCards);

        // Remove table Cards
        for (Pane pane: tableCards){
            this.paneCenter.getChildren().remove(pane);
        }
        // clear list
        tableCards.clear();

        // Restart the game
        this.game.gameRestart();

    }


    /**
     * Restart the game
     */
    public void restartGame(){


        // Fadeout winner label
        Animation.fadeOut(this.winnerLabel);
        // Update labels
        viewStart.updateLabelBalances();
        // Restart game
        this.resetGame();
        // Dealer deal 2 cards for each player
        this.getGame().dealTwoCards();
        // Add to scene
        this.showFirstTwoCards();
        // Get user chips
        this.showUserChips();
        //Dealer deal 5 for the table
        this.getGame().dealCards(5);

        //
        this.setChanged();
        this.notifyObservers();
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
                // Restart the game
                this.restartGame();
            }

        }
    }
}
