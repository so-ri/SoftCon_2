package org.example;

public class block {
    /*
    * possible states for state:
    * 1 = no guess
    * 2 = Hit
    * 3 = Missed
    * 4 = sunk
    *
    * possible states for shiptype:
    * 11 = empty
    * 12 = carrier
    * 13 = battleship
    * 14 = cruiser
    * 15 = submarine
    * 16 = destroyer
    * */
    private byte state;
    private byte shiptype;

    //constructor, first initiation with base cases and else we've got to change them
    public block() {
        this.state = 1;
        this.shiptype = 11;
    }

    //don't know if this is okay encapsulation-wise or better isEmpty()-checkers, but I think should be okay,
    //isEmpty() etc. should come in the board class them
    public byte getShiptype() {
        return shiptype;
    }

    public void setShiptype(byte Shiptype) {
        this.shiptype = shiptype;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
