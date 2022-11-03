package org.Battleship42.board;

public enum blockstate {
    NOGUESS,
    HIT,
    MISSED,
    SUNK
}

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
