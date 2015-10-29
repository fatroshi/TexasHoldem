/**
 * ### FARHAD READ THIS #####
 *
 * FIRST LEARN JAVA UTIL SUBJECT AND OBSERVABLE
 *
 * This class should not contain any graphics!!!
 * Store only the values for the graphics.
 * Label = String
 * Slider = Double
 * Balance = Double
 */


/**
 * Created by Farhad on 17/10/15.
 *
 * This class handles the game logic.
 * The logic for getting cards,chips and the controls items.
 */

package Poker;

import Dealer.*;
import Layout.CardLayout;
import Layout.ChipLayout;
import Layout.UserLayout;
import User.Hand;
import User.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import highscore.*;

public class Table{

    private Deck deck                               = new Deck();        // Dealer.Hand (52 cards)
    private List<Card> tableCards                   = new ArrayList<>(); // Store all table cards, total of 5
    private List<Player> players                    = new ArrayList<>(); // List for storing the players

    // Variables used by all players
    private int activeUser;                         // Id of current active player (Active --> Still in the game)
    private int oldActiveUser;                      // Id of previous active player
    private double bet;                             // Current bet in game
    private double newBet;                          // User to check if bet has been changed
    private double pot;                             // Total current pot in the game
    private int rounds;                             // Counts the poker rounds, used for showing cards and when the game in done.
    private int playCounter;                        // increases for call,bet. Sets to 0 for raise

    // Graphics
    private String msg;                             // Used for passing strings to objects from class Graphics


    HighScoreList hsl = new HighScoreList();
    DB db = new DB("dbScoreList.bin");

    // Var for viwStart slider
    double sliderValue;
    double sliderMinValue;
    double sliderMaxValue;

    /**
     * Constructor
     * Initializes nine objects
     */
    public Table(){
        hsl = db.getData();                                     // Load data from database
        //Slider
        sliderMinValue = 0;
        sliderMaxValue = 100;
    }

    public void setSliderValue(double value){
        this.sliderValue = value;

    }

    public double getPot(){
        return this.pot;
    }

    public double getSliderMinValue() {
        return sliderMinValue;
    }

    public void setSliderMinValue(double sliderMinValue) {
        this.sliderMinValue = sliderMinValue;
    }

    /**
     * Checks game rounds, if its the last round the de pot will
     * be split between the winners and the game starts over again.
     *
     * The Controller class uses this to know which cards to show
     */
    public int round(){
        // 1. How many active players
        // 2. if the playCounter = (acive players) - 1, one round
        // When to reset the counter ?
        // well we will se that!

        if(playCounter == getActivePlayers()){
            playCounter = 0;
            // Increase rounds
            rounds++;
            if(this.rounds == 1){       // First round
                // Show 1-3 cards
                System.out.println(" *** Table Round 1 *** ");
            }else if(this.rounds == 2){ // Second round
                // 3-4
                System.out.println(" *** Table Round 2 ***");
            }else if(this.rounds == 3){ // Third round
                // 4-5
                System.out.println(" *** Table Round 3 ***");
            }else if (this.rounds == 4){
                // CHECK WHO ONE
                System.out.println(" *** Table Round 4 ***");

                // Check who the winner is
                //splitPot(this.pot, getWinner());
                dealPot(this.pot,getWinner());
                //notifyObservers();

                System.out.println(players.get(0).getBalance() + " Balance: AVI");
                System.out.println(players.get(0).getBalance() + " Balance: Farhad");

            }else if(this.rounds == 5){
                // CHECK WHO ONE
                System.out.println(" *** Table Round 5 ***");
                // Reset values
                gameRestart();
            }
        }

        return rounds;
    }

    /**
     * Resets the rounds to zero
     */
    public void resetRounds(){
        this.rounds = 0;
    }

