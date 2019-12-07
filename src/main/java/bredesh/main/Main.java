package bredesh.main;

import bredesh.GUI.*;
import bredesh.actors.GUIActors.*;
import bredesh.actors.GameConstants;
import bredesh.actors.GameManager;
import bredesh.actors.GeneratorImageActors;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        GUIManager mangerGUI = new GUIManager(new CardLayout());

        GameManager mangerGame = new GameManager(
                (Background) GeneratorActors.generateActors(GameManager.Actor.Background),
                (Sea) GeneratorActors.generateActors(GameManager.Actor.Sea),
                (Boat) GeneratorActors.generateActors(GameManager.Actor.Boat),
                (Airplane) GeneratorActors.generateActors(GameManager.Actor.Airplane));

        GamePanel panelGame = new GamePanel(mangerGUI, mangerGame);
        mangerGUI.setPanel(new MenuPanel(mangerGUI), new SecondMenuPanel(mangerGUI), panelGame);
        panelGame.addLabel(new JLabel(), GeneratorImageActors.generateImageIconActors(GameConstants.PAUSE_BUTTON_IMAGE));
        panelGame.addKeyListener(panelGame);
        Thread thread = new Thread(panelGame);
        thread.start();

        mangerGUI.showMenu();
    }
}
