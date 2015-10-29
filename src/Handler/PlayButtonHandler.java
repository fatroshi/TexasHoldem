/**
 * Handles when the player clicks the play button, calls methods in the class Table and Controller.
 */
package Handler;

import View.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javax.swing.text.View;

/**
 * Created by Farhad & Avi
 * handles the play button by checking if player can play an which round it is
 */
public class PlayButtonHandler implements EventHandler<Event> {

    private ViewStart viewStart;
    private Label statusLabel;
    public PlayButtonHandler(ViewStart viewStart){
        this.viewStart = viewStart;
        this.statusLabel = statusLabel;
    }

    @Override
    public void handle(Event evt) {
        //this.statusLabel.setText(this.controller.getGame().getMsg());
        //controller.getGame().canPlay();
        //controller.getRound();
        viewStart.getController().getGame().canPlay();
        viewStart.getController().getRound();
        System.out.println("#### CLICKED PLAY BTN " + " MSG: " + viewStart.getController().getGame().getMsg());
    }
}
