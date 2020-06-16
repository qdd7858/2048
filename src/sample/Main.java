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

    private Stage stage;
    private Scene scene;
    private GridPane layout;
    private Game game;
    private Board board;
    private TextField[][] nodeList;

    public void init(Stage stage) throws Exception{
        stage.setTitle("2048");
        Game game = new Game();
        Board board = game.getBoard();
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 470, 470);
        this.stage = stage;
        this.scene = scene;
        this.layout = layout;
        this.game = game;
        this.board = board;
        this.nodeList = new TextField[Board.ROW_INDEX][Board.COL_INDEX];
        layout.setPadding(new Insets(20,20,20,20));
        layout.setHgap(10);
        layout.setVgap(10);
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX; col++){
                TextField textField = new TextField();
                textField.setMinSize(100,100);
                textField.setText(Integer.toString(board.getValueAt(row, col)));
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-text-inner-color: white;");
                textField.setEditable(false);
                textField.setMouseTransparent(true);
                textField.setFocusTraversable(false);
                layout.add(textField, col, row);
                nodeList[row][col] = textField;
                setTextFieldColor(row,col);
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception{
        init(stage);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A){
                System.out.println("pressed A");
                game.setMovingStrategy(Game.LEFT);
            }
            if (e.getCode() == KeyCode.D){
                game.setMovingStrategy(Game.RIGHT);
            }
            if (e.getCode() == KeyCode.W){
                game.setMovingStrategy(Game.UP);
            }
            if (e.getCode() == KeyCode.S){
                game.setMovingStrategy(Game.DOWN);
            }
            game.moving(board);
            this.update();
        });
    }

    public void update(){
        game.randGenererate();
        for (int row = 0; row < Board.ROW_INDEX; row++) {
            for (int col = 0; col < Board.COL_INDEX; col++) {
                if (Integer.parseInt(nodeList[row][col].getCharacters().toString()) < board.getValueAt(row, col)){
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.03), nodeList[row][col]);
                    scaleTransition.setCycleCount(2);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.setToX(1.1);
                    scaleTransition.setToY(1.1);
                    scaleTransition.play();
                }
                nodeList[row][col].setText(Integer.toString(board.getValueAt(row, col)));
                setTextFieldColor(row,col);

            }
        }

    }

    public void setTextFieldColor(int row, int col){
        int hex = 0xff0000;
        Paint paint = paint = Paint.valueOf("ffffff");
        if (!(board.getValueAt(row, col)== 0)){
            for (int i = 1; i < 15; i++){
                if (board.getValueAt(row, col) == Math.pow(2, i)){
                    hex = hex - 0x002000 * i;
                    paint = Paint.valueOf(Integer.toString(hex, 16));
                }
            }
        }
        nodeList[row][col].setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
