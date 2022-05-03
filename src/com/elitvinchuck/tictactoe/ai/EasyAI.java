package com.elitvinchuck.tictactoe.ai;

import com.elitvinchuck.tictactoe.controllers.GameController;

import java.util.Random;

public class EasyAI implements AI {

    protected final Random random;

    protected final GameController gameController;

    private boolean player;

    public EasyAI(GameController gameController) {
        this.gameController = gameController;
        this.player = false;
        this.random = new Random();
    }

    @Override
    public int[] move() {
        int[] coordinates = new int[2];
        coordinates[0] = random.nextInt(GameController.GRID_LENGTH);
        coordinates[1] = random.nextInt(GameController.GRID_LENGTH);
        return coordinates;
    }

    @Override
    public boolean isPlayer() {
        return player;
    }

    @Override
    public String getMoveMessage() {
        return "Making move level \"easy\"";
    }

}
