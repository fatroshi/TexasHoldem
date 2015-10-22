/**
 * Handles when the player clicks the play button, calls methods in the class Table and Controller.
 */
package Handler;

import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad & Avi
 * handles the play button by checking if player can play an which round it is
 */
public class PlayButtonHandler implements EventHandler<Event> {

    private Controller controller;

    public PlayButtonHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public void handle(Event evt) {
        controller.getGame().canPlay();
        controller.getRound();
    }
}
