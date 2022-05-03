package com.elitvinchuck.tictactoe.controllers;

import com.elitvinchuck.tictactoe.models.GameField;

public class GameController {

    public final static int GRID_LENGTH = 3;

    public final static char X = 'X';

    public final static char O = 'O';

    public final static char EMPTY = '_';

    final static String EMPTY_FIELD = "_________";

    private final GameField gameField;

    public GameController() {
        this.gameField = new GameField(GRID_LENGTH);
        populateGrid(EMPTY_FIELD);
    }

    public GameController(String gameState) {
        this();
        populateGrid(gameState);
    }

    public boolean isCellOccupied(int i, int j) {
        return gameField.get(i, j) != EMPTY;
    }

    public boolean isCellOccupiedBySide(int i, int j, char side) {
        return gameField.get(i, j) == side;
    }

    public char get(int i, int j) {
        return gameField.get(i, j);
    }

    public void set(char side, int i, int j) {
        gameField.set(side, i, j);
    }

    public int countX() {
        return countSide(X);
    }

    public int countO() {
        return countSide(O);
    }

    public String getGameState() {
        String gameState = new String();
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                gameState += get(i, j);
            }
        }
        return gameState;
    }

    @Override
    public GameController clone() {
        return new GameController(getGameState());
    }

    public boolean areNoMoreTurns() {
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            for (int j = 0; GameController.isIndexInBounds(j); j++) {
                if (!isCellOccupied(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWon(char side) {
        return isWonHorizontal(side) ||
                isWonVertical(side) ||
                isWonMainDiagonal(side) ||
                isWonSecondaryDiagonal(side);
    }

    private boolean isWonHorizontalN(char side, int n) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, n) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean isWonVerticalN(char side, int n) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(n, i) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean isWonHorizontal(char side) {
        boolean win = false;
        for (int i = 0; isIndexInBounds(i); i++) {
            win |= isWonHorizontalN(side, i);
        }
        return win;
    }

    private boolean isWonVertical(char side) {
        boolean win = false;
        for (int i = 0; isIndexInBounds(i); i++) {
            win |= isWonVerticalN(side, i);
        }
        return win;
    }

    private boolean isWonMainDiagonal(char side) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, i) != side) {
                return false;
            }
        }
        return true;
    }

    private boolean isWonSecondaryDiagonal(char side) {
        for (int i = 0; isIndexInBounds(i); i++) {
            if (get(i, GameController.GRID_LENGTH - i - 1) != side) {
                return false;
            }
        }
        return true;
    }

    private int countSide(char side) {
        int counter = 0;
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                if (get(i, j) == side) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    private void populateGrid(String gameState) {
        for (int i = 0; gameField.isIndexInBounds(i); i++) {
            for (int j = 0; gameField.isIndexInBounds(j); j++) {
                set(gameState.charAt(i * gameField.gridLength + j), i, j);
            }
        }
    }

    public static boolean isXTurn(String gameString) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < gameString.length(); i++) {
            if (gameString.charAt(i) == X) {
                countX += 1;
            } else if (gameString.charAt(i) == O) {
                countO += 1;
            }
        }
        return countX == countO;
    }

    public static boolean[][] areFieldsEmpty(GameController gameController) {
        boolean[][] fields = new boolean[GRID_LENGTH][GRID_LENGTH];
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                fields[i][j] = gameController.isCellOccupied(i, j);
            }
        }
        return fields;
    }

    public static int getNumberOfEmptyFields(GameController gameController) {
        int number = 0;
        boolean[][] fields = areFieldsEmpty(gameController);
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                if (!fields[i][j]) {
                    number += 1;
                }
            }
        }
        return number;
    }

    public static boolean isIndexInBounds(int i) {
        return i >= 0 && i < GRID_LENGTH;
    }

    public static char getOppositeSide(char side) {
        if (side == X) {
            return O;
        }
        return X;
    }

}
