package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public class Boat extends GUIActor {
    public static int BOAT_X = 50;
    public static int BOAT_Y = 300;
    public static String BOAT_IMAGE = "/Images/boat.png";
    private static final int BOAT_SPEED = 2;

    Boat(LocationActor locationActor, Image image) {
        super(locationActor, image, BOAT_SPEED);
    }
}
