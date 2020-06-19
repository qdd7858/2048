package sample;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Board;
import model.Game;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Game game = new Game();
        View view = new View(stage);
        Controller controller = new Controller(game, view);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
