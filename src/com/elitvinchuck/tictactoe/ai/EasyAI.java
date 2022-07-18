package com.elitvinchuck.tictactoe.ai;

import com.elitvinchuck.tictactoe.Constants;
import com.elitvinchuck.tictactoe.controllers.GameController;

import java.util.Random;

public class EasyAI implements AI {

    protected final Random random;

    public EasyAI() {
        this.random = new Random();
    }

    @Override
    public int[] move() {
        int i, j;

        do {
            i = random.nextInt(Constants.GRID_LENGTH);
            j = random.nextInt(Constants.GRID_LENGTH);
        } while (GameController.getInstance().isCellOccupied(i, j));

        return new int[] {i, j};
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

}
