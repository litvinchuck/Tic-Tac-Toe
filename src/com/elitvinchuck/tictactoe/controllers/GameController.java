package com.elitvinchuck.tictactoe.controllers;

import com.elitvinchuck.tictactoe.Constants;
import com.elitvinchuck.tictactoe.models.GameField;

public class GameController {

    private static final GameController self = new GameController();

    private GameField gameField;

    private GameController() {
        gameField = new GameField(Constants.GRID_LENGTH);
    }

    public static GameController getInstance() {
        return self;
    }

    public boolean isCellOccupied(int i, int j) {
        return gameField.isIndexInBounds(i)
                && gameField.isIndexInBounds(j)
                && !gameField.isCellEmpty(i, j);
    }

    public boolean isCellOccupiedBySide(int i, int j, char side) {
        return get(i, j) == side;
    }

    public boolean isIndexInBounds(int i) {
        return gameField.isIndexInBounds(i);
    }

    public boolean isGameFinished() {
        return hasXWon() || hasOWon() || areNoMoreTurns();
    }

    public boolean isDraw() {
        return !hasXWon() && !hasOWon() && areNoMoreTurns();
    }

    public boolean hasXWon() {
        return hasSideWon(Constants.X);
    }

    public boolean hasOWon() {
        return hasSideWon(Constants.O);
    }

    public boolean hasSideWon(char side) {
        return hasSideWonHorizontal(side) ||
                hasSideWonVertical(side) ||
                hasSideWonMainDiagonal(side) ||
                hasSideWonSecondaryDiagonal(side);
    }

    public boolean areNoMoreTurns() {
        for (int i = 0; gameField.isIndexInBounds(i); i++) {
            for (int j = 0; gameField.isIndexInBounds(j); j++) {
                if (!isCellOccupied(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public char get(int i, int j) {
        return gameField.get(i, j);
    }

    public int getNumberOfEmptyCells() {
        int numberOfEmptyCells = 0;
        for (int i = 0; i < gameField.gridLength; i++) {
            for (int j = 0; j < gameField.gridLength; j++) {
                if (gameField.isCellEmpty(i, j)) {
                    numberOfEmptyCells += 1;
                }
            }
        }
        return numberOfEmptyCells;
    }

    public void set(char side, int i, int j) {
        gameField.set(side, i, j);
    }

    private boolean hasSideWonHorizontalN(char side, int n) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, n) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean hasSideWonVerticalN(char side, int n) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(n, i) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean hasSideWonHorizontal(char side) {
        boolean win = false;
        for (int i = 0; isIndexInBounds(i); i++) {
            win |= hasSideWonHorizontalN(side, i);
        }
        return win;
    }

    private boolean hasSideWonVertical(char side) {
        boolean win = false;
        for (int i = 0; isIndexInBounds(i); i++) {
            win |= hasSideWonVerticalN(side, i);
        }
        return win;
    }

    private boolean hasSideWonMainDiagonal(char side) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, i) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean hasSideWonSecondaryDiagonal(char side) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, Constants.GRID_LENGTH - i - 1) != side) {
                return false;
            }
        }
        return true;
    }

}
