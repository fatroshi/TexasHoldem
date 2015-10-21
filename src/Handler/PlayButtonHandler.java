/**
 * Handles when the player clicks the play button, calls methods in the class Table and Controller.
 */
package Handler;

import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad on 19/10/15.
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
