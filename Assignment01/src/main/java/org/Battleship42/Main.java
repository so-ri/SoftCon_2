package org.Battleship42;

import org.Battleship42.gameflow.Game;

public class Main {
    public static void main(String[] args) {
        Game g1 = Game.getInstance();
        g1.start();
    }
}

