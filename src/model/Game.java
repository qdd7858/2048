package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Moving.*;
import java.util.*;

/**
 * The Game class keeps the information of the Game.
 *
 * The Context class of the Strategy design pattern,
 * the class have a Strategy field for user to change and apply that strategy to the Board
 *
 * The Originator class of the Memento design pattern,
 * the class which has the information is needed to be saved.
 *
 * @author Quan Do
 */
public class Game {
    /** The Board of the Game*/
    private Board board;
    /** The score of the Game*/
    private IntegerProperty score;
    /** Collection of Game's state for undo*/
    private Stack<GameMemento> stack;
    /** Strategy to arrange Tile after each move*/
    private MovingStrategy movingStrategy;
    /** MovingStrategy for moving left*/
    public static final MovingStrategy LEFT = new Left();
    /** MovingStrategy for moving right*/
    public static final MovingStrategy RIGHT = new Right();
    /** MovingStrategy for moving up*/
    public static final MovingStrategy UP = new Up();
    /** MovingStrategy for moving down*/
    public static final MovingStrategy DOWN = new Down();

    /** Constructor*/
    public Game (){
        this.board = new Board();
        score = new SimpleIntegerProperty(this, "score", 0);
        randGenerate();
        stack = new Stack<>();
    }

    /**Get the board of a Game
     *
     * @return the Board of a Game
     */
    public Board getBoard() {
        return board;
    }

    /**Set the moving strategy match with the moving command
     *
     * @param movingStrategy the moving strategy to arrange the Board
     */
    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    /**Arrange the Tile according to the moving strategy
     *
     * @param board the Board of a Game which is arranged
     */
    public void moving(Board board){
        movingStrategy.move(board, this);
    }

    /**Generate a new value of 2 in a random Tile if it is empty*/
    public void randGenerate(){
        Random random = new Random();
        int randRow;
        int randCol;
        do {
        randRow = random.nextInt(4);
        randCol = random.nextInt(4);
        } while (!board.isEmptyAt(randRow,randCol));
        board.setValueAt(randRow,randCol,2);
    }

    /** Update the Game which includes
     *  make a copy of the board,
     *  move the tiles,
     *  and don't update if the move doesn't change the board
     */
    public void update(){
        Board board = new Board();
        board.copy(this.board);
        stack.add(createMemento());
        moving(this.board);
        if (this.board.equal(board)){
            stack.pop();
        }
        else {
            randGenerate();
        }
    }

    /**Get the score Property of the game
     *
     * @return the score Property of the game
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**Set the score of the game
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score.set(score);
    }

    /**Get the value Property of a tile
     *
     * @return the score of this game
     */
    public int getScore() {
        return score.get();
    }

    /**Add more score the current score
     *
     * @param  score the score which will be added to the current score
     */
    public void addScore(int score){
        setScore(getScore() + score);
    }

    /**Check if the player have no more move.
     *
     * @return whether the player is able to move and change the board
     */
    public boolean noMoveLeft(){
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX - 1; col++){
                if (board.getValueAt(row, col) ==  board.getValueAt(row, col+1)){
                    return false;
                }
            }
        }
        for (int col = 0; col < Board.COL_INDEX; col++){
            for (int row = 0; row < Board.ROW_INDEX - 1; row++){
                if (board.getValueAt(row, col) ==  board.getValueAt(row + 1, col)){
                    return false;
                }
            }
        }
        return true;
    }

    /**Check if the board is full
     *
     * @return whether the board is full.
     */
    public boolean isFull(){
        for (int row = 0; row < Board.ROW_INDEX; row++){
            for (int col = 0; col < Board.COL_INDEX; col++){
                if (board.isEmptyAt(row, col)){
                    return false;
                }
            }
        }
        return true;
    }

    /**Check if the game is over
     *
     * @return whether the board is full && the game have no more move
     */
    public boolean isGameOver(){
        return isFull() && noMoveLeft();
    }

    /**Create a copy of important information of the game to restore when is needed
     *
     * @return a copy of important information of the game
     */
    public GameMemento createMemento(){
        return new GameMemento(board, score);
    }

    /**Restore the game to the previous states by restore the important information of the game
     */
    public void restore(){
        if (!stack.empty()){
            GameMemento memento = stack.pop();
            board.copy(memento.getBoard());
            setScore(memento.getScore());
        }
    }

    /**Create a new game which retain all the address of the field, in order to use Property to auto update the GUI
     */
    public void newGame (){
        this.board.copy(new Board());
        score.setValue(0);
        randGenerate();
        stack = new Stack<>();
    }

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("")){

            if (input.strip().equals("a")){
                game.setMovingStrategy(Game.LEFT);
                game.moving(game.getBoard());
            }
            if (input.strip().equals("d")){
                game.setMovingStrategy(Game.RIGHT);
                game.moving(game.getBoard());
            }
            if (input.strip().equals("w")){
                game.setMovingStrategy(Game.UP);
                game.moving(game.getBoard());
            }
            if (input.strip().equals("s")){
                game.setMovingStrategy(Game.DOWN);
                game.moving(game.getBoard());
            }

            game.randGenerate();
            System.out.println(game.getBoard().toString());
            input = scanner.nextLine();
        }
    }
}
