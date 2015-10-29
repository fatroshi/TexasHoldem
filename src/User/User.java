/**
 * Created by Farhad Atroshi & Avi
 * This class is used for creating players.
 */

package User;

import Dealer.Chip;
import Dealer.Chip_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


abstract public class User {
    private String username;
    private double balance;
    private List<Chip> chips;
    private Hand hand;
    private boolean active;

    private double totalBet;
    private double bet;

    /**
     * constructor
     */
    public User() {

    }

    /**
     * constructor initiates user with name and balance
     *@param username
     * @param balance
     */
    public User(String username, double balance) {
        chips = new ArrayList<>();
        hand = new Hand();

        this.username = username;
        this.balance = balance;
        this.active = true;
    }

    /**
     *
     * @return totalBet
     */

    public double getTotalBet() {
        return totalBet;
    }

    /**
     * sets totalbet
     *@param totalBet
     */

    public void setTotalBet(double totalBet) {
        this.totalBet = totalBet;
    }

    /**
     * @return bet made by user
     */

    public double getBet() {
        return bet;
    }

    /**
     * sets the bet
     *@param bet
     */
    public void setBet(double bet) {
        this.bet = bet;
        this.totalBet += this.bet;
    }

    /**
     * checks if the user is active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * sets the user active or inactive
     */

    public void setActive(boolean b) {
        this.active = b;
    }

    /**
     * returns the hand of the user
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * returns the chips of the user
     */
    public List<Chip> getChips() {
        return this.chips;
    }

    /**
     * returns the chip in chipcollection by index
     * @param index
     */
    public Chip getChip(int index) {
        return this.chips.get(index);
    }

    /**
     * converts the balance to chips
     */
    public List<Chip> balanceToChips() {
        // Convert balance to int
        int balance = (int) Math.round(this.balance);
        // Convert int version of balance to chips
        Map<String, Integer> converted = this.depositConvertToChips(balance);

        // Clear chips first
        this.chips.clear();
        // Create chips --> will add to this.chips
        createChips(converted);
        //
        return this.chips;
    }

    public void buyChips() {
        // Write logic for adding to existing list!
    }

    /**
     * @return username
     */

    public String getUsername() {
        return this.username;
    }

    // Should throw an Exception if the input is a float value --> Handle this by AlertWindow ?
    // This function should be user by users, and table!
    public Map<String, Integer> depositConvertToChips(int deposit) {

        // Here we should add on the current saldo --> balance + deposit
        // so that we can create correct amount of chips and later on show on table,
        // for the users and total pot on table

        Map<String, Integer> chips = new HashMap<>();
        while (deposit != 0) {
            for (Chip_ chip : Chip_.values()) {
                //System.out.println("Deposit: " + deposit);
                //System.out.println("Dealer.Chip value: " + chip.getValue());

                // Get highest chip value possible from saldo
                int count = deposit / chip.getValue();

                //System.out.println("Count:" + count);
                // Remove from deposit
                if (count > 0) {
                    chips.put(chip.name(), count);
                    deposit -= count * chip.getValue();
                }
            }
        }

        return chips;
    }

    // This function should be used by users, and table!

    /**
     * Map object describes Chip color and the quantity of that chip
     * The chips are created and added to List<Dealer.Chip> object
     *
     * @param map
     */
    public void createChips(Map<String, Integer> map) {
        // Output deposit converted to chips
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            Integer value = entry.getValue();
            //System.out.println(name + " : " + value);

            for (Chip_ chip : Chip_.values()) {
                // Compare map key(name) with the enum Dealer.Chip_ name
                if (chip.name() == name) {
                    // Get amount for this chip that needs to be created
                    // value = amount of chips of this specific type
                    for (int i = 0; i < value; i++) {
                        // Create chip
                        chips.add(new Chip(chip.getValue(), chip.name(), chip.getImgSrc()));
                    }
                }
            }
        }
    }

    /**
     * clears the chipcollection
     */

    public void clearChips() {
        this.chips.clear();
    }

    /**
     * returns the balnce of the user
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * adds deposit to users balance
     *@param deposit
     */
    public void depositBalance(double deposit) {
        this.balance += deposit;
    }

    /**
     * debits money from the user
     *@param debit
     */
    public void debitBalance(double debit) {
        this.balance -= debit;
    }

    /**
     * setsa the users balance
     *@param balance
     */

    public void setSaldo(double balance) {
        this.balance = balance;
    }


}
