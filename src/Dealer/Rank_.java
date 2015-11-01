/**
 * Is used when creating a card and linking the correct image to it.
 * It also sets the value of the card.
 */

package Dealer;

/**
 * Created by Farhad Atroshi
 */
public enum Rank_ {


    ACE(14), KING(13), QUEEN(12),JACK(11),TEN(10),NINE(9),EIGHT(8),SEVEN(7),SIX(6),FIVE(5),FOUR(4),THREE(3),TWO(2);

    private int rank;

    Rank_(int value) {
        this.rank = value;
    }

    public int getRank() {
        return this.rank;
    }

    private String getName() {
        return this.name();
    }
}