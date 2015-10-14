package Layout;

import javafx.scene.paint.Color;

/**
 * Created by Farhad on 14/10/15.
 */
public enum ButtonLayout {
    // USER BTN
    START(0,250,540,"#444444"),
    CHECK(1,350,540,"#0e731a"),
    RAISE(2,450,540, "#0e731a"),
    CALL(3,550,540,"#0e731a"),
    FOLD(4,650,540,"#0e731a")
    ;


    private double x,y;
    private int id;
    private String color;

    ButtonLayout(int id,double x, double y,String color) {
        this.id = id;
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

    public int getId(){
        return this.id;
    }
}