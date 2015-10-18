package Poker;

import javafx.scene.control.Label;

import java.awt.*;

/**
 * Created by Farhad on 18/10/15.
 */
public interface Observer {
    public void update(String name);
    public void updateSliderLabel(String sliderLabel);
    public void updateUserLabel(String usernameLabel);
    public void updateStatusLabel(String statusLabel);
}
