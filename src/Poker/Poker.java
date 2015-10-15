package Poker;

import Dealer.*;
import User.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


import java.util.List;



import java.util.*;


/**
 * Created by Farhad on 07/10/15.
 */
public class Poker {
    // Dealer.Hand (52 cards)
    Deck deck;

    // User.Player big (Status)
    public static int playerBig;

    public static int activeUser;

    // All players
    private List<Player> players;
    public static List<Card> tableCards;
    private List<Label> usernameLabels;
    private List<Label> balanceLabels;
    private List<Rectangle> playersBG;



    public Poker(){
        // 52 cards
        deck            = new Deck();
        // Background for players
        playersBG       = new ArrayList<>();
        // Players username
        usernameLabels  = new ArrayList<>();
        // Players balance
        balanceLabels   = new ArrayList<>();
        // All players in the game
        players         = new ArrayList<>();
        // Holder for 5 Cards
        tableCards      = new ArrayList<>();
        // Id of current selected user
        activeUser      = 0;
    }

    private Map<Integer,Player> playersBestHand = new HashMap<>();

    public void raise() {
        AlertWindow.show(" Raise"," Raise: " + players.get(activeUser).getUsername(), 200,100);
        //System.out.println(players.get(activeUser) + ": Raise ");

        // Set next user
        nextUser();
        // Set for next user
        //setActiveUser();
    }

    public void bet(){

        //System.out.println(players.get(activeUser).getUsername()  + ": Bet ");

        // Set next user
        nextUser();
        // Set for next user
        //setActiveUser();
    }

    public void call(){

        //System.out.println(players.get(activeUser).getUsername()  + ": Call ");

        // Set next user
        nextUser();
        // Set for next user
        //setActiveUser();
    }

    public void fold(){
        removePlayerInGame();
        //Do somthing with user cards
        Card c1 = players.get(activeUser).getHand().getCard(0);
        Card c2 = players.get(activeUser).getHand().getCard(1);

        Animation.fadeOut(c1);
        Animation.fadeOut(c2);
        // Set next user
        nextUser();
        // Set for next user
        //setActiveUser();
    }

    public void check(){
        // Set next user
        nextUser();
        // Set for next user
        //setActiveUser();
    }

    public String getCurrentPlayerUsername(){
        return players.get(activeUser).getUsername();
    }

    public Player addPlayer(String username, double balance){
        Player player = new Player(username,balance);
        players.add(player);

        // Add username to usernameLabels
        addUsername(player);
        // Add balance to balanceLabels
        addBalance(player);
        // Add bg to bgPlayers
        addPlayerBG(player);
        return player;
    }

    public void addUsername(Player player){
        // Index of the player in array
        int id = players.indexOf(player);
        // Create label for username
        Label username = new Label(player.getUsername());
        // Set x,y for label
        for (Table_ t: Table_.values()){
            if(id == t.getUserId()){
                username.setLayoutX(t.getXlayout() - 20);
                username.setLayoutY(t.getYlayout() + 100);
                username.setTextFill(Color.LIGHTGRAY);
                username.setFont(Font.font(18));
            }
        }
        this.usernameLabels.add(username);

    }

    public void addBalance(Player player){
        // Index of the player in array
        int id = players.indexOf(player);
        // Create Label for balance
        Label balance = new Label(player.getUsername());
        // Set x,y for label
        for (Table_ t: Table_.values()){
            if(id == t.getUserId()){
                // balance
                balance.setLayoutX(t.getXlayout() - 20);
                balance.setLayoutY(t.getYlayout() + 140);
                balance.setTextFill(Color.GREEN);
            }
        }
        // Add to array
        this.balanceLabels.add(balance);
    }

    public void addPlayerBG(Player player){
        // Index of the player in array
        int id = players.indexOf(player);
        // Create ractangle
        Rectangle r = new Rectangle();
        // Properties
        r.setFill(Color.BLACK);
        r.setStroke(Color.DARKGRAY);
        r.setWidth(140);
        r.setHeight(80);
        r.setArcWidth(20);
        r.setArcHeight(20);

        for (Table_ t: Table_.values()) {
            if (id == t.getUserId()) {
                r.setX(t.getXlayout() - 35);
                r.setY(t.getYlayout() + 90);
            }
        }
        // Add to array
        this.playersBG.add(r);
    }

