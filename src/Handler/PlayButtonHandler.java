/**
 * Handles when the player clicks the play button, calls methods in the class Table and Controller.
 */
package Handler;

import View.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


/**
 * Created by Farhad
 * handles the play button by checking if player can play an which round it is
 */
public class PlayButtonHandler implements EventHandler<Event> {

    private ViewStart viewStart;

    public PlayButtonHandler(ViewStart viewStart){
        this.viewStart = viewStart;

    }

    @Override
    public void handle(Event evt) {



        viewStart.getController().getGame().canPlay();
        //Update labels
        viewStart.getStatusLabel().setText(viewStart.getGame().getMsg());
        viewStart.getPotLabel().setText("$ " + String.valueOf(viewStart.getGame().getPot()));

        //Update slider
        double min = viewStart.getGame().getSliderMinValue();
        double max = viewStart.getGame().getActivePlayer().getBalance();
        viewStart.getSlider().setMin(min);
        viewStart.getSlider().setMax(max);
        viewStart.getSlider().setValue(min);
        viewStart.getSliderLabel().setText("$ " + String.valueOf(viewStart.getSlider().getValue()));

        //Update label balance
        viewStart.updateLabelBalances();

        viewStart.getController().getRound();

        // Update user BG
        viewStart.updateBgUser();
    }
}
