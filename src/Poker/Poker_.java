package Poker;

/**
 * Created by Farhad on 10/10/15.
 */
public enum Poker_ {

    /*
        Map(key,value)
        If the list is sorted by value -->
        if the second element in map is 1 then we know the rank (enum name)!

        ex. Cards in hand = { 2, 2, 1 , 10, 5, 4 }  --> call method bestHand(User.Hand hand)
        Will return
        2: 2
        1: 1 --> Second element is 1, then we know that we have "PAIR"
        4: 1
        .
        .
        .
     */

    // Third element is rank for the poker game, lowest = 1
    HIGH_CARD(1,1,1),
    PAIR(2,1,2),
    TWO_PAIR(2,2,3),
    THREE_OF_A_KIND(3,1,4),
    STRAIGHT(5,1,5),
    FLUSH(5,1,6),
    FULL_HOUSE(3,2,7),
    FOUR_OF_A_KIND(4,1,8),
    STRAIGHT_FLUSH(5,1,9),
    ROYAL_FLUSH(5,1,10);

    private int rank;
    private int firstQuantity;
    private int secondQuantity;

    Poker_(int rank){

    }

    Poker_(int firstQuantity, int secondQuantity, int rank) {

        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    private String getName(){
        return this.name();
    }

    public int getFirstQuantity(){
        return this.firstQuantity;
    }

    public int getSecondQuantity(){
        return this.secondQuantity;
    }
}
