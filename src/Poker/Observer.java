/**
 * Created by Farhad on 18/10/15.
 * Subject observer
 * This interface is used for setting upp what we need to update.
 */

package Poker;

public interface Observer {
    public void updateSlider(double currentValue, double max, String messages);
    public void decreaseUserBalance(int activeUser, double userBalance);
    public void updateTablePotLabel(double pot);
}
