package bredesh.actors;


import bredesh.actors.GUIActors.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    static final int TIME_TO_DROP_FIRST = 600;
    static final int SCORE = 0;
    static final int HEARTS = 3;
    private static final int GAIN_POINTS = 10;

    private ArrayList<GUIActor> parachutists;
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

    public GameManager(GUIActor background, GUIActor sea, GUIActor boat, GUIActor airplane) {
        actors = new HashMap<>();
        actors.put(Actor.Background, background);
        actors.put(Actor.Sea, sea);
        actors.put(Actor.Boat, boat);
        actors.put(Actor.Airplane, airplane);

        gameStatus = GameStatus.PAUSE;
    }

    public void resetGame() {
        gameStatus = GameStatus.RUNNING;
        timeToDrop = TIME_TO_DROP_FIRST;
        score = SCORE;
        hearts = HEARTS;

        actors.get(Actor.Boat).resetLocation();
        actors.get(Actor.Airplane).getLocationActor().resetToInitial();
        actors.get(Actor.Sea).resetLocation();
        parachutists = new ArrayList<>();
    }

    public void playRound(int keyPressedEvent) {
        if (keyPressedEvent == KeyEvent.VK_LEFT) {
            move(actors.get(GameManager.Actor.Boat), GameManager.Direction.LEFT);
        } else if (keyPressedEvent == KeyEvent.VK_RIGHT) {
            move(actors.get(GameManager.Actor.Boat), GameManager.Direction.RIGHT);
        }

        move(actors.get(GameManager.Actor.Airplane));
        move(actors.get(GameManager.Actor.Sea));

        resetIfOutOfFrame(actors.get(GameManager.Actor.Airplane));
        resetIfOutOfFrame(actors.get(Actor.Sea));

        checkParachutistLocation();
        dropParachute();
        moveParachutists();
    }

    public void move(GUIActor guiActor, Direction direction) {
        if (guiActor.canMoveByDirection(direction) && guiActor.canMoveBySpeed()) {
            guiActor.move(direction);
        }
    }

    public void move(GUIActor guiActor) {
        if (guiActor.canMoveBySpeed()) {
            guiActor.move();
        }
    }

    public void resetIfOutOfFrame(GUIActor guiActor) {
        if (guiActor.isOutOfFrame()) {
            guiActor.resetLocation();
        }
    }

    public void moveParachutists() {
        for (GUIActor parachutist : parachutists) {
            if (parachutist.canMoveBySpeed()) {
                parachutist.move();
            }
        }
    }

    public void dropParachute() {
        if (timeToDrop-- == 0) {
            timeToDrop = ThreadLocalRandom.current().nextInt(100, 7000);
            Image imageActors = GeneratorImageActors.generateImageActors(Actor.Parachutist);
            LocationActor locationActors = GeneratorLocationActors.generateLocationActors(actors.get(Actor.Airplane).getLocationActor(), imageActors);
            parachutists.add(GeneratorActors.generateActors(Actor.Parachutist, locationActors, imageActors));
        }
    }

    public void checkParachutistLocation() {
        ArrayList<GUIActor> parachutistsToRemove = new ArrayList<>();
        for (GUIActor parachutist : parachutists) {
            if (actors.get(Actor.Boat).getLocationActor().isTouching(parachutist.getLocationActor())) {
                parachutistsToRemove.add(parachutist);
                managedToCatch();
            } else if (actors.get(Actor.Sea).getLocationActor().isTouching(parachutist.getLocationActor())) {
                parachutistsToRemove.add(parachutist);
                failedToCatch();
            }
        }

        for (GUIActor parachutist : parachutistsToRemove) {
            parachutists.remove(parachutist);
        }
    }

    private void failedToCatch() {
        if (--hearts == 0) {
            gameStatus = GameStatus.GAME_OVER;
        }
    }

    private void managedToCatch() {
        score += GAIN_POINTS;
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

    public ArrayList<GUIActor> getParachutists() {
        return parachutists;
    }

    public int getHearts() {
        return hearts;
    }

    public int getScore() {
        return score;
    }
}
