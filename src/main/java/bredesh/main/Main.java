package bredesh.main;

import bredesh.GUI.*;
import bredesh.actors.GUI_actors.*;
import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        GUIManager mangerGUI = new GUIManager(new CardLayout());
        new GUIFrame(mangerGUI);
        GameManager mangerGame = new GameManager();
        mangerGame.initGame(
                (Background) GeneratorActors.generateActors(GameManager.Actor.Background),
                (Sea) GeneratorActors.generateActors(GameManager.Actor.Sea),
                (Boat) GeneratorActors.generateActors(GameManager.Actor.Boat),
                (Airplane) GeneratorActors.generateActors(GameManager.Actor.Airplane),
                new ArrayList<>());

        GamePanel panelGame = new GamePanel(mangerGUI, mangerGame);
        mangerGUI.setPanel(new MenuPanel(mangerGUI), new SecondMenuPanel(mangerGUI), panelGame);
        panelGame.addLabel(new JLabel(), GeneratorImageActors.generateImageIconActors(GameConstants.PAUSE_BUTTON_IMAGE));
        panelGame.addKeyListener(panelGame);
        Thread thread = new Thread(panelGame);
        thread.start();

        mangerGUI.showMenu();
    }
}
