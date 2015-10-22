/**
 * This class creates chips by creating object from the Chip class.
 */

package Dealer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad Atroshi & Avi
 * creates collection of chips for player
 */
public class ChipCollection {
    private int valueOfChips;
    private int noOfChips;
    private List<Chip> chipCollection;

    /**
     * Constructor
     * @param money
     */
    public ChipCollection(int money) {
        this.chipCollection = new ArrayList<>();
        fillChipCollection(money);
    }

    /**
     * @return value of the chipps
     *
     */

    public int getValueOfChips() {
        return this.valueOfChips;
    }

    /**
     * matches money with the chips
     *
     */

    public void fillChipCollection(int collections) {
        // We need a counter for the image files.
        // They are numbered 1-52
        // Ace = 1.png,
        // Loop value of collections
        for (int i = 0; i < collections; i++) {
            for (Chip_ chip : Chip_.values()) {
                // Create Dealer.Chip And Add to chioCollection
                chipCollection.add(new Chip(chip.getValue(), chip.name(), chip.getImgSrc()));
                // Add to valueOfChips so that we can get the total value
                this.valueOfChips += chip.getValue();
            }
        }
    }
    // returns the chip in a collection by index
    public Chip getChip(int index) {
        return chipCollection.get(index);
    }
    // returns number of chips in the collection
    public int getNoOfChips() {
        return this.chipCollection.size();
    }
    //removes chip in a collection by index
    public Chip removeChip(int index) {
        return null;
    }

}
