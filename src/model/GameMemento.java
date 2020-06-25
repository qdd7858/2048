package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Queue;

public class GameMemento {
    /** The Board of the Game*/
    private Board board;
    /** The score of the Game*/
    private IntegerProperty score;
    public GameMemento(Board board, IntegerProperty score){
        this.board = new Board();
        this.board.copy(board);
        this.score = new SimpleIntegerProperty(this, "score", score.get());
    }

    public int getScore() {
        return score.get();
    }

    public Board getBoard() {
        return board;
    }
}
