package Dealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Farhad on 29/09/15.
 */
public class Deck {

    List<Card> cards;

    /**
     * Create 52 cards and shuffle them
     */
    public Deck(){
        cards = new ArrayList<>();
        createDeck();
        shuffleCards();
        shuffleCards();
        shuffleCards();
        shuffleCards();
        shuffleCards();

    }

    /**
     * Create deck with help an enums
     */
    public void createDeck(){
        // We need a counter for the image files.
        // They are numbered 1-52
        // Ace = 1.png,
        int fileCounter = 1;

        String imgFolder = "../resource/cards/";
        String imgPath;
        for (Rank_ rank: Rank_.values()){
            for (Suit_ suit: Suit_.values()){
                imgPath = imgFolder + Integer.toString(fileCounter) + ".png";
                String imgBackPath = imgFolder + "b1fv.png";
                cards.add(new Card(rank.getRank(),suit.getSuit(),imgPath,imgBackPath));
                fileCounter++;
            }
        }
    }

    /**
     * Get no of cards in deck
     * @return no of cards in deck
     */
    public int getNoOfCards(){

        return cards.size();
    }

    public List<Card> getCards(){
        return this.cards;
    }

    /**
     * Deal card if deck is not empty
     * @return
     * @throws NoSuchCardException
     */
    public Card dealCard() throws NoSuchCardException {
        if(cards.isEmpty()){
            throw new NoSuchCardException("No more cards left");
        }else {
            return removeCard();
        }
    }

    /**
     * Remove the first card
     * @return the removed card
     */
    public Card removeCard(){
        return cards.remove(0);
    }

    /**
     * Shuffle created cards
     */
    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    /**
     * Sort cards by Suit_

    public void sortBySuit(){
        Collections.sort(cards, new CardBySuit());
    }*/

    /**
     * Sort cards by rank

    public void sortByRank(){
        Collections.sort(cards, new CardByRank());
    }*/

    public void  fill(){
        // Clear deck
        cards.clear();
        // Create 52 cards
        createDeck();
        shuffleCards();
    }

    @Override
    public String toString(){

        return null;
    }
}
