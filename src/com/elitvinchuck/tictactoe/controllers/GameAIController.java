package com.elitvinchuck.tictactoe.controllers;

import com.elitvinchuck.tictactoe.Constants;
import com.elitvinchuck.tictactoe.ai.AI;
import com.elitvinchuck.tictactoe.ai.EasyAI;
import com.elitvinchuck.tictactoe.ai.Player;

public class GameAIController {

    private static GameAIController self;

    private GameController gameController = GameController.getInstance();

    private AI x;

    private AI o;

    private boolean xTurn = true;

    private GameAIController() {
        this.x = new Player();
        this.o = new EasyAI();
    }

    private GameAIController(AI x, AI o) {
        this.x = x;
        this.o = o;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public char getCurrentSide() {
        return isXTurn() ? Constants.X : Constants.O;
    }

    public char getOppositeSide() {
        return isXTurn() ? Constants.O : Constants.X;
    }

    public boolean isPlayerAI() {
        return isXTurn() ? x.isPlayer() : o.isPlayer();
    }

    public void move() {
        if (isXTurn()) {
            moveAI(x);
        } else {
            moveAI(o);
        }
    }

    private void moveAI(AI ai) {
        int[] coordinates = ai.move();
        gameController.set(isXTurn() ? Constants.X : Constants.O, coordinates[0], coordinates[1]);
        xTurn = !xTurn;
    }

    public static GameAIController getInstance() {
        if (self == null) {
            self = new GameAIController();
        }
        return self;
    }

    public static GameAIController buildInstance(AI x, AI o) {
        if (self == null) {
            self = new GameAIController(x, o);
        }
        return self;
    }

    public static char getOppositeSide(char side) {
        return side == Constants.X ? Constants.O : Constants.X;
    }
}
