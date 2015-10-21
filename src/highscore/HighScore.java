/**
 * This class is used for storing the game high score.
 */
package highscore;

import java.io.Serializable;

public class HighScore implements Serializable {
    private String username;
    //private String hand;
    private int pot;
    
    public HighScore (String username,double pot){
        this.username = username;
     //  this.hand = hand;
        this.pot = (int)pot;
    }
    
    public String getUserName(){
        return this.username;
    }
    
    //public String getHand(){
    //    return this.hand;
        
   // }
    
    public int getPot(){
        return this.pot;
    }
    
   
    
    @Override
    public String toString(){
        String info = (" " + this.username + " " + this.pot);
        return info;
    }
}
