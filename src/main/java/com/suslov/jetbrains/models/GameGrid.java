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
        StringBuilder str = new StringBuilder();
        str.append("---------\n");
        for (char[] chars : field) {
            str.append("| ");
            for (char aChar : chars) {
                str.append(aChar).append(SPACE_SYMBOL);
            }
            str.append("|\n");
        }
        str.append("---------\n");

        System.out.println(str);
    }

    public boolean cellIsOccupied(int i, int j) {
        return field[i][j] != SPACE_SYMBOL;
    }

    public void setValueIntoCell(int i, int j, char value) {
        field[i][j] = value;
        if (value != SPACE_SYMBOL) {
            emptySells--;
        }
    }

    public char getValue(int i, int j) {
        return field[i][j];
    }
}
