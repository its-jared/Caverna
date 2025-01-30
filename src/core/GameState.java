package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import level.Level;

public class GameState extends JPanel implements ActionListener, KeyListener {

    // Controls the delay between each ticks in ms. 
    private final int DELAY = 60;

    public static final int TILE_SIZE = 16;
    public static final int ROWS = 24;
    public static final int COLUMNS = 36;

    public static final double GAME_SCALE = TILE_SIZE / 8;

    public static float TICKS_COMPLETED = 0;

    public final Random random;
    public final Level level;

    private final Timer gameClock;
    private final Player player;

    public GameState() {
        setPreferredSize(new Dimension(
            TILE_SIZE * COLUMNS, 
            TILE_SIZE * ROWS
        ));
        setBackground(new Color(230, 230, 230));

        random = new Random();

        gameClock = new Timer(DELAY, this);
        gameClock.start();

        level = new Level(0);
        player = new Player(level);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TICKS_COMPLETED += 1;

        player.tick(this);

        setBackground(new Color(30, 30, 30));

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g, this);
        player.draw(g, this);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // unused.
    }

    // Check to see if the amount of ticks has passed
    // since the application starts. Oneshot.
    public boolean checkTickLapse(float amount) {
        return (TICKS_COMPLETED / amount) == (int)(TICKS_COMPLETED / amount);
    }

}
