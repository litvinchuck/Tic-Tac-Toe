package com.elitvinchuck.tictactoe.models;

public class GameField {

    public int gridLength;

    private final char[][] grid;

    public GameField(int gridLength) {
        this.gridLength = gridLength;
        this.grid = new char[gridLength][gridLength];
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

    @Override
    public GameField clone() {
        GameField clone = new GameField(gridLength);
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                clone.set(get(i, j), i, j);
            }
        }
        return clone;
    }

}
