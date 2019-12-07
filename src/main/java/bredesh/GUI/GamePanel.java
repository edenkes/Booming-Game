package bredesh.GUI;


import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    public void addLabel(JLabel lb_back, ImageIcon imageIcon) {
        this.lb_back = lb_back;
        lb_back.setIcon(imageIcon);
        lb_back.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        lb_back.setLocation(690, 520);
        lb_back.addMouseListener(this);
        add(lb_back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameManager.drawGame(g2d);
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
        while (true)
            switch (gameManager.getGameStatus()) {
                case GAME_OVER:
                    guiManager.showMenu();
                    continue;

                case RUNNING:
                    if (keyPressedEvent == KeyEvent.VK_LEFT) {
                        gameManager.moveBoat(GameManager.Direction.LEFT);
                    } else if (keyPressedEvent == KeyEvent.VK_RIGHT) {
                        gameManager.moveBoat(GameManager.Direction.RIGHT);
                    }

                    gameManager.moveAirplane();
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
            lb_back.setIcon(GeneratorImageActors.generateImageIconActors(GameConstants.PAUSE_BOLD_BUTTON_IMAGE));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(GeneratorImageActors.generateImageIconActors(GameConstants.PAUSE_BUTTON_IMAGE));
        }
    }

    void setGameStatus(GameManager.GameStatus gameStatus) {
        gameManager.setGameStatus(gameStatus);
    }

    void resetGame() {
        gameManager.resetGame();
    }
}
