/**
 * Stuff to fix
 * 5. ******* Make your life easier and use subject observer.... fix this!!!
 * 1. Player BG should change back for other when checking
 * 2. Slider should show current bet
 * 3. Create 2 buttons, play and fold... the slider should decide if it was raise,call,bet
 * 4. Create another Scene ??? or at least create a separated button for start!!
 *    And create separated  buttonHandlers
 *
 */



package Poker;

import Dealer.*;
import Layout.CardLayout;
import Layout.ChipLayout;
import Layout.UserLayout;
import User.Hand;
import User.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Farhad on 17/10/15.
 */
public class Table {

    private List<Rectangle> playersBg               = new ArrayList<>(); // Background for player profile
    private List<Label> balanceLabels               = new ArrayList<>(); // Label for showing balance for the player
    private List<Label> usernameLabels              = new ArrayList<>(); // Label for showing username of the player
    private List<Button> tableButtons               = new ArrayList<>(); // Game buttons, PLAY, FOLD
    
    private Deck deck                               = new Deck();        // Dealer.Hand (52 cards)
    private List<Card> tableCards                   = new ArrayList<>(); // Store all table cards, total of 5
    
    private List<Player> players                    = new ArrayList<>(); // List for storing the players
    private Map<Integer[], Player> playersBestHand  = new HashMap<>();   // List for storing player and the players best poker hand

    // Variables used by all players
    private int playerBig;            // Not sure if this will be used!!! Update later!
    private int activeUser;           // Id of current active player (Active --> Still in the game)
    private int oldActiveUser;        // Id of previous active player
    private double bet;               // Current bet in game
    private double newBet;            // User to check if bet has been changed
    private double raise;             // The raise value 
    private double pot;               // Total current pot in the game

    private int rounds;               // Counts the poker rounds, used for showing cards and when the game in done.
    private int playCounter;          // increases for call,bet. Sets to 0 for raise
    
    // Graphics
    private Graphic graphic   = new Graphic();
    private Slider slider     = graphic.createSlider(0,100,10);       // Slider for player to make bet, call, raise
    private Label sliderLabel = graphic.createLabel(240,465,24);
    private Label statusLabel = graphic.createLabel(240,435,24);    // Will be shown when; check,raise,call, all in, fold
    
    public Table(){
        tableButtons = graphic.createTableButtons();
        
    }

    /**
     * Sets new bet from the slider
     * @param setBet
     */
    public void setBet(double setBet) {
        this.newBet = setBet;
    }

    /**
     * Check game round,
     * This will be used for knowing when to show cards
     */
    public int round(){
        // 1. How many active players
        // 2. if the counter = (acive players) - 1, one round
        // When to reset the counter ?
        // well we will se that!

        if(playCounter == getActivePlayers()){
            System.out.println("New round");
            // one fill round
            playCounter = 0;
            // Increase rounds
            rounds++;
            if(this.rounds == 1){       // First round
                // Show 1-3 cards
                System.out.println(" First rount ");
            }else if(this.rounds == 2){ // Second round
                // 3-4
                System.out.println(" Second round");
            }else if(this.rounds == 3){ // Third round
                // 4-5
                System.out.println(" Third round");
            }else if (this.rounds == 4){
                // CHECK WHO ONE
                System.out.println(" 4 round");

                // Check who the winner is
                //getWinner();
            }
        }

        return rounds;
    }


    public Player getWinner() {
        Map<Integer[], Player> bestHands = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            Hand hand = players.get(i).getHand();
            bestHands.put(bestHand(hand), players.get(i));
        }
        // Store hand rank
        Integer[] topRank = {0, 0, 0, 0};

