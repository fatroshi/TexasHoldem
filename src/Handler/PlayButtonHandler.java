/**
 * Handles when the player clicks the play button, calls methods in the class Table and Controller.
 */
package Handler;

import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 * Created by Farhad & Avi
 * handles the play button by checking if player can play an which round it is
 */
public class PlayButtonHandler implements EventHandler<Event> {

    private Controller controller;
    private Label statusLabel;
    public PlayButtonHandler(Controller controller, Label statusLabel){
        this.controller = controller;
        this.statusLabel = statusLabel;
    }

    @Override
    public void handle(Event evt) {
        this.statusLabel.setText(this.controller.getGame().getMsg());
        controller.getGame().canPlay();
        controller.getRound();

        System.out.println("#### CLICKED PLAY BTN " + " MSG: " + this.controller.getGame().getMsg());
    }
}
