import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {


    private final int BORDER = 2;
    private boolean isPrevious;

    Cell[][] grid;

    public Panel(Cell[][] grid){
        setGrid(grid);
    }


    public void paintComponent(Graphics graphics){

        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[0].length; y++){
                graphics.drawImage(grid[x][y].getStatusImage(),
                                (grid[x][y].IMAGE_WIDTH+BORDER)*x,
                                (grid[x][y].IMAGE_HEIGHT+BORDER)*y,
                                null);
            }
        }
        repaint();
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
