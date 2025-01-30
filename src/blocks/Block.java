package blocks;

import core.Game;
import core.ScaleBufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class Block {
    
    public int id; 
    public String name;
    public String imagePath;

    public Block(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public BufferedImage getImage() {
        BufferedImage image = null; 

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.err.println("Error when attempting to get " + name + "'s image: " + e.getMessage());
        }

        return ScaleBufferedImage.scaleNearest(image, Game.GAME_SCALE);
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
