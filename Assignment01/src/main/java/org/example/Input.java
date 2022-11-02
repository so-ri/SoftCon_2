package org.example;
import java.util.Scanner;
import java.util.Random;
import org.example.ships.*;


public class Input {
    static boolean ComputerIsScanning = false;
    static final String Ships[] = {"Carrier", "first Battleship", "second Battleship",
            "first Submarine", "second Submarine","third Submarine", "first Patrolboat",
            "second Patrolboat", "third Patrolboat", "fourth Patrolboat"};
    public static void ScanPlayerShips(board Playerboard){
        /*
        Playerboard.createShip(positionX.A, positionY.ZERO, positionX.A, positionY.FIVE);
        Playerboard.createShip(positionX.B, positionY.ZERO, positionX.B, positionY.THREE);
        Playerboard.createShip(positionX.B, positionY.FIVE, positionX.B, positionY.EIGHT);
        Playerboard.createShip(positionX.C, positionY.ZERO, positionX.C, positionY.TWO);
        Playerboard.createShip(positionX.C, positionY.FOUR, positionX.C, positionY.SIX);
        Playerboard.createShip(positionX.D, positionY.ZERO, positionX.D, positionY.TWO);
        Playerboard.createShip(positionX.D, positionY.FOUR, positionX.D, positionY.FIVE);
        Playerboard.createShip(positionX.D, positionY.SEVEN, positionX.D, positionY.EIGHT);
        Playerboard.createShip(positionX.E, positionY.ZERO, positionX.E, positionY.ONE);
        Playerboard.createShip(positionX.E, positionY.THREE, positionX.E, positionY.FOUR);
        */

        for (int i = 0; i < Ships.length; i++) {
            boolean e = true;
            while(e) {
                String msg = "Type in Position of " + Ships[i];
                String scan = Shipscan(msg, Playerboard);

                String pos1 = scan.substring(0,2);
                String pos2 = scan.substring(3);

                if(isValidShip(pos1,pos2, Ships[i], Playerboard)) {
                    createShip(pos1,pos2, Ships[i], Playerboard);
                    e = false;
                }
                else {
                    System.out.println("Invalid input, try again");
                }
            }
        }
    }

    public static void ScanComputerShips(board Computerboard) {
        Random rnd = new Random();
        ComputerIsScanning = true;
        int len;
        char F1;
        char F2;
        String pos2;
        for (int i = 0; i < Ships.length; i++) {
            boolean e = true;
            while(e) {
                char P1 = (char) (65 + rnd.nextInt(10));
                char P2 = (char) ('0' + rnd.nextInt(10));
                String pos1 = P1 + Character.toString(P2);

                int dir = rnd.nextInt(2);
                    if(dir == 0) {
                        len = isRightShip(Ships[i]);
                        F1 = (char) ((int) P1 + len - 1);
                        pos2 = F1 + Character.toString(P2);
                        if (onBoard(pos2) && isValidShip(pos1, pos2, Ships[i], Computerboard)) {
                            createShip(pos1, pos2, Ships[i], Computerboard);
                            e = false;
                            break;
                        }


                    }
                    else {
                        len = isRightShip(Ships[i]);
                        F2 = (char) ((int) P2 + len - 1);
                        pos2 = P1 + Character.toString(F2);

                        if (onBoard(pos2) && isValidShip(pos1, pos2, Ships[i], Computerboard)) {
                            createShip(pos1, pos2, Ships[i], Computerboard);
                            e = false;
                            break;
                        }
                    }
            }
        }
        ComputerIsScanning = false;
    }


    public static boolean onBoard(String pos) {
        if((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 74
                || (int) pos.charAt(1) < 48 || (int) pos.charAt(1) > 57) {
            return false;
        }
        else return true;
    }
    public static void createShip(String c1, String c2, String typus, board shipboard){
        System.out.println(typus +" Ship created from: " + c1 + " to " + c2);
        positionX x1 = Guess.translateX(c1);
        positionY y1 = Guess.translateY(c1);

        positionX x2 = Guess.translateX(c2);
        positionY y2 = Guess.translateY(c2);

        shipboard.createShip(x1,y1,x2,y2);

    }
    public static boolean isValidShip(String pos1, String pos2, String typus, board board2){
        char[] Spos = pos1.toCharArray();
        char[] Epos = pos2.toCharArray();
        if(!(Spos[0]==Epos[0]||Spos[1]==Epos[1])) {
            System.out.println("Ship cant be diagonal");
            return false;
        }
        //NOT WHEN COMPUTER DOES IT
        if(getlen(Spos,Epos) != isRightShip(typus)) {
            if(!ComputerIsScanning) System.out.println("Ship doesnt have proper length");
            return false;
        }

        positionX x1 = Guess.translateX(pos1);
        positionY y1 = Guess.translateY(pos1);
        positionX x2 = Guess.translateX(pos2);
        positionY y2 = Guess.translateY(pos2);

        ship test = new ship(x1,y1,x2,y2);
        positionX[] xes = test.getXcoordinates();
        positionY[] yes = test.getYcoordinates();
        for(int i = 0; i < xes.length; ++i) {
            if(!board2.IsEmpty(xes[i], yes[i])) {
                return false;
            }
        }
        return true;
    }


    public static String Shipscan(String msg, board Board1) {
        Scanner Input = new Scanner(System.in);
        String answer = null;
        boolean i = true;
        //Checks if valid input according to PRE-condition
        System.out.println(msg);
        while(i) {
            String pos2 = Input.nextLine();
            String pos1 = pos2.replaceAll("\\s+","");
            String pos = pos1.toUpperCase();
            if(!pos.contains(",")) {
                System.out.println("Positions need to be seperated by a coma");
            }
            else if(pos.length()!=5) {
                System.out.println("Please type in a Position on the Board");
            }
            else if((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 74
                    || (int) pos.charAt(1) < 48 || (int) pos.charAt(1) > 57) {
                System.out.println("Please type in a Position on the Board");
            }
            else if((int) pos.charAt(3) < 65 || (int) pos.charAt(3) > 74
                    || (int) pos.charAt(4) < 48 || (int) pos.charAt(4) > 57) {
                System.out.println("Please type in a Position on the Board");
            }
            else if(!(Guess.ValidShot(pos.substring(0,2), Board1))||!(Guess.ValidShot(pos.substring(3), Board1))) {
                System.out.println("Please type in a valid Position");
            }
            else {
                i = false;
                answer = pos;
            }
        }
        return answer;
    }

    private static int getlen(char[] start, char[] end ){
        // In Case of same X-Coordinates
        if(start[0] == end[0]){
            int Y1 = Character.getNumericValue(start[1]);
            int Y2 = Character.getNumericValue(end[1]);
            int length = Math.abs(Y2-Y1);

            return length+1;
        }
        // In Case of same Y-Coordinates
        else if(start[1] == end[1]){
            int X1 = start[0] - 65;
            int X2 = end[0] - 65;
            int length = Math.abs(X2-X1);
            return length+1;
        }
        else {throw new IllegalArgumentException("Ships can only be initialized vertical or horizontal!");}
    }

    private static int isRightShip(String typeofShip){
        int leng = 0;
        if(typeofShip == "Carrier") leng = 6;
        else if(typeofShip.contains("Battleship")) leng = 4;
        else if(typeofShip.contains("Submarine")) leng = 3;
        else if(typeofShip.contains("Patrol")) leng = 2;
        return leng;
    }

}
