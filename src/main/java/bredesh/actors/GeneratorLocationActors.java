package bredesh.actors;

import java.awt.*;

public class GeneratorLocationActors {
    public static LocationActor generateLocationActors(GameManager.Actor actor, Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        switch (actor) {
            case Background:
                return new LocationActor(GameConstants.BACKGROUND_X, GameConstants.BACKGROUND_Y, width, height);
            case Sea:
                return new LocationActor(GameConstants.SEA_X, GameConstants.SEA_Y, width, height);
            case Boat:
                return new LocationActor(GameConstants.BOAT_X, GameConstants.BOAT_Y, width, height);
            case Airplane:
                return new LocationActor(GameConstants.AIRPLANE_X, GameConstants.AIRPLANE_Y, width, height);
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
