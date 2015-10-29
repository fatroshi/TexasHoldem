/**
 * Created by Farhad Atroshi on 15/10/15.
 *
 * This class is used for the animations of the cards.
 */

package Poker;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * @author Farhad & Avi
 * creates animation for the cards and chips
 */



public class Animation {

    /**
     * create effect by fade-out the picture
     *@param p
     */
    public static void fadeOut(Picture p) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), p.getImageView());
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    /**
     * create effect by fade-in the picture
     *@param p
     */
    public static void fadeIn(Picture p) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), p.getImageView());
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    public static void fadeIn(Label l) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), l);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }


    public static void fadeOut(Label l) {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), l);
        ft.setFromValue(1.0);
        ft.setToValue(0.001);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    /**
     * create effect by rotating the picture
     *@param p
     */
    public static void rotation(Picture p) {
        RotateTransition rt = new RotateTransition(Duration.millis(3000), p.getImageView());
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition(
                new PauseTransition(Duration.millis(1000)), // wait a second
                rt
        );
        seqTransition.play();
    }

    /**
     * moves the picture to given coordinates
     *@param x
     * @param y
     * @param p
     */
    public static void move(Picture p, double x, double y) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), p.getImageView());
        tt.setByX(x);
        tt.setByY(y);
        //tt.setCycleCount(4);
        //tt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition(
                new PauseTransition(Duration.millis(1000)), // wait a second
                tt);

        seqTransition.play();
    }
}
