package com.suslov.jetbrains.models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static com.suslov.jetbrains.models.GameManager.*;

/**
 * @author Mikhail Suslov
 */
public class GameManagerTest {
    private GameManager gameManager;
    private GameGrid gameGrid;
    private Scanner scanner;

    @Before
    public void setUp() {
        scanner = new Scanner("1 1 2 1 2 2 3 1 3 3");
        gameGrid = new GameGrid(3);
        gameManager = new GameManager(gameGrid, scanner);
    }

    @After
    public void tearDown() {
        scanner.close();
    }

    @Test
    public void startGameWithCorrectInputSequence() {
        gameManager.startGame();
        Assert.assertTrue(gameManager.isEnd());
    }

    @Test
    public void startGameWithWinResultForX() {
        gameManager.startGame();
        Assert.assertTrue(gameManager.isxWin());
    }

    @Test
    public void startGameWithIncorrectInputSequence() {
        scanner = new Scanner("1 1 two one 6 2 3 1 3 3 3 2 2 2");
        gameManager = new GameManager(gameGrid, scanner);
        gameManager.startGame();
        Assert.assertTrue(gameManager.isEnd());
    }

    @Test
    public void startGameWithWinResultForO() {
        scanner = new Scanner("1 1 3 1 2 1 3 2 2 2 3 3");
        gameManager = new GameManager(gameGrid, scanner);
        gameManager.startGame();
        Assert.assertTrue(gameManager.isoWin());
    }

    @Test
    public void startGameWithDrawResult() {
        scanner = new Scanner("1 1 2 1 2 2 3 3 1 2 1 3 2 3 3 2 3 1");
        gameManager = new GameManager(gameGrid, scanner);
        gameManager.startGame();
        Assert.assertTrue(gameManager.isDraw());
    }

    @Test
    public void startGameWithCorrectInputSequenceForLargerSize() {
        scanner = new Scanner("1 1 2 1 2 2 1 2 3 3 4 1 4 4");
        gameManager = new GameManager(new GameGrid(4), scanner);
        gameManager.startGame();
        Assert.assertTrue(gameManager.isEnd());
    }

    @Test
    public void chooseUserSymbolMustBeO() {
        gameManager.sequence = 1;
        Assert.assertEquals(O_SYMBOL, gameManager.chooseUserSymbol());
    }

    @Test
    public void chooseUserSymbolMustBeX() {
        gameManager.sequence = 0;
        Assert.assertEquals(X_SYMBOL, gameManager.chooseUserSymbol());
    }

    @Test
    public void chooseUserSymbolCorrectChangeSequence() {
        gameManager.sequence = 0;
        Assert.assertEquals(X_SYMBOL, gameManager.chooseUserSymbol());
        Assert.assertEquals(O_SYMBOL, gameManager.chooseUserSymbol());
    }

    @Test
    public void setValueByCorrectUserCoordinates() {
        scanner = new Scanner("1 1");
        gameManager = new GameManager(gameGrid, scanner);
        gameGrid.initialize();
        gameManager.setValueByUserCoordinates(X_SYMBOL);
        Assert.assertEquals(X_SYMBOL, gameGrid.getValue(0, 0));
    }

    @Test
    public void setValueByNotAllCorrectUserCoordinates() {
        scanner = new Scanner("two three 1 9 2 2");
        gameManager = new GameManager(gameGrid, scanner);
        gameGrid.initialize();
        gameManager.setValueByUserCoordinates(X_SYMBOL);
        Assert.assertEquals(X_SYMBOL, gameGrid.getValue(1, 1));
    }

    @Test
    public void setValueIntoGridWithCorrectCoordinates() {
        gameGrid.initialize();
        Assert.assertTrue(gameManager.setCorrectValueIntoGrid("1", "2", X_SYMBOL));
        Assert.assertEquals(X_SYMBOL, gameGrid.getValue(1, 0));
    }

    @Test
    public void setValueIntoGridWithInvalidCoordinates() {
        gameGrid.initialize();
        Assert.assertFalse(gameManager.setCorrectValueIntoGrid("one", "two", X_SYMBOL));
    }

    @Test
    public void setValueIntoOccupiedCell() {
        gameGrid.initialize();
        gameManager.setCorrectValueIntoGrid("1", "2", O_SYMBOL);
        Assert.assertFalse(gameManager.setCorrectValueIntoGrid("1", "2", X_SYMBOL));
    }

    @Test
    public void correctDetermineValueInCell() {
        Assert.assertEquals(1, gameManager.determineValueInCell(X_SYMBOL));
        Assert.assertEquals(-1, gameManager.determineValueInCell(O_SYMBOL));
        Assert.assertEquals(0, gameManager.determineValueInCell(SPACE_SYMBOL));
        Assert.assertEquals(0, gameManager.determineValueInCell('$'));
    }

    @Test
    public void analyzeLineValueToWinX() {
        gameManager.analyzeLineValue(gameGrid.getGridSize());
        Assert.assertTrue(gameManager.isxWin());
    }

    @Test
    public void analyzeLineValueToWinO() {
        gameManager.analyzeLineValue(-gameGrid.getGridSize());
        Assert.assertTrue(gameManager.isoWin());
    }
}