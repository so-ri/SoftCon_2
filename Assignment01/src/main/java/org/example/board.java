package org.example;

//KÖNNEN WIR DAS IN DAS HAUPT-PACKAGE SCHIEBEN?
import org.example.ships.positionX;
import org.example.ships.positionY;

import java.util.Objects;


public class board {
    /*
     * possible types for blockshiptype:
     * EMPTY,
     * CARRIER,
     * BATTLESHIP,
     * CRUISER,
     * SUBMARINE,
     * DESTROYER
     *
     * possible types for blockstate:
     * NOGUESS,
     * HIT,
     * MISSED,
     * SUNK
     *
     * */
    public block[][] blockarray;
    public board(){
        //blockarray iniitierung mit neuen blöcken - OK so? auch mit assertion? die wurde mir vorgeschlagen von IntelliJ
        positionX positionXarray[] = positionX.values(); //helper array of values of enum object to iterate through it
        positionY positionYarray[] = positionY.values();

        for (positionX xpos: positionXarray) { //iterate through enum objects
            for (positionY ypos: positionYarray) {
                assert blockarray != null;
                blockarray[xpos.ordinal()][ypos.ordinal()] = new block();
            }
        }
    }

    public void setGuess(positionX x, positionY y) {

    }

    // setType, setGuess (this one changes to hit or miss and calls isSunk())
    // ShipInstance - is it safe to store it in f.e. 3 blocks? not needed outside of the board, is it?

    //BLOCKSTATE CHECKERS
    public boolean GotGuessed(positionX x, positionY y) {
        return !Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.NOGUESS); //returns true if it has been guessed
    }

    public boolean GotHit(int x, int y) {
        return !Objects.equals(blockarray[x][y].getState(), blockstate.HIT); //returns true if it has been hit
    }

    public boolean GotMissed(int x, int y) {
        return !Objects.equals(blockarray[x][y].getState(), blockstate.MISSED); //returns true if it has been missed
    }
    public boolean GotSunk(int x, int y) {
        return !Objects.equals(blockarray[x][y].getState(), blockstate.SUNK); //returns true if it has been sunk
    }

    //SHIPTYPE CHECKERS
    public boolean IsEmpty(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.EMPTY); //returns true if == EMPTY, returns false if != EMPTY
    }

    public boolean IsCarrier(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.CARRIER);
    }

    public boolean IsBattleship(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.BATTLESHIP);
    }

    public boolean IsCruiser(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.CRUISER);
    }

    public boolean IsSubmarine(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.SUBMARINE);
    }

    public boolean IsDestroyer(int x, int y) {
        return Objects.equals(blockarray[x][y].getShiptype(), blockshiptype.DESTROYER);
    }

}
