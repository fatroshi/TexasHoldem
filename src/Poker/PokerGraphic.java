package Poker;

import User.*;
import javafx.scene.control.Slider;

/**
 * Created by Farhad on 15/10/15.
 */
public class PokerGraphic {

    private Slider slider;

    public PokerGraphic(){
        createSlider();
    }

    public void createSlider(){
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);

        this.slider = slider;
    }

    public void setSliderMax(double max){
        this.slider.setMax(max);
    }

    public Slider getSlider(){
        return this.slider;
    }


}
