package org.example;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static Game uniqueInstance;

    //Random int (0 or 1) to decide who starts
    private int randomNum = ThreadLocalRandom.current().nextInt(1, 2);

    //Singleton implementation of game class
    private Game() {
    }

    public static synchronized Game getInstance() {
        // 'synchronized' avoids two separate threads from creating two game instances
        if (uniqueInstance == null) {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public void start() {

        board playerBoard = new board();
        board computerBoard = new board();
        printlogo.BSlogo();

        computerBoard.printEnemyBoard();
        System.out.println("-----------------------\n");
        playerBoard.printOwnBoard();

        Input.ScanComputerShips(computerBoard);
        Input.ScanPlayerShips(playerBoard);

        //input.placeComputerShips(computerBoard);
        //input.placePlayerShips(playerBoard);


        computerBoard.printOwnBoard();//HERE SHOULD BE PRINT ENEMY BOARD
        playerBoard.printOwnBoard();

        while (!computerBoard.IsGameOver() && !playerBoard.IsGameOver()) {

            if (this.randomNum == 1) {
                Guess.PlayerGuess(computerBoard);
                Guess.ComputerGuess(playerBoard);
            }
            if (this.randomNum == 2) {
                Guess.ComputerGuess(playerBoard);
                Guess.PlayerGuess(computerBoard);
            }

            computerBoard.printEnemyBoard();
            System.out.println("-----------------------\n");
            playerBoard.printOwnBoard();
        }

        if (playerBoard.IsGameOver()) {
            System.out.println("Computer won. This is how the boats were:");
            computerBoard.printOwnBoard();
            printlogo.BSlogo();
        }

        if (computerBoard.IsGameOver()) {
            System.out.println("player won");
            printlogo.BSlogo();
        }
    }
}