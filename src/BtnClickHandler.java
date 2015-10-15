import Layout.ButtonLayout;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Farhad on 14/10/15.
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
                // User info
                c.getUserInfo();
                // User chips
                c.getUserChips();
                // Dealer deals
                c.getGame().dealTwoCards();
                // Add to scene
                c.getFirstTwoCards();
                // Set active user
                //c.getGame().setActiveUser();
                // Remove start btn
                //btn.setVisible(false);
                btn.setVisible(false);
                // Show game buttons
                c.getGame().showGameButtons();
                // Select player
                c.getGame().nextUser();
                break;
            case "CHECK":
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
