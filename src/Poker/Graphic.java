package Poker;

import Layout.ButtonLayout;
import User.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
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
    private Label potLabel;
    private Label usernameLabel;
    private List<Label> blanceLabels = new ArrayList<>();

    public Graphic(){
        slider = this.createSlider(0,100,0);           // Slider fot betting (min,max,currentSliderValue)
        sliderLabel = createLabel(240, 465, 24);        // Label for the slider
        statusLabel = createLabel(400, 575, 24);        // Label for showing current status: bet/call/raise
        potLabel    = createLabel(445,390,24);
    }

    public Slider getSlider() {
        return slider;
    }


    public Label getPotLabel() {
        return potLabel;
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


    public Label getSliderLabel(){
       return this.sliderLabel;
    }

    public Label getStatusLabel(){
        return this.statusLabel;
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
        this.usernameLabel = new Label(player.getUsername());
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                usernameLabel.setLayoutX(t.getXlayout() - 20);
                usernameLabel.setLayoutY(t.getYlayout() + 95);
                usernameLabel.setTextFill(Color.LIGHTGRAY);
                usernameLabel.setFont(Font.font(18));
            }
        }


        return this.usernameLabel;

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
        Label label = new Label("$ " + String.valueOf(player.getBalance()));
        // Set x,y for label
        for (Table_ t : Table_.values()) {
            if (id == t.getUserId()) {
                // balance
                label.setLayoutX(t.getXlayout() - 20);
                label.setLayoutY(t.getYlayout() + 118);
                label.setTextFill(Color.GREEN);
            }
        }
        // Add to array
        this.blanceLabels.add(label);
        return label;
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



    public Button createButton(String name){
        Button btn = new Button();
        for (ButtonLayout b : ButtonLayout.values()) {

            if(b.name() == name) {
                btn.setText(b.name());
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
            }
        }
        return btn;
    }



    @Override
    public void updateSlider(double currentBet, double userBalance, String message) {
        // Update the slider
        //System.out.println("Slider got updated by the update method in graphic");
        this.slider.setValue(currentBet);
        this.slider.setMax(userBalance);

        double value = round(slider.getValue(), 0);
        String strValue = String.valueOf(value);
        // Slider label
        sliderLabel.setText("$ " + strValue);

        // Status label
        this.statusLabel.setText(message);
    }

    @Override
    public void decreaseUserBalance(int activeUser, double userBalance) {
            String strBalance = String.valueOf(userBalance);
            blanceLabels.get(activeUser).setText(strBalance);
    }

    @Override
    public void updateTablePotLabel(double pot) {
        pot = round(pot, 0);

        String strPot = "Pot $ " + String.valueOf(pot);

        this.potLabel.setTextFill(Color.LIGHTGREEN);

        this.potLabel.setText(strPot);
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

}
