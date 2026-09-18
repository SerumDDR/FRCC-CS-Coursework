/*
    Name: Donnie Ranjel
    Date: 4/10/2026
    File Name: Chap20B_PP_Ranjel.java
    Assignment: Chapter 20 Programming Project
    Program Name: LinkedList Traversal Benchmark
    Program Description: This program benchmarks LinkedList traversal by comparing ListIterator O(N) and get(index) O(N^2) performance using default or custom test settings, and outputs the average time for each method.
    Inputs: A user selection of default test or custom test. If custom is selected, the user provides integers for both the list size and the number of trials.
    Outputs: The average traversal time using a ListIterator, the average traversal time using get(index), progress updates during get(index) traversal, and a final comparison of both traversal methods.
*/

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Chap20B_PP_Ranjel {

    // Constants for default values
    private static final int DEFAULT_LIST_SIZE = 1_000_000;
    private static final int DEFAULT_TRIALS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize default values (may be overridden by user input)
        int listSize = DEFAULT_LIST_SIZE;
        int trials = DEFAULT_TRIALS;

        System.out.println("<-- LinkedList Traversal Benchmark -->");

        // Prompt the user for the default or custom test
        System.out.print("Run default test (1,000,000) [d], or enter custom values [c]? [d/c]: ");
        String userChoice = scanner.next();

        // Validates user input
        while (!userChoice.equalsIgnoreCase("d") && !userChoice.equalsIgnoreCase("c")) {
            System.out.print("Invalid choice. Please enter exactly 'd' or 'c': ");
            userChoice = scanner.next();
        }

        // User selects the custom test
        if (userChoice.equalsIgnoreCase("c")) {
            boolean validInputs = false;

            while (!validInputs) {

                // Attempt to read custom list size and trial count from the user
                try {
                    System.out.print("Enter custom list size (e.g., 10000): ");
                    listSize = scanner.nextInt();
                    System.out.print("Enter number of trials (e.g., 1): ");
                    trials = scanner.nextInt();
                    validInputs = true;

                  // Recover from non-numeric input and prompt the user again
                } catch (InputMismatchException e) {
                    String badInput = scanner.next();
                    System.out.println("Error: '" + badInput + "' is not a valid number. Let's try again.\n");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("\nRunning default test.");
        }

        // Check for invalid input
        if (trials <= 0) {
            System.out.println("Error: Number of trials must be greater than 0.");
            scanner.close();
            return;
        }

        // Displays the list of integers being generated
        System.out.println("Generating " + listSize + " in a list of integers.");

        // Generate a list of integers
        LinkedList<Integer> list = populateList(listSize);
        System.out.println("List generation complete!\n");

         // Displays that benchmark is starting with the given trial count
        System.out.println("<-- Starting Benchmark (" + trials + " Trials) -->");

        long totalIteratorTime = 0; // Initialize the total time for sequential traversal O(N)

        // Run multiple trials for sequential traversal O(N)
        for (int i = 1; i <= trials; i++) {
            totalIteratorTime += timeIterator(list);
        }

        // Calculate the average time for sequential traversal O(N)
        long avgIteratorTime = totalIteratorTime / trials;
        System.out.println("Iterator average of traversal: " + formatTime(avgIteratorTime));

        // Show progress during get(index) traversal
        System.out.println("\nStarting get(index) traversal on " + listSize + " elements.");
        long totalGetTime = 0; // Initialize the total time for get(index) traversal



        // Run multiple trials for get(index) traversal
        for (int i = 1; i <= trials; i++) {
            System.out.println("Running get(index) trial " + i + ".");
            totalGetTime += timeGet(list);
        }
        long avgGetTime = totalGetTime / trials; // Calculate the average time for get(index) traversal

        // Displays the final results
        System.out.println("\n<-- Final Results -->");
        System.out.println("Iterator Time: " + formatTime(avgIteratorTime)); // Display the average time for sequential traversal O(N)
        System.out.println("get(index) Time: " + formatTime(avgGetTime)); // Display the average time for random-access traversal O(N^2)
        System.out.println("<-- End of Benchmark -->");

        scanner.close();
    }

    // Helper method to populate a linked list with integers
    private static LinkedList<Integer> populateList(int size) {

        LinkedList<Integer> list = new LinkedList<>(); // Create a linked list

        // Populate the list with integers from 0 to size - 1
        for (int i = 0; i < size; i++) {
            list.add(i);    
        }
        return list;
    }

    // Helper method to benchmark the traversal time using ListIterator
    private static long timeIterator(LinkedList<Integer> list) {

        // Check if the list is null
        if (list == null) {
            return 0;
        }

        long startTime = System.nanoTime(); // Records the start time in nanoseconds

        ListIterator<Integer> iterator = list.listIterator(); // Create a list iterator

        // Iterate through the list
        while (iterator.hasNext()) {
            iterator.next();
        }

        return System.nanoTime() - startTime; // Returns the time in nanoseconds
    }

    // Helper method to benchmark the traversal time using get(index)
    private static long timeGet(LinkedList<Integer> list) {

        // Check if the list is null
        if (list == null) {
            return 0;
        }

        long startTime = System.nanoTime(); // Records the start time in nanoseconds
        int size = list.size(); // Gets the total number of elements in the list
        int milestone = Math.max(1, size / 10); // Computes the 10% progress milestone at least once

        // Iterate through the list
        for (int i  = 0; i  < size; i++) {
            list.get(i);

            // Displays progress every 10%
            if (i > 0 && i % milestone == 0) {
                int percentage = (i / milestone) * 10;
                System.out.println("Progress: " + percentage + "%");
            } 
        }

        return System.nanoTime() - startTime; // Returns the time in nanoseconds
    }

    // Helper method to format the time
    private static String formatTime(long nanoseconds) {

        // Convert nanoseconds to milliseconds
        double milliseconds = nanoseconds / 1_000_000.0;

        // Format the time in seconds with two decimal places
        if (milliseconds > 1000) {
            return String.format("%.2f seconds", milliseconds / 1000.0);
        }

        // Format the time in milliseconds with two decimal places
        return String.format("%.2f milliseconds", milliseconds);
    }
    
}
