package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;
import org.junit.Test;

import static bredesh.actors.GUIActors.Sea.SEA_X;
import static bredesh.actors.GUIActors.Sea.SEA_Y;
import static org.junit.Assert.*;

public class SeaTest {
    @Test
    public void move() {
        GUIActor sea = GeneratorActors.generateActors(GameManager.Actor.Sea, new LocationActor(SEA_X, SEA_Y, 0, 0), null);
        assert sea != null;
        assertEquals(SEA_X, sea.locationActor.getX());
        assertEquals(SEA_Y, sea.locationActor.getY());
        sea.move();
        assertEquals(SEA_X-1, sea.locationActor.getX());
        assertEquals(SEA_Y, sea.locationActor.getY());
        sea.move();
        assertEquals(SEA_X-2, sea.locationActor.getX());
        assertEquals(SEA_Y, sea.locationActor.getY());
    }

    @Test
    public void isOutOfFrame() {
        GUIActor sea = GeneratorActors.generateActors(GameManager.Actor.Sea, new LocationActor(LocationActor.OUT_OF_FRAME_LEFT, 0, 0, 0), null);
        assert sea != null;
        sea.move();
        assertTrue(sea.isOutOfFrame());
        sea.resetLocation();
        assertFalse(sea.isOutOfFrame());
    }

    @Test
    public void resetLocation() {
        GUIActor sea = GeneratorActors.generateActors(GameManager.Actor.Sea, new LocationActor(SEA_X, SEA_Y, 0, 0), null);
        assert sea != null;
        sea.resetLocation();
        assertEquals(SEA_X, sea.locationActor.getX());
        assertEquals(SEA_Y, sea.locationActor.getY());
    }
}