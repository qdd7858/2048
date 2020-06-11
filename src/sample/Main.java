package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Game;

public class Main extends Application {

    private int row = 4;
    private int col = 4;
    private Game board;
    private Label[] labels_list;
    private Label score;
    private Label status;
    private Image mole = new Image(getClass().getResourceAsStream("mole.png"));
    private Image hole = new Image(getClass().getResourceAsStream("hole.png"));

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        GridPane gridPane = new GridPane();
        for (int i =0; i < 16; i++) {
            Label label = new Label();
            label.setText("0");
            label.setGraphic(new ImageView(hole));
            gridPane.getChildren().add(label);
        }
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
