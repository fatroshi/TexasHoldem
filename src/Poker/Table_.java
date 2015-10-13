package Poker;

/**
 * Created by Farhad on 12/10/15.
 */
public enum Table_ {

    // DECK POS
    DECK(),
    // CARDS, CardId,x,y,rotation
    FIRST(0,72,0,0),
    SECOND(1,10,0,10),
    THIRD(2,90,-215,0),
    FOURTH(3,170,-215,0),
    FIFTH(4,250,-215,0),
    SIXTH(5,330,-215,0),
    SEVENTH(6,410,-215,0),
    DEAD_CARDS(7,-50,-50,0),

    // Userinfo
    USERNAME(),
    BALANCE(),
    CHIPSALDO(),
    // Big small
    BIG(),
    SMALL(),
    // Layout Position
    P0(0,100,200,"../resource/bg/bg_profile.png"),
    P1(1,200,500,"../resource/bg/bg_profile.png"),
    P2(2,700,500,"../resource/bg/bg_profile.png"),
    P3(3,800,200,"../resource/bg/bg_profile.png"),
    ;

    private int userId,cardId,rotation;
    private double xLayout,yLayout; // Layout position
    private double x,y;             // Item position
    private String imageSrc;

    Table_(){

    }

    Table_(int cardId,double x, double y,int rotation) {
        this.cardId = cardId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
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
    public int getRotation(){
        return this.rotation;
    }
}
