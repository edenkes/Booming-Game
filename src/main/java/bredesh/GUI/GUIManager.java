package bredesh.GUI;

import bredesh.actors.GameManager;

import javax.swing.*;
import java.awt.*;

import static bredesh.GUI.GUIConstants.*;

public class GUIManager extends JPanel {
    private CardLayout cardLayout;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private SecondMenuPanel secondMenuPanel;

    public GUIManager(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
        setLayout(cardLayout);
        new GUIFrame(this);
    }

    public void setPanel(MenuPanel menuPanel, SecondMenuPanel secondMenuPanel, GamePanel gamePanel) {
        this.menuPanel = menuPanel;
        add(menuPanel, MENU_TAG);
        this.secondMenuPanel = secondMenuPanel;
        add(secondMenuPanel, SUB_MENU_TAG);
        this.gamePanel = gamePanel;
        add(gamePanel, PLAY_GAME_TAG);
    }

    public void showMenu() {
        cardLayout.show(this, MENU_TAG);
        menuPanel.requestFocus();
    }

    void showSubMenu() {
        cardLayout.show(this, SUB_MENU_TAG);
        secondMenuPanel.requestFocus();
    }

    void showPlayGame() {
        cardLayout.show(this, PLAY_GAME_TAG);
        gamePanel.requestFocus();
    }

    void showNewGame() {
        gamePanel.resetGame();
        cardLayout.show(this, PLAY_GAME_TAG);
        gamePanel.requestFocus();
    }

    void exit() {
        setGameStatus(GameManager.GameStatus.GAME_OVER);
        System.exit(0);
    }

    public void initGamePanel() {
        gamePanel.addLabel(new JLabel());
        gamePanel.addKeyListener(gamePanel);
        Thread thread = new Thread(gamePanel);
        thread.start();
    }

    void setGameStatus(GameManager.GameStatus gameStatus) {
        gamePanel.setGameStatus(gameStatus);
    }
}
