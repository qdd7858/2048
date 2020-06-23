package sample;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import model.Board;
import model.Game;

public class Controller{
    private Game game;
    private View view;

    public Controller(Game game, View view){
        this.game = game;
        this.view = view;

        setTextField();
        setKeyListener();
        setButtons();
    }

    public void setButtons(){
        view.getUndoButton().setOnAction(e -> {
            game.restore();
            updateView();
        });

        view.getNewGameButton().setOnAction(e -> {
            if (ConfirmBox.display("Warning", "Are you sure you want to play a new game? Your current game will be deleted.")){
                game = new Game();
                updateView();
            }
        });
    }

    public void setTextField(){
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX; col++){
                TextField textField = new TextField();
                textField.setMinSize(100,100);
                textField.setText(Integer.toString(game.getBoard().getValueAt(row, col)));
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-text-inner-color: white;");
                textField.setEditable(false);
                textField.setMouseTransparent(true);
                textField.setFocusTraversable(false);
                view.getGridPane().add(textField, col, row);
                view.getTextFields()[row][col] = textField;
            }
        }
        updateView();
    }

    public void setKeyListener(){
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT){
                game.setMovingStrategy(Game.LEFT);
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT){
                game.setMovingStrategy(Game.RIGHT);
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP){
                game.setMovingStrategy(Game.UP);
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN){
                game.setMovingStrategy(Game.DOWN);
            }
            game.update();
            this.updateView();
            if (game.isGameOver()){
                this.showGameOver();
            }
        });
    }

    public void updateView(){
        for (int row = 0; row < Board.ROW_INDEX; row++) {
            for (int col = 0; col < Board.COL_INDEX; col++) {
                if (Integer.parseInt(view.getTextFields()[row][col].getCharacters().toString()) < game.getBoard().getValueAt(row, col)){
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.03), view.getTextFields()[row][col]);
                    scaleTransition.setCycleCount(2);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.setToX(1.1);
                    scaleTransition.setToY(1.1);
                    scaleTransition.play();
                }
                view.getTextFields()[row][col].setText(Integer.toString(game.getBoard().getValueAt(row, col)));
                setTextFieldColor(row,col);
                view.getScoreField().setText("Score \n" + game.getScore());
            }
        }

    }

    public void setTextFieldColor(int row, int col){
        int hex = 0xff0000;
        Paint paint = paint = Paint.valueOf("ffffff");
        if (!(game.getBoard().getValueAt(row, col)== 0)){
            for (int i = 1; i < 15; i++){
                if (game.getBoard().getValueAt(row, col) == Math.pow(2, i)){
                    hex = hex - 0x002000 * i;
                    paint = Paint.valueOf(Integer.toString(hex, 16));
                }
            }
        }
        view.getTextFields()[row][col].setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void showGameOver(){
        view.getScoreField().setText("game over");
    }

}
