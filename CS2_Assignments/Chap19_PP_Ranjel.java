/*
    Name: Donnie Ranjel
    Date: 3/29/2026
    File Name: Chap19_PP_Ranjel.java
    Assignment: Chapter 19 Programming Project
    Program Name: Integer List Utility
    Program Description: This program reads ten integers from the user, stores them in a list, removes duplicates, sorts the processed list in increasing order, and then randomly shuffles the elements before displaying all results.
    Inputs: Ten  integer values entered by the user.
    Outputs: The original list, the list with duplicates removed, the sorted list, and the randomly shuffled list.
*/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Chap19_PP_Ranjel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAppRunning = true;  // Flag to keep application running


        System.out.println("<-- Integer List Utility -->");

        // Main and test application loop
        while (isAppRunning) {

            ArrayList<Integer> userNumbers = new ArrayList<>(); //Stores integers from user input
            
            System.out.print("Enter 10 numbers: ");

            for (int i = 0; i < 10; i++) { // Continue loping while i is less than 10
                
                try {
                    userNumbers.add(scanner.nextInt()); // Reads and adds integer

                    // Handles invalid input by capturing bad input, displays error, clears buffer, and retry
                } catch (InputMismatchException e) {
                    String badInput = scanner.next();
                    System.out.println("Error: '" + badInput + "' is not a valid number. Please enter numbers only.");
                    scanner.nextLine();
                    i--;
                }
            }

            System.out.println("\n<-- Results -->");
            printList(userNumbers, "Original List: ");

            ArrayList<Integer> processedNumbers = removeDuplicates(userNumbers); // Creates a new list with duplicates removed
            System.out.println("Number of processed numbers: " + processedNumbers.size()); // Displays how many unique number remain
            printList(processedNumbers, "List after removing duplicates: "); // Prints the list after removing duplicates


            sort(processedNumbers); // Sorts the list in increasing order
            printList(processedNumbers, "List after sorting with increasing order: "); // Prints the list after sorting


            shuffle(processedNumbers); // Randomly shuffles the elements
            printList(processedNumbers, "List after random shuffling: "); // Prints the list after shuffling

            // To use again or close application
            System.out.print("Would you like to process another list? (y/n): ");
            String userChoice = scanner.next();

            // Validates user input
            while (!userChoice.equalsIgnoreCase("y") && !userChoice.equalsIgnoreCase("n")) {
                System.out.print("Invalid choice. Please enter 'y' or 'n': ");
                userChoice = scanner.next();
            }

            // Closes application
            if (userChoice.equalsIgnoreCase("n")) {
                isAppRunning = false;
            }
        }

        scanner.close();
        System.out.println("<-- End of Program -->");
    }

    //  A generic method that returns a list with duplicates removed
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        // Returns an empty list if the input list is null
        if (list == null) {
            return new ArrayList<>();
        }
        ArrayList<E> processedList = new ArrayList<>(); // Stores unique numbers only

        // Checks each element in the list
        for (E element : list) {

            // Adds elemet is not already in the processed list
            if (!processedList.contains(element)) {
                processedList.add(element);
            }
        }

        // returns list with duplicates removed
        return processedList;
    }

    // A generic method that sorts a list in increasing order
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {

        // Skips processing if the list has 0 or 1 element
        if (list == null || list.size() <= 1) {
            return;
        }

        // Set starting position and intialize the minimum index 
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;

            // Scan the remaining elements to find the smallest index
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Performs a swap, only if the smaller index is not the current index
            if (minIndex != i) {
                swap(list, i, minIndex);
            } 
        }
    }

    // A generic method that randomly shuffles the elements in a list
    public static <E> void shuffle(ArrayList<E> list) {
        if (list == null || list.size() <= 1) {
            return;
        }

        for (int i = list.size() - 1; i > 0; i--) { // Iterate the list in reverse order
            int j = (int)(Math.random() * (i + 1)); // Generate a random index

            swap(list, i, j); // Exchange the current index with the random index
        }
    }

    // A generic method that swaps two elements in a list
    private static <E> void swap(ArrayList<E> list, int index1, int index2) {
        E temp = list.get(index1); // Stores the element at index1
        list.set(index1, list.get(index2)); // Replace index1 with the element at index2
        list.set(index2, temp); // Put the saved element at index2
    }

    // A generic method that prints a list with a message
    private static <E> void printList(ArrayList<E> list, String message) {
        System.out.println(message); // Prints the message before the list

        //Prints each element followed by a space
        for (E element : list) {
            System.out.print(element + " ");
        }
        System.out.println("\n"); // Prints a blank line for spacing
    }
}