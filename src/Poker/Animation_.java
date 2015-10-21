/**
 * Created by Farhad Atroshi on 15/10/15.
 *
 * This class is created for were to put cards  for each of the player.
 * this class is not used in the game.
 */

package Poker;


public enum Animation_ {
    // DECK POS
    FOLD_P0(0, 150, 100),
    FOLD_P1(1, 50, 320),
    FOLD_P2(2, 880, 320),
    FOLD_P3(3, 880, 100);

    private int id;
    private double x, y;

    Animation_(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return this.id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.getY();
    }

}
