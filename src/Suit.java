/**
 * Created by Farhad on 29/09/15.
 */
public enum Suit {
    // 1 = spades, 2 = hearts, 3 = diamonds, 4 = clubs
    SPADES(1), CLUBS(4), HEARTS(2), DIAMONDS(3);


    private int suit;
    Suit(int value) {
        this.suit = value;
    }

    public int getSuit() {
        return this.suit;
    }


}