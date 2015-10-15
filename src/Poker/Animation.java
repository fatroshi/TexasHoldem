package Poker;

import javafx.animation.*;
import javafx.util.Duration;

import java.util.List;

/**
 * Created by Farhad on 15/10/15.
 */
public class Animation {

    public static void move(double x, double y, Picture p){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), p.getImageView());
        tt.setByX(x);
        tt.setByY(y);
        //tt.setCycleCount(4);
        //tt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (
                new PauseTransition(Duration.millis(1000)), // wait a second
                tt);

        seqTransition.play();
    }

    public static void fadeOut(Picture p){
        FadeTransition ft = new FadeTransition(Duration.millis(500), p.getImageView());
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }
}
