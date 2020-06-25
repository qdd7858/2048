package sample;

import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
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
        });

        view.getNewGameButton().setOnAction(e -> {
            if (ConfirmBox.display("Warning", "Are you sure you want to play a new game? Your current game will be deleted.")){
                game.newGame();
            }
        });
    }

    public void setTextField(){
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX; col++){
                TextField textField = new TextField();
                textField.setMinSize(100,100);
                textField.textProperty().bind(game.getBoard().valuePropetyAt(row,col).asString());
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-text-inner-color: white;");
                textField.setEditable(false);
                textField.setMouseTransparent(true);
                textField.setFocusTraversable(false);
                setTextFieldColor(textField);
                view.getGridPane().add(textField, col, row);
                view.getScoreField().textProperty().bind((game.scoreProperty().asString()));
                game.getBoard().valuePropetyAt(row,col).addListener((v, oldValue, newValue) -> {
                    if (oldValue.doubleValue() < newValue.doubleValue()){
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), textField);
                        scaleTransition.setCycleCount(2);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.setFromX(1);
                        scaleTransition.setFromY(1);
                        scaleTransition.setToX(1.15);
                        scaleTransition.setToY(1.15);
                        scaleTransition.play();
                    }
                    setTextFieldColor(textField);
                });
            }
        }
    }

    public void setKeyListener(){
        view.getScene().setOnKeyPressed(e -> {
            boolean isDirectionKey = false;
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT){
                game.setMovingStrategy(Game.LEFT);
                isDirectionKey = true;
            }
            else if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT){
                game.setMovingStrategy(Game.RIGHT);
                isDirectionKey = true;
            }
            else if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP){
                game.setMovingStrategy(Game.UP);
                isDirectionKey = true;
            }
            else if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN){
                game.setMovingStrategy(Game.DOWN);
                isDirectionKey = true;
            }
            if (isDirectionKey){
                game.update();
                if (game.isGameOver()){
                    this.showGameOver();
                }
            }
        });
    }


    public void setTextFieldColor(TextField textField){
        int hex = 0xff0000;
        Paint paint = paint = Paint.valueOf("ffffff");
        int value = Integer.parseInt(textField.getText());
        if (value != 0){
            int i = (int) (Math.log10(value)/Math.log10(2));
            hex = hex - (0x002000 * i);
            paint = Paint.valueOf(Integer.toString(hex, 16));
        }
        textField.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void showGameOver(){
        if (ConfirmBox.display("Game Over", "Game Over!!! Do you want to play a new game?")){
            game.newGame();
        }
    }

}
