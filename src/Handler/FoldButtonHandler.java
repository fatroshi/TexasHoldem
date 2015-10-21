package Handler;


import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad on 19/10/15.
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
        controller.getGame().dealTwoCards();
        // Add to scene
        controller.getFirstTwoCards();
        // Set active user
        //Dealer deal 5 for the table
        controller.getGame().dealCards(5);

    }
}
