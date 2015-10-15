package User;

/**
 * Created by Farhad Atroshi on 07/10/15.
 */
public class Player extends User {
    public Player(String username, double balance) {
        super(username, balance);
        balanceToChips();

    }

}
