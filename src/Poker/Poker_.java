/**
 * This enum class is used for checking the best hand.
 * For bette explanation please read the comments in the class.
 */

package Poker;

/**
 * Created by Farhad Atroshi on 10/10/15.
 */
public enum Poker_ {

    HIGH_CARD(1, 1, 1),                 // (1,1,1) = (HighCard,card,Poker rank = 1)
    PAIR(2, 1, 2),                      // (2,1,2) = (Pair, card, Poker rank = 2)
    TWO_PAIR(2, 2, 3),                  // (2,2,3) = (Pair,Pair, Poker rank = 3)
    THREE_OF_A_KIND(3, 1, 4),           // (3,1,4) = (Tree of a kind,card, Poker rank = 4)
    STRAIGHT(5, 1, 5),                  // Straight, poker rank = 5
    FLUSH(5, 1, 6),                     // Flush, poker rank = 6
    FULL_HOUSE(3, 2, 7),                // (3,2,6) = (Tree of a kind, pair, Poker rank = 6)
    FOUR_OF_A_KIND(4, 1, 8),            // (4,1,8) = (Four of a kind, poker rank = 7)
    STRAIGHT_FLUSH(5, 1, 9),            // Straight flush, poker rank = 9
    ROYAL_FLUSH(5, 1, 10);              // Royal flush, Poker rank = 10

    private int rank;
    private int firstQuantity;
    private int secondQuantity;

    Poker_(int rank) {

    }

    Poker_(int firstQuantity, int secondQuantity, int rank) {

        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    private String getName() {
        return this.name();
    }

    public int getFirstQuantity() {
        return this.firstQuantity;
    }

    public int getSecondQuantity() {
        return this.secondQuantity;
    }
}
