package conwaysGameOfLife;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Cell[][] grid;

    //---------------------------------------------------------------------------------

    public Panel(Cell[][] grid) {
        setGrid(grid);
    }

    //---------------------------------------------------------------------------------

    public void paintComponent(Graphics graphics) {
        final int TILE_BORDER = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                graphics.drawImage(grid[x][y].getStatusImage(),
                        (grid[x][y].getIMAGE_WIDTH() + TILE_BORDER) * x,
                        (grid[x][y].getIMAGE_HEIGHT() + TILE_BORDER) * y,
                        null);
            }
        }
        repaint();
    }

    //---------------------------------------------------------------------------------

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
