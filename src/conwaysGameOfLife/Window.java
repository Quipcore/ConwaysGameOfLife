package conwaysGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Window {

    Dimension windowDimension;
    JFrame frame;
    Panel gamePanel;

    //---------------------------------------------------------------------------------

    public Window(String title, Cell[][] grid) {
        this(grid[0][0].getIMAGE_WIDTH() * grid.length + 18,
                grid[0][0].getIMAGE_HEIGHT() * grid[0].length + 38,
                title,
                grid);
    }

    //---------------------------------------------------------------------------------

    public Window(int width, int height, String title, Cell[][] grid) {

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowDimension = new Dimension(width, height);
        frame.setSize(windowDimension);

        gamePanel = new Panel(grid);
        gamePanel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), exit);
        gamePanel.addKeyListener(new KeyInput(frame));
        gamePanel.setSize(windowDimension);
        frame.add(gamePanel);

        frame.setVisible(true);
    }

    Action exit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

}
