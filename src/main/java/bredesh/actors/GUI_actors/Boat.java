package bredesh.actors.GUI_actors;

import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Boat extends GUI_Actor {
    Boat(LocationActor locationActor, Image image) {
        super(locationActor, image, GameConstants.BOAT_SPEED);
    }

    @Override
    public void move(GameManager.Direction direction) {
        if (delayMove()) {
            return;
        }

        if (direction == GameManager.Direction.LEFT) {
            if (locationActor.isOutOfLeftFrame()) {
                locationActor.moveLeft();
            }
        } else {
            if (locationActor.isOutOfRightFrame()) {

                locationActor.moveRight();
            }
        }
    }
}
