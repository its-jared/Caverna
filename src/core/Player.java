package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import level.Level;

public class Player {
    
    private BufferedImage image; 

    private final Point pos; 

    private final Level level;

    public Player(Level l) {
        loadImage();

        pos = new Point(0, 0);

        level = l;
    }

    @SuppressWarnings("UseSpecificCatch")
    private void loadImage() {
        try {
            image = ImageIO.read(new File("assets/player.png"));

            image = ScaleBufferedImage.scaleNearest(image, GameState.GAME_SCALE);
        } catch (Exception e) {
            System.err.println("Error whilst opening player image file: " + e.getMessage());
        }
    }

    private void tryMove(Point delta) {
        if (level.getBlock(
            (pos.x + delta.x), (pos.y + delta.y)
        ).id != 1) {
            pos.translate(delta.x, delta.y);
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
            image,
            pos.x * GameState.TILE_SIZE,
            pos.y * GameState.TILE_SIZE - 8,
            observer
        );
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Vertical movement (w: up, s: down).
        if (key == KeyEvent.VK_W)
            tryMove(new Point(0, -1));
        if (key == KeyEvent.VK_S)
            tryMove(new Point(0, 1));
        // Horizontal movement (a: left, d: right).
        if (key == KeyEvent.VK_A) 
            tryMove(new Point(-1, 0));
        if (key == KeyEvent.VK_D)
            tryMove(new Point(1, 0));
    }

    public void tick(GameState display) {
        if (pos.x < 0) 
            pos.x = 0;
        else if (pos.x >= GameState.COLUMNS)
            pos.x = GameState.COLUMNS - 1;

        if (pos.y < 0) 
            pos.y = 0;
        else if (pos.y >= GameState.ROWS)
            pos.y = GameState.ROWS - 1;
    }

}
