package model;

import model.Moving.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    /** The Board of the Game*/
    private Board board;
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

    public Game (){
        this.board = new Board();
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
        movingStrategy.move(board);
    }

    /**Generate a new value of 2 in a random Tile if it is empty
     */
    public void randGenererate(){
        Random random = new Random();
        int randRow;
        int randCol;
        do {
        randRow = random.nextInt(4);
        randCol = random.nextInt(4);
        } while (!board.isEmptyAt(randRow,randCol));
        board.setValueAt(randRow,randCol,2);
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

            game.randGenererate();
            System.out.println(game.getBoard().toString());
            input = scanner.nextLine();
        }
    }
}
