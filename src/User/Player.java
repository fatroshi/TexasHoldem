

package User;

/**
 * @author Farhad
 *  This class extends User class.
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
