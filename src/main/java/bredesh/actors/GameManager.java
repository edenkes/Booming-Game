package bredesh.actors;


import bredesh.actors.GUI_actors.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    private Background background;
    private Sea sea;
    private Boat boat;
    private Airplane airplane;
    private ArrayList<Parachutist> parachutists;
    private GameStatus gameStatus;
    private int timeToDrop;
    private int score;
    private int hearts;


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

    public void initGame(Background background, Sea sea, Boat boat, Airplane airplane, ArrayList<Parachutist> parachutists) {
        this.background = background;
        this.sea = sea;
        this.boat = boat;
        this.airplane = airplane;
        this.parachutists = parachutists;

        gameStatus = GameStatus.RUNNING;
        timeToDrop = 200;
        score = 0;
        hearts = 3;
    }

    public void resetGame() {
        gameStatus = GameStatus.RUNNING;
        timeToDrop = 200;
        score = 0;
        hearts = 3;

        boat.getLocationActor().resetToInitial();
        airplane.getLocationActor().resetToInitial();
        parachutists = new ArrayList<>();
    }


    public synchronized void drawGame(Graphics2D g2d) {
        drawBackGround(g2d);
        drawBoat(g2d);
        drawParachutists(g2d);
        drawAirplane(g2d);
        drawSea(g2d);

        drawInfo(g2d);
    }

    private void drawBackGround(Graphics2D g2d) {
        g2d.drawImage(background.getImage(), background.getLocationActor().getX(), background.getLocationActor().getY(), null);
    }

    private void drawSea(Graphics2D g2d) {
        g2d.drawImage(sea.getImage(), sea.getLocationActor().getX(), sea.getLocationActor().getY(), null);
    }

    private void drawBoat(Graphics2D g2d) {
        g2d.drawImage(boat.getImage(), boat.getLocationActor().getX(), boat.getLocationActor().getY(), null);
    }

    private void drawAirplane(Graphics2D g2d) {
        g2d.drawImage(airplane.getImage(), airplane.getLocationActor().getX(), airplane.getLocationActor().getY(), null);
    }

    private void drawParachutists(Graphics2D g2d) {
        for (Parachutist parachutist : parachutists) {
            g2d.drawImage(parachutist.getImage(), parachutist.getLocationActor().getX(), parachutist.getLocationActor().getY(), null);
        }
    }

    private void drawInfo(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));

        g2d.drawString(String.format("Your Heart: %d", hearts), GameConstants.HEART_X, GameConstants.HEART_Y);
        g2d.drawString(String.format("Your Score: %d", score), GameConstants.SCORE_X, GameConstants.SCORE_Y);
    }

    public void moveBoat(Direction direction) {
        boat.move(direction);
    }

    public void moveAirplane() {
        airplane.moveAirplane();
    }

    public void moveParachutists() {
        for (Parachutist parachutist : parachutists) {
            parachutist.move();
        }
    }

    public void dropParachute() {
        if (timeToDrop-- == 0) {
            timeToDrop = ThreadLocalRandom.current().nextInt(100, 10000);
            Image imageActors = GeneratorImageActors.generateImageActors(Actor.Parachutist);
            LocationActor locationActors = GeneratorLocationActors.generateLocationActors(airplane.getLocationActor(), imageActors);
            parachutists.add((Parachutist) GeneratorActors.generateActors(Actor.Parachutist, locationActors, imageActors));
        }
    }

    public void checkParachutistLocation() {
        ArrayList<Parachutist> parachutistsToRemove = new ArrayList<>();
        for (Parachutist parachutist : parachutists) {
            if (parachutist.getLocationActor().isTouching(boat.getLocationActor())) {
                parachutistsToRemove.add(parachutist);
                managedToCatch();
            } else if (parachutist.getLocationActor().isTouching(sea.getLocationActor())) {
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getScore() {
        return score;
    }

}
