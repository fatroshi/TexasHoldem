import Dealer.Card;
import Dealer.Chip;
import Dealer.Chip_;
import Layout.ButtonLayout;
import Layout.CardLayout;
import Layout.ChipLayout;
import Layout.UserLayout;
import Poker.Picture;
import Poker.*;
import Poker.Table_;
import User.Hand;
import User.Player;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Created by Farhad on 14/10/15.
 */
public class Controller {
    // Create game
    private Poker game;
    // Place holder for items
    private Pane root;

    public Controller(){
        game = new Poker();
        root = new Pane();
    }

    public Pane getRootPane(){
        return this.root;
    }

    public Poker getGame(){
        return  this.game;
    }

    public void addToPane(Picture o,Pane pane){
        // Add each child to pane
        pane.getChildren().add(o.getImageView());
    }

    public void createPlayers(Pane root){
        game.addPlayer("Lawen", 1000);
        game.addPlayer("Farhad", 2130);
        game.addPlayer("Felicia", 4213);
        game.addPlayer("Elise", 4219);

        getUserInfo(root);
        getUserChips(root);
    }

    public void getUserInfo(Pane root){

        Rectangle r;
        Label username, balance;
        for (int i = 0; i < game.getPlayers().size(); i++) {
            r = new Rectangle();
            r.setFill(Color.BLACK);
            r.setStroke(Color.DARKGRAY);

            // Get username
            String user = game.getPlayer(i).getUsername();
            username = new Label(user);
            // Get balance
            String strBalance = "$ " + String.valueOf(game.getPlayer(i).getBalance());
            balance = new Label(strBalance);
            // Get your x,y layout
            for (Table_ t: Table_.values()){
                if(i == t.getUserId()){
                    r.setX(t.getXlayout() - 35);
                    r.setY(t.getYlayout() + 90);

                    // x,y for label
                    // username
                    username.setLayoutX(t.getXlayout() - 20);
                    username.setLayoutY(t.getYlayout() + 100);
                    username.setTextFill(Color.LIGHTGRAY);
                    username.setFont(Font.font(18));
                    // balance
                    balance.setLayoutX(t.getXlayout() - 20);
                    balance.setLayoutY(t.getYlayout() + 140);
                    balance.setTextFill(Color.GREEN);
                }
            }
            r.setWidth(140);
            r.setHeight(80);
            r.setArcWidth(20);
            r.setArcHeight(20);
            //System.out.println(username);
            //System.out.println(balance);

            root.getChildren().addAll(r,username, balance);
        }
    }

    public void getUserChips(Pane root){
        int blackCounter = 0;
        int redCounter = 0;
        int blueCounter = 0;
        int greenCounter = 0;
        int whiteCounter = 0;
        int addY=0;
        for (int playerId = 0; playerId < game.getPlayers().size(); playerId++) {
            Player p = game.getPlayer(playerId);
            //System.out.println("[CHIPS} Player ID" + playerId );
            for (int j = 0; j < p.getChips().size(); j++) {
                Chip chip = p.getChip(j);
                for (ChipLayout cl: ChipLayout.values()){
                    if(playerId == cl.getUserId()) {

                        if(chip.getChipValue() == Chip_.BLACK.getValue()){
                            blackCounter++;
                            addY = blackCounter;
                        }else if(chip.getChipValue() == Chip_.RED.getValue()){
                            redCounter++;
                            addY = redCounter;
                        }else if(chip.getChipValue() == Chip_.BLUE.getValue()){
                            blueCounter++;
                            addY = blueCounter;
                        }else if(chip.getChipValue() == Chip_.GREEN.getValue()){
                            greenCounter++;
                            addY = greenCounter;
                        }else if(chip.getChipValue() == Chip_.WHITE.getValue()){
                            whiteCounter++;
                            addY = whiteCounter;
                        }

                        if (chip.getChipValue() == cl.getChipValue()) {
                            chip.getImageView().setX(cl.getX());
                            chip.getImageView().setY(cl.getY() - addY -50);
                        }


                    }
                }

                // Set x,y for layout
                for (UserLayout ul: UserLayout.values()){
                    if( playerId == ul.getUserId()){
                        chip.getImageView().setLayoutX(ul.getLayoutX());
                        chip.getImageView().setLayoutY(ul.getLayoutY());
                    }
                }

                // Add to scene
                root.getChildren().add(chip.getImageView());
            }
            // Reset counters
            blackCounter    = 0;
            redCounter      = 0;
            blueCounter     = 0;
            greenCounter    = 0;
            whiteCounter    = 0;
        }
    }

