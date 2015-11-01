/**
 * Handles when a card is clicked, toggles the image (front/back)
 */
package Handler;

import Dealer.Card;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad Atroshi
 * turns the card when you click it
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