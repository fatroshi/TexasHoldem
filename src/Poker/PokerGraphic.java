package Poker;

import Layout.ButtonLayout;
import User.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad Atroshi on 15/10/15.
 */
public class PokerGraphic {

    private Slider slider;

    // Players
    private List<Rectangle> playersBG;
    private List<Label> balanceLabels;
    private List<Label> usernameLabels;
    private List<Button> buttons;

    public PokerGraphic() {
        createSlider();
        // Background for players
        playersBG = new ArrayList<>();
        // Players username
        usernameLabels = new ArrayList<>();
        // Players balance
        balanceLabels = new ArrayList<>();
        // Check, Bet... buttons
        buttons = new ArrayList<>();
        // Create btn, With no event handlers attached
        this.createButtons();

    }

    /**
     * Found at: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void createSlider() {
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);
        slider.setShowTickLabels(true);
        //slider.setShowTickMarks(true);
        //slider.setMajorTickUnit(50);
        //slider.setMinorTickCount(5);
        //slider.setBlockIncrement(10);
        slider.setMinWidth(520);
        slider.setLayoutX(240);
        slider.setLayoutY(510);
        slider.setTooltip(new Tooltip("Check or Raise"));

        slider.setStyle("-fx-color: RED;");
        slider.setOnMouseClicked(event -> sliderHandler());


        this.slider = slider;
    }

    public void sliderHandler() {
        double value = round(slider.getValue(), 0);
        String strValue = String.valueOf(value);
        usernameLabels.get(0).setText(strValue);
    }

    public void setSliderMax(double max) {
        this.slider.setMax(max);
    }

    public Slider getSlider() {
        return this.slider;
    }

    public void updateSlider(Player player, double currentBet, double currentRaise) {
        this.slider.setMin(currentBet);
        this.slider.setValue(currentRaise);
        this.slider.setMax(player.getBalance());

    }

    public void addPlayerBG(int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create ractangle
        Rectangle r = new Rectangle();
        // Properties
        r.setFill(Color.BLACK);
        r.setStroke(Color.DARKGRAY);
        r.setWidth(140);
        r.setHeight(50);
        r.setArcWidth(10);
        r.setArcHeight(10);

        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                r.setX(t.getXlayout() - 35);
                r.setY(t.getYlayout() + 90);
            }
        }
        // Add to array
        this.playersBG.add(r);
    }

    public Rectangle getPlayerBG(int index) {
        return playersBG.get(index);
    }

    public List<Rectangle> getPlayersBG() {
        return this.playersBG;
    }

    public void setUserBG(int index, Color color) {
        // Change color of player bg
        getPlayerBG(index).setFill(color);
        balanceLabels.get(index).setTextFill(Color.WHITESMOKE);

        for (int i = 0; i < getPlayersBG().size(); i++) {
            if (i != index) {
                getPlayerBG(i).setFill(Color.BLACK);
                balanceLabels.get(i).setTextFill(Color.GREEN);
            }
        }
    }

    public void addBalanceLabel(Player player, int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create Label for balance
        Label balance = new Label("$ " + String.valueOf(player.getBalance()));
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                // balance
                balance.setLayoutX(t.getXlayout() - 20);
                balance.setLayoutY(t.getYlayout() + 118);
                balance.setTextFill(Color.GREEN);
            }
        }
        // Add to array
        this.balanceLabels.add(balance);
    }

    public Label getBalanceLabel(int index) {
        return this.balanceLabels.get(index);
    }

    public void addUsername(Player player, int playerIndex) {
        // Index of the player in array
        int id = playerIndex;
        // Create label for username
        Label username = new Label(player.getUsername());
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                username.setLayoutX(t.getXlayout() - 20);
                username.setLayoutY(t.getYlayout() + 95);
                username.setTextFill(Color.LIGHTGRAY);
                username.setFont(Font.font(18));
            }
        }
        this.usernameLabels.add(username);

    }

    public Label getUsernameLabel(int index) {
        return usernameLabels.get(index);
    }

    public void createButtons() {
        Button btn;
        for (ButtonLayout b : ButtonLayout.values()) {
            btn = new Button(b.name());
            // Style btn
            String css = "-fx-stroke: #4e5b65; " +
                    "-fx-background-color:" + b.getColor() + ";" +
                    "-fx-stroke: green;";
            btn.setStyle(css);
            btn.setTextFill(Color.WHITESMOKE);
            // Set size
            btn.setMinWidth(90);
            btn.setMinHeight(40);

            // Set x,y layout
            btn.setLayoutX(b.getX());
            btn.setLayoutY(b.getY());

            if (btn.getText() != "START") {
                btn.setVisible(false);
            } else {
                // Start btn
                btn.setMinWidth(200);
                btn.setMinHeight(70);
                btn.setFont(Font.font(25));
                // Set x,y layout
                //btn.setLayoutX(380);
                //btn.setLayoutY(530);
            }

            // Assign EventHandler
            //btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PlayerButtonsHandler(b,this,btn));
            // Add to list
            buttons.add(btn);
        }

    }

    public void showGameButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText() != "START") {
                buttons.get(i).setVisible(true);
            }
        }
    }

    public List<Button> getButtons() {
        return this.buttons;
    }

    public Button getButton(int index) {
        return this.buttons.get(index);
    }

}
