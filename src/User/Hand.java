package User;

import Dealer.Card;
import Dealer.NoSuchCardException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad Atroshi on 29/09/15.
 */
public class Hand {
    List<Card> hand;
    List<Card> tableCards;

    public Hand() {

        hand = new ArrayList<>();
    }

    public int getNoOfCards() {
        return hand.size();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getCard(int index) throws NoSuchCardException {

        if (index < this.hand.size()) {
            return hand.get(index);
        } else {
            throw new NoSuchCardException("Wrong index of card");
        }

    }

    public void clearHand(){
        hand.clear();
    }

    public Card removeCard(int index) {

        return hand.remove(index);
    }

    @Override
    public String toString() {
        return getCard(0).toString();
    }
}
