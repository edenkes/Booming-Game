package bredesh.GUI;

import bredesh.actors.GameConstants;

import javax.swing.*;


public class GUIFrame extends JFrame {

    public GUIFrame(GUIManager manager) {
        setSize(GameConstants.FRAME_WIGHT, GameConstants.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(manager);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
