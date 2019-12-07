package bredesh.actors.GUI_actors;

import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Boat extends GUI_Actor {
    private int countSpeed;

    public Boat(LocationActor locationActor, Image image) {
        super(locationActor, image);
        countSpeed = 0;
    }

    public boolean delayMove(){
        if (++countSpeed % GameConstants.BOAT_SPEED == 0){
            countSpeed = 0;
            return false;
        }
        return true;
    }

    @Override
    public void move(GameManager.Direction direction) {
        if (delayMove()){
            return;
        }

        if (direction == GameManager.Direction.LEFT){
            locationActor.moveLeft();
        }else {
            locationActor.moveRight();
        }
    }
}
