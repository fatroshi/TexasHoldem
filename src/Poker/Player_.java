package Poker;

/**
 * Created by Farhad Atroshi on 15/10/15.
 */
public enum Player_ {

    ALL_IN(0),
    BET(1),
    CALL(2),
    RAISE(3),
    FOLD(4),
    BROKE(5);


    private int status;

    Player_(int status) {
        this.status = status;
    }

    private int getValue() {
        return this.status;
    }

}
