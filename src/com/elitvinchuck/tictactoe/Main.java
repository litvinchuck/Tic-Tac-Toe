package com.elitvinchuck.tictactoe;

import com.elitvinchuck.tictactoe.views.GameIO;

public class Main {

    public static void main(String[] args) {
        GameIO.printWelcome();
        String[] commands = GameIO.processStartCommands();
        if (commands[0].equals("start")) {
            GameIO gameIO = new GameIO(commands[1], commands[2]);
            gameIO.loop();
        }
    }

}
