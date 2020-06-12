package model;

public class Tile {
    /** The value in the Tile*/
    private int value;
    /** The row position of the Tile*/
    private int row;
    /** The column position of the Tile*/
    private int col;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
        this.value = 0;
    }


    /**Get the value of the Tile
     *
     * @return the value in the Tile
     */
    public int getValue() {
        return value;
    }

    /**Set the value of the Tile
     *
     * @param value the value we want to change to
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**Get the row position of the Tile
     *
     * @return the row position of the Tile
     */
    public int getRow() {
        return row;
    }

    /**Get the column position of the Tile
     *
     * @return the column position of the Tile
     */
    public int getCol() {
        return col;
    }

    /**The function is called when combining value of 2 Tiles
     */
    public void mergeValue(){
        value = value*2;
    }

    /**If the Tile has value of 0
     *
     * @return whether the value is equal to 0
     */
    public boolean isEmpty(){
        return value==0;
    }

    /**Get the output as String
     */
    @Override
    public String toString(){
        return "["+value+"]";
    }
}
