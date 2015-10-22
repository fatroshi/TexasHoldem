/**
 * This class creates a chip.
 */

package Dealer;

import Poker.Picture;

import java.util.Random;

/**
 * Created by Farhad Atroshi & Avi
 */
public class Chip extends Picture {

    private static double chipSize = 1;
    private static double chipY = 0;
    private int chipValue;
    private String chipName;
    // Dealer.Dealer.Chip position in grid, clumn,row
    private int position[]; //tabort
 /**
 * Constructor
 * @param value of the chip
 * @param name of the chip
 * @param imgSrc name of the image source
 */
    public Chip(int value, String name, String imgSrc) {
        // Call parent constructor
        super(imgSrc);
        this.chipValue = value;
        this.chipName = name;
        this.rotate();

    }
 /**
 * @return the chipvalue
 */
    public int getChipValue() {
        return this.chipValue;
    }
/**
 * @return the chipname
 */
    public String getChipName() {
        return this.chipName;
    }
 /**
 * rotates the chip
 */
    public void rotate() {
        //this.rotate();
        Random random = new Random();
        this.getImageView().setRotate(random.nextInt(10) + 15);
        this.getImageView().setScaleX(0.6);
        this.getImageView().setScaleY(0.6);
    }


}
