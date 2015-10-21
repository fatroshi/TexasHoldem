/**
 * This class creates a chip.
 */

package Dealer;

import Poker.Picture;

import java.util.Random;

/**
 * Created by Farhad Atroshi on 07/10/15.
 */
public class Chip extends Picture {

    private static double chipSize = 1;
    private static double chipY = 0;
    private int chipValue;
    private String chipName;
    // Dealer.Dealer.Chip position in grid, clumn,row
    private int position[]; //tabort

    public Chip(int value, String name, String imgSrc) {
        // Call parent constructor
        super(imgSrc);
        this.chipValue = value;
        this.chipName = name;
        this.rotate();

    }

    public int getChipValue() {
        return this.chipValue;
    }

    public String getChipName() {
        return this.chipName;
    }

    public void rotate() {
        //this.rotate();
        Random random = new Random();
        this.getImageView().setRotate(random.nextInt(10) + 15);
        this.getImageView().setScaleX(0.6);
        this.getImageView().setScaleY(0.6);
    }


}
