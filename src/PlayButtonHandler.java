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
        System.out.println("i just got cliiiicked maaaan");

    }
}
