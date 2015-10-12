package Poker;

/**
 * Created by Farhad on 12/10/15.
 */
public enum Table_ {

    // DECK POS
    DECK(),
    // CARDS
    FIRST(0,0,0),
    SECOND(1,72,0),
    THIRD(2,140,0),
    FOURTH(),
    FIFTH(),
    DEAD_CARDS(),
    // Userinfo
    USERNAME(),
    BALANCE(),
    CHIPSALDO(),
    // Big small
    BIG(),
    SMALL(),
    // Layout Position
    P1(0,200,100,"../resource/bg/bg_profile.png"),
    P2(1,600,200,"../resource/bg/bg_profile.png"),
    P3(2,400,400,"../resource/bg/bg_profile.png"),
    P4(3,500,500,"../resource/bg/bg_profile.png"),
    ;

    private int userId,cardId;
    private double xLayout,yLayout; // Layout position
    private double x,y;             // Item position
    private String imageSrc;

    Table_(){

    }

    Table_(int cardId,double x, double y) {
        this.cardId = cardId;
        this.x = x;
        this.y = y;
    }

    Table_(int userId, double xLayout, double yLayout, String imageSrcProfile) {
        this.userId = userId;
        this.xLayout = xLayout;
        this.yLayout = yLayout;
        this.imageSrc = imageSrcProfile;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }

    public double getXlayout(){
        return  this.xLayout;
    }

    public double getYlayout(){
        return this.yLayout;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public int getUserId(){
        return this.userId;
    }

    public int getCardId(){
        return this.cardId;
    }
}
