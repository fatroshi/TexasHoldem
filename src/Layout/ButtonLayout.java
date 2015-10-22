package Layout;

/**
 * Created by Farhad Atroshi & Avi
 *
 * This enum class is used for position and colorization of the buttons
 *
 */
public enum ButtonLayout {
    // USER BTN
    START(0,190,400,"#0e731a"),
    PLAY(1,240,570,"#0e731a"),
    //BET(2,340,570,"#0e731a"),
    //CALL(3,440,570,"#0e731a"),
    //RAISE(4,540,570, "#0e731a"),
    FOLD(5,675,570,"#a80317")
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