/**
 * Handles if the player has fold, restarts the game.
 */
package Handler;


import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad & Avi
 * handles when a player folds the the player looses the game and restarts
 */
public class FoldButtonHandler implements EventHandler<Event> {
    private Controller controller;

    public FoldButtonHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(Event evt) {

        controller.getGame().fold();

        // Restart game
        controller.restartGame();
        // Dealer deal 2 cards for each player
        //controller.getGame().dealTwoCards();
        // Add to scene
        //controller.showFirstTwoCards();
        // Set active user
        //Dealer deal 5 for the table
        //controller.getGame().dealCards(5);

    }
}
