/**
 * Created by Farhad Atroshi on 07/10/15.
 * This class extends User class.
 */

package User;

/**
 * @author Farhad & Avi
 * creates a player with name and balance
 */


public class Player extends User {

    /**
     * constructor
     *@param username
     * @param balance
     */

    public Player(String username, double balance) {
        super(username, balance);
        balanceToChips();

    }

}
