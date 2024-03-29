package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;
import bredesh.actors.GeneratorLocationActors;
import bredesh.actors.LocationActor;

import java.awt.*;

public class GeneratorActors {
    public static GUIActor generateActors(GameManager.Actor actor, LocationActor locationActors, Image imageActors){
        switch (actor){
            case Background:
                return new Background(locationActors, imageActors);
            case Sea:
                return new Sea(locationActors, imageActors);
            case Boat:
                return new Boat(locationActors, imageActors);
            case Airplane:
                return new Airplane(locationActors, imageActors);
            case Parachutist:
                return new Parachutist(locationActors, imageActors);
            default:
                return null;
        }
    }

    public static GUIActor generateActors(GameManager.Actor actor){
        Image imageActors = GeneratorImageActors.generateImageActors(actor);
        LocationActor locationActors = GeneratorLocationActors.generateLocationActors(actor, imageActors);
        return generateActors(actor, locationActors, imageActors);
    }
}
