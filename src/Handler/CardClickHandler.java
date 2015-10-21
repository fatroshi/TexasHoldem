/**
 * Handles when a card is clicked, toggles the image (front/back)
 */
package Handler;

import Dealer.Card;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by Farhad Atroshi on 14/10/15.
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