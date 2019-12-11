package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;
import org.junit.Test;

import static bredesh.actors.GUIActors.Airplane.*;
import static org.junit.Assert.*;

public class AirplaneTest {

    @Test
    public void move() {
        GUIActor airplane = GeneratorActors.generateActors(GameManager.Actor.Airplane, new LocationActor(AIRPLANE_X, AIRPLANE_Y, 0, 0), null);
        assert airplane != null;
        assertEquals(AIRPLANE_X, airplane.locationActor.getX());
        assertEquals(AIRPLANE_Y, airplane.locationActor.getY());
        airplane.moveLeft();
        assertEquals(AIRPLANE_X-1, airplane.locationActor.getX());
        assertEquals(AIRPLANE_Y, airplane.locationActor.getY());
        airplane.moveLeft();
        assertEquals(AIRPLANE_X-2, airplane.locationActor.getX());
        assertEquals(AIRPLANE_Y, airplane.locationActor.getY());
    }

    @Test
    public void isOutOfFrame() {
        GUIActor airplane = GeneratorActors.generateActors(GameManager.Actor.Airplane, new LocationActor(LocationActor.OUT_OF_FRAME_LEFT, 0, 0, 0), null);
        assert airplane != null;
        airplane.moveLeft();
        assertTrue(airplane.isOutOfFrame());
        airplane.resetLocation();
        assertFalse(airplane.isOutOfFrame());
    }

    @Test
    public void resetLocation() {
        GUIActor airplane = GeneratorActors.generateActors(GameManager.Actor.Airplane, new LocationActor(AIRPLANE_X, AIRPLANE_Y, 0, 0), null);
        assert airplane != null;
        airplane.resetLocation();
        assertEquals(AIRPLANE_X_START, airplane.locationActor.getX());
        assertEquals(AIRPLANE_Y, airplane.locationActor.getY());
    }
}