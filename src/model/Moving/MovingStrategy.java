package model.Moving;

import model.Board;
import model.Game;

public interface MovingStrategy {
    public void move(Board board, Game game);
}
