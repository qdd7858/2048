package model;

public class Board {
    public static final int NUM_ROW = 4;
    public static final int NUM_COL = 4;
    private Tile[][] board = new Tile[4][4];

    public Board(){
        for (int row = 0; row < NUM_ROW; row++){
            for (int col = 0; col < NUM_COL; col++){
                board[row][col] = new Tile(row, col);
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getValueAt(int row, int col){
        return board[row][col].getValue();
    }

    public Tile getTileAt(int row, int col){
        return board[row][col];
    }

    public boolean isEmptyAt(int row, int col){
        return board[row][col].getValue() == 0;
    }

    public boolean isRowEmpty(int row){
        for (int i = 0; i < NUM_COL; i++){
            if (!isEmptyAt(row, i)){
                return false;
            }
        }
        return true;
    }

    public boolean isColEmpty(int col){
        for (int i = 0; i < NUM_ROW; i++){
            if (!isEmptyAt(i, col)){
                return false;
            }
        }
        return true;
    }

    public void setValueAt(int row, int col, int value){
        board[row][col].setValue(value);
    }

    @Override
    public String toString(){
        String output = "";
        for (int col = 0; col < NUM_COL; col++){
            for (int row = 0; row < NUM_ROW; row++){
                output = output + board[row][col].toString();
            }
            output = output + "\n";
        }
        return output;
    }
}
