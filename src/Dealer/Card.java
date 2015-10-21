/**
 * This class creates a card object.
 */


package Dealer;

import Poker.Picture;

/**
 * Objects of this class represents cards in a deck (of cards). A card is
 * immutable, i.e. once created its rank or suit cannot be changed.
 */

public class Card extends Picture {

    // Tables for converting rank & suit to text (why static?)
    private static final String[] rankTab = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };
    // SPADES(1), CLUBS(4), HEARTS(2), DIAMONDS(3);
    private static final String[] suitTab = {
            "spades", "hearts", "diamonds", "clubs"
    };
    private final int rank, suit;

    /**
     * @param rank 1 = Ace, 2 = 2, ...
     * @param suit 1 = spades, 2 = hearts, 3 = diamonds, 4 = clubs
     */

    // hearts (high), diamonds, spades, clubs (low) (in Austria and in Sweden)
    public Card(int rank, int suit, String imgSrc) {
        super(imgSrc);
        this.rank = rank;
        this.suit = suit;
    }

    public Card(int rank, int suit, String imgSrcFront, String imgSrcBack) {
        super(imgSrcFront, imgSrcBack);
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public boolean equals(Card other) {
        return this.rank == other.rank && this.suit == other.suit;
    }

    @Override
    public String toString() {
        String info = rankTab[this.rank - 1] + " of " + suitTab[this.suit - 1];
        return info;
    }


}
