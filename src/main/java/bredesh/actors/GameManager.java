package bredesh.actors;


import bredesh.actors.GUIActors.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    private ArrayList<Parachutist> parachutists;
    private GameStatus gameStatus;
    private int timeToDrop;
    private int score;
    private int hearts;
    private Map<Actor, GUIActor> actors;

    public enum Direction {
        RIGHT,
        LEFT
    }

    public enum Actor {
        Background,
        Sea,
        Boat,
        Airplane,
        Parachutist,
    }

    public enum GameStatus {
        RUNNING,
        PAUSE,
        GAME_OVER,
    }

    public GameManager(Background background, Sea sea, Boat boat, Airplane airplane) {
        actors = new HashMap<>();
        actors.put(Actor.Background, background);
        actors.put(Actor.Sea, sea);
        actors.put(Actor.Boat, boat);
        actors.put(Actor.Airplane, airplane);
        this.parachutists = new ArrayList<>();

        initFields();
    }

    public void resetGame() {
        initFields();

        actors.get(Actor.Boat).getLocationActor().resetToInitial();
        actors.get(Actor.Airplane).getLocationActor().resetToInitial();
        parachutists = new ArrayList<>();
    }

    private void initFields() {
        gameStatus = GameStatus.RUNNING;
        timeToDrop = GameConstants.TIME_TO_DROP_P;
        score = GameConstants.SCORE;
        hearts = GameConstants.HEARTS;
    }

    public void move(Actor actor, Direction direction) {
        actors.get(actor).move(direction);
    }
    public void move(Actor actor) {
        actors.get(actor).move();
    }

    public void moveParachutists() {
        for (Parachutist parachutist : parachutists) {
            parachutist.move();
        }
    }

    public void dropParachute() {
        if (timeToDrop-- == 0) {
            timeToDrop = ThreadLocalRandom.current().nextInt(100, 7000);
            Image imageActors = GeneratorImageActors.generateImageActors(Actor.Parachutist);
            LocationActor locationActors = GeneratorLocationActors.generateLocationActors(actors.get(Actor.Airplane).getLocationActor(), imageActors);
            parachutists.add((Parachutist) GeneratorActors.generateActors(Actor.Parachutist, locationActors, imageActors));
        }
    }

    public void checkParachutistLocation() {
        ArrayList<Parachutist> parachutistsToRemove = new ArrayList<>();
        for (Parachutist parachutist : parachutists) {
            if (parachutist.getLocationActor().isTouching(actors.get(Actor.Boat).getLocationActor())) {
                parachutistsToRemove.add(parachutist);
                managedToCatch();
            } else if (parachutist.getLocationActor().isTouching(actors.get(Actor.Sea).getLocationActor())) {
                parachutistsToRemove.add(parachutist);
                failedToCatch();
            }
        }

        for (Parachutist parachutist : parachutistsToRemove) {
            parachutists.remove(parachutist);
        }
    }

    private void failedToCatch() {
        if (--hearts == 0) {
            gameStatus = GameStatus.GAME_OVER;
        }
    }

    private void managedToCatch() {
        score++;
    }


    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public GUIActor getActor(Actor actor) {
        return actors.get(actor);
    }

    public ArrayList<Parachutist> getParachutists() {
        return parachutists;
    }

    public int getHearts() {
        return hearts;
    }

    public int getScore() {
        return score;
    }
}
