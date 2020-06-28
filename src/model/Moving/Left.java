package model.Moving;

import model.Board;
import model.Game;

/** The ConcreteStrategy for the Strategy design pattern
 *  Handling moving left
 *
 * @author Quan Do
 */
public class Left implements MovingStrategy {
    @Override
    public void move(Board board, Game game) {
        for (int row = 0; row < Board.ROW_INDEX; row++){
            if (!board.isRowEmpty(row)){
                for (int col = 0; col < Board.COL_INDEX - 1; col++){
                    if (board.isEmptyAt(row, col)){
                        for (int i = col+1; i < Board.COL_INDEX; i++){
                            if (!board.isEmptyAt(row, i)){
                                board.getTileAt(row,col).setValue(board.getValueAt(row, i));
                                board.getTileAt(row, i).setValue(0);
                                break;
                            }
                        }
                    }
                    if (!board.isEmptyAt(row, col)){
                        for (int i = col+1; i < Board.COL_INDEX; i++){
                            if (!board.isEmptyAt(row, i)){
                                if (board.getValueAt(row,col) == board.getValueAt(row, i)){
                                    board.getTileAt(row,col).mergeValue();
                                    board.setValueAt(row,i, 0);
                                    game.addScore(board.getValueAt(row,col));
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
