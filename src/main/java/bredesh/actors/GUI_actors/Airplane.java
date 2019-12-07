package bredesh.actors.GUI_actors;

import bredesh.actors.LocationActor;

import javax.swing.*;
import java.awt.*;

public class Airplane extends GUI_Actor {
    private int moveTimes = 0;
    private int countSpeed;

    public Airplane(LocationActor locationActor, Image image) {
        super(locationActor, image);
        countSpeed = 1000000;
    }

    public boolean delayMove() {
        if (countSpeed-- == 0) {countSpeed = 1000000;}
        return countSpeed % (10) != 0;
    }

    public void moveAirplane() {
        if (delayMove())
            return;

        locationActor.moveLeft();

        if (locationActor.isOutOfFrame()) {
            locationActor.resetHorizontal(1000);
        }
    }
}
