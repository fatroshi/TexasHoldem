package Layout;

/**
 * Created by Farhad Atroshi
 *
 * This enum class is used for the layout position of each player.
 */

/**
 * Setting upp the position of the background for each player.
 * Using user id for the linking.
 */
public enum UserLayout {
    P0(0,-150,100,"../resource/bg/bg_profile.png"),
    P1(1,-150,320,"../resource/bg/bg_profile.png"),
    P2(2,680,320,"../resource/bg/bg_profile.png"),
    P3(3,680,100,"../resource/bg/bg_profile.png");

    private int userId;                     // User id
    private double xLayout,yLayout;         // Layout position
    private String img;                     // Image source path

    /**
     * User when setting upp layout postion for each player
     * @param userId user id (comparing with index in the players list)
     * @param xLayout x value position of the layout
     * @param yLayout y value postion of the layout
     * @param img image path source
     */
    UserLayout(int userId, double xLayout, double yLayout, String img) {
        this.userId = userId;
        this.xLayout = xLayout;
        this.yLayout = yLayout;
        this.img = img;
    }

    /**
     * Get the user id
     * @return the user id
     */
    public int getUserId(){
        return this.userId;
    }

    /**
     * Get the x value of the layout
     * @return x-value
     */
    public double getLayoutX(){
        return this.xLayout;
    }

    /**
     * Get y value of the layout
     * @return y - value
     */
    public double getLayoutY(){
        return this.yLayout;
    }

    /**
     * Get the image source path
     * @return path source
     */
    public String getImg(){
        return this.img;
    }

}
