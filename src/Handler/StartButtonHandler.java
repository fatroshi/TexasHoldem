/**
 * Handles the start button, initializes the game.
 * Get following:
 * Game scene
 * slider
 * user information (username, balance, background)
 * user chips
 * deals the cards
 * adds them to the scene
 *
 */

package Handler;

import View.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Farhad Atroshi & Avi
 * Starts the game
 */
public class StartButtonHandler implements EventHandler<Event> {

    private Controller c;

    public StartButtonHandler(Controller c){
        this.c = c;

    }

    @Override
    public void handle(Event evt) {
        loadGameData();

    }

    public void loadGameData(){

        c.getViewStart().getStatusLabel().setText("Helloooo");

        // New Scene BG
        //c.getViewStart().drawTableLabels();
        // Get UI Items
        //c.drawTableGraphics();
        // User info
        //c.getUserInfo();
        // User chips
        //c.getUserChips();
        // Dealer deal 2 cards for each player
        //c.getGame().dealTwoCards();
        // Add to scene
        //c.getFirstTwoCards();
        // Set active user
        //Dealer deal 5 for the table
        //c.getGame().dealCards(5);
        //Start the game
        //c.getGame().gameStart();
        // Get play btn
        //c.getPlayBtn();
        // Get fold btn
        //c.getFoldBtn();
        // Get total pot label
        //c.getPotLabel();
        //c.getGame().updateGame();
    }


}
