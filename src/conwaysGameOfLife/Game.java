package conwaysGameOfLife;

import java.util.Scanner;

public class Game {

    Cell[][] grid;
    Cell[][] prevGrid;

    Window gameWindow;

    private final int xCellAmount;
    private final int yCellAmount;

    //---------------------------------------------------------------------------------

    public Game(int xCellAmount, int yCellAmount) throws Exception {
        this.xCellAmount = xCellAmount;
        this.yCellAmount = yCellAmount;

        grid = new Cell[xCellAmount][yCellAmount];
        prevGrid = new Cell[xCellAmount][yCellAmount];
        setup();

        //int windowWidth = (grid[0][0].IMAGE_WIDTH+1) * xCellAmount;
        //int windowHeight = (grid[0][0].IMAGE_HEIGHT+1) * yCellAmount;
        gameWindow = new Window("Conways Game of Life", grid);
    }

    //---------------------------------------------------------------------------------

    public void run(int generations) throws InterruptedException {
        int delayInMs = 25;
        run(generations, delayInMs);
    }

    public void run(int generations, int delayInMs) throws InterruptedException {
        if (generations < 0) {
            while (!gridsAreEqual()) {
                simulate(delayInMs);
            }
            System.out.println("Game has reached a stable state");

        } else {

            for (int gen = 0; gen < generations; gen++) {
                simulate(delayInMs);
            }
            System.out.println("Game has reached the end of the specified amount of generations");
        }
    }

    private void simulate(int delayInMs) throws InterruptedException {
        Thread.sleep(delayInMs);
        copyGrid(grid);
        updateGrid();
    }


    //---------------------------------------------------------------------------------

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

    //---------------------------------------------------------------------------------

    private void copyGrid(Cell[][] grid) {
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                prevGrid[i][j].setStatus(grid[i][j].isAlive());
            }
        }
    }

    //---------------------------------------------------------------------------------

    private void setup() throws Exception {

        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                grid[i][j] = new Cell(false);
                prevGrid[i][j] = new Cell(false);
            }
        }

        setGlider();
    }

    //---------------------------------------------------------------------------------

    private void setGlider() {
        grid[2][0].setStatus(true);

        grid[3][1].setStatus(true);

        grid[1][2].setStatus(true);
        grid[2][2].setStatus(true);
        grid[3][2].setStatus(true);

    }

    //---------------------------------------------------------------------------------

    private void updateGrid() {
        //update grid to follow conways game of life rules
        for (int i = 0; i < xCellAmount; i++) {
            for (int j = 0; j < yCellAmount; j++) {
                grid[i][j].setStatus(checkStatus(i, j));
            }
        }
    }

    //---------------------------------------------------------------------------------

    private boolean checkStatus(int x, int y) {
        int aliveNeighbours = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                aliveNeighbours += isNeighbourAlive(i, j, x, y) ? 1 : 0;
            }
        }

        //Apply conways game of life rules and return result
        //See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
        return (grid[x][y].isAlive() && aliveNeighbours == 2) || aliveNeighbours == 3;
    }

    //---------------------------------------------------------------------------------

    private boolean isNeighbourAlive(int xOffset, int yOffset, int xPos, int yPos) {

        if (xOffset == 0 && yOffset == 0) {
            return false;
        }

        int neighbourXPos = xPos + xOffset;
        int neighbourYPos = yPos + yOffset;

        //Is the position of the neighbouring cell outside the grid
        if (neighbourXPos < 0
                || neighbourYPos < 0
                || neighbourXPos >= xCellAmount
                || neighbourYPos >= yCellAmount) {
            return false;
        }

        return prevGrid[neighbourXPos][neighbourYPos].isAlive();
    }

    //---------------------------------------------------------------------------------
}
