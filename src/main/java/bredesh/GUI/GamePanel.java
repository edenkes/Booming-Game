package bredesh.GUI;


import bredesh.actors.GUIActors.GUIActor;
import bredesh.actors.GUIActors.Parachutist;
import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static bredesh.GUI.GUIConstants.*;

public class GamePanel extends JPanel implements KeyListener, Runnable, MouseListener {
    private GameManager gameManager;
    private JLabel lb_back;
    private GUIManager guiManager;
    private int keyPressedEvent;

    public GamePanel(GUIManager guiManager, GameManager gameManager) {
        setLayout(null);
        this.guiManager = guiManager;
        this.gameManager = gameManager;
    }

    void addLabel(JLabel lb_back) {
        ImageIcon imageIcon = GeneratorImageActors.generateImageIconActors(GUIConstants.PAUSE_BUTTON_IMAGE);
        this.lb_back = lb_back ;
        lb_back.setIcon(imageIcon);
        lb_back.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        lb_back.setLocation(PAUSE_BUTTON_X, PAUSE_BUTTON_Y);
        lb_back.addMouseListener(this);
        add(lb_back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGame(g2d);
    }

    private synchronized void drawGame(Graphics2D g2d) {
        drawActor(g2d, GameManager.Actor.Background);
        drawActor(g2d, GameManager.Actor.Boat);
        drawParachutists(g2d);
        drawActor(g2d, GameManager.Actor.Airplane);
        drawActor(g2d, GameManager.Actor.Sea);

        drawInfo(g2d);
    }

    private void drawActor(Graphics2D g2d, GameManager.Actor actorType) {
        GUIActor actor = gameManager.getActor(actorType);
        g2d.drawImage(actor.getImage(), actor.getLocationActor().getX(), actor.getLocationActor().getY(), null);
    }

    private void drawParachutists(Graphics2D g2d) {
        for (GUIActor parachutist : gameManager.getParachutists()) {
            g2d.drawImage(parachutist.getImage(), parachutist.getLocationActor().getX(), parachutist.getLocationActor().getY(), null);
        }
    }

    private void drawInfo(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));

        g2d.drawString(String.format("Your Heart: %d", gameManager.getHearts()), GUIConstants.HEART_X, GUIConstants.HEART_Y);
        g2d.drawString(String.format("Your Score: %d", gameManager.getScore()), GUIConstants.SCORE_X, GUIConstants.SCORE_Y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressedEvent = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyPressedEvent = 0;
    }

    @Override
    public void run() {
        while (true) {
            switch (gameManager.getGameStatus()) {
                case GAME_OVER:
                    guiManager.showMenu();
                    continue;

                case RUNNING:
                    if (keyPressedEvent == KeyEvent.VK_LEFT) {
                        gameManager.move(GameManager.Actor.Boat, GameManager.Direction.LEFT);
                    } else if (keyPressedEvent == KeyEvent.VK_RIGHT) {
                        gameManager.move(GameManager.Actor.Boat, GameManager.Direction.RIGHT);
                    }

                    gameManager.move(GameManager.Actor.Airplane);

                    gameManager.checkParachutistLocation();
                    gameManager.dropParachute();
                    gameManager.moveParachutists();

                    repaint();

                case PAUSE:
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            keyPressedEvent = 0;
            gameManager.setGameStatus(GameManager.GameStatus.PAUSE);
            guiManager.showSubMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(GeneratorImageActors.generateImageIconActors(GUIConstants.PAUSE_BOLD_BUTTON_IMAGE));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(GeneratorImageActors.generateImageIconActors(GUIConstants.PAUSE_BUTTON_IMAGE));
        }
    }

    void setGameStatus(GameManager.GameStatus gameStatus) {
        gameManager.setGameStatus(gameStatus);
    }

    void resetGame() {
        gameManager.resetGame();
    }
}
