package model;

public class Board {
    /** The value in the Tile*/
    public static final int ROW_INDEX = 4;
    /** The value in the Tile*/
    public static final int COL_INDEX = 4;
    /** The collection of Tiles which is a double array*/
    private Tile[][] board = new Tile[ROW_INDEX][COL_INDEX];

    public Board(){
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
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
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     *
     * @return the value of the Tile at row, col
     */
    public int getValueAt(int row, int col){
        return board[row][col].getValue();
    }

    /**Get the row index of the Tile
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     * @return the Tile at row, col
     */
    public Tile getTileAt(int row, int col){
        return board[row][col];
    }

    /**Check if a Tile is empty
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     * @return whether the value in the Tile is equal to 0
     */
    public boolean isEmptyAt(int row, int col){
        return board[row][col].isEmpty();
    }

    /**Check if the row is empty
     *
     * @param row the row index of the Tile
     * @return whether all the Tiles on a row are all equal to 0
     */
    public boolean isRowEmpty(int row){
        for (int i = 0; i < COL_INDEX; i++){
            if (!isEmptyAt(row, i)){
                return false;
            }
        }
        return true;
    }

    /**Check if the column is empty
     *
     * @param col the row index of the Tile
     * @return whether all the Tiles on a column are all equal to 0
     */
    public boolean isColEmpty(int col){
        for (int i = 0; i < ROW_INDEX; i++){
            if (!isEmptyAt(i, col)){
                return false;
            }
        }
        return true;
    }

    /**Set the value of a Tile
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     */
    public void setValueAt(int row, int col, int value){
        board[row][col].setValue(value);
    }

    public boolean equal(Board board){
        Tile[][] b = board.getBoard();
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                if (this.board[row][col].getValue() != b[row][col].getValue()){
                    return false;
                }
            }
        }
        return true;
    }

    public void copy(Board board){
        Tile[][] b = board.getBoard();
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                this.board[row][col].setValue(b[row][col].getValue());
            }
        }
    }

    /**Presenting the object of this class as String
     */
    @Override
    public String toString(){
        String output = "";

        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                output = output + board[row][col].toString();
            }
            output = output + "\n";
        }
        return output;
    }
}
