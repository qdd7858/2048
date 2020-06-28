package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**The Memento class of the Memento design pattern
 * The GameMemento which saves the important information of the Game
 *
 * @author Quan Do
 */
public class GameMemento {
    /** The Board of the Game*/
    private Board board;
    /** The score of the Game*/
    private IntegerProperty score;

    /** Constructor*/
    public GameMemento(Board board, IntegerProperty score){
        this.board = new Board();
        this.board.copy(board);
        this.score = new SimpleIntegerProperty(this, "score", score.get());
    }

    /** Get the saved score of the game
     *
     * @return the saved score of the game
     */
    public int getScore() {
        return score.get();
    }

    /** Get the saved Board of the game
     *
     * @return the saved Board of the game
     */
    public Board getBoard() {
        return board;
    }
}
