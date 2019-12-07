package bredesh.GUI;

import javax.swing.*;


class GUIFrame extends JFrame {

    GUIFrame(GUIManager manager) {
        setSize(GUIConstants.FRAME_WIGHT, GUIConstants.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(manager);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
