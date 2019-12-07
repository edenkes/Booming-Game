package bredesh.actors;

import bredesh.actors.GUIActors.*;

import javax.swing.*;
import java.awt.*;

public class GeneratorImageActors {
    public static Image generateImageActors(GameManager.Actor actor){
        String image_url = "";
        switch (actor){
            case Background:
                image_url = Background.BACKGROUND_IMAGE;
                break;
            case Sea:
                image_url = Sea.SEA_IMAGE;
                break;
            case Boat:
                image_url = Boat.BOAT_IMAGE;
                break;
            case Airplane:
                image_url = Airplane.AIRPLANE_IMAGE;
                break;
            case Parachutist:
                image_url = Parachutist.PARACHUTIST_IMAGE;
                break;
        }
        return generateImageActors(image_url);
    }

    public static Image generateImageActors(String image_url){
        return generateImageIconActors(image_url).getImage();
    }

    public static ImageIcon generateImageIconActors(String image_url){
        return new ImageIcon(GeneratorImageActors.class.getResource(image_url));
    }
}
