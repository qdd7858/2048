package ViewAndControl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** GUI which is a pop-up message which notifies the user to choose yes or no
 *
 * @author Quan Do*/
public class ConfirmBox {

    private static boolean answer;

    public static boolean display (String title, String message){
        Stage stage = new Stage();

        // Block event to other windows
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinHeight(150);
        Label label = new Label();
        label.setPadding(new Insets(20,20,20,20));
        label.setText(message);

        // yes or no buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        // set function of action when is clicked
        yesButton.setOnAction(event -> {
            answer = true;
            stage.close();
        });
        noButton.setOnAction(event -> {
            answer = false;
            stage.close();
        });

        // add the message and boxes and display them
        VBox layout = new VBox(10);
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttons);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();

        return answer;
    }
}
