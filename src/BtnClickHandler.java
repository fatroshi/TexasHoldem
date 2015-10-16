import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Farhad Atroshi on 14/10/15.
 */
public class BtnClickHandler implements EventHandler<Event> {



    private String text;
    private Controller c;
    private Button btn;
    public BtnClickHandler(String text, Controller c, Button btn){
        this.text = text;
        this.c = c;
        this.btn = btn;
    }

    @Override
    public void handle(Event evt) {
        switch (this.text){
            case "START":
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
                // Remove start btn
                c.getUserBtn();
                //btn.setVisible(false);
                btn.setVisible(false);
                // Show game buttons
                c.getGame().getPokerGraphic().showGameButtons();
                // Select player
                c.getGame().nextUser();
                break;
            case "CHECK":
                //
                //c.getGame().round(); controller should check round!
                c.getRound();
                // Set next active user
                c.getGame().check();
                break;
            case "BET":
                c.getGame().bet();
                break;
            case "RAISE":
                c.getGame().raise();
                break;
            case "CALL":
                c.getGame().call();
                break;
            case "FOLD":
                c.getGame().fold();
                break;
        }

    }

}
