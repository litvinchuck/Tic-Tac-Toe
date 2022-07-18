package com.elitvinchuck.tictactoe.models;

import com.elitvinchuck.tictactoe.Constants;

public class GameField {

    public int gridLength;

    private final char[][] grid;

    public GameField(int gridLength) {
        this.gridLength = gridLength;

        grid = new char[gridLength][gridLength];
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                grid[i][j] = Constants.EMPTY_CELL;
            }
        }
    }

    public void set(char side, int i, int j) {
        grid[i][j] = side;
    }

    public char get(int i, int j) {
        return grid[i][j];
    }

    public boolean isIndexInBounds(int i) {
        return i >= 0 && i < gridLength;
    }

    public boolean isCellEmpty(int i, int j) {
        return grid[i][j] == Constants.EMPTY_CELL;
    }

}
