package com.elitvinchuck.tictactoe.controllers;

import com.elitvinchuck.tictactoe.exceptions.TicTacToeCellIsOccupiedException;
import com.elitvinchuck.tictactoe.exceptions.TicTacToeException;
import com.elitvinchuck.tictactoe.ai.AI;

public class GameAIController {

    private final GameController gameController;

    private final AI aiOne;

    private final AI aiTwo;

    private boolean xTurn;

    public GameAIController(GameController gameController, AI aiOne, AI aiTwo) {
        this.gameController = gameController;
        this.aiOne = aiOne;
        this.aiTwo = aiTwo;
        this.xTurn = true;
    }

    public boolean hasNoMoreTurns() {
        for (int i = 0; isIndexInBounds(i); i++) {
            for (int j = 0; isIndexInBounds(j); j++) {
                if (!gameController.isCellOccupied(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasXWon() {
        return gameController.isWon(GameController.X);
    }

    public boolean hasOWon() {
        return gameController.isWon(GameController.O);
    }

    public boolean isGameFinished() {
        return hasXWon() || hasOWon() || hasNoMoreTurns();
    }

    public boolean isDraw() {
        return !hasXWon() && !hasOWon() && hasNoMoreTurns();
    }

    public boolean isGameImpossible() {
        return (hasXWon() && hasOWon()) ||
                Math.abs(gameController.countX() - gameController.countO()) >= 2;
    }

    public void move() throws TicTacToeException {
        if (isXTurn()) {
            moveAI(aiOne);
        } else {
            moveAI(aiTwo);
        }
    }

    public boolean isPlayerAI() {
        return isXTurn() ? aiOne.isPlayer() : aiTwo.isPlayer();
    }

    public String getMoveMessage() {
        return isXTurn() ? aiOne.getMoveMessage() : aiTwo.getMoveMessage();
    }

    public boolean isXTurn() {
        return xTurn;
    }

    private boolean isCellOccupied(int i, int j) {
        return gameController.isCellOccupied(i, j);
    }

    private void moveAI(AI ai) throws TicTacToeException {
        int[] coordinates = ai.move();
        if (!isCellOccupied(coordinates[0], coordinates[1])) {
            gameController.set(isXTurn() ? GameController.X : GameController.O, coordinates[0], coordinates[1]);
            xTurn = !xTurn;
            return;
        }
        throw new TicTacToeCellIsOccupiedException("This cell is occupied! Choose another one!");
    }

    private boolean isIndexInBounds(int i) {
        return GameController.isIndexInBounds(i);
    }

}
