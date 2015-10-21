/**
 * Created by Farhad Atroshi on 08/10/15.
 * This enum class is when setting backgrounds
 */

package Poker;


public enum GameBackground_ {
    TABLE("../resource/bg/table.png"),
    TABLE_BLACK("../resource/bg/tableStart.png"),
    START("../resource/bg/table_Black.png"),
    PLAYER("../resource/bg/bg_profile.png");

    private String imageSrc;

    GameBackground_(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }
}
