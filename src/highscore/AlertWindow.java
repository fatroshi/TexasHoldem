/**
 * Created by Farhad Atroshi on 06/10/15.
 * This class displays an AlertWindow with customizable title and message body
 * The method displayBox is static and therefore can be called directly from this class.
 * Inspiration from youtube tutorial: https://youtu.be/SpL3EToqaXA?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
 */

package highscore;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertWindow {
    public static void show(String windowTitle, String msg, double width, double height) {
        Stage w = new Stage();
        // Set title
        w.setTitle(windowTitle);
        // Set window dim.
        w.setMinWidth(width);
        w.setMinHeight(height);
        //
        w.initModality(Modality.APPLICATION_MODAL);
        // Window body text
        Label bodyText = new Label();
        bodyText.setFont(Font.font(17));
        bodyText.setStyle("-fx-text-alignment: center");
        bodyText.setText(msg);
        // Close btn
        Button btn = new Button("Close");
        btn.setOnAction(event -> w.close());

        //Create layout
        VBox layout = new VBox(50);
        //layout.setStyle("-fx-background-color: #8fbc8f;");
        layout.getChildren().addAll(bodyText, btn);

        // Create scene
        Scene scene = new Scene(layout,300,200);
        w.setScene(scene);
        // w.initStyle(StageStyle.TRANSPARENT);
        w.showAndWait();
    }
}
