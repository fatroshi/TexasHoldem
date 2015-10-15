package Poker;

import User.Player;

/**
 * Created by Farhad on 15/10/15.
 */
public class TableLogic {



    public TableLogic(){

    }

    public Player_ makeBet(Player player, double currentBet){

        Player_ status;

        double balance = player.getBalance();
        double bet = currentBet;

        if(balance > bet){
            // Can bet!
            status = Player_.BET;
        }else if(balance > 0){
            status = Player_.ALL_IN;
        }else{
            status = Player_.BROKE;
        }

        return status;
    }


}
