package Poker.Handlers;

import Layout.ButtonLayout;
import javafx.event.Event;
import javafx.event.EventHandler;


/**
 * Created by Farhad on 14/10/15.
 */
public class BtnClickHandler implements EventHandler<Event> {

    private String name;

    public BtnClickHandler(String name){
        this.name = name;
    }

    @Override
    public void handle(Event evt) {
        System.out.println(this.name);
    }

}
