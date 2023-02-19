import javax.swing.*;
import java.awt.*;


public class Window{

    Dimension windowDimension;
    JFrame frame;
    Panel gamePanel;

    public Window(int width, int height, String title,Cell[][] grid) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowDimension = new Dimension(width, height);
        frame.setSize(windowDimension);

        gamePanel = new Panel(grid);
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    public void refresh(Cell[][] grid){
        gamePanel.setGrid(grid);
        gamePanel.repaint();
    }
}
