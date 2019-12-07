package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Boat extends GUIActor {
    public static int BOAT_X = 50;
    public static int BOAT_Y = 300;
    public static String BOAT_IMAGE = "/Images/boat.png";
    private static final int BOAT_SPEED = 3;

    Boat(LocationActor locationActor, Image image) {
        super(locationActor, image, BOAT_SPEED);
    }

    @Override
    public void move(GameManager.Direction direction) {
        if (delayMove()) {
            return;
        }

        if (direction == GameManager.Direction.LEFT) {
            if (!locationActor.isOutOfLeftFrame()) {
                locationActor.moveLeft();
            }
        } else {
            if (!locationActor.isOutOfRightFrame()) {

                locationActor.moveRight();
            }
        }
    }
}
