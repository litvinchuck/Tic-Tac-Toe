package com.elitvinchuck.tictactoe.views;

import com.elitvinchuck.tictactoe.ai.*;
import com.elitvinchuck.tictactoe.controllers.GameAIController;
import com.elitvinchuck.tictactoe.controllers.GameController;
import com.elitvinchuck.tictactoe.exceptions.TicTacToeException;

import java.util.Scanner;

public class GameIO {

    public final static String EASY = "easy";

    public final static String MEDIUM = "medium";

    public final static String HARD = "hard";

    public final static String USER = "user";

    private final GameController gameController;

    private final GameAIController gameAIController;

    public GameIO(String x, String o) {
        this.gameController = new GameController();
        this.gameAIController = new GameAIController(gameController, produceAI(x), produceAI(o));
    }

    public void printGameField() {
        System.out.println("---------");
        for (int i = 0; GameController.isIndexInBounds(i); i++) {
            System.out.print("| ");
            for (int j = 0; GameController.isIndexInBounds(j); j++) {
                System.out.print(gameController.get(i, j));
                System.out.print(' ');
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public void printGameState() {
        if (gameAIController.isGameImpossible()) {
            System.out.println("Impossible");
        } else if (gameAIController.hasXWon()) {
            System.out.println("X wins");
        } else if (gameAIController.hasOWon()) {
            System.out.println("O wins");
        } else if (gameAIController.isDraw()) {
            System.out.println("Draw");
        } else if (!gameAIController.isGameFinished()) {
            System.out.println("Game not finished");
        }
    }

    public void loop() {
        while(!gameAIController.isGameFinished()) {
            printGameField();
            move();
        }
        printGameField();
        printGameState();
    }

    private void move() {
        try {
            String message = gameAIController.getMoveMessage();
            gameAIController.move();
            if (!message.isEmpty()) {
                System.out.println(message);
            }
        } catch (TicTacToeException e) {
            if (gameAIController.isPlayerAI()) {
                System.out.println(e.getMessage());
            }
            move();
        }
    }

    private AI produceAI(String name) {
        if (name.equals(EASY)) {
            return new EasyAI(gameController);
        } else if (name.equals(MEDIUM)) {
            return new MediumAI(gameController);
        } else if (name.equals(HARD)) {
            return new HardAI(gameController);
        } else {
            return new Player();
        }
    }

    public static int[] processPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String[] coordinates = scanner.nextLine().split(" ");
        int i;
        int j;

        try {
            i = Integer.parseInt(coordinates[0]);
            j = Integer.parseInt(coordinates[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("You should enter numbers!");
            return processPlayerInput();
        }

        i -= 1;
        j -= 1;

        if (!GameController.isIndexInBounds(i) || !GameController.isIndexInBounds(j)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return processPlayerInput();
        }
        return new int[] { i, j };
    }

    public static void printWelcome() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("To start type: start player player");
        System.out.println("where player is: ");
        System.out.println("    user - human player");
        System.out.println("    easy - easy ai");
        System.out.println("    medium - medium ai");
        System.out.println("    hard - hard ai");
    }

    public static String[] processStartCommands() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input command: ");
        String[] command = scanner.nextLine().split(" ");
        if (!isCommandValid(command)) {
            System.out.println("Bad parameters!");
            return processStartCommands();
        }
        return command;
    }

    private static boolean isCommandValid(String[] command) {
        return areParametersValid(command) || (command.length == 1 && command[0].equals("exit"));
    }

    private static boolean areParametersValid(String[] command) {
        return command.length == 3
                && isParameterValid(command[1])
                && isParameterValid(command[2]);
    }

    private static boolean isParameterValid(String parameter) {
        return parameter.equals(EASY) || parameter.equals(USER) || parameter.equals(MEDIUM) || parameter.equals(HARD);
    }

}
