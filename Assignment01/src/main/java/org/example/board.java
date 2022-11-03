package org.example;
import org.example.ships.ship;
import org.example.ships.positionX;
import org.example.ships.positionY;

import java.util.Objects;

public class board {
    /*
     * possible types for blockshiptype:
     * EMPTY,
     * CARRIER,
     * *BATTLESHIP,
     * *PATROL,
     * *SUBMARINE
     *
     * possible types for blockstate:
     * NOGUESS,
     * HIT,
     * MISSED,
     * SUNK
     *
     * */

    private byte sunkcounter = 0;
    private final block[][] blockarray = new block[10][10];
    public board(){
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionX xpos: positionXarray) { //iterate through enum objects
            for (positionY ypos: positionYarray) {
                blockarray[xpos.ordinal()][ypos.ordinal()] = new block(); //initiate new block
            }
        }
    }

    // can be called to determine if all 10 ships have been sunk (and game is finished)
    public boolean IsGameOver() {
        return sunkcounter >= 10;
    }
    /*
    * two print methods for either showing the board for the player or the one the enemy sees
    */
    public void printOwnBoard() {
        System.out.println("""
                ===== OCEAN GRID =====
                  A B C D E F G H I J
                +-+-+-+-+-+-+-+-+-+-+""");
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionY ypos: positionYarray) { //iterate through enum objects, row by row (first y)
            StringBuilder ToBePrinted = new StringBuilder(ypos.ordinal() + "|"); //puts number of line to the left. Using ToBePrinted as a StringBuilder
            for (positionX xpos: positionXarray) {
                block currentblock = blockarray[xpos.ordinal()][ypos.ordinal()];
                if (currentblock.getState() != blockstate.NOGUESS) //if currentblock has been guessed, it can have the following types
                    switch (currentblock.getState()) {
                        case HIT -> ToBePrinted.append("X|");
                        case MISSED -> ToBePrinted.append("o|");
                        case SUNK -> ToBePrinted.append("s|");
                }
                else { //else it has not been guessed and can be:
                    switch (currentblock.getShiptype()) {
                        case EMPTY -> ToBePrinted.append(" |");
                        case CARRIER -> ToBePrinted.append("C|");
                        case BATTLESHIP -> ToBePrinted.append("B|");
                        case PATROL -> ToBePrinted.append("P|");
                        case SUBMARINE -> ToBePrinted.append("S|");
                    }
                }
            }
            ToBePrinted.append(ypos.ordinal()); //adds number of line at the end of the row
            System.out.println(ToBePrinted);
            // row ends here
        }
        System.out.println("""
                +-+-+-+-+-+-+-+-+-+-+
                  A B C D E F G H I J
                """);
    }

    public void printEnemyBoard(){
        System.out.println("""
                ===== TARGET GRID =====
                  A B C D E F G H I J
                +-+-+-+-+-+-+-+-+-+-+""");
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionY ypos: positionYarray) { //iterate through enum objects, row by row (first y)
            StringBuilder ToBePrinted = new StringBuilder(ypos.ordinal() + "|"); //puts number of line to the left. Using ToBePrinted as a StringBuilder
            for (positionX xpos: positionXarray) {
                block currentblock = blockarray[xpos.ordinal()][ypos.ordinal()];
                if (currentblock.getState() == blockstate.HIT) //if currentblock was hit, it can have the following types that should be shown
                    switch (currentblock.getShiptype()) {
                        case EMPTY -> ToBePrinted.append(" |");
                        case CARRIER -> ToBePrinted.append("C|");
                        case BATTLESHIP -> ToBePrinted.append("B|");
                        case PATROL -> ToBePrinted.append("P|");
                        case SUBMARINE -> ToBePrinted.append("S|");
                    }
                switch (currentblock.getState()) { //here, we only show these states.
                    case NOGUESS -> ToBePrinted.append(" |");
                    case MISSED -> ToBePrinted.append("o|");
                    case SUNK -> ToBePrinted.append("s|");
                }

            }
            ToBePrinted.append(ypos.ordinal());
            System.out.println(ToBePrinted);
            // row ends here

        }
        System.out.println("""
                +-+-+-+-+-+-+-+-+-+-+
                  A B C D E F G H I J
                """);

    }

    /*
    * gets called by input class and then sets up a new ship instance at the right blocks
    */
    public void createShip(positionX x, positionY y, positionX x2, positionY y2) {

        //input validation is being made by the input class
        ship ship = new ship(x,y,x2,y2);
        positionX[] xcoordinates = ship.getXcoordinates();
        positionY[] ycoordinates = ship.getYcoordinates();


        if (ship.getShipType() == blockshiptype.EMPTY) {
            throw new IllegalArgumentException("BLOCKSHIPTYPE IS EMPTY EVEN AFTER INITIALIZATION");
        }

        //assert ship.getShipType() != blockshiptype.EMPTY;
        blockshiptype shiptype = ship.getShipType(); //cache the shiptype

        for (byte idx = 0; idx < xcoordinates.length; idx++) { // go through all the shipinstance coordinates
            block newshipblock = blockarray[xcoordinates[idx].ordinal()][ycoordinates[idx].ordinal()]; //find the block with the coordinates
            newshipblock.setShiptype(shiptype);
            newshipblock.setShipinstance(ship);
        }
    }

    //PRE: block.getstate() == NOGUESS
    public void setGuess(positionX x, positionY y) {
        block block = blockarray[x.ordinal()][y.ordinal()]; //get the guessed block and then modify it
        assert block.getState() == blockstate.NOGUESS; //assertion to check for precondition

        if (IsEmpty(x, y)) {
            block.setState(blockstate.MISSED);
            System.out.println("SHOT WAS MISSED");
        }
        else {
            block.setShiptoHit(x,y);
            block.setState(blockstate.HIT);
            System.out.println("SHOT WAS HIT");

            //get ship and shipinstance coordinates to set it to sunk if it is down
            if (block.getShipinstance().isDown()){
                ship shipinstance = block.getShipinstance();
                positionX[] xcoordinates = shipinstance.getXcoordinates();
                positionY[] ycoordinates = shipinstance.getYcoordinates();
                for (byte idx = 0; idx < xcoordinates.length; idx++) { // go through all the shipinstance coordinates
                    block sunkblock = blockarray[xcoordinates[idx].ordinal()][ycoordinates[idx].ordinal()]; //find the block with the coordinates
                    sunkblock.setState(blockstate.SUNK); //set it to sunk
                }
                System.out.println("SHIP GOT SUNK");
                sunkcounter++;
            }
        }
    } //POST: block.getstate != NOGUESS

    //BLOCKSTATE CHECKERS
    public boolean GotHit(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.HIT); //returns true if it has been hit
    }
    public boolean GotSunk(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.SUNK); //returns true if it has been sunk
    }
    public boolean IsEmpty(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.EMPTY); //returns true if == EMPTY
    }
}