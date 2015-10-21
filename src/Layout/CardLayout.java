package Layout;

/**
 * Created by Farhad Atroshi on 13/10/15.
 *
 * This eum class is used for setting the table card positions.
 *
 */
public enum CardLayout {

    // CARDS, CardId,x,y,rotation
    FIRST(0,0,0,0),
    SECOND(1,10,0,10),
    THIRD(2,1,0,0),
    FOURTH(3,2,0,0),
    FIFTH(4,3,0,0),
    SIXTH(5,4,0,0),
    SEVENTH(6,5,0,0),
    DEAD_CARDS(7,-50,-50,0),
    ;

    private int cardId,rotation;
    double x,y;

    CardLayout(int cardId,double x, double y,int rotation) {
        this.cardId = cardId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int getCardId(){
        return this.cardId;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public int getRotation(){
        return this.rotation;
    }
}
