package bredesh.actors;

import bredesh.actors.GUIActors.Airplane;
import bredesh.actors.GUIActors.Background;
import bredesh.actors.GUIActors.Boat;
import bredesh.actors.GUIActors.Sea;

import java.awt.*;

public class GeneratorLocationActors {
    public static LocationActor generateLocationActors(GameManager.Actor actor, Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        switch (actor) {
            case Background:
                return new LocationActor(Background.BACKGROUND_X, Background.BACKGROUND_Y, width, height);
            case Sea:
                return new LocationActor(Sea.SEA_X, Sea.SEA_Y, width, height);
            case Boat:
                return new LocationActor(Boat.BOAT_X, Boat.BOAT_Y, width, height);
            case Airplane:
                return new LocationActor(Airplane.AIRPLANE_X, Airplane.AIRPLANE_Y, width, height);
            default:
                return null;
        }
    }

    static LocationActor generateLocationActors(LocationActor locationActor, Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        return new LocationActor(locationActor.getX(), locationActor.getY(), width, height);
    }
}
