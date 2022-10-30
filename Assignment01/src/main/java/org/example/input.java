package org.example;
import java.util.Scanner;
import java.util.Random;
import java.lang.*;

public class input {
    public static void ScanShips(){
        String Ships[] = {"Battleship", "Carrier", "Patrol", "Submarine"};

        for (int i = 0; i < Ships.length; i++) {

            boolean e = true;
            int counter = 1;
            int counter1 = 1;
            int counter2 = 1;

            while(e) {
                String msg = "Type in first Position of " + Ships[i];
                String pos1 = Shipscan(msg);

                String msg2 = "Typ in second Position of " + Ships[i];
                String pos2 = Shipscan(msg2);

                if(isValidShip(pos1, pos2, Ships[i]) && Ships[i] == "Battleship") {
                    createShip(pos1,pos2);
                    if(counter == 2) {
                        e = false;
                        counter = 1;
                    }
                    else {
                        e = true;
                        ++counter;
                    }
                }
                else if(isValidShip(pos1, pos2, Ships[i]) && Ships[i] == "Patrol") {
                    createShip(pos1,pos2);
                    if(counter1 == 4) {
                        e = false;
                        counter1 = 1;
                    }
                    else {
                        e = true;
                        ++counter1;
                    }
                }
                else if(isValidShip(pos1, pos2, Ships[i]) && Ships[i] == "Submarine") {
                    createShip(pos1,pos2);
                    if(counter2 == 3) {
                        e = false;
                        counter2 = 1;
                    }
                    else {
                        e = true;
                        ++counter2;
                    }
                }
                else if(isValidShip(pos1, pos2, Ships[i]) && Ships[i] == "Carrier") {
                    createShip(pos1, pos2);
                    e = false;
                }
                else {
                    System.out.println("Invalid input, try again");
                }
            }
        }




    }

    public static void placeComputerShips(board computerBoard){
        //place computers ships
    }

    public static void placePlayerShips(board playerBoard){

    }
    public static void createShip(String c1, String c2){
        System.out.println("Ship created from: " + c1 + " to " + c2);
    }
    public static boolean isValidShip(String pos1, String pos2, String typus){
        char[] Spos = pos1.toCharArray();
        char[] Epos = pos2.toCharArray();
        int leng = getlen(Spos,Epos);
        if(!(leng == isRightShip(typus))) {
            System.out.println("Not vaild Ship");
            return false;
        }
        /*      !!!!! How to translete in enum && block checkers not static
        for(int i = 0; i<leng; ++i) {

            board.IsEmpty(Spos, Epos);
            }
        }
        */

        return true;

    }
    public static String Shipscan(String msg) {
        Scanner Input = new Scanner(System.in);
        String answer = null;
        boolean i = true;
        //Checks if valid input according to PRE-condition
        System.out.println(msg);
        while(i) {
            String pos = Input.nextLine();
            if((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 72
                    || (int) pos.charAt(1) < 49 || (int) pos.charAt(1) > 56) {
                System.out.println("Please type in a correct Position");
            }
            else if(!Guess.ValidShot(pos)) {
                System.out.println("Please type in a valid Position");
            }
            else {
                i = false;
                answer = pos;
            }
        }
        Input.close();
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
        else if(typeofShip == "Battleship") leng = 4;
        else if(typeofShip == "Submarine") leng = 3;
        else if(typeofShip == "Patrol") leng = 2;
        else throw new IllegalArgumentException("Not a ship!");
        return leng;
    }

}
