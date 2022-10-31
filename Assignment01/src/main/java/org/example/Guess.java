package org.example;

import java.util.Random;
import java.util.Scanner;
import org.example.board;
import org.example.ships.positionX;
import org.example.ships.positionY;

public class Guess {

    //PRE: Inputs a Position between A1 and H8

    public static void PlayerGuess(board playerBoard) {
        Scanner Input = new Scanner(System.in);
        String answer = null;
        boolean i = true;

        //Checks if valid input according to PRE-condition
        System.out.println("Type in new Guess: ");
        while (i) {
            //converts String
            String pos2 = Input.nextLine();
            String pos1 = pos2.replaceAll("\\s+", "");
            String pos = pos1.toUpperCase();


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

        positionX x1 = translateX(answer);
        positionY y1 = translateY(answer);


        playerBoard.setGuess(x1, y1);


    }

    public static positionX translateX(String min) {
        int C1 = (int) min.charAt(0) - 65;
        positionX[] V1 = positionX.values();
        positionX x1 = V1[C1];
        return x1;
    }

    public static positionY translateY(String min) {
        int C2 = Character.getNumericValue(min.charAt(1));
        positionY[] V2 = positionY.values();
        positionY y1 = V2[C2];
        return y1;
    }


    public static boolean ValidShot(String pos, board shotboard) {
        positionX x3 = translateX(pos);
        positionY y3 = translateY(pos);

        if (shotboard.GotSunk(x3, y3) || shotboard.GotHit(x3, y3)) {
            return false;
        } else return true;
    }


    //PRE: Generates an Input Position between A1 and H8
    public static void ComputerGuess(board Cboard) {

        Random rnd = new Random();
        char P1 = (char) (65 + rnd.nextInt(10));
        char P2 = (char) ('0' + rnd.nextInt(10));


        String Canswer = P1 + Character.toString(P2);

        positionX x2 = translateX(Canswer);
        positionY y2 = translateY(Canswer);

        Cboard.setGuess(x2, y2);
    }
}
		


	
	


	




