package com.suslov.jetbrains;

import com.suslov.jetbrains.models.GameGrid;
import com.suslov.jetbrains.models.GameManager;

/**
 * Hello world!
 */
public class TicTacToeGame {
    public static final int GRID_SIZE = 3;

    public static void main(String[] args) {
        GameManager gameManager = new GameManager(new GameGrid(GRID_SIZE));
        gameManager.startGame();
        System.out.println("=== Application execution completed ===");
    }
}
