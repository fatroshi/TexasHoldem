package Poker;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Farhad on 07/10/15.
 */
abstract public class Picture extends ImageView{
    ImageView imageView;
    Image image;
    Image imageBack;

    public Picture(){

    }

    public Picture(String src){
        // init object
        this.imageView = new ImageView();
        // Load image in container
        this.image = new Image(this.getClass().getResource(src).toString());
        // Apply to image view
        imageView.setImage(this.image);
    }

    public Picture(String srcFront,String srcBack){
        // init object
        this.imageView = new ImageView();
        // Load image in container
        this.image = new Image(this.getClass().getResource(srcFront).toString());
        // Load image (Back of the card) in container
        this.imageBack = new Image(this.getClass().getResource(srcBack).toString());
        // Apply to image Back view
        setImageBackView();
    }

    public Picture(String src,double x, double y){
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

    public void setImageFrontView(){
        this.imageView.setImage(this.image);
    }

    public void setImageBackView(){
        this.imageView.setImage(this.imageBack);
    }

    public ImageView getImageView(){
        return this.imageView;
    }



}
