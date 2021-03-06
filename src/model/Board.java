package model;

import javafx.beans.property.IntegerProperty;

/**
 * The Board class which keep all the tiles of the Game
 *
 * @author Quan Do
 */
public class Board {
    /** The value in the Tile*/
    public static final int ROW_INDEX = 4;
    /** The value in the Tile*/
    public static final int COL_INDEX = 4;
    /** The collection of Tiles which is a double array*/
    private Tile[][] tiles = new Tile[ROW_INDEX][COL_INDEX];

    /** Constructor*/
    public Board(){
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                tiles[row][col] = new Tile(row, col);
            }
        }
    }

    /**Get the collection of Tiles
     *
     * @return the collection of Tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**Get the value of the Tile at a specific location
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     *
     * @return the value of the Tile at row, col
     */
    public int getValueAt(int row, int col){
        return tiles[row][col].getValue();
    }

    /**Get the row index of the Tile
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     * @return the Tile at row, col
     */
    public Tile getTileAt(int row, int col){
        return tiles[row][col];
    }

    /**Check if a Tile is empty
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     * @return whether the value in the Tile is equal to 0
     */
    public boolean isEmptyAt(int row, int col){
        return tiles[row][col].isEmpty();
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
        tiles[row][col].setValue(value);
    }

    /**Compare 2 boards if they have the same the same tile's value in each tile.
     *
     * @param board the other board
     *
     * @return whether if the boards are equal
     */
    public boolean equal(Board board){
        Tile[][] b = board.getTiles();
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                if (this.tiles[row][col].getValue() != b[row][col].getValue()){
                    return false;
                }
            }
        }
        return true;
    }

    /**Copy the tile's value in each tile of the target board to this board.
     *
     * @param board the target board
     */
    public void copy(Board board){
        Tile[][] b = board.getTiles();
        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                this.tiles[row][col].setValue(b[row][col].getValue());
            }
        }
    }

    /**Get the value Property of a tile
     *
     * @param row the row index of the Tile
     * @param col the column index of the Tile
     * @return the value property at row, col in the board
     */
    public IntegerProperty valuePropertyAt(int row, int col){
        return tiles[row][col].valueProperty();
    }

    /**Presenting the object of this class as String
     */
    @Override
    public String toString(){
        String output = "";

        for (int row = 0; row < ROW_INDEX; row++){
            for (int col = 0; col < COL_INDEX; col++){
                output = output + tiles[row][col].toString();
            }
            output = output + "\n";
        }
        return output;
    }
}
