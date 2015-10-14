package Layout;

import javafx.scene.paint.Color;

/**
 * Created by Farhad on 14/10/15.
 */
public enum ButtonLayout {
    // USER BTN
    CHECK(300,560,"#0e731a"),
    RAISE(400,560, "#0e731a"),
    CALL(500,560,"#0e731a"),
    FOLD(600,560,"#0e731a")
    ;


    private double x,y;
    private String color;

    ButtonLayout(double x, double y,String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getColor(){
        return this.color;
    }
}