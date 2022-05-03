package com.elitvinchuck.tictactoe.ai;

import com.elitvinchuck.tictactoe.controllers.GameController;

public class MediumAI extends EasyAI {

    public MediumAI(GameController gameController) {
        super(gameController);
    }

    @Override
    public int[] move() {
        if (canWinInOneMove(getCurrentSide())) {
            return getWinningCoordinates(getCurrentSide());
        } else if (canWinInOneMove(getOppositeSide())) {
            return getWinningCoordinates(getOppositeSide());
        }
        return super.move();
    }

    @Override
    public String getMoveMessage() {
        return "Making move level \"medium\"";
    }

    protected boolean isXTurn() {
        return gameController.countX() == gameController.countO();
    }

    protected char getCurrentSide() {
        return isXTurn() ? GameController.X : GameController.O;
    }

    protected char getOppositeSide() {
        return isXTurn() ? GameController.O : GameController.X;
    }

    protected boolean isCellOccupiedBySide(int i, int j, char side) {
        return gameController.isCellOccupiedBySide(i, j, side);
    }

    protected boolean isCellOccupied(int i, int j) {
        return gameController.isCellOccupied(i, j);
    }

    protected boolean canWinInOneMove(char side) {
        return canWinInOneMoveVertical(side)
                || canWinInOneMoveHorizontal(side)
                || canWinInOneMoveMainDiagonal(side)
                || canWinInOneMoveSecondaryDiagonal(side);
    }

    private int[] getWinningCoordinates(char side) {
        if (canWinInOneMoveHorizontal(side)) {
            return getWinningCoordinatesHorizontal(side);
        } else if (canWinInOneMoveVertical(side)) {
            return getWinningCoordinatesVertical(side);
        } else if (canWinInOneMoveMainDiagonal(side)) {
            return getWinningCoordinatesMainDiagonal(side);
        } else if (canWinInOneMoveSecondaryDiagonal(side)) {
            return getWinningCoordinatesSecondaryDiagonal(side);
        }
        return new int[] {0, 0};
    }

    private boolean canWinInOneMoveHorizontalN(char side, int n) {
        return isCellOccupiedBySide(n, 0, side) && isCellOccupiedBySide(n, 1, side) && !isCellOccupied(n, 2)
                || isCellOccupiedBySide(n, 0, side) && !isCellOccupied(n, 1) && isCellOccupiedBySide(n, 2, side)
                || !isCellOccupied(n, 0) && isCellOccupiedBySide(n, 1, side) && isCellOccupiedBySide(n, 2, side);
    }

    private boolean canWinInOneMoveHorizontal(char side) {
        boolean win = false;
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            win |= canWinInOneMoveHorizontalN(side, i);
        }
        return win;
    }

    private boolean canWinInOneMoveVerticalN(char side, int n) {
        return isCellOccupiedBySide(0, n, side) && isCellOccupiedBySide(1, n, side) && !isCellOccupied(2, n)
                || isCellOccupiedBySide(0, n, side) && !isCellOccupied(1, n) && isCellOccupiedBySide(2, n, side)
                || !isCellOccupied(0, n) && isCellOccupiedBySide(1, n, side) && isCellOccupiedBySide(2, n, side);
    }

    private boolean canWinInOneMoveVertical(char side) {
        boolean win = false;
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            win |= canWinInOneMoveVerticalN(side, i);
        }
        return win;
    }

    private boolean canWinInOneMoveMainDiagonal(char side) {
        return isCellOccupiedBySide(0, 0, side) && isCellOccupiedBySide(1, 1, side) && !isCellOccupied(2, 2)
                || isCellOccupiedBySide(0, 0, side) && !isCellOccupied(1, 1) && isCellOccupiedBySide(2, 2, side)
                || !isCellOccupied(0, 0) && isCellOccupiedBySide(1, 1, side) && isCellOccupiedBySide(2, 2, side);
    }

    private boolean canWinInOneMoveSecondaryDiagonal(char side) {
        return isCellOccupiedBySide(0, 2, side) && isCellOccupiedBySide(1, 1, side) && !isCellOccupied(2, 0)
                || isCellOccupiedBySide(0, 2, side) && !isCellOccupied(1, 1) && isCellOccupiedBySide(2, 0, side)
                || !isCellOccupied(0, 2) && isCellOccupiedBySide(1, 1, side) && isCellOccupiedBySide(2, 0, side);
    }

    private int[] getWinningCoordinatesHorizontal(char side) {
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            if (canWinInOneMoveHorizontalN(side, i)) {
                return getWinningCoordinatesHorizontalN(side, i);
            }
        }
        return new int[] {0, 0};
    }

    private int[] getWinningCoordinatesVertical(char side) {
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            if (canWinInOneMoveVerticalN(side, i)) {
                return getWinningCoordinatesVerticalN(side, i);
            }
        }
        return new int[] {0, 0};
    }

    private int[] getWinningCoordinatesHorizontalN(char side, int n) {
        if (isCellOccupiedBySide(n, 0, side) && isCellOccupiedBySide(n, 1, side) && !isCellOccupied(n, 2)) {
            return new int[] {n, 2};
        } else if (isCellOccupiedBySide(n, 0, side) && !isCellOccupied(n, 1) && isCellOccupiedBySide(n, 2, side)) {
            return new int[] {n, 1};
        } else {
            return new int[] {n, 0};
        }
    }

    private int[] getWinningCoordinatesVerticalN(char side, int n) {
        if (isCellOccupiedBySide(0, n, side) && isCellOccupiedBySide(1, n, side) && !isCellOccupied(2, n)) {
            return new int[] {2, n};
        } else if (isCellOccupiedBySide(0, n, side) && !isCellOccupied(1, n) && isCellOccupiedBySide(2, n, side)) {
            return new int[] {1, n};
        }
        return new int[] {0, n};
    }

    private int[] getWinningCoordinatesMainDiagonal(char side) {
        if (isCellOccupiedBySide(0, 0, side) && isCellOccupiedBySide(1, 1, side) && !isCellOccupied(2, 2)) {
            return new int[] {2, 2};
        } else if (isCellOccupiedBySide(0, 0, side) && !isCellOccupied(1, 1) && isCellOccupiedBySide(2, 2, side)) {
            return new int[] {1, 1};
        }
        return new int[] {0, 0};
    }

    private int[] getWinningCoordinatesSecondaryDiagonal(char side) {
        if (isCellOccupiedBySide(0, 2, side) && isCellOccupiedBySide(1, 1, side) && !isCellOccupied(2, 0)) {
            return new int[] {2, 0};
        } else if (isCellOccupiedBySide(0, 2, side) && !isCellOccupied(1, 1) && isCellOccupiedBySide(2, 0, side)) {
            return new int[] {1, 1};
        }
        return new int[] {0, 2};
    }

}
