package core;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

    private static void buildWindow() {
        JFrame window = new JFrame("Caverna - Java");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Display gameDisplay = new Display();
        window.add(gameDisplay);
        window.addKeyListener(gameDisplay);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            buildWindow();
        });
    }
    
}
