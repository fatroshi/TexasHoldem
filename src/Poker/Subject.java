/**
 * Subject observer
 *
 */

package Poker;

/**
 * Created by Farhad & Avi
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
