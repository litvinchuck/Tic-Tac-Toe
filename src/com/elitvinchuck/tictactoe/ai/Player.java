package com.elitvinchuck.tictactoe.ai;

import com.elitvinchuck.tictactoe.views.GameIO;

public class Player implements AI {

    public boolean player;

    public Player() {
        this.player = true;
    }

    @Override
    public int[] move() {
        return GameIO.processPlayerInput();
    }

    @Override
    public boolean isPlayer() {
        return player;
    }

    @Override
    public String getMoveMessage() {
        return "";
    }

}
