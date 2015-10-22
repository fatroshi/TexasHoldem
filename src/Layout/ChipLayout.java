package Layout;

/**
 * Created by Farhad Atroshi & Avi
 * This enum class is used for creating chips.
 * Setting  correct value, position for each user.
 */
public enum ChipLayout {
    //
    // Player 0
    P0_BLACK    (0,500,300,100),
    P0_RED      (0,100,335,100),
    P0_BLUE     (0,50,355,130),
    P0_GREEN    (0,10,320,130),
    P0_WHITE    (0,1,370,100),
    // Player 1
    P1_BLACK    (1,500,300,100),
    P1_RED      (1,100,335,100),
    P1_BLUE     (1,50,355,135),
    P1_GREEN    (1,10,320,135),
    P1_WHITE    (1,1,370,100),
    // Player 2
    P2_BLACK    (2,500,100,100),
    P2_RED      (2,100,65,100),
    P2_BLUE     (2,50,45,135),
    P2_GREEN    (2,10,80,135),
    P2_WHITE    (2,1,30,100),
    // Player 3
    P3_BLACK    (3,500,100,100),
    P3_RED      (3,100,65,100),
    P3_BLUE     (3,50,45,135),
    P3_GREEN    (3,10,80,135),
    P3_WHITE    (3,1,30,100);

    int x,y,userId,chipValue;
    /**
     * initiates the chip layout
     * @param userid which chip it is
     * @param chipValue how much that chip worhts
     * @param x ,y  
     */
    ChipLayout(int userId,int chipValue, int x, int y){
        this.userId = userId;
        this.chipValue = chipValue;
        this.x = x;
        this.y = y;
    }
    /**
     * return userid which chip it is  
     */
    public int getUserId(){
        return this.userId;
    }
    /**
     * return chipValue how much that chip worhts  
     */
    public int getChipValue(){
        return this.chipValue;
    }
    /**
     * return x coordinate  
     */
    public int getX(){
        return this.x;
    }
    /**
     * return y coordinate  
     */
    public int getY(){
        return this.y;
    }
}
