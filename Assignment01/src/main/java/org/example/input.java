package org.example;
import org.example.ships.ship;
import java.util.Scanner;
import java.util.Random;
//import java.util.Arrays;
public class input {
    public static void ScanShips(){
        Scanner ShipInput = new Scanner(System.in);
        String Answer = null;
        String Ships[] = {"Battleship", "Carrier", "Cruiser", "Destroyer", "Submarine"};



        for (int i = 0; i < Ships.length; i++) {

            boolean e = true;
            while(e) {
                String msg = "Type in first Position of " + Ships[i];
                String pos1 = Shipscan(msg);

                String msg2 = "Typ in second Position of " + Ships[i];
                String pos2 = Shipscan(msg2);

                if(isValidShip(pos1, pos2)) {
                    createShip(pos1, pos2);
                    e = false;

                }
                else {
                    System.out.println("Invalid input");
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
        
    }
    public static boolean isValidShip(String pos1, String pos2){


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



}
