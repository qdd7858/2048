package model;

public class Board {
    /** The value in the Tile*/
    public static final int NUM_ROW = 4;
    /** The value in the Tile*/
    public static final int NUM_COL = 4;
    /** The collection of Tiles which is a double array*/
    private Tile[][] board = new Tile[NUM_ROW][NUM_COL];

    public Board(){
        for (int row = 0; row < NUM_ROW; row++){
            for (int col = 0; col < NUM_COL; col++){
                board[row][col] = new Tile(row, col);
            }
        }
    }

    /**Get the collection of Tiles
     *
     * @return the collection of Tiles
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**Get the value of the Tile at a specific location
     *
     * @param row the row position of the Tile
     * @param col the column position of the Tile
     *
     * @return the value of the Tile at row, col
     */
    public int getValueAt(int row, int col){
        return board[row][col].getValue();
    }

    /**Get the row position of the Tile
     *
     * @param row the row position of the Tile
     * @param col the column position of the Tile
     * @return the Tile at row, col
     */
    public Tile getTileAt(int row, int col){
        return board[row][col];
    }

    public boolean isEmptyAt(int row, int col){
        return board[row][col].isEmpty();
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
