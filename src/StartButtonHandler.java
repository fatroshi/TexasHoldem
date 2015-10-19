import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Farhad Atroshi on 14/10/15.
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
        // New Scene BG
        c.getGameScene();
        // Get UI Items
        c.getSlider();
        // User info
        c.getUserInfo();
        // User chips
        c.getUserChips();
        // Dealer deal 2 cards for each player
        c.getGame().dealTwoCards();
        // Add to scene
        c.getFirstTwoCards();
        // Set active user
        //Dealer deal 5 for the table
        c.getGame().dealCards(5);
        //Start the game
        c.getGame().gameStart();
        // Get play btn
        c.getPlayBtn();
        // Get fold btn
        c.getFoldBtn();
        c.getGame().updateGame();
    }


}
