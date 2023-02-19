import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    Cell[][] grid;
    Cell[][] prevGrid;

    Window gameWindow;
//    Window prevWindow;

    private final int xCellAmount;
    private final int yCellAmount;

    private Scanner scanner;

    public Game(int xCellAmount, int yCellAmount) throws IOException {
        scanner = new Scanner(System.in);
        this.xCellAmount = xCellAmount;
        this.yCellAmount = yCellAmount;


        grid = new Cell[xCellAmount][yCellAmount];
        prevGrid = new Cell[xCellAmount][yCellAmount];
        setup();
        setupPrev(grid);

        int windowWidth = (grid[0][0].IMAGE_WIDTH + 3) * xCellAmount;
        int windowHeight = (grid[0][0].IMAGE_HEIGHT + 3) * yCellAmount;

        gameWindow = new Window(windowWidth, windowHeight, "Conways Game of Life", grid);
//        prevWindow = new Window(windowWidth, windowHeight, "Previous", grid);
    }


    public void run(int generations) throws IOException, InterruptedException {

        if (generations < 0) {
            while (!gridsAreEqual()) {
                Thread.sleep(100);
                copyGrid(grid);
                updateGrid();
            }

            System.out.println("Game has reached a stable state");
            System.exit(0);
        }

        for (int gen = 0; gen < generations; gen++) {
            scanner.nextLine();

            copyGrid(grid);
            updateGrid();

            gameWindow.refresh(grid);
        }
    }

    private boolean gridsAreEqual() {
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                if (grid[i][j].isAlive() != prevGrid[i][j].isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void copyGrid(Cell[][] grid) {
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                prevGrid[i][j].setStatus(grid[i][j].isAlive());
            }
        }
    }

    private void setupPrev(Cell[][] grid) throws IOException {
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                prevGrid[i][j] = new Cell(grid[i][j].getPreviousStatus());
            }
        }
    }

    private void setup() throws IOException {
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                grid[i][j] = new Cell(false);
            }
        }

        setGlider();
    }

    private void setGlider() {
        grid[2][0].setStatus(true);

        grid[3][1].setStatus(true);

        grid[1][2].setStatus(true);
        grid[2][2].setStatus(true);
        grid[3][2].setStatus(true);

    }

    private void updateGrid() {
        //update grid to follow conways rules
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                grid[i][j].setStatus(checkStatus(i, j));
            }
        }
    }

    private boolean checkStatus(int x, int y) {
        int aliveNeighbours = 0;

        //check all neighbours of cell x,y and count alive ones
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int neighbourX = x + i;
                int neighbourY = y + j;

                if (neighbourX < 0 || neighbourY < 0 || neighbourX >= xCellAmount || neighbourY >= yCellAmount)
                    continue;

                if (prevGrid[neighbourX][neighbourY].isAlive()) aliveNeighbours++;
            }
        }

        //Apply conways game of life rules and return result
        if (grid[x][y].isAlive() && (aliveNeighbours == 2 || aliveNeighbours == 3)) return true;

        return !grid[x][y].isAlive() && aliveNeighbours == 3;

    }

}
