package Dealer;
import Poker.Picture;

import java.util.Random;

/**
 * Created by Farhad on 07/10/15.
 */
public class Chip extends Picture {

    private static double chipSize = 1;
    private static double chipY = 0;
    private int chipValue;
    private String chipName;
    // Dealer.Dealer.Chip position in grid, clumn,row
    private int position[] ;

    public Chip(int value, String name, String imgSrc){
        // Call parent constructor
        super(imgSrc);
        this.chipValue = value;
        this.chipName = name;
        this.rotate();

    }

    public int getChipValue(){
        return this.chipValue;
    }

    public String getChipName(){
        return this.chipName;
    }

    public void rotate(){
        //this.rotate();
        Random random = new Random();
        this.getImageView().setRotate(random.nextInt(160) +10);
    }




}
