/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled;

/**
 *
 * @author Wilmer Carranza
 */
import javax.imageio.*;
import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class ImageLibrary {

    private HashMap<Tile.tileID, BufferedImage> imageCollection;

    public ImageLibrary() {
        imageCollection = new HashMap(8);
        BufferedImage img;
        try {
            img = ImageIO.read(new File("resources/gemRed.png"));
            imageCollection.put(Tile.tileID.RED, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemGreen.png"));
            imageCollection.put(Tile.tileID.GREEN, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemBlue.png"));
            imageCollection.put(Tile.tileID.BLUE, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemOrange.png"));
            imageCollection.put(Tile.tileID.ORANGE, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemPurple.png"));
            imageCollection.put(Tile.tileID.PURPLE, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemWhite.png"));
            imageCollection.put(Tile.tileID.WHITE, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/gemYellow.png"));
            imageCollection.put(Tile.tileID.YELLOW, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            img = ImageIO.read(new File("resources/focus.png"));
            imageCollection.put(Tile.tileID.FOCUS, img);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    BufferedImage getImage(Tile.tileID id) {
        return imageCollection.get(id);
    }
}
