package model;

public class Tile {
    private int value;
    private int row;
    private int col;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void mergeValue(){
        value = value*2;
    }
    @Override
    public String toString(){
        return "["+value+"]";
    }
}
