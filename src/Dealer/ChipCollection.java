package Dealer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad on 07/10/15.
 */
public class ChipCollection {
    private int valueOfChips;
    private int noOfChips;
    private List<Chip> chipCollection;

    public ChipCollection(int money){
        this.chipCollection = new ArrayList<>();
        fillChipCollection(money);
    }

    public int getValueOfChips(){
        return this.valueOfChips;
    }

    public void fillChipCollection(int collections){
        // We need a counter for the image files.
        // They are numbered 1-52
        // Ace = 1.png,
        // Loop value of collections
        for (int i = 0; i < collections; i++) {
            for (Chip_ chip: Chip_.values()){
                // Create Dealer.Chip And Add to chioCollection
                chipCollection.add(new Chip(chip.getValue(),chip.name(),chip.getImgSrc()));
                // Add to valueOfChips so that we can get the total value
                this.valueOfChips += chip.getValue();
            }
        }
   }

    public Chip getChip(int index){
        return chipCollection.get(index);
    }

    public int getNoOfChips(){
        return this.chipCollection.size();
    }

    public Chip removeChip(int index){
        return null;
    }

}
