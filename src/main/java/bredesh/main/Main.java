package bredesh.main;

import bredesh.GUI.GUIManager;
import bredesh.GUI.GamePanel;
import bredesh.GUI.MenuPanel;
import bredesh.GUI.SecondMenuPanel;
import bredesh.actors.GUIActors.*;
import bredesh.actors.GameManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        GUIManager mangerGUI = new GUIManager(new CardLayout());

        GameManager mangerGame = new GameManager(
                GeneratorActors.generateActors(GameManager.Actor.Background),
                GeneratorActors.generateActors(GameManager.Actor.Sea),
                GeneratorActors.generateActors(GameManager.Actor.Boat),
                GeneratorActors.generateActors(GameManager.Actor.Airplane));

        mangerGUI.setPanel(new MenuPanel(mangerGUI), new SecondMenuPanel(mangerGUI), new GamePanel(mangerGUI, mangerGame));
        mangerGUI.initGamePanel();
        mangerGUI.showMenu();
    }
}
