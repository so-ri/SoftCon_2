package org.example.ships;

public class coordinate {
    private positionY CY;
    private positionX CX;
    private state State;

    public coordinate(positionX v1, positionY v2, state v3){
        this.CX = v1;
        this.CY = v2;
        this.State = v3;
    }

    public positionX getX(){
        positionX PX = this.CX;
        return PX;
    }

    public positionY getY(){
        positionY PY = this.CY;
        return PY;
    }

    public void hit(){
        this.State = state.STRIKED;
    }

    public Boolean ishit(){
        if(this.State == state.STRIKED){
            return true;
        }
        else{return false;}
    }


}
