import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Farhad on 07/10/15.
 */
abstract public class User {
    String username;
    double balance;
    List<Chip> chips;
    Hand hand;

    public User(){

    }

    public User(String username, double balance){
        chips = new ArrayList<>();
        hand = new Hand();

        this.username = username;
        this.balance = balance;
    }

    public Hand getHand(){
        return this.hand;
    }
    public List<Chip> getChips(){
        return this.chips;
    }

    public void buyChips(){
       // Write logic for adding to existing list!
    }

    public String getUsername(){
        return this.username;
    }

    // Should throw an Exception if the input is a float value --> Handle this by AlertWindow ?
    // This function should be user by users, and table!
    public Map<String,Integer> depositConvertToChips(int deposit){

        // Here we should add on the current saldo --> balance + deposit
        // so that we can create correct amount of chips and later on show on table,
        // for the users and total pot on table

        Map<String,Integer> chips = new HashMap<>();
        while (deposit !=0){
            for (Chip_ chip: Chip_.values()){
                //System.out.println("Deposit: " + deposit);
                //System.out.println("Chip value: " + chip.getValue());

                // Get highest chip value possible from saldo
                int count = deposit / chip.getValue();

                //System.out.println("Count:" + count);
                // Remove from deposit
                if(count > 0) {
                    chips.put(chip.name(),count);
                    deposit -= count * chip.getValue();
                }
            }
        }

        return  chips;
    }

    // This function should be used by users, and table!

    /**
     * Map object describes Chip color and the quantity of that chip
     * The chips are created and added to List<Chip> object
     * @param map
     */
    public void createChips(Map<String,Integer> map){

        // Here we should clear all objects in chips
        // And then create new ones
        // We do this so that the table wont get to crowded with chips

        // Output deposit converted to chips
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(name + " : " + value);

            for(Chip_ chip: Chip_.values()){
                // Compare map key(name) with the enum Chip_ name
                if(chip.name() == name){
                    // Get amount for this chip that needs to be created
                    // value = amount of chips of this specific type
                    for (int i = 0; i < value; i++) {
                        // Create chip
                        chips.add(new Chip(chip.getValue(),chip.name(),chip.getImgSrc()));
                    }
                }
            }
        }
    }

    public void clearChips(){
        this.chips.clear();
    }

    public double getSaldo(){
        return this.balance;
    }

    public void setSaldo(double balance){
        this.balance = balance;
    }
}
