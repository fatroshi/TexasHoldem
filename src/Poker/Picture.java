/**
 * Created by Farhad Atroshi on 07/10/15.
 * This class is used when creating the cards.
 */


package Poker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Farhad & Avi
 * creates picture object
 *
 */

abstract public class Picture extends ImageView {
    private ImageView imageView;
    private Image image;
    private Image imageBack;
    private int toggle = 0;


    public Picture() {

    }
    /**
     * constructor only with image source
     *@param src
     */

    public Picture(String src) {
        // init object
        this.imageView = new ImageView();
        // Load image in container
        this.image = new Image(this.getClass().getResource(src).toString());
        // Apply to image view
        imageView.setImage(this.image);
    }

    /**
     * constructor only with image source both back and forth
     *@param srcFront
     * @param srcBack
     */


    public Picture(String srcFront, String srcBack) {
        // init object
        this.imageView = new ImageView();
        // Load image in container
        this.image = new Image(this.getClass().getResource(srcFront).toString());
        // Load image (Back of the card) in container
        this.imageBack = new Image(this.getClass().getResource(srcBack).toString());
        // Apply to image Back view
        setImageBackView();
    }

    /**
     * constructor with image source with coordinates
     *@param src
     * @param x
     * @param y
     */

    public Picture(String src, double x, double y) {
        // init object
        this.imageView = new ImageView();
        // Load image in container
        this.image = new Image(this.getClass().getResource(src).toString());
        // Apply to image view
        imageView.setImage(this.image);
        // Set x coordinate
        this.setX(x);
        // Set y coordinate
        this.setY(y);
    }

    /**
     * sets the front view of the object
     *
     */

    public void setImageFrontView() {
        this.imageView.setImage(this.image);
    }

    /**
     * sets the back view of the object

     */

    public void setImageBackView() {
        this.imageView.setImage(this.imageBack);
    }

    /**
     * toggles the object to change view from front to back and vice versa
     */


    public void toggleImage() {
        if (this.toggle == 0) {
            this.toggle = 1;
            setImageFrontView();
        } else if (this.toggle == 1) {
            this.toggle = 0;
            setImageBackView();
        }


    }

    /**
     * @return imageview
     */

    public ImageView getImageView() {
        return this.imageView;
    }


}