    /**
     * Set all properties for the cards,
     * x,y,layoutX,layoutY
     * Animation, Show front/Back
     * @return list of cards for each player
     */
    public List<Card> getPlayerCards() {

        List<Card> cards = new ArrayList<>();
        for (int playerId = 0; playerId < players.size(); playerId++) {
            Hand hand = players.get(playerId).getHand();
            for (int j = 0; j < hand.getNoOfCards(); j++) {
                Card card = hand.getCard(j);
                for (CardLayout cl : CardLayout.values()) {
                    // Set x,y for inside layout
                    if (j == cl.getCardId()) {
                        card.getImageView().setX(cl.getX());
                        card.getImageView().setY(cl.getY());
                        card.getImageView().setRotate(cl.getRotation());
                        //card.setImageFrontView();
                        // Toggle card when clicked
                        //card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
                    }
                    // Set x,y for layout
                    for (UserLayout ul : UserLayout.values()) {
                        if (playerId == ul.getUserId()) {
                            card.getImageView().setLayoutX(ul.getLayoutX() + 200);
                            card.getImageView().setLayoutY(ul.getLayoutY());
                            Animation.fadeIn(card);
                        }
                    }
                }

                cards.add(card);
            }
        }

        return cards;
    }

    /**
     * Adding player to the players list
     * Adding username and balance to list in graphic object
     * @param username the players username
     * @param balance the players balance, the balance is used for gambling
     * @return the created player.
     */
    public Player addPlayer(String username, double balance) {
        Player player = new Player(username, balance);
        players.add(player);

        return player;
    }

    /**
     * Removes player from the game by calling the players method player.active(false);
     */
    public void removePlayerInGame() {
        getPlayer(this.activeUser).setActive(false);
        System.out.println(players.get(this.activeUser).getUsername() + " Removed, ID: " + this.activeUser);
    }

    /**
     * Deals tho cards for each player
     */
    public void dealTwoCards() {
        for (Player player : this.players) {
            // Deal 2 cards for each player
            for (int i = 0; i < 2; i++) {
                player.getHand().addCard(deck.dealCard());
            }

        }
    }

    /**
     * Deals cards to table and players
     * this is used for the 4 table cards not the first 2 cards for players'
     * Setting for card, show back/front
     * @param quantityOfDeals the quantity of cards that will be dealt by the deck object.
     */
    public void dealCards(int quantityOfDeals) {
        // Deal same 5 cards to each player
        for (int i = 0; i < quantityOfDeals; i++) {
            Card card = deck.dealCard();
            card.setImageFrontView();
            tableCards.add(card);

            for (Player player : players) {
                player.getHand().addCard(card);
            }
        }
    }

    /**
     * Get list of all players in the game
     * @return list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get player from players list by index
     * @param index the index of the player in the players list
     * @return Player
     */
    public Player getPlayer(int index) {
        return this.players.get(index);
    }

    /**
     * Returns the best hand
     * element [0] --> Refers to Poker rank 1-10. 1 = High card ... 10 = Royal Flush
     * element [1] --> Refers to Highest Pair, or Tree of a kind
     * element [2] --> Refers to pair
     * element [3] --> Refers to highest card in the hand
     * @param hand the best hand.
     * @return values check in enum class Poker_
     */
    public Integer[] bestHand(Hand hand) {

        //tmpRqBestHand
        Integer[] tmpRqBestHand = new Integer[4];
        Integer[] tmpRsBestHand = new Integer[4];
        Integer[] bestHand = new Integer[4];

        // Store rank and quantity of suit
        Map<Integer, Integer> rqCards = getRqCards(hand);
        // Store rank and suit
        Map<Integer, Integer> rsCards = getRsCards(hand);

        // Highest Dealer.Card, Pair, Three of a kind, Four of a kind
        tmpRqBestHand = getPairThreeFour(rqCards);
        // Check Royal Flush, Straight flush, flush
        tmpRsBestHand = getFlush(rsCards);

        // Get the best hand
        // This might look like a strange logic with the extra else
        // but we do this because we are interested in Rank rather than suit
        if (tmpRsBestHand[0] > tmpRqBestHand[0]) {
            bestHand = tmpRsBestHand;
        } else if(tmpRsBestHand[0] < tmpRqBestHand[0]) {
            bestHand = tmpRqBestHand;
        } else{
            //
            bestHand = tmpRqBestHand;
        }

        // Print out the best hand with current cards
        for (Poker_ p : Poker_.values()) {
            if (bestHand[0] == p.getRank()) {
                System.out.println(p.name());
            }
        }

        return bestHand;
    }

