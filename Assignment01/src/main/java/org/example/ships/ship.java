package org.example.ships;

import java.util.Arrays;

public class ship {
    private int len;
    private coordinate[] position;
    // positionY[] Y = positionY.values();
    // positionX[] X = positionX.values();



    public ship(String C1, String C2){
        // Separate Strings to CharArrays to get the values for enum
        char[] StartPosition = C1.toCharArray();
        char[] EndPosition = C2.toCharArray();
        // get the type of the ship and store it in len
        this.len = getlen(StartPosition, EndPosition);
        this.position = new coordinate[len];
        // safe enum values in array
        positionY[] Y = positionY.values();
        positionX[] X = positionX.values();
        //in case of the same X-Coordinates
        if(StartPosition[0] == EndPosition[0]){
            //position X safe in a constant variable
            final int Xconstant = StartPosition[0] - 65;
            //verbessern mit turnYchar
            int Yvariable = Character.getNumericValue(StartPosition[1]);
            for(int i = 0; i < this.len; i++){
                positionX V1 = X[Xconstant];
                positionY V2 = Y[Yvariable];
                state V3 = state.NOTSTRIKED;
                position[i] = new coordinate(V1,V2,V3);
                Yvariable += 1;
            }
        }
        if(StartPosition[1] == EndPosition[1]){
            final int Yconstant = turnYCharToInt(StartPosition[1]);
            int Xvariable = turnXCharToInt(StartPosition[0]);
            for(int i = 0; i < this.len; i++){
                positionX V1 = X[Xvariable];
                positionY V2 = Y[Yconstant];
                state V3 = state.NOTSTRIKED;
                position[i] = new coordinate(V1,V2,V3);
                Xvariable += 1;
            }
        }
    }

    //Funktioniert momentan nur falls Koordinaten von Links (start) nach rechts (ende) (horizontal eingegeben werden)
    //BZW falls von oben (start) nach unten (ende) (vertikal)

    private int getlen(char[] start, char[] end ){
        // In Case of same X-Coordinates
        if(start[0] == end[0]){
            int Y1 = Character.getNumericValue(start[1]);
            int Y2 = Character.getNumericValue(end[1]);
            int length = Y2 - Y1;
            return length+1;
        }
        // In Case of same Y-Coordinates
        else if(start[1] == end[1]){
            int X1 = start[0] - 65;
            int X2 = end[0] - 65;
            int length = X2 - X1;
            return length+1;
        }
        else {throw new IllegalArgumentException("Ships can only be initialized vertical or horizontal!");}
    }

    public String getTypeAsString() {
        if (this.len == 6) {return "Carrier";}
        else if (this.len == 4) {return "Battleship";}
        else if (this.len == 3) {return "Submarine";}
        else if (this.len == 2) {return "Patrol Boat";}
        else return "invalid Ship";
    }

    public void hitShip(String Coordinate){
        char[] Strike = Coordinate.toCharArray();
        int[] Strikeconverted = new int[2];
        positionY[] Y = positionY.values();
        positionX[] X = positionX.values();
        Strikeconverted[0] = turnXCharToInt(Strike[0]);
        Strikeconverted[1] = turnYCharToInt(Strike[1]);
        for(int i = 0; i<this.len ; i++){
            if(position[i].getX() == X[Strikeconverted[0]] && position[i].getY() == Y[Strikeconverted[1]] ){
            position[i].hit();
            System.out.println("Ship has been hit");
            }
        }
    }

    public Boolean isDown(){
        int hits = 0;
        for(int i = 0; i< this.len; i++){
        if (position[i].ishit()){hits += 1;}
        if (hits == this.len){boolean a = true; return true;}
        }
        return false;
    }

    private int turnXCharToInt(char c){
        int i = c - 65;
        return i;
    }
    private int turnYCharToInt(char c){
        int i = Character.getNumericValue(c);
        return i;
    }


}
