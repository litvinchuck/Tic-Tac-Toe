package com.elitvinchuck.tictactoe.views;

import com.elitvinchuck.tictactoe.Constants;
import com.elitvinchuck.tictactoe.controllers.GameAIController;
import com.elitvinchuck.tictactoe.controllers.GameController;

import java.util.Scanner;

public class GameIO {

    private static GameController gameController = GameController.getInstance();

    public static void redraw() {
        drawGameField();
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    private static void drawGameField() {
        System.out.println("     1   2   3");
        for (int i = 0; i < Constants.GRID_LENGTH; i++) {
            System.out.printf("%d   ", i + 1);
            for (int j = 0; j < Constants.GRID_LENGTH; j++) {
                System.out.printf(" %c", gameController.get(i, j));
                if (j != Constants.GRID_LENGTH - 1) {
                    System.out.print(" |");
                }
            }
            System.out.println();
            if (i != Constants.GRID_LENGTH - 1) {
                System.out.println("    ---+---+---");
            }
        }
    }

    public static void printGameState() {
        if (gameController.hasXWon()) {
            System.out.println("X wins");
        } else if (gameController.hasOWon()) {
            System.out.println("O wins");
        } else if (gameController.isDraw()) {
            System.out.println("Draw");
        } else if (!gameController.isGameFinished()) {
            System.out.println("Game not finished");
        }
    }

    public static int[] getCoordinates() {
        printPrompt(GameAIController.getInstance().getCurrentSide());
        Scanner scanner = new Scanner(System.in);
        String[] coordinates;
        int i = 0;
        int j = 0;

        do {
            coordinates = scanner.nextLine().split(" ");
            if (!areCoordinatesValid(coordinates)) {
                System.out.println("Please enter a pair of coordinates (1-3) (1-3):");
                continue;
            }

            i = Integer.parseInt(coordinates[0]) - 1;
            j = Integer.parseInt(coordinates[1]) - 1;
            if (gameController.isCellOccupied(i, j)) {
                System.out.println("Cell already occupied. Enter another one:");
            }
        }
        while (!areCoordinatesValid(coordinates) || gameController.isCellOccupied(i, j));
        return new int[] {i, j};
    }

    private static boolean areCoordinatesValid(String[] coordinates) {
        return coordinates.length == 2
                && coordinates[0].matches("\\d+")
                && coordinates[1].matches("\\d+")
                && gameController.isIndexInBounds(Integer.parseInt(coordinates[0]) - 1)
                && gameController.isIndexInBounds(Integer.parseInt(coordinates[1]) - 1);
    }

    private static void printPrompt(char side) {
        System.out.printf("It's %c turn! Type a coordinate. Ex: 2 3:%n", side);
    }

}