    /**
     * Store Rank and quantity of each card in the hand
     * @param hand the card hand of the player
     * @return  A Map with rank and quantity of the cards in the hand.
     */
    public Map<Integer, Integer> getRqCards(Hand hand) {

        // Store card rank, quantity
        Map<Integer, Integer> rqCards = new HashMap<>();
        for (int i = 0; i < hand.getNoOfCards(); i++) {
            Card card = hand.getCard(i);
            // Check if the key exists
            Integer value = rqCards.get(card.getRank());
            if (value != null) {
                // Value stored for this specific key
                value = rqCards.get(card.getRank());
                // Quantity
                value += 1;
                rqCards.put(card.getRank(), value);
            } else {
                // No such key
                rqCards.put(card.getRank(), 1);
            }
        }

        return rqCards;
    }

    /**
     * Store rank and suit of each card in the hand
     * @param hand the card hand of the player
     * @return A map of Card ranks ant suit
     */
    public Map<Integer, Integer> getRsCards(Hand hand) {

        // Store card rank, suit
        Map<Integer, Integer> rsCards = new HashMap<>();
        for (int i = 0; i < hand.getNoOfCards(); i++) {
            Card card = hand.getCard(i);

            rsCards.put(card.getRank(), card.getSuit());
        }
        return rsCards;
    }

    /**
     * Search for Pair, three of a kind and four of a kind in the hand
     * @param rqCards Rank ang quantity of each card in the player hand.
     * @return the best possible poker hand, values stored in an array.
     * bestHand[0] = What we got, Royal Flush, Flust etc
     * bestHand[1] = highest pair or three of a kind
     * bestHand[2] = second pair
     * bestHand[3] = highestRank of the fifth card
     */
    public Integer[] getPairThreeFour(Map<Integer, Integer> rqCards) {
        Integer[] bestHand = {0, 0, 0, 0};

        // Check quantities of the cards
        int firstQuantity = 0;
        int firstQuantityRank = 0;
        int secondQuantity = 0;
        int secondQuantityRank = 0;
        int highestRank = 0;

        for (Map.Entry<Integer, Integer> entry : rqCards.entrySet()) {
            Integer rank = entry.getKey();
            Integer quantity = entry.getValue();
            //System.out.println("Rank_ : " + rank + ", Quantity: " + quantity);

            // Check for

            // Get Highest firstQuantity, secondQuantity
            if (quantity > firstQuantity) {
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
            } else if (quantity <= firstQuantity && quantity > secondQuantity) {
                // MOST BE PAIR
                secondQuantity = quantity;
                // Get rank
                secondQuantityRank = rank;
                // Update bestHand
                bestHand[2] = quantity;
            }

            // GET Highest rank for fifth card
            if (rank > highestRank && rank != firstQuantityRank && rank != secondQuantityRank) {
                highestRank = rank;
                // Update bestHand
                bestHand[3] = highestRank;
            }

        }

        // PRINT OUT firstQuantity AND secondQuantity
        System.out.println("First         --> quantity: " + bestHand[1]);
        System.out.println("Second        --> quantity: " + bestHand[2]);
        System.out.println("Fifth card: " + bestHand[3]);

        // PRINT OUT THE BEST HAND
        // bestHand = {Poker.Poker_:rank, Dealer.Card rank firstQuantity, secondQuantity, fifth best card}


        // STORE THE POKER RANK AFTER FOUND WHAT WE GOT
        // CREATE 2 NEW VAR, FOR RANK

        for (Poker_ p : Poker_.values()) {
            // Comparing to the enum values
            if (bestHand[1] == p.getFirstQuantity() && bestHand[2] == p.getSecondQuantity()) {
                bestHand[0] = p.getRank();          // What we got, Royal Flush, Flust etc
                bestHand[1] = firstQuantityRank;    // highest pair or three of a kind
                bestHand[2] = secondQuantityRank;   // second pair
                bestHand[3] = highestRank;          // fifth card
            }
        }

        return bestHand;
    }

