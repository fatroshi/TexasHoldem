/**
 *  This class is used when saving high score to database.
 */

package highscore;

import highscore.HighScore;
import highscore.HighScoreComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Farhad & avi
 * creates a highscorelist object
 *
 */
public class HighScoreList implements Serializable {
    private ArrayList<HighScore> highscorelist;

    /**
     * Constructor
     * Initializes highscorelist object
     */
    public HighScoreList(){
        highscorelist = new ArrayList<HighScore>();
    }
    //
    /**
     * updates the list by pot values takes only ten objects
     *
     */
    public void updateHighScoreList(HighScore highscore){
        highscorelist.add(highscore);
        Collections.sort(highscorelist, new HighScoreComparator());
        
        if(highscorelist.size()>10){
            highscorelist.remove(10);
        }
           
    }

    /**
     * @return highscorelist
     *
     */

    public ArrayList<HighScore> getHighScoreList(){
        return this.highscorelist;
    }
    
    
    @Override
    public String toString(){
        String info = "HighScoreList";
        for (int i = 0; i<highscorelist.size();i++){
            info = info + " \n " + highscorelist.get(i).toString();
        }

        return info;
    }
}
