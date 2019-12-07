package bredesh.actors.GUIActors;

import bredesh.actors.GameConstants;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Airplane extends GUIActor {
    Airplane(LocationActor locationActor, Image image) {
        super(locationActor, image, GameConstants.AIRPLANE_SPEED);
    }

    public void move() {
        if (delayMove())
            return;

        locationActor.moveLeft();

        if (locationActor.isOutOfLeftFrame()) {
            locationActor.resetHorizontal(1000);
        }
    }
}
