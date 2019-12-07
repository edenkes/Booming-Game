package bredesh.actors.GUI_actors;


import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;

import java.awt.*;

public abstract class GUI_Actor {

    LocationActor locationActor;
    private Image image;

    public GUI_Actor(LocationActor locationActor, Image image){
        this.locationActor = locationActor;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    void setImage(Image image) {
        this.image = image;
    }


    public LocationActor getLocationActor() {
        return locationActor;
    }

    public void setLocationActor(LocationActor locationActor) {
        this.locationActor = locationActor;
    }

    public void move(GameManager.Direction direction){}

    public void move(){}
}
