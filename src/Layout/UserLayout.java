package Layout;

/**
 * Created by Farhad Atroshi & Avi
 *
 * This enum class is used for the layout position of each player.
 */
public enum UserLayout {
    P0(0,-150,100,"../resource/bg/bg_profile.png"),
    P1(1,-150,320,"../resource/bg/bg_profile.png"),
    P2(2,680,320,"../resource/bg/bg_profile.png"),
    P3(3,680,100,"../resource/bg/bg_profile.png");

    private int userId;
    private double xLayout,yLayout;
    private String img;

    UserLayout(int userId, double xLayout, double yLayout, String img) {
        this.userId = userId;
        this.xLayout = xLayout;
        this.yLayout = yLayout;
        this.img = img;
    }

    public int getUserId(){
        return this.userId;
    }

    public double getLayoutX(){
        return this.xLayout;
    }

    public double getLayoutY(){
        return this.yLayout;
    }

    public String getImg(){
        return this.img;
    }

}
