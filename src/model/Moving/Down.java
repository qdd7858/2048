package model.Moving;

import model.Board;
import model.Game;

/** The ConcreteStrategy for the Strategy design pattern
 *  Handling moving down
 *
 * @author Quan Do
 */
public class Down implements MovingStrategy {
    @Override
    public void move(Board board, Game game) {
        for (int col = 0; col < Board.ROW_INDEX; col++){                                          // Go through each column
            if (!board.isColEmpty(col)){                                                        // If the column is empty we can skip that row
                for (int row = Board.COL_INDEX -1; row > 0; row--){                                // Go through each row of the column
                    if (board.isEmptyAt(row, col)){                                             // If there is a empty Tile, change the value of the next non-empty Tile to this Tile
                        for (int i = row-1; i >= 0; i--){
                            if (!board.isEmptyAt(i, col)){                                      // Find the next non-empty Tile
                                board.getTileAt(row,col).setValue(board.getValueAt(i, col));    // Change the value of the empty Tile to the value of the next non-empty Tile
                                board.getTileAt(i, col).setValue(0);                            // Set the next non-empty Tile to empty
                                break;                                                          // Break the loop since we already found the next non-empty Tile
                            }                                                                   // Continue the loop may cause an error when there are many non-empty Tile
                        }
                    }
                    if (!board.isEmptyAt(row, col)){                                            // If there is a non-empty Tile, find if the next non-empty Tile has the same value of this Tile
                        for (int i = row-1; i >= 0; i--){
                            if (!board.isEmptyAt(i, col)){                                      // Find the next non-empty Tile
                                if (board.getValueAt(row,col) == board.getValueAt(i, col)){     // If the value of two Tiles are the same,
                                    board.getTileAt(row,col).mergeValue();                      // merge the value to non-empty Tile
                                    board.setValueAt(i, col, 0);                          // and set the next non-empty Tile to empty
                                    game.addScore(board.getValueAt(row,col));                   // Add score
                                }
                                break;                                                          // This break will stop the loop after we found the next non-empty Tile
                                                                                                // also prevents Tiles to merge many time in one move
                            }
                        }
                    }
                }
            }
        }
    }
/**
    @Override
    public void merge(Board board) {
        for (int row = 0; row < Board.ROW_INDEX; row++) {
            if (!board.isRowEmpty(row)) {
                for (int col = Board.COL_INDEX -1; col >= 1; col--) {
                    if (board.getValueAt(row,col) == board.getValueAt(row, col-1)){
                        board.getTileAt(row,col).mergeValue();
                        board.setValueAt(row,col -1, 0);
                    }
                }
            }
        }
    }
    */
}
