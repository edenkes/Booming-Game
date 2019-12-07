package bredesh.actors.GUI_actors;

import bredesh.actors.GameConstants;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Parachutist extends GUI_Actor {

    Parachutist(LocationActor locationActor, Image image) {
        super(locationActor, image, GameConstants.PARACHUTIST_SPEED);
    }

    public void move() {
        if (delayMove())
            return;

        locationActor.moveDown();
    }
}
