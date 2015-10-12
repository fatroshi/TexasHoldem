/**
 * Created by Farhad on 08/10/15.
 */
public enum Background_ {
    TABLE("img/bg/table.png"), START("img/bg/start.png"), PLAYER("img/bg/bg_profile.png");

    private String imageSrc;

    Background_(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }
}
