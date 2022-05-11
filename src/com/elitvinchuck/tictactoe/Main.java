package com.elitvinchuck.tictactoe;

import com.elitvinchuck.tictactoe.views.GameIO;

public class Main {

    public static void main(String[] args) {
        if (GameIO.isSingleUser(args)) {
            GameIO.printSingleWelcome();
            String parameter = GameIO.processSingleUserStartCommands();
            GameIO gameIO = new GameIO("user", parameter);
            gameIO.loop();
        } else {
            GameIO.printWelcome();
            String[] commands = GameIO.processStartCommands();
            if (commands[0].equals("start")) {
                GameIO gameIO = new GameIO(commands[1], commands[2]);
                gameIO.loop();
            }
        }
        GameIO.getUserReturn();
    }

}
