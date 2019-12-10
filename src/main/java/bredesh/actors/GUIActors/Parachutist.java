package bredesh.actors.GUIActors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Parachutist extends GUIActor {
    public static String PARACHUTIST_IMAGE = "/Images/parachutist.png";
    private static final int PARACHUTIST_SPEED = 10;

    Parachutist(LocationActor locationActor, Image image) {
        super(locationActor, image, PARACHUTIST_SPEED);
    }

    public void move() {
        locationActor.moveDown();
    }
}
