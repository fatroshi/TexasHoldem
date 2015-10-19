package Poker;

import javafx.scene.control.Label;

import java.awt.*;

/**
 * Created by Farhad on 18/10/15.
 */
public interface Observer {
    public void updateSlider(double currentValue, double max, String messages);
    public void decreaseUserBalance(int activeUser, double userBalance);
    public void updateTablePotLabel(double pot);
}
