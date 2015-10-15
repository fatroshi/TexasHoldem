package Poker;

import Layout.ButtonLayout;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by Farhad on 15/10/15.
 */
public class PlayerButtonsHandler implements EventHandler<Event> {
    private ButtonLayout bl;
    private Poker poker;
    private Button btn;
    public PlayerButtonsHandler(ButtonLayout bl, Poker poker, Button btn){
        this.bl = bl;
        this.poker = poker;
        this.btn = btn;
    }

    @Override
    public void handle(Event evt) {
        switch (this.bl){
            case START:
                // Dealer deals
                poker.dealTwoCards();
                // Add to scene
                poker.getPlayerCards();
                // Remove start btn
                btn.setVisible(false);
                // Get player
                poker.nextUser();
                break;
            case CHECK:
                // Set next active user

                poker.check();
                break;
            case BET:
                poker.bet();
                break;
            case RAISE:
                poker.raise();
                break;
            case CALL:
                poker.call();
                break;
            case FOLD:
                poker.fold();
                break;
        }

    }
}
