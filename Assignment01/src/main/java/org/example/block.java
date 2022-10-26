package org.example;

import org.example.ships.ship;
import static org.example.blockshiptype.EMPTY;
import static org.example.blockstate.NOGUESS;

public class block {

    /*
    * public enum blockshiptype {
    * EMPTY,
    * CARRIER,
    * BATTLESHIP,
    * CRUISER,
    * SUBMARINE,
    * DESTROYER
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
        return shiptype;
    }

    public void setShiptype(byte Shiptype) {
        this.shiptype = shiptype;
    }

    public blockstate getState() {
        return state;
    }

    public void setState(blockstate state) {
        this.state = state;
    }

    public ship getShipinstance() {
        return shipinstance;
    }

    public void setShipinstance(ship shipinstance) {
        this.shipinstance = shipinstance;
    }
}
