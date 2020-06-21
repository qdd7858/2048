package model;

import java.util.Queue;

public class GameMemento {
    /** The Board of the Game*/
    private Board board;
    /** The score of the Game*/
    private int score;
    public GameMemento(Board board, int score){
        this.board = new Board();
        this.board.copy(board);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return board;
    }
}
