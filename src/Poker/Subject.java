package Poker;

/**
 * Created by Farhad on 18/10/15.
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
