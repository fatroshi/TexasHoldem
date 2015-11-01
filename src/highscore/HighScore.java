/**
 * This class is used for storing the game high score.
 */
package highscore;

import java.io.Serializable;

/**
 * Created by Farhad Atroshi
 * creates highscore objekt
 * @param username
 * @param pot
 */

public class HighScore implements Serializable {
    private String username;
    //private String hand;
    private int pot;

    /**
     * Constructor
     * Initializes highscore object
     */
    
    public HighScore (String username,double pot){
        this.username = username;
     //  this.hand = hand;
        this.pot = (int)pot;
    }

    /**
     * @return username
     */
    
    public String getUserName(){
        return this.username;
    }
    
    //public String getHand(){
    //    return this.hand;
        
   // }
    /**
     * @return pot
     */
    public int getPot(){
        return this.pot;
    }
    
   
    
    @Override
    public String toString(){
        String info = (" " + this.username + " " + this.pot);
        return info;
    }
}
