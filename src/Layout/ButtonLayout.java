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
    /**
     * initiates the button layout
     * @param id which botton it is
     * @param x ,y  coordinates of the button
     * @param color color of the button
     */
    ButtonLayout(int id,double x, double y,String color) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    /**
     * @return x value of the coordinate
     */
    public double getX(){
        return this.x;
    }
    /**
     * @return y value of the coordinate
     */
    public double getY(){
        return this.y;
    }
    /**
     * @return color of the button
     */
    public String getColor(){
        return this.color;
    }
    /**
     * @return id of the button
     */
    public int getId(){
        return this.id;
    }
}