    public Rectangle getPlayerBG(int index){
        return playersBG.get(index);
    }

    public Label getUsernameLabel(int index){
        return usernameLabels.get(index);
    }

    public Label getBalanceLabel(int index){
        return balanceLabels.get(index);
    }

    public void removePlayerInGame(){
        getPlayer(activeUser).active(false);
        System.out.println(players.get(activeUser).getUsername() + " Removed, ID: " + activeUser);
    }

    public void dealTwoCards(){
        for (int j = 0; j < this.players.size(); j++) {
         // Deal 2 cards for each player
            for (int i = 0; i < 2; i++) {
                players.get(j).getHand().addCard(deck.dealCard());
            }

        }
    }

    public void dealCards(int quantityOfDeals) {
        // Deal same 5 cards to each player
        for (int i = 0; i < quantityOfDeals; i++) {
            Card card = deck.dealCard();

            tableCards.add(card);

        }
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public Player getPlayer(int index){
        return this.players.get(index);
    }

    public <T> boolean inArrayList(int value, List<T> list){
        return list.contains(value);
    }

    public boolean inArray(int value, int[] array){

        return Arrays.asList(array).contains(value);
    }


    public static void setPlayerBig(int playerId){
        playerBig = playerId;
    }

    public static int getPlayerBig(){
        return playerBig;
    }

    public Integer[] bestHand(Hand hand){

        //tmpRqBestHand
        Integer[] tmpRqBestHand = new Integer[4];
        Integer[] tmpRsBestHand = new Integer[4];
        Integer[] bestHand = new Integer[4];

        // Store rank and quantity of suit
        Map<Integer, Integer> rqCards = getRqCards(hand);
        // Store rank and suit
        Map<Integer,Integer> rsCards = getRsCards(hand);

        // Highest Dealer.Card, Pair, Three of a kind, Four of a kind
        tmpRqBestHand = getPairThreeFour(rqCards);
        // Check Royal Flush, Straight flush, flush
        tmpRsBestHand = getFlush(rsCards);

        if(tmpRsBestHand[0] > tmpRqBestHand[0]){
            bestHand = tmpRsBestHand;
        }else{
            bestHand = tmpRqBestHand;
        }

        for (Poker_ p: Poker_.values()){
            if(bestHand[0] == p.getRank()){
                System.out.println(p.name());
            }
        }

        return bestHand;
    }

    public Map<Integer, Integer> getRqCards(Hand hand){

        // Store card rank, quantity
        Map<Integer,Integer> rqCards = new HashMap<>();
        for (int i = 0; i < hand.getNoOfCards(); i++) {
            Card card = hand.getCard(i);
            // Check if the key exists
            Integer value = rqCards.get(card.getRank());
            if (value != null) {
                // Value stored for this specific key
                value = rqCards.get(card.getRank());
                // Quantity
                value += 1;
                rqCards.put(card.getRank(),  value );
            } else {
                // No such key
                rqCards.put(card.getRank(), 1);
            }
        }

        return rqCards;
    }

    public Map<Integer, Integer> getRsCards(Hand hand){

        // Store card rank, suit
        Map<Integer,Integer> rsCards = new HashMap<>();
        for (int i = 0; i < hand.getNoOfCards(); i++) {
            Card card = hand.getCard(i);

            rsCards.put(card.getRank(), card.getSuit());
        }
        return rsCards;
    }

    public Integer[] getPairThreeFour(Map<Integer, Integer> rqCards){
        Integer[] bestHand = {0,0,0,0};

        // Check quantities of the cards
        int firstQuantity = 0;
        int firstQuantityRank = 0;
        int secondQuantity = 0;
        int secondQuantityRank = 0;
        int highestRank = 0;

        for (Map.Entry<Integer, Integer> entry : rqCards.entrySet()) {
            Integer rank = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("Dealer.Rank_ : " + rank + ", Quantity: " + quantity);

            // Check for

            // Get Highest firstQuantity, secondQuantity
            if(quantity > firstQuantity){
                // First update secondQuantity
                // Else we will loose that information
                secondQuantity = firstQuantity;
                bestHand[2] = firstQuantity;
                // Update firstQuantity
                firstQuantity = quantity;
                // Get rank
                firstQuantityRank = rank;
                // Update bestHand
                bestHand[1] = quantity;

                //System.out.println("Current best hand: " + name);
            }else if(quantity <= firstQuantity && quantity > secondQuantity){
                // MOST BE PAIR
                secondQuantity = quantity;
                // Get rank
                secondQuantityRank = rank;
                // Update bestHand
                bestHand[2] = quantity;
            }

            // GET Highest rank for fifth card
            if(rank > highestRank && rank != firstQuantityRank && rank != secondQuantityRank){
                highestRank = rank;
                // Update bestHand
                bestHand[3] = highestRank;
            }

        }

        // PRINT OUT firstQuantity AND secondQuantity
        System.out.println("First  Dealer.Rank_       --> quantity: " + bestHand[1]);
        System.out.println("Second Dealer.Rank_       --> quantity: " + bestHand[2]);
        System.out.println("Fifth card top rank: " + bestHand[3]);

        // PRINT OUT THE BEST HAND
        // bestHand = {Poker.Poker_:rank, Dealer.Card rank firstQuantity, secondQuantity, fifth best card}


        // STORE THE RANK AFTER FOUND WHAT WE GOT
        // CREATE 2 NEW VAR, FOR RANK

        for (Poker_ p: Poker_.values()){
            if(bestHand[1] == p.getFirstQuantity() && bestHand[2] == p.getSecondQuantity()){
                bestHand[0] = p.getRank();
                // Compare rank with result from getFlush
                bestHand[1] = firstQuantityRank;
                bestHand[2] = secondQuantityRank;
                bestHand[3] = highestRank;
            }
        }

        return bestHand;
    }

    public Integer[] getFlush(Map<Integer, Integer> rsCards){
        Integer[] tmpBestHand = {0,0,0,0};

        // rank
        Integer pRank = 0;
        Integer pSuit = 0;
        int highestCard = 0;
        int rankCounter = 0;
        int suitCounter = 0;
        System.out.println(rsCards.size());
        for (Map.Entry<Integer, Integer> entry : rsCards.entrySet()) {
            Integer rank = entry.getKey();
            Integer suit = entry.getValue();
            //System.out.println("Dealer.Rank_: " + rank + ", Dealer.Suit_: " + suit);

            // Check straight
            if(descByOne(pRank,rank)){
                rankCounter++;
                //System.out.println(counter);
            }else{
                rankCounter = 1;

            }
            // Check flush
            if(pSuit == suit){
                suitCounter++;
            }else{
                suitCounter = 1;
            }
            // Store value int tmp var
            pRank = rank;
            pSuit = suit;

            if(rankCounter >= 5 && suitCounter >= 5){
                // Royal flush or Straight flush
                if(rank == Rank_.ACE.getRank()){
                    // Royal Flush
                    tmpBestHand[0] = 10;
                    //print("Royal Flush");
                }else{
                    // Straight Flush
                    // Store the highest card which should be this one
                    //print("Straight Flush, highest card: " +rank );
                    highestCard = rank;
                    // Store values
                    tmpBestHand[0] = 9;
                    tmpBestHand[3] = rank;
                }
            }else if(rankCounter >= 5){
                // Straight
                // Store the highest card which should be this one
                //print("Straight, highest card: " + rank);
                highestCard = rank;
                // Store values
                tmpBestHand[0] = 5;
                tmpBestHand[3] = rank;
            }else if(suitCounter >= 5){
                // Wer got flush
                //print("Flush");
                // Store values
                tmpBestHand[0] = 6;
                tmpBestHand[3] = rank;

            }
        }

        return tmpBestHand;
    }

    public boolean descByOne(int a, int b){
        boolean desc = false;
        if(a == (b-1)){
            desc = true;
        }
        return  desc;
    }

    public int getPlayersInGame(){
        int counter =0;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).isActive()){
                counter++;
            }
        }
        return counter;
    }

    public void nextUser(){
        // Add to activeUser
        int nextActiveUser = activeUser + 1;
        // Find next active User
        if(nextActiveUser < players.size()){
            activeUser = nextActiveUser;
            for (int i = activeUser; i < players.size(); i++) {
                Player player = players.get(i);
                // Check if player still in game
                if(player.isActive()){
                    activeUser = i;
                    // Found user, break the loop
                    break;
                }
            }
            System.out.println("activeUser: " + activeUser);
        }else{

            // activeUser = 0 --> First user
            // Reset var to no selected user
            activeUser = -1;
        }

    }

}
