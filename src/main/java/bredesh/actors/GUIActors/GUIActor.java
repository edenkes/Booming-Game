package bredesh.actors.GUIActors;


import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public abstract class GUIActor {
    private final int actorSpeed;
    private int countSpeed;
    LocationActor locationActor;
    private Image image;

    GUIActor(LocationActor locationActor, Image image, int actorSpeed) {
        this.locationActor = locationActor;
        this.image = image;
        this.actorSpeed = actorSpeed;
        countSpeed = 0;
    }

    GUIActor(LocationActor locationActor, Image image) {
        this.locationActor = locationActor;
        this.image = image;
        this.actorSpeed = 1;
        countSpeed = 0;
    }

    public Image getImage() {
        return image;
    }

    public LocationActor getLocationActor() {
        return locationActor;
    }

    public boolean canMoveBySpeed() {
        if (++countSpeed % actorSpeed == 0) {
            countSpeed = 0;
            return true;
        }
        return false;
    }

    public void move(GameManager.Direction direction) {
    }

    public void move() {
    }

    public boolean canMoveByDirection(GameManager.Direction direction){
        return ((direction == GameManager.Direction.LEFT && !locationActor.isOutOfLeftFrame()) ||
                (direction == GameManager.Direction.RIGHT && !locationActor.isOutOfRightFrame()));
    }

    public boolean isOutOfFrame() {
        return false;
    }

    public void resetLocation() {
        locationActor.resetToInitial();
    }
}
