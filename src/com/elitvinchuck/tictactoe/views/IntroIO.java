package com.elitvinchuck.tictactoe.views;

import com.elitvinchuck.tictactoe.Constants;
import com.elitvinchuck.tictactoe.ai.*;

import java.util.Scanner;

public class IntroIO {

    public static void printIntro() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println();
    }

    public static AI selectDifficulty() {
        System.out.println("Please select the game difficulty.");
        System.out.println("Easy, Medium or Hard:");
        Scanner scanner = new Scanner(System.in);
        return parseAI(scanner.nextLine());
    }

    private static AI parseAI(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case Constants.EASY:
                return new EasyAI();
            case Constants.MEDIUM:
                return new MediumAI();
            case Constants.HARD:
                return new HardAI();
            default:
                return new Player();
        }
    }

}
