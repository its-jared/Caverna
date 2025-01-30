package core;

import javax.swing.JFrame;

import level.Level;

public class Game implements Runnable {
    
    public static final String NAME = "Caverna";
    public static final int TILE_SIZE = 16;
    public static final int ROWS = 24;
    public static final int COLUMNS = 36;
    public static final int GAME_SCALE = 2;

    private boolean running = false; 

    public int gameTime = 0;

    private final Display display;
    private final JFrame gameWindow;
    
    public final Player player;
    public final Level level;
    
    
    public Game() {
    	level = new Level(100);
        player = new Player(level);
    	
        display = new Display(this);
        gameWindow = new JFrame(NAME);

        gameWindow.add(display);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void tick() {
    	
    }
    
    public void start() {
    	running = true; 
    	new Thread(this).start();
    }
    
    public void stop() {
    	running = false;
    }
    
    @Override
    public void run() {
    	long lastTime = System.nanoTime();
    	double unprocessed = 0;
    	double nsPerTick = 1000000000.0 / 60;
    	int frames = 0;
    	int ticks = 0;
    	long lastTime1 = System.currentTimeMillis();
    	
    	while (running) {
    		long now = System.nanoTime();
    		unprocessed += (now - lastTime) / nsPerTick;
    		lastTime = now;
    		
    		while (unprocessed >= 1) {
    			ticks++;
    			
    			tick();
    			
    			unprocessed -= 1;
    		}
    		
    		try {
    			Thread.sleep(2);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    		if (System.currentTimeMillis() - lastTime1 > 1000) {
    			lastTime1 += 1000;
    			
    			System.out.println(ticks + " ticks, " + frames + " fps");
    			
    			frames = 0;
    			ticks = 0;
    		}
    	}
    }
    
}
