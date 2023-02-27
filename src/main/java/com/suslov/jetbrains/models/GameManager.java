package com.suslov.jetbrains.models;

import java.util.Scanner;

/**
 * @author Mikhail Suslov
 */
public class GameManager {
    static final char SPACE_SYMBOL = ' ';
    static final char X_SYMBOL = 'X';
    static final char O_SYMBOL = 'O';

    private final Scanner scanner;
    private final GameGrid gameGrid;

    int sequence;

    private boolean isEnd;
    private boolean xWin;
    private boolean oWin;
    private boolean draw;

    public GameManager(GameGrid gameGrid) {
        this(gameGrid, new Scanner(System.in));
    }

    public GameManager(GameGrid gameGrid, Scanner scanner) {
        this.scanner = scanner;
        this.gameGrid = gameGrid;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isxWin() {
        return xWin;
    }

    public boolean isoWin() {
        return oWin;
    }

    public boolean isDraw() {
        return draw;
    }

    public void startGame() {
        gameGrid.initialize();
        gameGrid.represent();

        while (!isEnd) {
            setValueByUserCoordinates(chooseUserSymbol());
            gameGrid.represent();
            analyzeGameState();
        }
        finishGame();
    }

    char chooseUserSymbol() {
        char symbol = X_SYMBOL;
        if (sequence != 0) {
            symbol = O_SYMBOL;
        }
        sequence = Math.abs(sequence - 1);
        return symbol;
    }

    void setValueByUserCoordinates(char value) {
        System.out.printf("Enter the coordinates (1-%d): ", gameGrid.getGridSize());
        String x;
        String y;
        do {
            y = scanner.next();
            x = scanner.next();
        } while (!setCorrectValueIntoGrid(x, y, value));
    }

    boolean setCorrectValueIntoGrid(String x, String y, char value) {
        String regExOnlyDigits = "\\d";
        boolean incorrectCoords = !x.matches(regExOnlyDigits) || !y.matches(regExOnlyDigits);
        if (incorrectCoords) {
            System.out.println("You should enter numbers!");
            return false;
        }
        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);
        boolean coordsOutOfBounds = xInt > gameGrid.getGridSize() || yInt > gameGrid.getGridSize();
        if (coordsOutOfBounds) {
            System.out.printf("Coordinates should be from 1 to %d!\n", gameGrid.getGridSize());
            return false;
        }
        if (gameGrid.cellIsOccupied(yInt - 1, xInt - 1)) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        gameGrid.setValueIntoCell(yInt - 1, xInt - 1, value);
        return true;
    }

    public void analyzeGameState() {
        int diagL = 0;
        int diagR = 0;
        int gridSize = gameGrid.getGridSize();

        for (int i = 0; i < gridSize; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < gridSize; j++) {
                row += determineValueInCell(gameGrid.getValue(i, j));
                col += determineValueInCell(gameGrid.getValue(j, i));
                if (i == j) {
                    diagL += determineValueInCell(gameGrid.getValue(i, j));
                }
                if (Math.abs(i - j) == gridSize - 1 || (i == j && i == gridSize / 2)) {
                    diagR += determineValueInCell(gameGrid.getValue(i, j));
                }
            }
            analyzeLineValue(row);
            analyzeLineValue(col);
        }
        analyzeLineValue(diagL);
        analyzeLineValue(diagR);

        draw = !oWin && !xWin && !gameGrid.hasEmptySells();
        isEnd = oWin || xWin || draw;
    }

    int determineValueInCell(char gridCell) {
        switch (gridCell) {
            case X_SYMBOL:
                return 1;
            case O_SYMBOL:
                return -1;
            case ' ':
            default:
                return 0;
        }
    }

    void analyzeLineValue(int lineValue) {
        if (lineValue == gameGrid.getGridSize()) {
            xWin = true;
        } else if (lineValue == -gameGrid.getGridSize()) {
            oWin = true;
        }
    }

    void finishGame() {
        if (draw) {
            System.out.println("Result: draw");
        } else if (oWin) {
            System.out.println("Congratulations! O wins!");
        } else {
            System.out.println("Congratulations! X wins!");
        }

        scanner.close();
    }
}
