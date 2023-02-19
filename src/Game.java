import java.io.IOException;
import java.util.Random;

public class Game {

    Cell[][] grid;

    Window gameWindow;

    private final int xCellAmount;
    private final int yCellAmount;

    public Game(int xCellAmount, int yCellAmount) throws IOException {
        this.xCellAmount = xCellAmount;
        this.yCellAmount = yCellAmount;

        //grid = setRandomGrid();
        grid = setup();

        int windowWidth = (grid[0][0].IMAGE_WIDTH+3)*xCellAmount;
        int windowHeight = (grid[0][0].IMAGE_HEIGHT+3)*yCellAmount;

        gameWindow = new Window(windowWidth, windowHeight, "Conways Game of Life", grid);
    }


    public void run(int generations) throws IOException {
        for(int gen = 0; gen < generations; gen++){
            grid = updateGrid();
            gameWindow.refresh(grid);
            System.out.println(gen);
        }
    }

    private Cell[][] setup() throws IOException {
        Cell[][] temp = new Cell[xCellAmount][yCellAmount];
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                temp[i][j] = new Cell(false);
            }
        }
        return temp;
    }
    private Cell[][] updateGrid() throws IOException {

        /*
        Cell[][] temp = new Cell[xCellAmount][yCellAmount];
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                temp[i][j] = new Cell(checkStatus(i,j));
            }
        }
        return temp;*/


        //update grid to follow conways rules
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                grid[i][j].setStatus(checkStatus(i,j));
            }
        }

        return null;

    }

    private boolean checkStatus(int x, int y) {
        int aliveNeighbours = 0;

        if(x > 0 && y > 0 && grid[x-1][y-1].isAlive()) aliveNeighbours++;
        if(x > 0 && grid[x-1][y].isAlive()) aliveNeighbours++;
        if(x > 0 && y < yCellAmount-1 && grid[x-1][y+1].isAlive()) aliveNeighbours++;

        if(y > 0 && grid[x][y-1].isAlive()) aliveNeighbours++;
        if(y < yCellAmount-1 && grid[x][y+1].isAlive()) aliveNeighbours++;

        if(x < xCellAmount-1 && y > 0 && grid[x+1][y-1].isAlive()) aliveNeighbours++;
        if(x < xCellAmount-1 && grid[x+1][y].isAlive()) aliveNeighbours++;
        if(x < xCellAmount-1 && y < yCellAmount-1 && grid[x+1][y+1].isAlive()) aliveNeighbours++;


        return aliveNeighbours >= 2 && aliveNeighbours <= 3;
    }

}
