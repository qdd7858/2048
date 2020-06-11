package model.Moving;

import model.Board;

public class Right implements MovingStrategy {
    @Override
    public void move(Board board) {
        for (int col = 0; col < Board.NUM_ROW; col++){
            if (!board.isColEmpty(col)){
                for (int row = Board.NUM_COL-1; row > 0; row--){
                    if (board.isEmptyAt(row, col)){
                        for (int i = row-1; i >= 0; i--){
                            if (!board.isEmptyAt(i, col)){
                                board.getTileAt(row,col).setValue(board.getValueAt(i, col));
                                board.getTileAt(i, col).setValue(0);
                                break;
                            }
                        }
                    }
                    else{
                        for (int i = row-1; i >= 0; i--){
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
