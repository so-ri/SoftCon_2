package org.Battleship42.gameflow;
import org.Battleship42.printlogo;

import java.util.Random;

public class Game {
    private static Game uniqueInstance;

    //Random boolean to decide who starts
    Random rnd = new Random();
    private final boolean randomBoolean = rnd.nextBoolean();

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

    public void start() { //starts game

        //instantiate two new boards & print greeting message
        board playerBoard = new board();
        board computerBoard = new board();
        printlogo.BSlogo();

        //prints the boards empty for the first time
        computerBoard.printEnemyBoard();
        System.out.println("-----------------------\n");
        playerBoard.printOwnBoard();

        //take input (for ship creation) from computer and from player
        Input.ScanComputerShips(computerBoard);
        Input.ScanPlayerShips(playerBoard);

        //print the "filled" boards for the first time
        computerBoard.printOwnBoard();//HERE SHOULD BE PRINT ENEMY BOARD
        playerBoard.printOwnBoard();

        //this while loop controls the game flow and takes the guesses. It ends if either of the boards is > 10.
        while (!computerBoard.IsGameOver() && !playerBoard.IsGameOver()) {

            if (this.randomBoolean) {
                Guess.PlayerGuess(computerBoard);
                Guess.ComputerGuess(playerBoard);
            }
            else {
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