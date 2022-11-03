package org.example;

import java.util.Random;
import java.util.Scanner;
import org.example.ships.positionX;
import org.example.ships.positionY;

public class Guess {

    //Takes a guess input from user and verifies it
    //PRE: must be an empty position on the board
    public static void PlayerGuess(board playerBoard) {
        Scanner Input = new Scanner(System.in);
        String answer = null;
        boolean i = true;
        System.out.println("Type in new Guess: ");

        while (i) {
            String pos2 = Input.nextLine();

            //adjusts typed in position
            String pos1 = pos2.replaceAll("\\s+", "");
            String pos = pos1.toUpperCase();

            //Checks if valid input according to PRE-condition
            if (pos.length() != 2) {
                System.out.println("Please type in a Position on the Board");
            } else if ((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 74
                    || (int) pos.charAt(1) < 48 || (int) pos.charAt(1) > 57) {
                System.out.println("Please type in a Position on the Board");
            } else if (!ValidShot(pos, playerBoard)) {
                System.out.println("Please type in a valid Position");

            } else {
                i = false;
                answer = pos;
            }
        }

        //converts string position to enum and places the guess
        positionX x1 = translateX(answer);
        positionY y1 = translateY(answer);
        playerBoard.setGuess(x1, y1);
    }

    //translates the alphabetic part of String position to enum
    public static positionX translateX(String min) {
        int C1 = (int) min.charAt(0) - 65;
        positionX[] V1 = positionX.values();
        return V1[C1];
    }

    //translates the numeric part of String position to enum
    public static positionY translateY(String min) {
        int C2 = Character.getNumericValue(min.charAt(1));
        positionY[] V2 = positionY.values();
        return V2[C2];
    }

    //Validates a given guess
    public static boolean ValidShot(String pos, board shotboard) {
        positionX x3 = translateX(pos);
        positionY y3 = translateY(pos);

        //Checks whether block has already been hit
        return !shotboard.GotSunk(x3, y3) && !shotboard.GotHit(x3, y3);
    }


    //Generates a random guess
    public static void ComputerGuess(board Cboard) {
        boolean i = true;
        Random rnd = new Random();
        String Canswer = null;

        //Generates random positions until a valid one has been found
        while(i) {
            char P1 = (char) (65 + rnd.nextInt(10));
            char P2 = (char) ('0' + rnd.nextInt(10));
            String temp = P1 + Character.toString(P2);

            if(ValidShot(temp, Cboard)) {
                Canswer = temp;
                i = false;
            }
        }

        //converts string position to enum and sets guess
        positionX x2 = translateX(Canswer);
        positionY y2 = translateY(Canswer);
        Cboard.setGuess(x2, y2);
    }
}
		


	
	


	




