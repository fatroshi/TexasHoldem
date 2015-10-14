package Layout;

import javafx.scene.paint.Color;

/**
 * Created by Farhad on 14/10/15.
 */
public enum ButtonLayout {
    // USER BTN
    START(0,200,540,"#444444"),
    CHECK(1,300,540,"#0e731a"),
    BET(2,400,540,"#0e731a"),
    CALL(3,500,540,"#0e731a"),
    RAISE(4,600,540, "#0e731a"),
    FOLD(5,750,540,"#a80317")
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