package model;

import model.Moving.*;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private MovingStrategy movingStrategy;
    public static final MovingStrategy LEFT = new Left();
    public static final MovingStrategy RIGHT = new Right();
    public static final MovingStrategy UP = new Up();
    public static final MovingStrategy DOWN = new Down();

    public Game (){
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void moving(Board board){
        movingStrategy.move(board);
    }

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
