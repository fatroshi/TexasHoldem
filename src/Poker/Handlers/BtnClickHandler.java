package Poker.Handlers;

import Layout.ButtonLayout;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad on 14/10/15.
 */
public class BtnClickHandler implements EventHandler<Event> {



    private ButtonLayout bl;
    private Controller o;
    public BtnClickHandler(ButtonLayout bl, Object o){
        this.o = o;
        this.bl = bl;
    }

    @Override
    public void handle(Event evt) {
        switch (this.bl){
            case START:
                System.out.println("Start function");
                o.
                break;
            case CHECK:
                System.out.println("Check function");
                break;
            case RAISE:
                System.out.println("Raise function");
                break;
            case FOLD:
                System.out.println("Fold function");
                break;
        }

    }

}
