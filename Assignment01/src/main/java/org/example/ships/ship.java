package org.example.ships;
import java.lang.*;
import org.example.blockshiptype;

public class ship {

    private coordinate[] position;
    private int len;

    public ship(positionX X1, positionY Y1, positionX X2, positionY Y2){

        //change values in case they are not given in the correct order

        if(X1.ordinal() > X2.ordinal()){
            positionX temp = X1;
            X1 = X2;
            X2 = temp;
        }

        if(Y1.ordinal() > Y2.ordinal()){
            positionY temp = Y1;
            Y1 = Y2;
            Y2 = temp;
        }

        // vertical ships

        if(X1 == X2){
            //get length and initialize array position (filled with coordinates) with length
            this.len = Math.abs(Y1.ordinal() - Y2.ordinal())+1;
            this.position = new coordinate[this.len];

            int positionINDX = 0;
            for(int i = Y1.ordinal(); i<= Y2.ordinal(); i++){
                positionY[] y = positionY.values();
                position[positionINDX] = new coordinate(X1, y[i], state.NOTSTRIKED);
                positionINDX += 1;
            }
        }

        //horizontal ships

        if(Y1 == Y2){
            this.len = Math.abs(X1.ordinal() - X2.ordinal())+1;
            this.position = new coordinate[this.len];
            int positionINDX = 0;
            for(int i = X1.ordinal(); i<= X2.ordinal(); i++){
                positionX[] x = positionX.values();
                position[positionINDX] = new coordinate(x[i], Y1, state.NOTSTRIKED);
                positionINDX += 1;
            }
        }
    }

    public void hitShip(positionX X1, positionY Y1){
        //search for the coordinate which is going to be hit
        for(coordinate c: position){
            if(c.getX() == X1 && c.getY() == Y1){
                c.hit();
            }
        }
    }

    public Boolean isDown(){
        int numHits = 0;
        for(coordinate c: position){
            if (c.ishit()){
                numHits++;
            }
        }
        return numHits == this.len;
    }

    public blockshiptype getShipType(){ //
        if (this.len == 2){return blockshiptype.PATROL;}
        if (this.len == 3){return blockshiptype.SUBMARINE;}
        if (this.len == 4){return blockshiptype.BATTLESHIP;}
        if (this.len == 6){return blockshiptype.CARRIER;}
        else{return blockshiptype.EMPTY;}
    }

    public positionX[] getXcoordinates(){
        positionX[] X = new positionX[this.len];
        int idx = 0;
        for(coordinate c: position){
            X[idx] = c.getX();
            idx++;
        }
        return X;
    }

    public positionY[] getYcoordinates(){
        positionY[] Y = new positionY[this.len];
        int idx = 0;
        for(coordinate c: position){
            Y[idx] = c.getY();
            idx++;
        }
        return Y;
    }


}
