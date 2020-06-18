package sample;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Board;
import model.Game;

public class View {
    private Stage stage;
    private Scene scene;
    private GridPane layout;
    private TextField[][] nodeList;

    public View(Stage stage){
        stage.setTitle("2048");

        // Construct the gui
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 470, 470);

        // Added to private variable
        this.stage = stage;
        this.scene = scene;
        this.layout = layout;
        this.nodeList = new TextField[Board.ROW_INDEX][Board.COL_INDEX];

        // Adding text field to the gui and the nodeList for easier tracking
        layout.setPadding(new Insets(20,20,20,20));
        layout.setHgap(10);
        layout.setVgap(10);

        // Add the scene to stage and display it
        stage.setScene(scene);
        stage.show();
    }

    public TextField[][] getNodeList(){
        return nodeList;
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getLayout() {
        return layout;
    }

    public Scene getScene() {
        return scene;
    }
}
