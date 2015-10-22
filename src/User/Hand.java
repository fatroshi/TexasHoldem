
/**
 * Created by Farhad Atroshi on 29/09/15.
 * This class is used for holding the player cards.
 * Adding cards and removing cards from the hand.
 */

package User;

import Dealer.Card;
import Dealer.NoSuchCardException;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Farhad & Avi
 * creates list of cards in the hand
 */

public class Hand {
    List<Card> hand;

    /**
     * constructor
     *initiates the hand object
     */

    public Hand() {

        hand = new ArrayList<>();
    }

    /**
     * @return number of card in the hand
     */
    public int getNoOfCards() {
        return hand.size();
    }

    /**
     * adds card to a hand
     *@param card
     */

    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * returns card by index in the hand
     *@param index
     * @return Card
     */

    public Card getCard(int index) throws NoSuchCardException {

        if (index < this.hand.size()) {
            return hand.get(index);
        } else {
            throw new NoSuchCardException("Wrong index of card");
        }

    }
    /**
     * clears all the cards in the hands
     */
    public void clearHand(){
        hand.clear();
    }

    /**
     * removes card in the hand by index
     *@param index
     */
    public Card removeCard(int index) {

        return hand.remove(index);
    }

    @Override
    public String toString() {
        return getCard(0).toString();
    }
}
