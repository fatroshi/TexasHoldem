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
 * Created by Farhad on 17/10/15.
 */

public class Graphic implements Observer{



    private Slider slider;
    private Label sliderLabel;
    private Label statusLabel;

    public Graphic(){
        slider = this.createSlider(10,100,50);      // Slider fot betting
        sliderLabel = new Label();                  // Label for the slider
        statusLabel = new Label();                  // Label for showing current status: bet/call/raise
    }

    public Slider getSlider() {
        return slider;
    }

    /**
     * Create a slider set min,max and start value
     * @param setMin
     * @param setMax
     * @param setValue
     * @return
     */
    public Slider createSlider(int setMin, int setMax, int setValue) {
        Slider slider = new Slider();
        slider.setMin(setMin);
        slider.setMax(setMax);
        slider.setValue(setValue);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(500);
        slider.setMinWidth(520);
        slider.setLayoutX(240);
        slider.setLayoutY(510);
        slider.setTooltip(new Tooltip("Check or Raise"));
        slider.setStyle("-fx-color: RED;");

        return slider;
    }

    /**
     * Update the slider
     * @param slider
     * @param playerBalance
     * @param playerId
     * @param currentBet
     * @param currentRaise
     */
    public double updateSlider(Slider slider, Label sliderLabel, double playerBalance, int playerId, double currentBet, double currentRaise) {
        slider.setOnMouseClicked(even -> new SliderHandler(slider,sliderLabel));
        slider.setMin(currentBet);
        slider.setValue(currentRaise);
        slider.setMax(playerBalance);

        return currentBet;
    }

    /**
     * Create label set x,y and fontsize
     * @param layoutX
     * @param layoutY
     * @param fontSize
     * @return the label
     */
    public Label createLabel(double layoutX, double layoutY, int fontSize){
        Label label = new Label();
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setFont(Font.font(fontSize));
        label.setTextFill(Color.WHITE);
        return label;
    }

    /**
     * Update label text
     * @param label
     * @param text
     */
    public void updateLabel(Label label, String text, Color color){
        label.setText(text);
        label.setTextFill(Color.WHITESMOKE);
    }

    /**
     * Create label for player, label text will be the username
     * Enum Table_ is used to set the layoutX, layoutY values.
     * @param player
     * @param playerIndex
     * @return
     */
    public Label createUsernameLabel(Player player, int playerIndex) {
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
        return username;

    }

    /**
     * Create label for player, label text will be the balance
     * Enum Table_ is used to set the layoutX, layoutY values.
     * @param player
     * @param playerIndex
     */
    public Label createBalanceLabel(Player player, int playerIndex) {
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
        return balance;
    }
    
    /**
     * Create background for user, and set layoutX, LayoutY
     * @param playerIndex
     * @return
     */
    public Rectangle createPlayerBg(int playerIndex) {
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

        // Find the correct x,y position for the player
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                r.setX(t.getXlayout() - 35);
                r.setY(t.getYlayout() + 90);
            }
        }
        // Add to array
        return r;
    }

    /**
     * Updade player background
     * @param rectangle
     * @param color
     */
    public void updatePlayerBg(Rectangle rectangle, Color color) {
        // Change color of player bg
        rectangle.setFill(color);
    }


    /** SHOUD FIND A BETTER SOLUTION
     * Return a list of buttons for the game
     * @return
     */
    public List<Button> createTableButtons() {
        List<Button> buttons = new ArrayList<>();

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
        return buttons;
    }

    /**
     * Set buttons visible property to true
     * @param buttons
     */
    public void showTableButtons(List<Button> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText() != "START") {
                buttons.get(i).setVisible(true);
            }
        }
    }


    @Override
    public void updateSlider(double currentBet, double userBalance) {
        this.slider.setValue(currentBet);
        this.slider.setMax(userBalance);
    }

}
