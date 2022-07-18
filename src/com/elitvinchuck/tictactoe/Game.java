package com.elitvinchuck.tictactoe;

import com.elitvinchuck.tictactoe.ai.AI;
import com.elitvinchuck.tictactoe.ai.Player;
import com.elitvinchuck.tictactoe.controllers.GameAIController;
import com.elitvinchuck.tictactoe.controllers.GameController;
import com.elitvinchuck.tictactoe.views.GameIO;
import com.elitvinchuck.tictactoe.views.IntroIO;

public class Game {

    public static void loop() {
        IntroIO.printIntro();
        AI o = IntroIO.selectDifficulty();

        GameAIController gameAIController = GameAIController.buildInstance(new Player(), o);
        GameController gameController = GameController.getInstance();
        while (!gameController.isGameFinished()) {
            GameIO.redraw();
            gameAIController.move();
        }
        GameIO.redraw();
        GameIO.printGameState();
    }

}
