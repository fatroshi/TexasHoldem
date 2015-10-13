package User;

import Dealer.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad on 29/09/15.
 */
public class Hand {
    List<Card> hand;
    List<Card> tableCards;

    public Hand(){

        hand = new ArrayList<>();
    }

    public int getNoOfCards(){
        return hand.size();
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public Card getCard(int index) throws NoSuchCardException {

        if(index < this.hand.size()){
            return hand.get(index);
        }else{
            throw new NoSuchCardException("Wrong index of card");
        }

    }

    public Card removeCard(int index){

        return hand.remove(index);
    }

    @Override
    public String toString(){
        return getCard(0).toString();
    }
}
