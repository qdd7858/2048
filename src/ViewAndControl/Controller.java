package ViewAndControl;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import model.Board;
import model.Game;

/** The Controller class which follow MVC pattern
 *  responsible for managing the data of the application. It receives user input from the controller.
 *
 * @author Quan Do*/
public class Controller{
    /** The Game which the Controller will use to set the data for the GUI*/
    private Game game;
    /** The GUI of the Game*/
    private View view;

    /** Constructor*/
    public Controller(Game game, View view){
        this.game = game;
        this.view = view;

        setTextField();
        setKeyListener();
        setButtons();
    }

    /** Set the functions of the buttons in the GUI*/
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

    /** Set the style and the Property to the TextField which display the Board of the Game*/
    public void setTextField(){
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX; col++){
                // Set styles
                TextField textField = new TextField();
                textField.setMinSize(100,100);
                textField.textProperty().bind(game.getBoard().valuePropertyAt(row,col).asString());
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-text-inner-color: white;");
                textField.setEditable(false);
                textField.setMouseTransparent(true);
                textField.setFocusTraversable(false);
                setTextFieldColor(textField);

                // add the TextField to the GridPane to display
                view.getGridPane().add(textField, col, row);

                // bind the score Property of the Game to score field to display
                view.getScoreField().textProperty().bind((game.scoreProperty().asString()));

                // add a listener to change the TextField when ever the value of the Tile is changed
                game.getBoard().valuePropertyAt(row,col).addListener((v, oldValue, newValue) -> {
                    // animation
                    if (oldValue.doubleValue() < newValue.doubleValue() && oldValue.doubleValue() != 0){
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), textField);
                        scaleTransition.setCycleCount(2);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.setFromX(1);
                        scaleTransition.setFromY(1);
                        scaleTransition.setToX(1.15);
                        scaleTransition.setToY(1.15);
                        scaleTransition.play();
                    }
                    // Change the color of the TextField's background base on the value Property of the Tile in the Board
                    setTextFieldColor(textField);
                });
            }
        }
    }

    /** Set the key listener to play the game*/
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
            // If it is a valid key, update the game and check for endgame.
            if (isDirectionKey){
                game.update();
                if (game.isGameOver()){
                    this.showGameOver();
                }
            }
        });
    }


    public void setTextFieldColor(TextField textField){
        // set to white if the value Property bind to the Text Field is 0
        Paint paint = Color.rgb(255,255,255);
        int value = Integer.parseInt(textField.getText());
        if (value != 0){
            int i = (int) (Math.log10(value)/Math.log10(2));

            // Reset the color
            i = i%10;
            // new color which is a combination of red and yellow
            paint = Color.rgb(255,(int)(255-25.5*i),0);
        }
        // set the background color
        textField.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /** New Game if the user choose yes to reset the game*/
    public void showGameOver(){
        if (ConfirmBox.display("Game Over", "Game Over!!! Do you want to play a new game?")){
            game.newGame();
        }
    }

}
