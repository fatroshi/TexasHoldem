package highscore;

import highscore.HighScore;

import java.util.Comparator;

/**
 *
 * @author avi
 */
public class HighScoreComparator implements Comparator<HighScore>{

@Override
public int compare(HighScore hs1, HighScore hs2) {
    if (hs1.getPot() > hs2.getPot()) {
        return -1;
    } else if (hs1.getPot() < hs2.getPot()) {
        return 1;
    }
    return 0;
}

}
    