    /**
     * Search for Royal Flush, Straight Flush, Straight and flush in the hand
     * @param rsCards rank and suit of the cards in player hand.
     * @return the best possible poker hand, values stored in an array.
     * bestHand[0] = What we got, Royal Flush, Flush etc
     * bestHand[3] = highestRank of the fifth card
     */
    public Integer[] getFlush(Map<Integer, Integer> rsCards) {
        Integer[] tmpBestHand = {0, 0, 0, 0};

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
            if (descByOne(pRank, rank)) {
                rankCounter++;
                //System.out.println(counter);
            } else {
                rankCounter = 1;

            }
            // Check flush
            if (pSuit == suit) {
                suitCounter++;
            } else {
                suitCounter = 1;
            }
            // Store value int tmp var
            pRank = rank;
            pSuit = suit;

            if (rankCounter >= 5 && suitCounter >= 5) {
                // Royal flush or Straight flush
                if (rank == Rank_.ACE.getRank()) {
                    // Royal Flush
                    tmpBestHand[0] = 10;
                    //print("Royal Flush");
                } else {
                    // Straight Flush
                    // Store the highest card which should be this one
                    //print("Straight Flush, highest card: " +rank );
                    highestCard = rank;
                    // Store values
                    tmpBestHand[0] = 9;
                    tmpBestHand[3] = rank;
                }
            } else if (rankCounter >= 5) {
                // Straight
                // Store the highest card which should be this one
                highestCard = rank;
                // Store values
                tmpBestHand[0] = 5;
                tmpBestHand[3] = rank;
            } else if (suitCounter >= 5) {
                // Wer got flush
                //print("Flush");
                // Store values
                tmpBestHand[0] = 6;
                tmpBestHand[3] = rank;

            }
        }

