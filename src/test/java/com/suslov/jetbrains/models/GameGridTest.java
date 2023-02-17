package com.suslov.jetbrains.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.suslov.jetbrains.models.GameManager.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Mikhail Suslov
 */
public class GameGridTest {
    private GameGrid gameGrid;

    @Before
    public void setUp() {
        gameGrid = new GameGrid(3);
    }

    @Test
    public void hasEmptySellsFromFilledGrid() {
        for (int i = 0; i < gameGrid.getGridSize(); i++) {
            for (int j = 0; j < gameGrid.getGridSize(); j++) {
                gameGrid.setValueIntoCell(i, j, X_SYMBOL);
            }
        }
        Assert.assertFalse(gameGrid.hasEmptySells());
    }

    @Test
    public void hasEmptySellsFromNotFilledGrid() {
        for (int i = 0; i < gameGrid.getGridSize() - 1; i++) {
            for (int j = 0; j < gameGrid.getGridSize() - 1; j++) {
                gameGrid.setValueIntoCell(i, j, O_SYMBOL);
            }
        }
        Assert.assertTrue(gameGrid.hasEmptySells());
    }

    @Test
    public void hasEmptySellsAfterSpaceFilled() {
        for (int i = 0; i < gameGrid.getGridSize(); i++) {
            for (int j = 0; j < gameGrid.getGridSize(); j++) {
                gameGrid.setValueIntoCell(i, j, SPACE_SYMBOL);
            }
        }
        Assert.assertTrue(gameGrid.hasEmptySells());
    }

    @Test
    public void correctInitialize() {
        gameGrid.initialize();
        for (int i = 0; i < gameGrid.getGridSize(); i++) {
            for (int j = 0; j < gameGrid.getGridSize(); j++) {
                assertEquals(SPACE_SYMBOL, gameGrid.getValue(i, j));
            }
        }
    }

    @Test
    public void cellIsOccupiedCorrect() {
        gameGrid.setValueIntoCell(1, 1, X_SYMBOL);
        Assert.assertTrue(gameGrid.cellIsOccupied(1, 1));
    }

    @Test
    public void cellIsOccupiedAfterSetSpace() {
        gameGrid.setValueIntoCell(1, 1, SPACE_SYMBOL);
        Assert.assertFalse(gameGrid.cellIsOccupied(1, 1));
    }

    @Test
    public void setCorrectValueIntoCell() {
        gameGrid.setValueIntoCell(1, 1, X_SYMBOL);
        Assert.assertEquals(X_SYMBOL, gameGrid.getValue(1, 1));

    }

    @Test
    public void setSpaceValueIntoCell() {
        gameGrid.setValueIntoCell(1, 1, SPACE_SYMBOL);
        Assert.assertEquals(SPACE_SYMBOL, gameGrid.getValue(1, 1));
    }
}