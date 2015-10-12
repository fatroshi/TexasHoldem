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
    P1("../resource/bg/bg_profile.png"),
    P2("../resource/bg/bg_profile.png"),
    P3("../resource/bg/bg_profile.png"),
    P4("../resource/bg/bg_profile.png"),
    ;

    private String imageSrc;

    Table_() {

    }

    Table_(String imageSrcProfile) {
        this.imageSrc = imageSrcProfile;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }
}
