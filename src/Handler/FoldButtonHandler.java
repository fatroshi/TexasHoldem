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

        controller.getRound();

    }
}
