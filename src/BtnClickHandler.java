import Layout.ButtonLayout;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Farhad on 14/10/15.
 */
public class BtnClickHandler implements EventHandler<Event> {



    private ButtonLayout bl;
    private Controller c;
    private Button btn;
    public BtnClickHandler(ButtonLayout bl, Controller c, Button btn){
        this.bl = bl;
        this.c = c;
        this.btn = btn;
    }

    @Override
    public void handle(Event evt) {
        switch (this.bl){
            case START:
                // Dealer deals
                c.getGame().dealTwoCards();
                // Add to scene
                c.getFirstTwoCards();
                // Set active user
                //c.getGame().setActiveUser();
                // Remove start btn
                btn.setVisible(false);
                break;
            case CHECK:
                // Set next active user
                c.getGame().check();
                break;
            case BET:
                c.getGame().bet();
                break;
            case RAISE:
                c.getGame().raise();
                break;
            case CALL:
                c.getGame().call();
                break;
            case FOLD:
                c.getGame().fold();
                break;
        }

    }

}
