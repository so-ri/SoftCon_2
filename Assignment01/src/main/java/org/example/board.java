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
     * *SUBMARINE,
     *
     * possible types for blockstate:
     * NOGUESS,
     * HIT,
     * MISSED,
     * SUNK
     *
     * */

    private byte sunkcounter = 0;
    private block[][] blockarray = new block[10][10];
    public board(){
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionX xpos: positionXarray) { //iterate through enum objects
            for (positionY ypos: positionYarray) {
                blockarray[xpos.ordinal()][ypos.ordinal()] = new block();
            }
        }
    }

    public void printOwnBoard() {
        System.out.println("===== OCEAN GRID =====\n" + "  A B C D E F G H I J\n" + "+-+-+-+-+-+-+-+-+-+-+");
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionY ypos: positionYarray) { //iterate through enum objects, BUT THE Y WAY - horizontally
            String ToBePrinted = ypos.ordinal() + "|"; //Prints number of line to the left
            for (positionX xpos: positionXarray) {
                block currentblock = blockarray[xpos.ordinal()][ypos.ordinal()];
                if (currentblock.getState() != blockstate.NOGUESS)
                    switch (currentblock.getState()) {
                        case HIT -> ToBePrinted += "X|";
                        case MISSED -> ToBePrinted += "o|";
                        case SUNK -> ToBePrinted += "s|";
                }
                else {
                    switch (currentblock.getShiptype()) {
                        case EMPTY -> ToBePrinted += " |";
                        case CARRIER -> ToBePrinted += "C|";
                        case BATTLESHIP -> ToBePrinted += "B|";
                        case PATROL -> ToBePrinted += "P|";
                        case SUBMARINE -> ToBePrinted += "S|";
                    }
                }
            }
            ToBePrinted += ypos.ordinal();
            System.out.println(ToBePrinted);
            // "line" ends

        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+\n" + "  A B C D E F G H I J\n");
    }

    public void printEnemyBoard(){
        System.out.println("===== TARGET GRID =====\n" + "  A B C D E F G H I J\n" + "+-+-+-+-+-+-+-+-+-+-+");
        positionX[] positionXarray = positionX.values(); //helper array of values of enum object to iterate through it
        positionY[] positionYarray = positionY.values();

        for (positionY ypos: positionYarray) { //iterate through enum objects, BUT THE Y WAY - horizontally
            String ToBePrinted = ypos.ordinal() + "|"; //Prints number of line to the left
            for (positionX xpos: positionXarray) {
                block currentblock = blockarray[xpos.ordinal()][ypos.ordinal()];
                    switch (currentblock.getState()) {
                        case NOGUESS -> ToBePrinted += " |";
                        case HIT -> ToBePrinted += "X|";
                        case MISSED -> ToBePrinted += "o|";
                        case SUNK -> ToBePrinted += "s|";
                    }

            }
            ToBePrinted += ypos.ordinal();
            System.out.println(ToBePrinted);
            // "line" ends

        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+\n" + "  A B C D E F G H I J\n");

    }

    public boolean IsGameOver() {
        return sunkcounter >= 10;
    }

    public void createShip(positionX x, positionY y, positionX x2, positionY y2) {

        ship ship = new ship(x,y,x2,y2);
        positionX[] xcoordinates = ship.getXcoordinates();
        positionY[] ycoordinates = ship.getYcoordinates();

        if (ship.getShipType() == blockshiptype.EMPTY) {
            throw new IllegalArgumentException("BLOCKSHIPTYPE IST EMPTY, VMTL STIMMT BLOCKSHIPTYPE NICHT");
        }

        //assert ship.getShipType() != blockshiptype.EMPTY;
        blockshiptype shiptype = ship.getShipType(); //cache the shiptype

        for (byte idx = 0; idx < xcoordinates.length; idx++) { // go through all the shipinstance coordinates
            block newshipblock = blockarray[xcoordinates[idx].ordinal()][ycoordinates[idx].ordinal()]; //find the block with the coordinates
            newshipblock.setShiptype(shiptype);
            newshipblock.setShipinstance(ship);
        }
    }

    public void setGuess(positionX x, positionY y) {
        block block = blockarray[x.ordinal()][y.ordinal()];
        if (IsEmpty(x, y)) {
            block.setState(blockstate.MISSED);
            System.out.println("SHOT WAS MISSED");
        }
        else {
            block.setState(blockstate.HIT);
            block.setShiptoHit(x,y);
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
    }


    //BLOCK MODIFIERS
    public void setState(positionX x, positionY y, blockstate bs) {
        block block = blockarray[x.ordinal()][y.ordinal()];
        block.setState(bs);
    }
    public void setShipType(positionX x, positionY y, blockshiptype bst) {
        block block = blockarray[x.ordinal()][y.ordinal()];
        block.setShiptype(bst);
    }
    //! ShipInstance - is it safe to store it in f.e. 3 blocks at the same time?
    public void setShipInstance(positionX x, positionY y, ship s) {
        block block = blockarray[x.ordinal()][y.ordinal()];
        System.out.println(this.IsEmpty(x, y));
        if (this.IsEmpty(x, y) == false) {
            throw new IllegalArgumentException("Schiffsinstanz wurde bereits gesetzt");
        }
        block.setShipinstance(s);
    }

    //BLOCKSTATE CHECKERS
    public boolean GotGuessed(positionX x, positionY y) {
        return !Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.NOGUESS); //returns true if it has been guessed
    }

    public boolean GotHit(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.HIT); //returns true if it has been hit
    }

    public boolean GotMissed(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.MISSED); //returns true if it has been missed
    }
    public boolean GotSunk(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getState(), blockstate.SUNK); //returns true if it has been sunk
    }

    //SHIPTYPE CHECKERS
    public boolean IsEmpty(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.EMPTY); //returns true if == EMPTY, returns false if != EMPTY
    }

    public boolean IsCarrier(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.CARRIER);
    }

    public boolean IsBattleship(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.BATTLESHIP);
    }

    public boolean IsPatrol(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.PATROL);
    }

    public boolean IsSubmarine(positionX x, positionY y) {
        return Objects.equals(blockarray[x.ordinal()][y.ordinal()].getShiptype(), blockshiptype.SUBMARINE);
    }


}
