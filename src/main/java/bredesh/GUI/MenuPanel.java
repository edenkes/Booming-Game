package bredesh.GUI;

import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel implements MouseListener {
    private GUIManager guiManager;
    private JLabel lbPlay;
    private JLabel lbExit;

    public MenuPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(null);
        addComp();
    }

    private void addComp() {
        int x = GameConstants.FRAME_WIGHT / 2 - 182 / 2;
        int y = 200;

        lbPlay = setLabel(new JLabel(), x, y, GameConstants.PLAY_BUTTON_IMAGE);
        add(lbPlay);
        lbPlay.addMouseListener(this);
        y += 70;

        lbExit = setLabel(new JLabel(), x, y, GameConstants.EXIT_BUTTON_IMAGE);
        add(lbExit);
        lbExit.addMouseListener(this);
    }

    private JLabel setLabel(JLabel jLabel, int x, int y, String url) {
        ImageIcon imageIcon = GeneratorImageActors.generateImageIconActors(url);
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image image = GeneratorImageActors.generateImageActors(GameConstants.BACKGROUND_MENU_IMAGE);
        g2d.drawImage(image, -24, 0, GameConstants.FRAME_WIGHT + 24,GameConstants.FRAME_HEIGHT, null);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lbPlay)) {
            guiManager.getGamePanel().setGameStatus(GameManager.GameStatus.RUNNING);
            guiManager.showNewGame();
        } else if (e.getSource().equals(lbExit)) {
            guiManager.getGamePanel().setGameStatus(GameManager.GameStatus.GAME_OVER);
            guiManager.exit();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lbPlay)) {
            lbPlay.setIcon(GeneratorImageActors.generateImageIconActors(GameConstants.PLAY_BUTTON_IMAGE));
        } else if (e.getSource().equals(lbExit)) {
            lbExit.setIcon(GeneratorImageActors.generateImageIconActors(GameConstants.EXIT_BUTTON_IMAGE));
        }
    }
}
