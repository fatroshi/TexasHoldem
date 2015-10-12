package Dealer;

/**
 * Created by Farhad on 29/09/15.
 */
public enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13),ACE(14);

    private int rank;

    Rank(int value) {
        this.rank = value;
    }

    Rank(int value, int v2) {
        this.rank = value;
    }

    public int getRank() {
        return this.rank;
    }

    private String getName(){
        return this.name();
    }
}