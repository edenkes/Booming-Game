package bredesh.actors.GUIActors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Sea extends GUIActor {
    public static int SEA_X = 0;
    public static int SEA_Y = 400;
    public static String SEA_IMAGE = "/Images/sea.png";
    private static final int SEA_SPEED = 100;

    Sea(LocationActor locationActor, Image image) {
        super(locationActor, image, SEA_SPEED);
    }

    public void move() {
        if (delayMove())
            return;

        locationActor.moveLeft();

        if (locationActor.isOutOfLeftFrame()) {
            locationActor.resetToInitial();
        }
    }

}
