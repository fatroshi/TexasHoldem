
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aviko
 */
public class EvaluateHand {
    
    public EvaluateHand(){
        
    }
    
    public boolean royalFlush(ArrayList<Card> handToEvaluate){ //should get seven card
        boolean answer,card1,card2,card3,card4,card5;
        int cntSpades = 0;
        int cntHearts = 0;
        int cntDiamonds = 0;
        int cntClubs =0;
        

        // counts for the suits 
        for(int i =0; i<handToEvaluate.size() ; i++){
            if (handToEvaluate.get(i).getSuit() == 1){
                cntSpades ++;
            }
            else if (handToEvaluate.get(i).getSuit() == 2){
                cntHearts ++;
            }
            else if (handToEvaluate.get(i).getSuit() == 3){
                cntDiamonds ++;
            }
            else{
                cntClubs ++;
            }
        }
        
        if (cntSpades > 4){
            for (int i =0; i<7;i++){

            }
        }
        
        
        
        return true;
    }

    /*
    public Map<String,Integer> bla (List<Card> cards){

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
    */


    
}
