package level;

import blocks.Block;
import blocks.BlockManager;
import core.GameState;
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
        blocks = new int[GameState.COLUMNS][GameState.ROWS];

        if (s == 0) 
            seed = random.nextLong(-999999, 999999);
        else 
            seed = s;

        generateWorld();        
    } 

    private void generateWorld() {
        for (int x = 0; x < GameState.COLUMNS; x++) {
            for (int y = 0; y < GameState.ROWS; y++) {
                float n = OpenSimplex2S.noise2(seed, x * 0.15, y * 0.15) * 3;
                int block = 1; 

                if (n < 0.8) {
                    block = 0;
                }

                setBlock(x, y, block);
            }
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        for (int x = 0; x < GameState.COLUMNS; x++) {
            for (int y = 0; y < GameState.ROWS; y++) {
                Block block = getBlock(x, y);

                if (block.id != 0) {        
                    BufferedImage image = block.getImage();
                    if (image != null) {
                        g.drawImage(
                            image,
                            x * GameState.TILE_SIZE,
                            y * GameState.TILE_SIZE,
                            observer
                        );
                    }
                }
            }
        }
    }

    public Block getBlock(int x, int y) {
        int localX, localY; 

        localX = x / GameState.TILE_SIZE;
        localY = y / GameState.TILE_SIZE;
        
        if (localX < 0 && localX >= GameState.COLUMNS
            && localY < 0 && localY >= GameState.ROWS)
            return BlockManager.BLOCKS[1];
        
        return BlockManager.BLOCKS[blocks[x][y]];
    }

    public void setBlock(int x, int y, int id) {
        int localX, localY; 

        localX = x / GameState.TILE_SIZE;
        localY = y / GameState.TILE_SIZE;
        
        if (localX < 0 && localX >= GameState.COLUMNS
            && localY < 0 && localY >= GameState.ROWS)
            return;
        
        blocks[x][y] = id;
    }

}
