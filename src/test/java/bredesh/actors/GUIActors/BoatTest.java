package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;
import org.junit.Test;

import static bredesh.actors.GUIActors.Boat.*;
import static org.junit.Assert.*;

public class BoatTest {

    @Test
    public void moveLeft() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(BOAT_X, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertEquals(BOAT_X, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.LEFT);
        assertEquals(BOAT_X - 1, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.LEFT);
        assertEquals(BOAT_X - 2, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
    }

    @Test
    public void moveRight() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(BOAT_X, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertEquals(BOAT_X, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.RIGHT);
        assertEquals(BOAT_X + 1, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.RIGHT);
        assertEquals(BOAT_X + 2, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.resetLocation();
        assertEquals(BOAT_X, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
    }

    @Test
    public void moveRightAndLeft() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(BOAT_X, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertEquals(BOAT_X, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.RIGHT);
        assertEquals(BOAT_X + 1, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
        boat.move(GameManager.Direction.LEFT);
        assertEquals(BOAT_X, boat.locationActor.getX());
        assertEquals(BOAT_Y, boat.locationActor.getY());
    }

    @Test
    public void canMove() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(BOAT_X, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertTrue(boat.canMoveByDirection(GameManager.Direction.RIGHT));
        assertTrue(boat.canMoveByDirection(GameManager.Direction.LEFT));
    }

    @Test
    public void cantMoveLeft() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(LocationActor.OUT_OF_FRAME_LEFT, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertTrue(boat.canMoveByDirection(GameManager.Direction.RIGHT));
        assertTrue(boat.canMoveByDirection(GameManager.Direction.LEFT));
        boat.move(GameManager.Direction.LEFT);
        assertTrue(boat.canMoveByDirection(GameManager.Direction.RIGHT));
        assertFalse(boat.canMoveByDirection(GameManager.Direction.LEFT));
    }

    @Test
    public void cantMoveRight() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(LocationActor.OUT_OF_FRAME_RIGHT, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertTrue(boat.canMoveByDirection(GameManager.Direction.RIGHT));
        assertTrue(boat.canMoveByDirection(GameManager.Direction.LEFT));
        boat.move(GameManager.Direction.RIGHT);
        assertFalse(boat.canMoveByDirection(GameManager.Direction.RIGHT));
        assertTrue(boat.canMoveByDirection(GameManager.Direction.LEFT));
    }

    @Test
    public void canMoveBySpeed() {
        GUIActor boat = GeneratorActors.generateActors(GameManager.Actor.Boat, new LocationActor(LocationActor.OUT_OF_FRAME_RIGHT, BOAT_Y, 0, 0), null);
        assert boat != null;
        assertFalse(boat.canMoveBySpeed());
        assertTrue(boat.canMoveBySpeed());
        assertFalse(boat.canMoveBySpeed());
    }
}