package bredesh.actors.GUIActors;

import bredesh.actors.GameManager;
import bredesh.actors.LocationActor;
import org.junit.Test;

import static bredesh.actors.GUIActors.Airplane.AIRPLANE_X;
import static bredesh.actors.GUIActors.Airplane.AIRPLANE_Y;
import static org.junit.Assert.*;

public class ParachutistTest {

    @Test
    public void move() {
        GUIActor parachutist = GeneratorActors.generateActors(GameManager.Actor.Parachutist, new LocationActor(AIRPLANE_X, AIRPLANE_Y, 0, 0), null);
        assert parachutist != null;
        assertEquals(AIRPLANE_X, parachutist.locationActor.getX());
        assertEquals(AIRPLANE_Y, parachutist.locationActor.getY());
        parachutist.move();
        assertEquals(AIRPLANE_X, parachutist.locationActor.getX());
        assertEquals(AIRPLANE_Y+1, parachutist.locationActor.getY());
        parachutist.move();
        assertEquals(AIRPLANE_X, parachutist.locationActor.getX());
        assertEquals(AIRPLANE_Y+2, parachutist.locationActor.getY());
    }

    @Test
    public void canMove() {
        GUIActor parachutist = GeneratorActors.generateActors(GameManager.Actor.Parachutist, new LocationActor(AIRPLANE_X, AIRPLANE_Y, 0, 0), null);
        assert parachutist != null;
        for (int i = 0; i < Parachutist.PARACHUTIST_SPEED - 1; i++) {
            assertFalse(parachutist.canMoveBySpeed());
        }
        assertTrue(parachutist.canMoveBySpeed());
    }
}