package org.example;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private static Game uniqueInstance;

    //Singleton implementation of game class
    private Game() {
    }

    public static Game getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public void start() {

        board playerBoard = new board();
        board computerBoard = new board();
        printlogo.BSlogo();


        Input.ScanComputerShips(computerBoard);
        Input.ScanPlayerShips(playerBoard);

        //input.placeComputerShips(computerBoard);
        //input.placePlayerShips(playerBoard);


        //Random int (0 or 1) to decide who starts

        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);




        while (!computerBoard.IsGameOver() && !playerBoard.IsGameOver()) {

            if (randomNum == 1) {
                Guess.PlayerGuess(playerBoard);
                Guess.ComputerGuess(computerBoard);
            }
            if (randomNum == 2) {
                Guess.ComputerGuess(computerBoard);
                Guess.PlayerGuess(playerBoard);
            }


        }
    }
}