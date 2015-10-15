package Dealer;

/**
 * Created by Farhad Atroshi on 07/10/15.
 */
public enum Chip_ {
    // 1 = spades, 2 = hearts, 3 = diamonds, 4 = clubs
    BLACK("../resource/chips/1.png", 500),
    RED("../resource/chips/2.png", 100),
    BLUE("../resource/chips/3.png", 50),
    GREEN("../resource/chips/4.png", 10),
    WHITE("../resource/chips/5.png", 1);


    private int value;
    private String imgSrc;

    Chip_(String imgSrc, int value) {
        this.value = value;
        this.imgSrc = imgSrc;
    }

    public int getValue() {
        return this.value;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }


}
