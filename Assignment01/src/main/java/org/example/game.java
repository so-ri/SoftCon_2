package org.example;

public class Game {
    private static Game uniqueInstance;
    //Singleton implementation of game class
    private Game() {}
    public static Game getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public void start(){
        board playerBoard = new board();
        board computerBoard = new board();
        input.placeComputerShips(computerBoard);
        input.placePlayerShips(playerBoard);
        while(!playerBoard.isOver() && !computerBoard.isOver()){

        }


    }

}
