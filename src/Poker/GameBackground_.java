package Poker;

/**
 * Created by Farhad on 08/10/15.
 */
public enum GameBackground_ {
    TABLE("../resource/bg/table.png"),
    START("../resource/bg/start.png"),
    PLAYER("../resource/bg/bg_profile.png");

    private String imageSrc;

    GameBackground_(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }
}
