package bredesh.actors.GUI_actors;


import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public abstract class GUI_Actor {
    private final int actorSpeed;
    private int countSpeed;
    LocationActor locationActor;
    private Image image;

    GUI_Actor(LocationActor locationActor, Image image, int actorSpeed) {
        this.locationActor = locationActor;
        this.image = image;
        countSpeed = 0;
        this.actorSpeed = actorSpeed;
    }

    GUI_Actor(LocationActor locationActor, Image image) {
        this.locationActor = locationActor;
        this.image = image;
        countSpeed = 0;
        this.actorSpeed = 1;
    }

    public Image getImage() {
        return image;
    }

    public LocationActor getLocationActor() {
        return locationActor;
    }

    boolean delayMove() {
        if (++countSpeed % actorSpeed == 0) {
            countSpeed = 0;
            return false;
        }
        return true;
    }

    public void move(GameManager.Direction direction) {
    }

    public void move() {
    }
}
