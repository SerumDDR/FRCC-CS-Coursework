/*
    Name: Donnie Ranjel
    File Name: NestedWhileLoop.java
    Date: 09/05/2025
    Class: CSC 1060-503
 */

import java.util.Random;
import java.util.Scanner;

public class NestedWhileLoop {

    public static void main(String[] args) {
        // Step 1 & 8: Declare all variables
        int count;
        int quantity;
        int nestedCount;
        int value;
        int sum;

        // Create Scanner and Random objects
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Step 2: Set count to 0 and get a value for quantity
        System.out.println("Number of times to run the loop.");
        quantity = scanner.nextInt();

        sum = 0; // Initialize sum
        count = 0; // Step 7: Loop 1 Initializer

        // Step 3 & 7: Create a while loop with a condition
        // Loop 1 Condition
        while (count < quantity) {

            // Step 7: Loop 1 Body starts here  
            // Step 5: Add an accumulator for count
            // Step 7: Loop 1 Changer
            count ++;

            // Step 6: Output count in the body of the while loop
            System.out.println("\nOuter loop count: " + count);

            // Step 9: Generate a random number between 0 and 100
            value = random.nextInt(101); // nextInt(101) gives a number from 0-100
            System.out.println("Random number is " + value);

            // Step 12: Add value to sum
            sum += value;

            // Step 10 & 14: Nested while loop
            nestedCount = 0; // Step 14: Nested Loop Initializer

            // Step 14: Nested Loop Condition
            while (nestedCount < value) {
                // Step 14: Nested Loop Body starts here

                // Step 14: Nested Loop Changer
                nestedCount++;
                System.out.print(nestedCount + " "); // Output nestedCount horizontally

            } // end nested while

            // Step 11: After the inside loop add new line
            System.out.println();

        } // Step 4: end while

        // Step 12: Output sum at the end of the program
        System.out.println("\ntotal is: " + sum);

    } // end main
} // end class

