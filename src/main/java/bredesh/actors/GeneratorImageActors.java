package bredesh.actors;

import javax.swing.*;
import java.awt.*;

public class GeneratorImageActors {
    public static Image generateImageActors(GameManager.Actor actor){
        String image_url = "";
        switch (actor){
            case Background:
                image_url = GameConstants.BACKGROUND_IMAGE;
                break;
            case Sea:
                image_url = GameConstants.SEA_IMAGE;
                break;
            case Boat:
                image_url = GameConstants.BOAT_IMAGE;
                break;
            case Airplane:
                image_url = GameConstants.AIRPLANE_IMAGE;
                break;
            case Parachutist:
                image_url = GameConstants.PARACHUTIST_IMAGE;
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
