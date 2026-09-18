/*
    Name: Donnie Ranjel
    File Name: InsertionSortProgram.java
    Date: 10/28/2025
    Class: CSC 1060-503
*/

import java.util.Scanner;

/*
    This InsertionSortProgram reads a line of integers from the user.
    The program then sorts the numbers using the Insertion Sort algorithm.
    It prints each intermediate step of sorting and counts the total
    number of comparisons and swaps (shifts) performed.
*/
public class InsertionSortProgram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 2: Read the full input line from the user.
        // The first number will indicate how many numbers will follow.
        String input = sc.nextLine();
        String[] tokens = input.split(" ");

        // Convert the first token into an integer for array size.
        int size = Integer.parseInt(tokens[0]);
        int[] arr = new int[size];

        // Store the next 'size' numbers into the array.
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokens[i + 1]);
        }

        // Step 3: Display the array before sorting to confirm input.
        printArray(arr);

        // Step 5: Initialize counters for comparisons and swaps.
        int comparisons = 0;
        int swaps = 0;

        // Step 4: Perform the Insertion Sort algorithm where the left portion is sorted
        // and insert each new element into its correct position.

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];  // Current value to insert
            int j = i - 1;

            // Move elements greater than key one position ahead
            // until the correct position for key is found.
            while (j >= 0) {
                comparisons++;  // Count each comparison

                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // Shift the larger value right
                    swaps++;             // Count each shift as a swap
                    j--;                 // Move left in the sorted portion
                } else {
                    // Stop when the correct position for key is found
                    break;
                }
            }

            // Insert key in its correct position
            arr[j + 1] = key;

            // Print the array after each insertion step
            printArray(arr);
        }

        // Step 5: After sorting, print total number of comparisons and swaps
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total swaps: " + swaps);

        sc.close();
    }

    // Helper method that prints the array elements in one line.
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
