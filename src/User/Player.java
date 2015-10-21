/**
 * Created by Farhad Atroshi on 07/10/15.
 * This class extends User class.
 */

package User;


public class Player extends User {
    public Player(String username, double balance) {
        super(username, balance);
        balanceToChips();

    }

}
