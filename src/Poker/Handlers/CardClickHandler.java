package Poker.Handlers;

import Dealer.Card;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad on 14/10/15.
 */
public class CardClickHandler implements EventHandler<Event> {
    Card card;

    public CardClickHandler(Card card){
        this.card = card;
    }

    @Override
    public void handle(Event evt) {
        this.card.toggleImage();
    }
}