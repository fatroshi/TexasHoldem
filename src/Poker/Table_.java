package Poker;

/**
 * Created by Farhad on 12/10/15.
 */
public enum Table_ {

    // DECK POS
    DECK(),
    // CARDS
    FIRST(),
    SECOND(),
    THIRD(),
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
    P1(0,100,100,"../resource/bg/bg_profile.png"),
    P2(1,300,300,"../resource/bg/bg_profile.png"),
    P3(2,400,400,"../resource/bg/bg_profile.png"),
    P4(3,500,500,"../resource/bg/bg_profile.png"),
    ;

    private String imageSrc;
    private double xLayout,yLayout; // Layout position
    private double x,y;             // Item position

    Table_(){

    }

    Table_(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Table_(int playerId, double xLayout, double yLayout, String imageSrcProfile) {
        this.xLayout = xLayout;
        this.yLayout = yLayout;
        this.imageSrc = imageSrcProfile;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }
}
