package bredesh.actors.GUIActors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Background extends GUIActor {
    public static int BACKGROUND_X = 0;
    public static int BACKGROUND_Y = 0;
    public static String BACKGROUND_IMAGE = "/Images/background.png";
    public static String BACKGROUND_MENU_IMAGE = "/Images/back_ground_menu.png";

    Background(LocationActor locationActor, Image image) {
        super(locationActor, image);
    }
}
