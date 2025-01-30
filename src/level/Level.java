package level;

import blocks.Block;
import blocks.BlockManager;
import core.Display;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Level {
    
    private final long seed;
    private final Random random;
    
    private final int[][] blocks;

    public Level(long s) {
        random = new Random();
        blocks = new int[Display.COLUMNS][Display.ROWS];

        if (s == 0) 
            seed = random.nextLong(-999999, 999999);
        else 
            seed = s;

        generateWorld();        
    } 

    private void generateWorld() {
        for (int x = 0; x < Display.COLUMNS; x++) {
            for (int y = 0; y < Display.ROWS; y++) {
                float n = OpenSimplex2S.noise2(seed, x * 0.2, y * 0.2);
                int block = 1; 

                if (n <= 0.5) {
                    block = 0;
                }

                setBlock(x, y, block);
            }
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        for (int x = 0; x < Display.COLUMNS; x++) {
            for (int y = 0; y < Display.ROWS; y++) {
                Block block = getBlock(x, y);

                if (block.id != 0) {        
                    BufferedImage image = block.getImage();
                    if (image != null) {
                        g.drawImage(
                            image,
                            x * Display.TILE_SIZE,
                            y * Display.TILE_SIZE,
                            observer
                        );
                    }
                }
            }
        }
    }

    public Block getBlock(int x, int y) {
        int localX, localY; 

        localX = x / Display.TILE_SIZE;
        localY = y / Display.TILE_SIZE;
        
        if (localX < 0 && localX >= Display.COLUMNS
            && localY < 0 && localY >= Display.ROWS)
            return BlockManager.BLOCKS[1];
        
        return BlockManager.BLOCKS[blocks[x][y]];
    }

    public void setBlock(int x, int y, int id) {
        int localX, localY; 

        localX = x / Display.TILE_SIZE;
        localY = y / Display.TILE_SIZE;
        
        if (localX < 0 && localX >= Display.COLUMNS
            && localY < 0 && localY >= Display.ROWS)
            return;
        
        blocks[x][y] = id;
    }

}
