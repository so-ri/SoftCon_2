package org.example;

import org.example.ships.positionX;
import org.example.ships.positionY;
import org.example.ships.ship;
import static org.example.blockshiptype.EMPTY;
import static org.example.blockstate.HIT;
import static org.example.blockstate.NOGUESS;

public class block {

    /*
    * public enum blockshiptype {
    * EMPTY,
    * CARRIER,
    * BATTLESHIP,
    * PATROL,
    * SUBMARINE,
    * }
    *
    * public enum blockstate {
    * NOGUESS,
    * HIT,
    * MISSED,
    * SUNK
    * }
    * */
    private blockstate state;
    private blockshiptype shiptype;
    private ship shipinstance;

    //constructor, first initiation with base cases and else we've got to change them
    public block() {
        this.state = NOGUESS;
        this.shiptype = EMPTY;
    }

    //don't know if this is okay encapsulation-wise or better isEmpty()-checkers, but I think should be okay,
    //isEmpty() etc. should come in the board class then
    public blockshiptype getShiptype() {
        blockshiptype shiptypecopy = shiptype; //copy 
        return shiptypecopy;
    }

    public void setShiptype(blockshiptype shiptype) {
        this.shiptype = shiptype;
    }

    public blockstate getState() {
        blockstate blockstatecopy = state;
        return blockstatecopy;
    }

    public void setState(blockstate state) {
        this.state = state;
    }
/*
    public void setShiptoHit(positionX x, positionY y) {
            shipinstance.hitShip(x, y); //Call muss je nach dem nochmals angepasst werden
    }
*/
    public ship getShipinstance() {
        ship instancecopy = shipinstance;
        return instancecopy;
    }

    public void setShipinstance(ship shipinstance) {
        this.shipinstance = shipinstance;
    }
}
