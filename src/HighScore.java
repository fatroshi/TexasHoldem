import java.io.Serializable;

/**
 * Created by Farhad Atroshi on 07/10/15.
 * 
 */
public class HighScore implements Serializable {
    private String username;
    private String hand;
    private int pot;
    
    public HighScore(String username,String hand,double pot){
        this.username = username;
        this.hand = hand;
        this.pot = (int)pot;
    }
    
    public String getUserName(){
        return this.username;
    }
    
    public String getHand(){
        return this.hand;
        
    }
    
    public int getPot(){
        return this.pot;
    }
    
   
    
    @Override
    public String toString(){
        String info = (" " + this.username + " " + this.hand + " " + this.pot);
        return info;
    }
}
