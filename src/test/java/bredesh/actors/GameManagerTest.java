package bredesh.actors;

import bredesh.actors.GUIActors.*;
import org.junit.Before;
import org.junit.Test;

import static bredesh.actors.GameManager.GameStatus.*;
import static bredesh.actors.GameManager.*;
import static org.junit.Assert.*;

public class GameManagerTest {

    private GameManager mangerGame;

    @Before
    public void setUp() {
        mangerGame = new GameManager(
                GeneratorActors.generateActors(GameManager.Actor.Background),
                GeneratorActors.generateActors(GameManager.Actor.Sea),
                GeneratorActors.generateActors(GameManager.Actor.Boat),
                GeneratorActors.generateActors(GameManager.Actor.Airplane));

    }

    @Test
    public void resetGame() {
        mangerGame.resetGame();
        assertEquals(RUNNING, mangerGame.getGameStatus());
        assertEquals(SCORE, mangerGame.getScore());
        assertEquals(HEARTS, mangerGame.getHearts());

        assertEquals(Boat.BOAT_X, mangerGame.getActor(Actor.Boat).getLocationActor().getX());
        assertEquals(Boat.BOAT_Y, mangerGame.getActor(Actor.Boat).getLocationActor().getY());

        assertEquals(Airplane.AIRPLANE_X, mangerGame.getActor(Actor.Airplane).getLocationActor().getX());
        assertEquals(Airplane.AIRPLANE_Y, mangerGame.getActor(Actor.Airplane).getLocationActor().getY());

        assertEquals(Sea.SEA_X, mangerGame.getActor(Actor.Sea).getLocationActor().getX());
        assertEquals(Sea.SEA_Y, mangerGame.getActor(Actor.Sea).getLocationActor().getY());
    }

    @Test
    public void moveBoat() {
        mangerGame.move(Actor.Boat, Direction.LEFT);
        assertEquals(Boat.BOAT_X, mangerGame.getActor(Actor.Boat).getLocationActor().getX());

        mangerGame.move(Actor.Boat, Direction.LEFT);
        assertEquals(Boat.BOAT_X - 1, mangerGame.getActor(Actor.Boat).getLocationActor().getX());

        mangerGame.move(Actor.Boat, Direction.LEFT);
        assertEquals(Boat.BOAT_X - 1, mangerGame.getActor(Actor.Boat).getLocationActor().getX());
    }

    @Test
    public void moveAirplane() {
        for (int i = 0; i < Airplane.AIRPLANE_SPEED - 1; i++) {
            mangerGame.move(Actor.Airplane);
            assertEquals(Airplane.AIRPLANE_X, mangerGame.getActor(Actor.Airplane).getLocationActor().getX());
        }
        mangerGame.move(Actor.Airplane);
        assertEquals(Airplane.AIRPLANE_X - 1, mangerGame.getActor(Actor.Airplane).getLocationActor().getX());

        mangerGame.move(Actor.Airplane);
        assertEquals(Airplane.AIRPLANE_X - 1, mangerGame.getActor(Actor.Airplane).getLocationActor().getX());
    }

    @Test
    public void moveParachutists() {
        for (int i = 0; i < TIME_TO_DROP_FIRST + 1; i++) {
            mangerGame.dropParachute();
        }
        assertEquals(1, mangerGame.getParachutists().size());
        for (int i = 0; i < Parachutist.PARACHUTIST_SPEED - 1; i++) {
            mangerGame.moveParachutists();
            assertEquals(Airplane.AIRPLANE_X, mangerGame.getParachutists().get(0).getLocationActor().getX());
            assertEquals(Airplane.AIRPLANE_Y, mangerGame.getParachutists().get(0).getLocationActor().getY());
        }
        mangerGame.moveParachutists();
        assertEquals(Airplane.AIRPLANE_X, mangerGame.getParachutists().get(0).getLocationActor().getX());
        assertEquals(Airplane.AIRPLANE_Y + 1, mangerGame.getParachutists().get(0).getLocationActor().getY());
    }
}