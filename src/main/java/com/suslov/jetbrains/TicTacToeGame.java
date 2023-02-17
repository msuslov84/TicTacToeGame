package com.suslov.jetbrains;

import com.suslov.jetbrains.models.GameGrid;
import com.suslov.jetbrains.models.GameManager;

/**
 * Hello world!
 */
public class TicTacToeGame {
    private static final int GRID_SIZE = 3;

    public static void main(String[] args) {
        System.out.println("=== Application execution started ===");
        GameManager gameManager = new GameManager(new GameGrid(GRID_SIZE));
        gameManager.startGame();
        System.out.println("=== Application execution completed ===");
    }
}
