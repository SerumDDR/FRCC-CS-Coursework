/*
 * Name: Donnie Ranjel
 * Assignment Title: Chapter 3: Branches Assignment
 * Course & Section: CSC (FRCC) 1060 503
 */

import java.util.Scanner;

public class CompareValues {
    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        
        // Block 1: Compare two numbers

        int numberOne;
        int numberTwo;

        System.out.print("Enter the first number: ");
        numberOne = scnr.nextInt();

        System.out.print("Enter the second number: ");
        numberTwo = scnr.nextInt();

        if (numberOne > numberTwo) {
            System.out.println("The higher number is: " + numberOne);
        }
        else if (numberTwo > numberOne) {
            System.out.println("The higher number is: " + numberTwo);
        }
        else {
            System.out.println("Both numbers are equal.");
        }

        // Block 2: Order three numbers

        int numberThree;

        System.out.print("Enter a third number: ");
        numberThree = scnr.nextInt();

        // Sorting logic using if statements
        if (numberOne <= numberTwo && numberOne <= numberThree) {
            if (numberTwo <= numberThree) {
                System.out.println("Order: " + numberOne + ", " + numberTwo + ", " + numberThree);
            }
            else {
                System.out.println("Order: " + numberOne + ", " + numberThree + ", " + numberTwo);
            }
        }
        else if (numberTwo <= numberOne && numberTwo <= numberThree) {
            if (numberOne <= numberThree) {
                System.out.println("Order: " + numberTwo + ", " + numberOne + ", " + numberThree);
            }
            else {
                System.out.println("Order: " + numberTwo + ", " + numberThree + ", " + numberOne);
            }
        }
        else {
            if (numberOne <= numberTwo) {
                System.out.println("Order: " + numberThree + ", " + numberOne + ", " + numberTwo);
            }
            else {
                System.out.println("Order: " + numberThree + ", " + numberTwo + ", " + numberOne);
            }
        }

        // Block 3: Compare two Strings

        String str1;
        String str2;

        System.out.print("Enter the first word: ");
        str1 = scnr.nextLine();

        System.out.print("Enter the second word: ");
        str2 = scnr.nextLine();

        // Compare alphabetically
        if (str1.compareTo(str2) < 0) {
            System.out.println(str1 + " is before " + str2);
        }
        else if (str1.compareTo(str2) > 0) {
            System.out.println(str2 + " is before " + str1);
        }
        else {
            System.out.println("Both words are the same alphabetically");
        }

        // Compare with ==
        if (str1.equals(str2)) {
            System.out.println("The words are identical.");
        }
        else {
            System.out.println(str1 + " == " + str2 + " is FALSE (different references)");
        }

        // Compare with !=
        if (str1 != str2) {
            System.out.println(str1 + " != " + str2 + " is TRUE (different references)");
        }
        else {
            System.out.println(str1 + " != " + str2 + " is FALSE (same reference)");
        }

    }
}
