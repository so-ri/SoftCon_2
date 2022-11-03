package org.example;

import org.example.ships.positionX;
import org.example.ships.positionY;
import org.example.ships.ship;
import static org.example.blockshiptype.EMPTY;
import static org.example.blockstate.NOGUESS;

/*
* block classes are being stored in the board class.
* */
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
    private ship shipinstance = null;

    //constructor, first initiation with base cases and else we've got to change them
    public block() {
        this.state = NOGUESS;
        this.shiptype = EMPTY;

    }

    //copy constructor to respect encapsulation. deep copy of shipinstance
    public block(block block){
        this.state = block.state;
        this.shiptype = block.shiptype;
        this.shipinstance = new ship(block.shipinstance.getXcoordinates()[0],block.shipinstance.getYcoordinates()[0], block.shipinstance.getXcoordinates()[block.shipinstance.getXlength()], block.shipinstance.getYcoordinates()[block.shipinstance.getYlength()]);
    }

    private void blockcopy() {
    }

    public blockshiptype getShiptype() {
        block blockcopy = new block(this); //copy them to not give the calling class a chance to modify the values
        return blockcopy.shiptype;
    }
    //PRE: was empty before we set a shiptype
    public void setShiptype(blockshiptype shiptype) {
        assert (this.shiptype == EMPTY); //assertion to make sure a shiptype won't get changed after changing it from empty
        this.shiptype = shiptype;
    }

    public blockstate getState() {
        block blockcopy = new block(this); //copy them to not give the calling class a chance to modify the values
        return blockcopy.state;
    }

    public void setState(blockstate state) {
        this.state = state;
    }

    //PRE: was not hit before
    public void setShiptoHit(positionX x, positionY y) {
            assert(state == NOGUESS); //it can't be hit "twice"
            shipinstance.hitShip(x, y);
    }
    public ship getShipinstance() {
        block blockcopy = new block(this); //copy them to not give the calling class a chance to modify the values
        return blockcopy.shipinstance;
    }
    //PRE: was null before we set a shipinstance
    public void setShipinstance(ship shipinstance) {
        assert (this.shipinstance == null); //assertion - shipinstance should not be set twice
        this.shipinstance = shipinstance;
    }
}
