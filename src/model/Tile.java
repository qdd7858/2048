package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The Tile class which keeps the value of each spot in the Board
 *
 * @author Quan Do
 */
public class Tile {

    /** The value in the Tile*/
    private IntegerProperty value;
    /** The row index of the Tile*/
    private int row;
    /** The column index of the Tile*/
    private int col;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
        //this.value = 0;
        this.value = new SimpleIntegerProperty(this, "value", 0);
    }


    /**Get the row index of the Tile
     *
     * @return the row index of the Tile
     */
    public int getRow() {
        return row;
    }

    /**Get the column index of the Tile
     *
     * @return the column index of the Tile
     */
    public int getCol() {
        return col;
    }

    /**The function is called when combining value of 2 Tiles
     */
    public void mergeValue(){
        value.setValue(value.get()*2);
    }

    /**If the Tile has value of 0
     *
     * @return whether the value is equal to 0
     */
    public boolean isEmpty(){
        return value.get() == 0;
    }

    public int getValue() {
        return value.get();
    }

    /**Get the value property of the Tile
     *
     * @return the value property of the Tile
     */
    public IntegerProperty valueProperty() {
        return value;
    }

    /**Set the value of the Tile
     *
     * @param value the new value to be set
     */
    public void setValue(int value) {
        this.value.set(value);
    }

    /**Presenting the object of this class as String
     */
    @Override
    public String toString(){
        return "["+value+"]";
    }
}