        int sameHandCounter = 0;
        int winner = -1;
        for (Map.Entry<Integer[], Player> entry : bestHands.entrySet()) {
            Integer[] rank = entry.getKey();
            Player player = entry.getValue();

            if(rank[0] > topRank[0]){
                winner = players.indexOf(player);
                System.arraycopy(rank, 0, topRank, 0, 4);
            }else if(rank[0] == topRank[0] && rank[1] > topRank[1]){
                winner = players.indexOf(player);
                System.arraycopy(rank, 0, topRank, 0, 4);
            }else if(rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] > topRank[2]){
                winner = players.indexOf(player);
                System.arraycopy(rank, 0, topRank, 0, 4);
            }else if(rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] == topRank[2] && rank[3] > topRank[3]){
                winner = players.indexOf(player);
                System.arraycopy(rank, 0, topRank, 0, 4);
            }else if(rank[0] == topRank[0] && rank[1] == topRank[1] && rank[2] == topRank[2] && rank[3] == topRank[3]){
                // Player has same hand
                // Do something
                // Store id of the player... or the object...
                // split the cash
            }
        }
        return players.get(winner);
    }



    public void raise() {
        AlertWindow.show(" Raise", " Raise: " + players.get(this.activeUser).getUsername(), 200, 100);
        //System.out.println(players.get(this.activeUser) + ": Raise ");

        if (oneActivePlayer()) {
            //We got a winner
            setWinnerBG();
        } else {
            // Set next user
            nextUser();
        }

    }

    public void bet() {
        // Check if player can bet

        if (oneActivePlayer()) {
            //We got a winner
            setWinnerBG();
        } else {
            // Set next user
            nextUser();
        }
    }

    public void call() {
        if (oneActivePlayer()) {
            //We got a winner
            setWinnerBG();
        } else {
            // Set next user
            nextUser();
        }
    }

    public void fold() {
        if (oneActivePlayer()) {
            //We got a winner

        } else {
            //Set active = false
            removePlayerInGame();
            //Do somthing with user cards
            Card c1 = players.get(this.activeUser).getHand().getCard(0);
            Card c2 = players.get(this.activeUser).getHand().getCard(1);

            Animation.fadeOut(c1);
            Animation.fadeOut(c2);
            // Set next user
            nextUser();
        }
    }

    public void check() {
        if (oneActivePlayer()) {
            //We got a winner
            setWinnerBG();
        } else {
            // Set next user
            nextUser();
        }
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
                        card.setImageFrontView();
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
     * @param username
     * @param balance
     * @return
     */
    public Player addPlayer(String username, double balance) {
        Player player = new Player(username, balance);
        players.add(player);

        // Player Index in list players
        int playerIndex = players.indexOf(player);

        // Create graphics for the user
        // Add username to usernameLabels
        Label usernameLabel = graphic.createUsernameLabel(player, playerIndex);
        this.usernameLabels.add(usernameLabel);
        // Add balance to balanceLabels
        Label balandeLabel = graphic.createBalanceLabel(player,playerIndex);
        this.balanceLabels.add(balandeLabel);
        // Add bg to bgPlayers
        Rectangle playerBg = graphic.createPlayerBg(playerIndex);
        this.playersBg.add(playerBg);
        
        return player;
    }

    /**
     * Returns the graphic object
     * @return
     */
    public Graphic getGraphic() {
        return this.graphic;
    }

    /**
     * Removes player from the game by calling the players method player.active(false);
     */
    public void removePlayerInGame() {
        getPlayer(this.activeUser).active(false);
        System.out.println(players.get(this.activeUser).getUsername() + " Removed, ID: " + this.activeUser);
    }

    /**
     * Deals tho cards for each player
     */
    public void dealTwoCards() {
        for (int j = 0; j < this.players.size(); j++) {
            // Deal 2 cards for each player
            for (int i = 0; i < 2; i++) {
                players.get(j).getHand().addCard(deck.dealCard());
            }

        }
    }

    /**
     * Deals cards to table and players
     * this is used for the 4 table cards not the first 2 cards for players'
     * Setting for card, show back/front
     * @param quantityOfDeals
     */
    public void dealCards(int quantityOfDeals) {
        // Deal same 5 cards to each player
        for (int i = 0; i < quantityOfDeals; i++) {
            Card card = deck.dealCard();
            card.setImageFrontView();
            tableCards.add(card);

            for (int j = 0; j < players.size(); j++) {
                players.get(j).getHand().addCard(card);
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
     * @param index
     * @return Player
     */
    public Player getPlayer(int index) {
        return this.players.get(index);
    }

    /**
     * Returns the best hand
     * element [0] --> Refers to Poker rank 1-10. 1 = Highcard ... 10 = Royal Flush
     * element [1] --> Refers to Highest Pair, or Tree of a kind
     * element [2] --> Refers to pair
     * element [3] --> Refers to highest card in the hand
     * @param hand
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
     * @param hand
     * @return
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
     * @param hand
     * @return
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
     * @param rqCards
     * @return
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
     * @param rsCards
     * @return
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
                //print("Straight, highest card: " + rank);
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

    public boolean descByOne(int a, int b) {
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
            updateGame();

        } else {
            // Reset
            this.activeUser = 0;
            // Find and set next active player
            // and update game
            updateGame();

        }
    }

    /**
     * Game control,
     * updates graphic elements
     * (Explain more when done)
     */
    public void updateGame(){
        if (setActivePlayer()) {
            // Check round
            if(this.rounds < 5) {
                this.round();
                //Check if it was a raise
                String oldPlayer = players.get(this.oldActiveUser).getUsername();
                String msg = "";

                if (newBet > bet) {
                    bet = newBet;
                    // Raise
                    msg = oldPlayer + " RAISE";
                    // reset play counter
                    playCounter = 1;
                } else if (newBet == 0) {
                    // FOLD
                    playCounter++;
                } else if (newBet < bet) {
                    // All in
                    //msg = oldPlayer + " All IN";
                    playCounter++;
                } else {
                    // Check
                    msg = oldPlayer + " CHECK";
                    playCounter++;
                }

                // Display previous action from user
                graphic.updateLabel(this.statusLabel,msg,Color.WHITESMOKE);

                // For current active user

                // Show current selected user
                graphic.updatePlayerBg(this.playersBg.get(this.activeUser), Color.DARKGREEN);
                // Get player info.
                double balance = players.get(this.activeUser).getBalance();
                graphic.updateSlider(this.slider, this.sliderLabel, balance,this.activeUser, bet, raise);
            }

        }
    }

    /**
     * Count all active players in the game
     * @return quantity of active players
     */
    public int getActivePlayers() {
        int activePlayers = 0;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
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
     * Change bg for the winner/s
     */
    public void setWinnerBG() {
        // Change color of player bg
        this.playersBg.get(this.activeUser).setFill(Color.GOLD);
        this.usernameLabels.get(this.activeUser).setTextFill(Color.BLACK);
        this.balanceLabels.get(this.activeUser).setTextFill(Color.BLACK);
    }

    /**
     * Get cheap object for the user
     * @param playerId
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

    public List<Rectangle> getPlayersBg(){
        return this.playersBg;
    }

    public List<Label> getBalanceLabels(){
        return this.balanceLabels;
    }

    public List<Label> getUsernameLabels(){
        return this.usernameLabels;
    }

    public List<Card> getTableCards(){
        return this.tableCards;
    }

    public  List<Button> getTableButtons(){
        return this.tableButtons;
    }

    public Slider getSlider(){
        return this.slider;
    }

    public Label getStatusLabel(){
        return this.statusLabel;
    }

    public Label getSliderLabel(){
        return this.sliderLabel;
    }

    public void showTableButtons(){
        graphic.showTableButtons(this.tableButtons);
    }
}

    

