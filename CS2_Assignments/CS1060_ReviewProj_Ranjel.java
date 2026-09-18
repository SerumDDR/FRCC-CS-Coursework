/*
    Name: Donnie Ranjel
    Date: 2/21/2025
    File Name: CS1060_ReviewProj_Ranjel.java
    Program Name: Craps
    Program Description: This program is a simplified game of Craps that tracks dice rolls, win/loss conditions, and player statisics. 
    Inputs:  User's name, yes/no to play again, and random dice rolls.
    Outputs: ASCII dice images, roll results, win/loss messages, and final statistics.
*/

import java.util.Scanner;

public class CS1060_ReviewProj_Ranjel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Constants for the game
        final int MAX_ROLLS = 500;
        final int DIE_SIDES = 6;

        //Flag for the Main Game
        boolean playAgain = true;

        // Variables to track stats
        int totalWins = 0;
        int totalLosses = 0;
        int doubleWins = 0;

        int [] rollHistory = new int[MAX_ROLLS]; // Array to hold roll history (sums 2-12)
        int rollCount = 0; // counter or total rolls

        System.out.println("Welcome to Craps!");
        System.out.printf("Please enter your name: ");    // Prompt user for name
        String playerName = scanner.nextLine();

        while (playAgain) {

            System.out.println("\n--- New Game ---");
            
            // Roll two dice (using math. Random) individually
            int die1 = (int)(Math.random() * DIE_SIDES) + 1;
            int die2 = (int)(Math.random() * DIE_SIDES) + 1;
            int sum = die1 + die2;  // sum of both dice rolled

            // Store the sum of the dice in the roll history array
            rollHistory[rollCount] = sum;
            rollCount++;    // increment roll count for history tracking

            // calling the die to print out
            printDice(die1);
            printDice(die2);

            // Print out dice roll values.
            System.out.println(playerName + " rolled: " + die1 + " + " + die2 + " = " + sum);

            // If the sum is 7 or 11 (called natural), you win
            if (sum == 7 || sum == 11) {
                System.out.println("Natural! You Win!");
                totalWins++;    // adds to total win stats
            }
            // If the sum is 2, 3, or 12 (called craps), you lose
            else if (sum == 3 || sum == 2 || sum == 12) {
                System.out.println("Craps! You Lose!");   
                totalLosses++;  // adds to total loss stats
            }
            else {
                // If the sum is another value (i.e., 4, 5, 6, 8, 9, or 10), a point is established.
                int point = sum;
                System.out.println("Point is " + point);
                 boolean pointPhase = true;

                 while (pointPhase) {
                    System.out.println("\nRolling for point...");

                    // roll dice again and store the new sum
                    die1 = (int)(Math.random() * DIE_SIDES) + 1;
                    die2 = (int)(Math.random() * DIE_SIDES) + 1;
                    int newSum = die1 + die2;

                    // Store the sum of the dice in the roll history array
                    rollHistory[rollCount] = newSum;
                    rollCount++;

                    // calling the die to print out
                    printDice(die1);
                    printDice(die2);

                    // Print new dice values
                    System.out.println("Rolled: " + newSum);


                    if (newSum == point) {
                        // If the point is an even number and the roll is doubles matching the point.
                        if (die1 == die2) {
                            System.out.println("Big Win! You rolled doubles!");
                            doubleWins++;

                        // print out that the player rolled doubles and won big
                        } else {
                            System.out.println("You Win!");
                        }
                        totalWins++;    // adds to total win stats
                        pointPhase = false;
                    }
                    else if (newSum == 7) { // If a 7 is rolled before the point then you lose
                        System.out.println("Seven! You Lose!");
                        totalLosses++;  // adds to total loss stats
                        pointPhase = false;
                    }
                    else {
                        System.out.println("Keep rolling.");
                    }
                }
            }

            // Asks user if they want to play again.
            System.out.println("Would you like to play again? (y/n)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("n")) {
                playAgain = false;
            }
        }

        // print game stastics
        System.out.println("\n--- Final Statistics for " + playerName + " ---");
        System.out.println("Total Wins: " + totalWins);
        System.out.println(doubleWins + " Big Wins!");
        System.out.println(totalLosses + " Losses");

        System.out.println("\nRoll History");
        for (int i = 0; i < rollCount; i++) {
            System.out.println(rollHistory[i]);
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Helper method to print dice.
    // Each die has six faces representing values 1, 2, ..., and 6. 
    public static void printDice(int val) {
        switch (val) {
            case 1:
                System.out.println("-----");
                System.out.println("|   |");
                System.out.println("| o |");
                System.out.println("|   |");
                System.out.println("-----");
                break;
            case 2:
                System.out.println("-----");
                System.out.println("|o  |");
                System.out.println("|   |");
                System.out.println("|  o|");
                System.out.println("-----");
                break;
            case 3:
                System.out.println("-----");
                System.out.println("|o  |");
                System.out.println("| o |");
                System.out.println("|  o|");
                System.out.println("-----");
                break;
            case 4:
                System.out.println("-----");
                System.out.println("|o o|");
                System.out.println("|   |");
                System.out.println("|o o|");
                System.out.println("-----");
                break;
            case 5:
                System.out.println("-----");
                System.out.println("|o o|");
                System.out.println("| o |");
                System.out.println("|o o|");
                System.out.println("-----");
                break;
            case 6:
                System.out.println("-----");
                System.out.println("|o o|");
                System.out.println("|o o|");
                System.out.println("|o o|");
                System.out.println("-----");
                break;

        }
    }

}