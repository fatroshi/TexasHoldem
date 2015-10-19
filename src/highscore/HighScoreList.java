package highscore;

import highscore.HighScore;
import highscore.HighScoreComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author avi
 */
public class HighScoreList implements Serializable {
    private ArrayList<HighScore> highscorelist;
    
    public HighScoreList(){
        highscorelist = new ArrayList<HighScore>();
    }
    
    public void updateHighScoreList(HighScore highscore){
        highscorelist.add(highscore);
        Collections.sort(highscorelist, new HighScoreComparator());
        
        if(highscorelist.size()>10){
            highscorelist.remove(10);
        }
           
    }
    
    public ArrayList<HighScore> getHighScoreList(){
        return this.highscorelist;
    }
    
    
    @Override
    public String toString(){
        return highscorelist.toString();
    }
}
