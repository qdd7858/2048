package model.Moving;

import model.Board;
import model.Tile;

public class Down implements MovingStrategy {
    @Override
    public void move(Board board) {
        for (int row = 0; row < Board.NUM_ROW; row++){
            if (!board.isRowEmpty(row)){
                for (int col = Board.NUM_COL-1; col > 0; col--){
                    if (board.isEmptyAt(row, col)){
                        for (int i = col-1; i >= 0; i--){
                            if (!board.isEmptyAt(row, i)){
                                board.getTileAt(row,col).setValue(board.getValueAt(row, i));
                                board.getTileAt(row, i).setValue(0);
                                break;
                            }
                        }
                    }
                    else{
                        for (int i = col-1; i >= 0; i--){
                            if (!board.isEmptyAt(row, i)){
                                if (board.getValueAt(row,col) == board.getValueAt(row, i)){
                                    board.getTileAt(row,col).mergeValue();
                                    board.setValueAt(row,i, 0);
                                }
                                break;
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
        for (int row = 0; row < Board.NUM_ROW; row++) {
            if (!board.isRowEmpty(row)) {
                for (int col = Board.NUM_COL -1; col >= 1; col--) {
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
