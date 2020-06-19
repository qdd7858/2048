package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Board;
public class View {
    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    private TextField[][] textFields;
    private BorderPane borderPane;
    private TextField scoreField;

    public View(Stage stage){
        stage.setTitle("2048");

        // Construct the gui
        this.stage = stage;
        gridPane = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        scene = new Scene(borderPane, 470, 570);
        textFields = new TextField[Board.ROW_INDEX][Board.COL_INDEX];
        scoreField = new TextField();
        scoreField.setMinSize(50,100);
        scoreField.setText("Score \n 0");
        scoreField.setAlignment(Pos.CENTER);
        //scoreField.setStyle("-fx-text-inner-color: white;");
        scoreField.setEditable(false);
        scoreField.setMouseTransparent(true);
        scoreField.setFocusTraversable(false);
        borderPane.setTop(scoreField);

        // Add spacing between text fields
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add the scene to stage and display it
        stage.setScene(scene);
        stage.show();
    }

    public TextField[][] getTextFields(){
        return textFields;
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getScoreField() {
        return scoreField;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}