        return tmpBestHand;
    }

    /**
     * This is method is mainly created for the method getFlush, it check if the next value is in order.
     * this helps when we need to decide if the user has a Royal flish, straight flush or straight.
     * @param a first card rank
     * @param b second card rank
     * @return true if the are in desc.
     */
    boolean descByOne(int a, int b) {
        boolean desc = false;
        if (a == (b - 1)) {
            desc = true;
        }
        return desc;
    }

    /**
     * Sets the new active player and
     * updates game elements by calling method update method
     */
    public void nextUser() {
        // Store old activeUser
        this.oldActiveUser = this.activeUser;
        // Add to activeUser
        int nextActiveUser = this.activeUser + 1;
        // Find next active User
        if (nextActiveUser < players.size()) {
            this.activeUser = nextActiveUser;
            // Find and set next active player
            // and update game
            //updateGame();

        } else {
            // Reset
            this.activeUser = 0;
            // Find and set next active player
            // and update game
            //updateGame();

        }
    }


    /**
     * Compares players best hand, return a of players that are the winners
     * @return list of players that won
     */
    public List<Player> getWinner() {
        Map<Integer[], Player> bestHands = new HashMap<>();

        List<Player> winners = new ArrayList<>();

        for (Player player1 : players) {
            Hand hand = player1.getHand();
            bestHands.put(bestHand(hand), player1);
        }
        // Store hand rank
        Integer[] topRank = {0, 0, 0, 0};

        int winner = -1;
        int loopCounter = 0;
        for (Map.Entry<Integer[], Player> entry : bestHands.entrySet()) {
            Integer[] rank = entry.getKey();
            Player player = entry.getValue();

            if(player.isActive()) {
                if (rank[0] > topRank[0]) {
                    winner = players.indexOf(player);
                    System.arraycopy(rank, 0, topRank, 0, 4);
                } else if (rank[0] == topRank[0] && rank[1] > topRank[1]) {
                    winner = players.indexOf(player);
                    System.arraycopy(rank, 0, topRank, 0, 4);
                } else if (rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] > topRank[2]) {
                    winner = players.indexOf(player);
                    System.arraycopy(rank, 0, topRank, 0, 4);
                } else if (rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] == topRank[2] && rank[3] > topRank[3]) {
                    winner = players.indexOf(player);
                    System.arraycopy(rank, 0, topRank, 0, 4);
                } else if (rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] == topRank[2] && rank[3] == topRank[3]) {
                    // Player has same hand
                    // Do something
                    // Store id of the player... or the object...
                    // split the cash
                    winners.add(player);
                }

                if ((loopCounter > 0 || oneActivePlayer()) && player.isActive()) {
                    Player p = players.get(winner);
                    System.out.println(p.getUsername() + " ** WON ** ");
                    winners.add(p);
                }

                loopCounter++;
            }
        }
        return  winners;
    }

    /**
     * Splits the pot between winners and updates the high score list if the
     * pot was high enough.
     * @param pot total pot
     * @param winners list of players that won
     */
    public void dealPot(double pot,List<Player> winners){
        double splitPot;
        int count =0;


        // checks if winners bet the same amount of money
        for(int i = 0; i <winners.size() -1 ;i++ ){
            if(winners.get(i).getTotalBet() == winners.get(i+1).getTotalBet()){
                count++;
            }
        }
        if (winners.size() == 1){
            System.out.println("only one gets the money");

            winners.get(0).depositBalance(pot);
            hsl.updateHighScoreList(new HighScore(winners.get(0).getUsername(),pot));
        }
        else {
            if (winners.size() > 1 && count == winners.size() - 1) {
                System.out.println("winners share the money");
                splitPot = pot / winners.size();
                // deals the pot to players
                for (int i = 0; i < winners.size(); i++) {
                    winners.get(i).depositBalance(splitPot);
                    hsl.updateHighScoreList(new HighScore(winners.get(i).getUsername(), splitPot));
                }

            }
            // if winners bet different amount they get their totalbet back
            else {
                System.out.println("winnerts get what they bet");
                for (int i = 0; i < winners.size(); i++) {
                    winners.get(i).depositBalance(winners.get(i).getTotalBet());
                    hsl.updateHighScoreList(new HighScore(winners.get(i).getUsername(), winners.get(i).getTotalBet()));
                }
            }
        }
        // hsl.updateHighScoreList(new HighScore(player.getUsername(),splitPot));
        db.insert(hsl);

    }

    /**
     * If the player folds, the player looses his bets.
     */
    public void fold() {
        removePlayerInGame();
        //Fadeout player cards
        Card c1 = players.get(this.activeUser).getHand().getCard(0);
        Card c2 = players.get(this.activeUser).getHand().getCard(1);

        Animation.fadeOut(c1);
        Animation.fadeOut(c2);

        // Deal the pot to the winner
        dealPot(this.pot,getWinner());
        // Notify all observers
        //notifyObservers();
        // Reset all game values in this class
        gameRestart();

        // Set next user
        nextUser();
    }


    /**
     * Resets the variables used in the game and also sets all users to active mode
     */
    public void gameRestart(){

        // Reset all variables
        this.pot = 0;
        this.newBet = 0;
        this.bet = 0;


        // Round
        this.resetRounds();
        //this.playCounter = 0;

        // Reset slider
        this.setSliderValue(0);

        // Remove all table cards
        tableCards.clear();
        // Remove player cards
        for (Player player1 : players) {
            player1.getHand().clearHand();
        }

        // Reset deck
        deck.fill();
        deck.shuffleCards();

        // Set all players active, players with balance > 0
        for (Player player: players){
            if(!player.isActive()){
                player.setActive(true);
            }

            player.setBet(0);
            player.setTotalBet(0);
        }

        // notify observers
        //notifyObservers();

    }

    public int getActiveUser(){
        return this.activeUser;
    }

    /**
     * Checks if we have a winner otherwise it will find the next active user
     * and also updates the player background.
     */
    public void play() {

        if (!oneActivePlayer()) {
            // Set next user
            this.nextUser();
        }

    }

    /**
     * Checks if the user balance is > 0 and also handles the bet,raise,check and call.
     * Updates the pot,slider, slider label and status label.
     */
    public void canPlay(){
        // Check if player can bet
        Player player = getActivePlayer();

        // Set to 2 decimal
        this.newBet = sliderValue;
        this.newBet = roundDouble(this.newBet,0);
        this.msg = "First round";
        //notifyObservers();

        if(canBet(player)){

            if(this.newBet > this.bet) {                                                    // RAISE
                // Check if it was raise
                this.raise(player);
            }else if((this.newBet == this.bet) && (this.newBet != 0) && (this.bet != 0)){   // CALL
                // Check if the challenge was accepted
                this.call(player);
            }else if(this.newBet < this.bet){                                               // ALL IN
                // Check if the user went all in
                this.allIn(player);
            }else if(this.newBet == 0 && this.bet == 0){                                    // CHECK
                // Check if the user checked
                this.check(player);
            }else{
                System.out.println("Else something ... " + "newBet: " + newBet + " bet: " + bet);
            }
            // Go to next player
            play();
        }

    }

    /**
     * If the user raised the bet updates variables in the game.
     * @param player the current active player
     */
    public void raise(Player player){
        System.out.println(" RAISE from: " + player.getUsername());

        // We need to check first what the user needs to "raise"
        // The scenario could be that this user raised first and
        // the second user raised again.

        double prevBet = player.getBet();
        double tmpBet = Math.abs(newBet - prevBet);
        tmpBet = roundDouble(tmpBet,2);
        // Update user
        player.debitBalance(tmpBet);
        player.setBet(tmpBet);

        // Set bet to the new value
        this.bet = this.newBet;
        this.sliderMinValue = bet;
        this.sliderValue = bet;

        System.out.println("FROM raise: newBet " + newBet + " bet: " + bet + " Balance: " + player.getBalance() );

        // Use to disable the slider
        boolean allInFlag = false;
        // CHECK IF ALL IN
        if(0 == player.getBalance()){
            // Set status text
            this.msg = "ALL IN by " + player.getUsername();
            // Update playBtn
            //playBtn.setText("CALL");
            // Set flag to disable slider
            allInFlag = true;
        }else{
            // Set status text
            this.msg = "Raise by " + player.getUsername();
            // Update playBtn
            //playBtn.setText("CALL or RAISE");
        }


        // Update the pot
        this.pot += tmpBet;

        // Update status label (this.msg --> will be the text)
        //notifyObservers();

        if(allInFlag) {
            // Disable slider for next user
            //this.slider.setDisable(true);
        }

        // Update slider
        this.setSliderMinValue(this.newBet);

        // Reset playCounter
        playCounter = 1;
    }

    /**
     * If the user Called the raise updates variables in the game.
     * @param player the current active player
     */
    public void call(Player player){
        System.out.println("CALL from: " + player.getUsername());

        // We need to check first what the user needs to "raise"
        // The scenario could be that this user raised first and
        // the second user raised again.

        double prevBet = player.getBet();
        double tmpBet = Math.abs(newBet - prevBet);

        // Update user
        tmpBet = roundDouble(tmpBet,2);
        player.debitBalance(tmpBet);
        player.setBet(tmpBet);

        // Reset slider value
        this.setSliderMinValue(0);
        this.setSliderValue(0);

        // Set status text
        this.msg = "Call by " + player.getUsername();

        // Update the pot
        this.pot += tmpBet;

        // Update playBtn
        //playBtn.setText("PLAY");

        // Reset values for bet and newBet
        this.bet = 0;
        this.newBet = 0;
        // Reset the user bet
        player.setBet(0);

        // Update status label (this.msg --> will be the text)
        //notifyObservers();

        // Increase playCounter
        playCounter++;
    }

    /**
     * If the user checked then update variables in the game.
     * @param player the current active player
     */
    public void check(Player player){
        System.out.println("CHECK from:" + player.getUsername());

        // Set status text
        this.msg = "Check by " + player.getUsername();

        this.newBet = 0;
        this.bet = 0;

        // Increase playCounter
        playCounter++;
        //notifyObservers();
    }


    public String getMsg(){
        return this.msg;
    }

    /**
     * If the user went all in the bet updates variables in the game.
     * and user balance = zero
     * @param player the current active player
     */
    public void allIn(Player player){
        // CHECK IF ITS ALL IN,
        // ELSE TELL THE USER THAT THIS IS NOT ACCEPTED
        System.out.println("Could this be all in??? " + player.getUsername());

        // The user is broke
        double balance = player.getBalance();
        // Update user
        player.debitBalance(balance);
        player.setBet(balance);

        // Set status text
        this.msg = "ALL IN " + player.getUsername();

        // Update the pot
        this.pot += balance;

        // Update status label (this.msg --> will be the text)
        //notifyObservers();

        // Increase playCounter
        playCounter++;

        // Reset all bet
        this.bet = 0;
        this.newBet = 0;

        System.out.println(" ALL IN " + player.getUsername() + " newBet: " + newBet + " bet: " + bet);
    }

    /**
     * Check if the player can bet
     * @param player the current ative player in the game
     * @return true if the player balance > 0
     */
    public boolean canBet(Player player){
        boolean canBet = false;
        if(player.getBalance() >= this.bet || player.getBalance() > 0){
            canBet = true;
        }

        return  canBet;
    }


    /**
     * Gets the current active user
     * @return return the player object
     */
    public Player getActivePlayer(){
        return this.players.get(activeUser);
    }


    /**
     * Count all active players in the game
     * @return quantity of active players
     */
    public int getActivePlayers() {
        int activePlayers = 0;
        for (Player player : players) {
            if (player.isActive()) {
                activePlayers++;
            }
        }

        return activePlayers;
    }

    /**
     * Check if we only have one active player
     * @return true if only one player is active
     */
    public boolean oneActivePlayer() {
        boolean onePlayer = false;
        if (getActivePlayers() == 1) {
            onePlayer = true;
        }

        return onePlayer;
    }

    /**
     * Check if we could find next activePlayer
     * @return true if player was found
     */
    public boolean setActivePlayer() {
        boolean foundUser = false;
        for (int i = this.activeUser; i < players.size(); i++) {
            Player player = players.get(i);
            // Check if player still in game
            if (player.isActive()) {
                this.activeUser = i;
                System.out.println("this.activeUser: " + this.activeUser + " " + player.getUsername());
                //
                foundUser = true;
                // Found user, break the loop
                break;
            }
        }

        return foundUser;
    }



    /**
     * Get all cheap object for the user
     * @param playerId for which player
     * @return list of all chips
     */
    public List<Chip> getPlayerChips(int playerId) {
        int blackCounter = 0;
        int redCounter = 0;
        int blueCounter = 0;
        int greenCounter = 0;
        int whiteCounter = 0;
        int addY = 0;

        List<Chip> chips = new ArrayList<>();
        // Get player
        Player p = getPlayer(playerId);
        //System.out.println("[CHIPS} Player ID" + playerId );
        for (int j = 0; j < p.getChips().size(); j++) {
            Chip chip = p.getChip(j);
            for (ChipLayout cl : ChipLayout.values()) {
                if (playerId == cl.getUserId()) {

                    if (chip.getChipValue() == Chip_.BLACK.getValue()) {
                        blackCounter++;
                        addY = blackCounter;
                    } else if (chip.getChipValue() == Chip_.RED.getValue()) {
                        redCounter++;
                        addY = redCounter;
                    } else if (chip.getChipValue() == Chip_.BLUE.getValue()) {
                        blueCounter++;
                        addY = blueCounter;
                    } else if (chip.getChipValue() == Chip_.GREEN.getValue()) {
                        greenCounter++;
                        addY = greenCounter;
                    } else if (chip.getChipValue() == Chip_.WHITE.getValue()) {
                        whiteCounter++;
                        addY = whiteCounter;
                    }

                    if (chip.getChipValue() == cl.getChipValue()) {
                        chip.getImageView().setX(cl.getX());
                        chip.getImageView().setY(cl.getY() - addY - 50);
                    }

                }
            }

            // Set x,y for layout
            for (UserLayout ul : UserLayout.values()) {
                if (playerId == ul.getUserId()) {
                    chip.getImageView().setLayoutX(ul.getLayoutX());
                    chip.getImageView().setLayoutY(ul.getLayoutY());
                }
            }
            // Add to list
            chips.add(chip);
        }
        // Return the list
        return chips;
    }


    /**
     * Get table cards
     * @return list of labels
     */
    public List<Card> getTableCards(){
        return this.tableCards;
    }


    /**
     * Found at: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     *
     * @param value The value that we want to round
     * @param places Max decimals of the returned value
     * @return the value
     */
    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}