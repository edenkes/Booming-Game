package bredesh.actors.GUIActors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Sea extends GUIActor {
    public static int SEA_X = 0;
    public static int SEA_Y = 400;
    public static String SEA_IMAGE = "/Images/sea.png";
    private static final int SEA_SPEED = 50;

    Sea(LocationActor locationActor, Image image) {
        super(locationActor, image, SEA_SPEED);
    }

    @Override
    public boolean isOutOfFrame() {
        return locationActor.isOutOfLeftFrame();
    }
}
