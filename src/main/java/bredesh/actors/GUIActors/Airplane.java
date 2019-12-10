package bredesh.actors.GUIActors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Airplane extends GUIActor {
    public static int AIRPLANE_X = 650;
    public static int AIRPLANE_Y = 45;
    public static String AIRPLANE_IMAGE = "/Images/plane.png";
    private static final int AIRPLANE_SPEED = 10;

    Airplane(LocationActor locationActor, Image image) {
        super(locationActor, image, AIRPLANE_SPEED);
    }

    public void move() {
        locationActor.moveLeft();
    }

    @Override
    public boolean isOutOfFrame() {
        return locationActor.isOutOfLeftFrame();
    }

    @Override
    public void resetLocation() {
        locationActor.resetHorizontal(1000);
    }
}
