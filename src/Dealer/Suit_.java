/**
 * This enum class is used for creating a card and setting suit property.
 */
package Dealer;

/**
 * Created by Farhad Atroshi on 29/09/15.
 */
public enum Suit_ {
    // 1 = spades, 2 = hearts, 3 = diamonds, 4 = clubs
    SPADES(1), CLUBS(4), HEARTS(2), DIAMONDS(3);


    private int suit;

    Suit_(int value) {
        this.suit = value;
    }

    public int getSuit() {
        return this.suit;
    }


}