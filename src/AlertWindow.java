import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Created by Farhad on 06/10/15.
 * This class displays an AlertWindow with customizable title and message body
 * The method displayBox is static and therefore can be called directly from this class.
 * Inspiration from youtube tutorial: https://youtu.be/SpL3EToqaXA?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
 */
public class AlertWindow {
    public static void show(String windowTitle, String msg){
        Stage w = new Stage();
        // Set title
        w.setTitle(windowTitle);
        // Set window dim.
        w.setMinWidth(300);
        w.setMinHeight(450);
        //
        w.initModality(Modality.APPLICATION_MODAL);
        // Window body text
        Label bodyText = new Label();
        bodyText.setText(msg);
        // Close btn
        Button btn = new Button("Close");
        btn.setOnAction(event -> w.close());

        //Create layout
        VBox layout = new VBox(50);
        layout.getChildren().addAll(bodyText,btn);

        // Create scene
        Scene scene = new Scene(layout);
        w.setScene(scene);
        w.showAndWait();

    }
}
