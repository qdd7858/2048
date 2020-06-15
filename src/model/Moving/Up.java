package model.Moving;

import model.Board;

public class Up implements MovingStrategy {
    @Override
    public void move(Board board) {
        for (int col = 0; col < Board.COL_INDEX; col++){
            if (!board.isColEmpty(col)){
                for (int row = 0; row < Board.ROW_INDEX -1; row++){
                    if (board.isEmptyAt(row, col)){
                        for (int i = row+1; i < Board.ROW_INDEX; i++){
                            if (!board.isEmptyAt(i, col)){
                                board.getTileAt(row,col).setValue(board.getValueAt(i, col));
                                board.getTileAt(i, col).setValue(0);
                                break;
                            }
                        }
                    }
                    if (!board.isEmptyAt(row, col)){
                        for (int i = row+1; i < Board.ROW_INDEX; i++){
                            if (!board.isEmptyAt(i, col)){
                                if (board.getValueAt(row,col) == board.getValueAt(i, col)){
                                    board.getTileAt(row,col).mergeValue();
                                    board.setValueAt(i, col, 0);
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
