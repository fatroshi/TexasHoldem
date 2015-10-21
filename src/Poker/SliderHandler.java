/**
 * Created by Farhad on 17/10/15.
 * This class is used for creating the game slider.
 */

package Poker;

import javafx.scene.control.*;


public class SliderHandler {


    public SliderHandler(Slider slider, Label sliderLabel){
        double value = round(slider.getValue(), 0);
        String strValue = String.valueOf(value);
        // Slider label
        sliderLabel.setText("$ " + strValue);

    }

    /**
     * Found at: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
