package bredesh.actors.GUIActors;

import bredesh.actors.GameConstants;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Parachutist extends GUIActor {

    Parachutist(LocationActor locationActor, Image image) {
        super(locationActor, image, GameConstants.PARACHUTIST_SPEED);
    }

    public void move() {
        if (delayMove())
            return;

        locationActor.moveDown();
    }
}
