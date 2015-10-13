package Dealer;

/**
 * Created by Farhad on 13/10/15.
 */
public enum ChipLayout {
    //CHIP
    P0_BLACK(0,500,50,50),
    P1_RED(1,100,50,100),
    P2_BLUE(2,50,50,150),
    P3_WHITE(3,10,50,200);

    int x,y,userId,chipValue;

    ChipLayout(int userId,int chipValue, int x, int y){
        this.userId = userId;
        this.chipValue = chipValue;
        this.x = x;
        this.y = y;
    }
}
