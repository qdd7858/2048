package sample;

import javafx.application.Application;
import javafx.stage.Stage;
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
