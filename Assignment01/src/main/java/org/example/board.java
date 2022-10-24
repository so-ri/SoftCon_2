package org.example;

public class board {
    /*
     * possible states for block state:
     * 1 = no guess
     * 2 = Hit
     * 3 = Missed
     * 4 = sunk
     *
     * possible states for block shiptype:
     * 11 = empty
     * 12 = carrier
     * 13 = battleship
     * 14 = cruiser
     * 15 = submarine
     * 16 = destroyer
     * */
    public block[][] blockarray; //this seems to be Java array declaration according to IntelliJ

    public board(){
        //blockarray iniitierung mit neuen blöcken - OK so? auch mit assertion? die wurde mir vorgeschlagen von IntelliJ
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                assert blockarray != null;
                blockarray[x][y] = new block();
            }
        }
    }
    public boolean IsEmpty(int x, int y) {
        return blockarray[x][y].getState() == 11; //returns true if == 11, returns false if != 11
    }

    //toImplement: gotGuessed, gotMissed, gotHit, isSunk
    // setType, setGuess (this one changes to hit or miss and calls isSunk())
    // getShipType (or isCruiser etc) -- wait no, it should return the ship object? is this safe?
    // - HOW DO WE STORE SHIPINFORMATION? DAS MUSS IN DEN BLOCK! DAS MUSS IM BLOCK INSTANZIERT WERDEN?! GEHT NICHT AUF

}
