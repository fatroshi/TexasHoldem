/**
 * Handles if the card does not exist
 */

package Dealer;

/**
 * Created by Farhad Atroshi
 *
 */
public class NoSuchCardException extends RuntimeException {

    private String msg;
/**
 * @param msg the message that will show 
 *
 */
    public NoSuchCardException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}

