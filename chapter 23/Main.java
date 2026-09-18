/*
    Name: Donnie Ranjel
    Date: 4/25/2026
    File Name: Main.java
    Assignment: Chapter 23 Programming Project 
    Program Name: Enhanced Sorting Algorithms
    Program Description: This program implements two enhanced sorting algorithms: QuickSort with median-of-three pivot selection and an in-place MergeSort that recursively sorts array halves without creating temporary subarrays. This program runs automated tests and allows the user to enter custom integer lists, demonstrating that both sorting methods correctly reorder the data in ascending order.
    Inputs: Hardcoded test arrays and an optional user-entered list of integers provided through console input.
    Outputs: The original and sorted arrays produced by QuickSort and MergeSort, along with error messages for invalid input.

    <-- RUN THIS FILE -->
*/

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("<-- Running Default Tests -->");
        runAutomatedTests();

        System.out.println("<-- Enhanced Sorting Algorithms -->");

        Scanner scanner = new Scanner(System.in);
        boolean isAppRunning = true;

        // Main menu loop to repeat sorting sessions
        while (isAppRunning) {
            System.out.print("Would you like to sort a list of integers? (y/n): ");
            String userChoice = scanner.nextLine().trim().toLowerCase();

            // Handles user choice to either exit or enter a custom list to sort
            if (userChoice.equals("n") || userChoice.equals("no")) {
                isAppRunning = false;
                System.out.println("Exiting Program. Goodbye!");
            } else if (userChoice.equals("y") || userChoice.equals("yes")) {
                System.out.print("Enter a list of integers separated by spaces: ");
                String line = scanner.nextLine().trim();

                // Parse the input line into tokens and create arrays for sorting
                if (!line.isEmpty()) {
                    String[] stringTokens = line.split("\\s+");
                    int[] userArray1 = new int[stringTokens.length];
                    int[] userArray2 = new int[stringTokens.length];

                    try {

                        // Convert each token to an integer and copy into both arrays
                        for (int i = 0; i < stringTokens.length; i++) {
                            int num = Integer.parseInt(stringTokens[i]);
                            userArray1[i] = num;
                            userArray2[i] = num;
                        }

                        // Run QuickSort and display the original and sorted arrays
                        System.out.println("\n<-- Custom Quick Sorting -->");
                        System.out.println("Original array: " + Arrays.toString(userArray1));
                        QuickSort.quickSort(userArray1);
                        System.out.println("Sorted array: " + Arrays.toString(userArray1));

                        // Run MergeSort and display the original and sorted arrays 
                        System.out.println("\n<-- Custom Merge Sorting -->");
                        System.out.println("Original array: " + Arrays.toString(userArray2));
                        MergeSort.mergeSort(userArray2);
                        System.out.println("Sorted array: " + Arrays.toString(userArray2));
                        System.out.println();

                      // Handle invalid integer input
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter valid integers only.\n");
                    }

                  // Handles if user presses enter without typing anything
                } else {
                    System.out.println("No input detected.\n");
                }

              // Handles responses that is not 'y' or 'n'
            } else {
                System.out.println("Invalid choice. Please enter 'y' or 'n'.\n");
            } 
        }
        scanner.close();
    }

    private static void runAutomatedTests() {

        // Hardcoded first list values for default testing
        int[] list1 = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};

        // Display the original array 
        System.out.println("<-- Testing Quick Sort -->");
        System.out.println("Original array: " + Arrays.toString(list1));

        // Run Quicksort on the test array and display sorted results
        QuickSort.quickSort(list1);
        System.out.println("Sorted array: " + Arrays.toString(list1));
        System.out.println();

        // Hardcoded second list values
        int [] list2 = {9, 1, 8, 2, 7, 3, 6, 4, 5, -5, 0};

        // Display the original array
        System.out.println("<-- Testing Merge Sort -->");
        System.out.println("Original array: " + Arrays.toString(list2));

        // Run MergeSort on the test array and display the sorted results
        MergeSort.mergeSort(list2);
        System.out.println("Sorted array: " + Arrays.toString(list2));
    }
}