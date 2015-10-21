/**
 * Handles if the card does not exist
 */

package Dealer;

/**
 * Created by Farhad Atroshi on 30/09/15.
 */
public class NoSuchCardException extends RuntimeException {

    private String msg;

    public NoSuchCardException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}

