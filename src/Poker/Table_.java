/**
 * Created by Farhad Atroshi on 12/10/15.
 * This enum class is used for setting card position and rotation.
 */

package Poker;


public enum Table_ {
    // CARDS, CardId,x,y,rotation
    FIRST(0, 72, 0, 0),
    SECOND(1, 10, 0, 10),
    THIRD(2, 1, 0, 0),
    FOURTH(3, 2, 0, 0),
    FIFTH(4, 3, 0, 0),
    SIXTH(5, 4, 0, 0),
    SEVENTH(6, 5, 0, 0),
    DEAD_CARDS(7, -50, -50, 0),

    // Userinfo
    USERNAME(),
    BALANCE(),
    CHIPSALDO(),
    // Big small
    BIG(),
    SMALL(),
    // Layout Position
    P0(0, 50, 100, "../resource/bg/bg_profile.png"),
    P1(1, 50, 320, "../resource/bg/bg_profile.png"),
    P2(2, 880, 320, "../resource/bg/bg_profile.png"),
    P3(3, 880, 100, "../resource/bg/bg_profile.png"),;

    private int userId, cardId, rotation;
    private double xLayout, yLayout; // Layout position
    private double x, y;             // Item position
    private String imageSrc;

    Table_() {

    }

    /**
     * Used when setting upp  cards for the user
     * @param cardId id value of the card, if its the first, second ...
     * @param x position
     * @param y position
     * @param rotation rotation value
     */
    Table_(int cardId, double x, double y, int rotation) {
        this.cardId = cardId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * User when setting up layouts for each user
     * @param userId user id (compares index in players list)
     * @param xLayout x position of the layout
     * @param yLayout u position of the layout
     * @param imageSrcProfile image background for the user
     */
    Table_(int userId, double xLayout, double yLayout, String imageSrcProfile) {
        this.userId = userId;
        this.xLayout = xLayout;
        this.yLayout = yLayout;
        this.imageSrc = imageSrcProfile;
    }


    /**
     * Get the image source of the card
     * @return image source
     */
    public String getImageSrc() {
        return this.imageSrc;
    }

    /**
     * Get Layout X-value  position
     * @return x-value of the layout
     */
    public double getXlayout() {
        return this.xLayout;
    }

    /**
     * Get layout Y-value position position
     * @return y-value position value of the layout
     */
    public double getYlayout() {
        return this.yLayout;
    }


    /**
     * Get x-value position of the image
     * @return x-value position
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the Y-value position
     * @return Y-value position
     */
    public double getY() {
        return this.y;
    }


    /**
     * Get the user id, this is used for knowing which cards are linked to user
     * @return id of the user (index in the players list)
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Get the card id value
     * @return card id value
     */
    public int getCardId() {
        return this.cardId;
    }

    /**
     * Get the rotation value for the card
     * @return rotaion value
     */
    public int getRotation() {
        return this.rotation;
    }
}
