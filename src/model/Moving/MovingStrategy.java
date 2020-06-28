package model.Moving;

import model.Board;
import model.Game;

/** The Strategy interface for the Strategy design pattern
 *
 * @author Quan Do
 */
public interface MovingStrategy {
    public void move(Board board, Game game);
}
