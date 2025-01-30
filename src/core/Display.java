package core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Display extends Canvas {

	private static final long serialVersionUID = 1L;
	
	public static final int GAME_HEIGHT = 120;
    public static final int GAME_WIDTH = 160;

    private final Game game;
    
    public Display(Game game) {
    	this.game = game;
    	
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        
        g2d.drawString(game.gameTime + " fps", 10, 20);
        
        game.player.draw(g2d, getFocusCycleRootAncestor());
    }

}