    public void getFirstTwoCards(){
        for (int playerId = 0; playerId < game.getPlayers().size(); playerId++) {
            Hand hand = game.getPlayer(playerId).getHand();
            for (int j = 0; j < hand.getNoOfCards(); j++) {
                Card card = hand.getCard(j);
                for (CardLayout cl: CardLayout.values()){
                    // Set x,y for inside layout
                    if( j == cl.getCardId()){
                        card.getImageView().setX(cl.getX());
                        card.getImageView().setY(cl.getY());
                        card.getImageView().setRotate(cl.getRotation());
                        // Toggle card when clicked
                        card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));
                    }
                }

                // Set x,y for layout
                for (UserLayout ul: UserLayout.values()){
                    if( playerId == ul.getUserId()){
                        card.getImageView().setLayoutX(ul.getLayoutX() + 200);
                        card.getImageView().setLayoutY(ul.getLayoutY());
                        fadeIn(card);
                    }
                }

                // Add to scene
                this.root.getChildren().add(card.getImageView());
            }

        }
    }

    public Pane getTableCards(int from, int to,Pane root){

        from--;

        Pane pane = new Pane();

        for (int cardID = from; cardID < to; cardID++) {
            Card card = Poker.tableCards.get(cardID);

            //System.out.println(Poker.tableCards.size() + " i :" + cardID);
            //System.out.println(card.getRank());


            for(Table_ t: Table_.values()){
                if(t.getCardId() == (cardID+2)){

                    card.getImageView().setX(150);                 // Set x
                    card.getImageView().setY(-100);
                    move(card,t.getX() * card.getImageView().getImage().getWidth()-150, t.getY()+100);

                    //card.getImageView().setX(t.getX() * card.getImageView().getImage().getWidth());                 // Set x
                    //card.getImageView().setY(t.getY());                                                             // Set y


                    card.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new CardClickHandler(card));

                }
            }

            card.getImageView().setLayoutX(240);
            card.getImageView().setLayoutY(230);

            addToPane(card, root);
        }

        return  pane;
    }

    public void getUserBtn(Pane root){
        Button btn;

        for (ButtonLayout b: ButtonLayout.values()){
            btn = new Button(b.name());

            // Style btn
            String css = "-fx-stroke: #4e5b65; " +
                    "-fx-background-color:" + b.getColor() +";" +
                    "-fx-stroke: green;"
                    ;
            btn.setStyle(css);
            btn.setTextFill(Color.WHITESMOKE);
            // Set size
            btn.setMinWidth(90);
            btn.setMinHeight(40);

            // Set x,y layout
            btn.setLayoutX(b.getX());
            btn.setLayoutY(b.getY());

            // Assign EventHandler
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new BtnClickHandler(b,this,btn));
            root.getChildren().add(btn);
        }
    }

    public void addPaneToPane(Pane p, Pane root){
        root.getChildren().add(p);
    }

    public void fadeIn(Picture p){
        FadeTransition ft = new FadeTransition(Duration.millis(1000), p.getImageView());
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        //ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    public void fadeOut(Picture p){
        FadeTransition ft = new FadeTransition(Duration.millis(500), p.getImageView());
        ft.setFromValue(0.0);
        ft.setToValue(1.);
        ft.setCycleCount(Timeline.INDEFINITE);
        //ft.setAutoReverse(true);
        ft.play();
    }

    public void move(Picture p, double x, double y){
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), p.getImageView());
        tt.setByX(x);
        tt.setByY(y);
        //tt.setCycleCount(4);
        //tt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (
                new PauseTransition(Duration.millis(1000)), // wait a second
                tt);

        seqTransition.play();
    }

    public void rotation(Picture p){
        RotateTransition rt = new RotateTransition(Duration.millis(3000), p.getImageView());
        rt.setByAngle(180);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);
        SequentialTransition seqTransition = new SequentialTransition (
                new PauseTransition(Duration.millis(1000)), // wait a second
                rt
        );
        seqTransition.play();
    }

    public void getTurn(){

    }

}
