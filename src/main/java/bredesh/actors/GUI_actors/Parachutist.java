package bredesh.actors.GUI_actors;

import bredesh.actors.LocationActor;

import java.awt.*;

public class Parachutist extends GUI_Actor {
    private int countSpeed;

    Parachutist(LocationActor locationActor, Image image) {
        super(locationActor,  image);
        countSpeed = 0;
    }

    private boolean delayMove(){
        if (countSpeed == 0) countSpeed = 1000000;
        countSpeed--;
        return (countSpeed % (10)) != 0;
    }

    public void move() {
        if(delayMove())
            return;

        locationActor.moveDown();
    }
}
