package com.elitvinchuck.tictactoe.ai;

import com.elitvinchuck.tictactoe.controllers.GameController;

public class HardAI extends MediumAI {

    public HardAI(GameController gameController) {
        super(gameController);
    }

    @Override
    public String getMoveMessage() {
        return "Making move level \"hard\"";
    }

    @Override
    public int[] move() {
        return miniMax(gameController, getCurrentSide(), true)
                .getCoordinates();
    }

    private Move miniMax(GameController gameController, char maximizingSide, boolean isMaximizing) {
        if (gameController.isWon(maximizingSide)) {
            return new Move(-1, -1, Move.POSITIVE_SCORE);
        } else if (gameController.isWon(GameController.getOppositeSide(maximizingSide))) {
            return new Move(-1, -1, Move.NEGATIVE_SCORE);
        } else if (gameController.areNoMoreTurns()) {
            return new Move(-1, -1, Move.ZERO);
        }

        Move maxEvaluation;
        Move minEvaluation;

        maxEvaluation = new Move(-1, -1, Move.NEGATIVE_IMPOSSIBLE);
        minEvaluation = new Move(-1, -1, Move.POSITIVE_IMPOSSIBLE);

        char moveSide = isMaximizing ? maximizingSide : GameController.getOppositeSide(maximizingSide);

        Move[] moves = getAvailableMoves(gameController);
        for (Move move : moves) {
            gameController.set(moveSide, move.getI(), move.getJ());

            move.setScore(
                    miniMax(gameController.clone(), maximizingSide, !isMaximizing)
                            .getScore());

            gameController.set(GameController.EMPTY, move.getI(), move.getJ());
            maxEvaluation = maxEvaluation.max(move);
            minEvaluation = minEvaluation.min(move);
        }

        if (isMaximizing) {
            return maxEvaluation;
        }
        return minEvaluation;
    }

    private Move[] getAvailableMoves(GameController gameController) {
        boolean[][] emptyFields = GameController.areFieldsEmpty(gameController);
        Move[] moves = new Move[GameController.getNumberOfEmptyFields(gameController)];
        int movesIndex = 0;

        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            for (int j = 0; GameController.isIndexInBounds(j); j++) {
                if (!emptyFields[i][j]) {
                    moves[movesIndex++] = new Move(i, j);
                }
            }
        }

        return moves;
    }

}

class Move {

    private int i;

    private int j;

    private int score;

    public final static int POSITIVE_SCORE = 10;

    public final static int NEGATIVE_SCORE = -10;

    public final static int ZERO = 0;

    public final static int POSITIVE_IMPOSSIBLE = 1000;

    public final static int NEGATIVE_IMPOSSIBLE = -1000;

    public Move(int i, int j) {
        this.i = i;
        this.j = j;
        this.score = ZERO;
    }

    public Move(int i, int j, int score) {
        this(i, j);
        this.score = score;
    }

    public int[] getCoordinates() {
        return new int[] {i, j};
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Move max(Move other) {
        if (this.getScore() > other.getScore()) {
            return this;
        }
        return other;
    }

    public Move min(Move other) {
        if (this.getScore() < other.getScore()) {
            return this;
        }
        return other;
    }

}
