package com.suslov.jetbrains.models;


import static com.suslov.jetbrains.models.GameManager.SPACE_SYMBOL;

/**
 * @author Mikhail Suslov
 */
public class GameGrid {
    private final int gridSize;
    private final char[][] field;
    private int emptySells;

    public GameGrid(int gridSize) {
        this.gridSize = gridSize;
        field = new char[gridSize][gridSize];
        emptySells = gridSize * gridSize;
    }

    public int getGridSize() {
        return gridSize;
    }

    public boolean hasEmptySells() {
        return emptySells > 0;
    }

    public void initialize() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = i * gridSize; j < i * gridSize + gridSize; j++) {
                field[i][j % gridSize] = SPACE_SYMBOL;
            }
        }
    }

    public void represent() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + SPACE_SYMBOL);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean cellIsOccupied(int i, int j) {
        return field[i - 1][j - 1] != SPACE_SYMBOL;
    }

    public void setValueIntoCell(int i, int j, char value) {
        field[i - 1][j - 1] = value;
        emptySells--;
    }

    public char getValue(int i, int j) {
        return field[i][j];
    }
}
