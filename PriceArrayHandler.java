/*
    Name: Donnie Ranjel
    File Name: PriceArrayHandles.java
    Date: 09/06/2025
    Class: CSC 1060-503
*/
import java.util.Scanner;

public class PriceArrayHandler {

    public static void main(String[] args) {
        // Step 1: Declare a variable named pricesArray, that is an array with 100 float numbers
        float[] pricesArray = new float[100];

        // Step 2: Write the code that will initialize the above array, pricesArray, so that all elements are -1.
        for (int i = 0; i < pricesArray.length; i++) {
            pricesArray[i] = -1.0f;
        }

        // Step 4: Write the code that will fill the array with prices until the user enters a negative number.
        Scanner scanner = new Scanner(System.in);
        int count = 0; // This will track how many prices the user actually enters.

        // Loop as long as the user enters positive numbers AND the array is not full.
        while (count < pricesArray.length) {
            System.out.print("Enter a price (or a negative number to stop): ");
            float price = scanner.nextFloat();

            // Store the entered price in the array
            pricesArray[count] = price;
            count++; // Increment the counter for the next item

            // If the user entered a negative number, exit the input loop.
            if (price < 0) {
                break;
            }
        }
        
        System.out.println(); // Add a newline for cleaner output formatting

        // Step 3: Write the code that will output every element in the pricesArray
        // Step 5: Output the array with each value on a separate line.
        System.out.println("--- Entered Prices ---");
        // Loop only for the number of items the user entered (tracked by 'count').
        for (int i = 0; i < count; i++) {
            System.out.println("Value " + (i + 1) + " is " + pricesArray[i]);
        }

    } // end main
} // end class

