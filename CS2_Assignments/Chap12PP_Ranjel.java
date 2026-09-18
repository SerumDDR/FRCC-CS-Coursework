/*
    Name: Donnie Ranjel
    Date: 3/20/2026
    File Name: Chap12_PP_Ranjel.java
    Assignment: Chapter 12 Programming Project
    Program Name: Hex and Binary Conversion Utility
    Program Description: This program converts user-entered hexdecimal or binary values into their decimal and alternate base equivalents. It validates input, handles custom exceptions, and logs all activity to an output file.
    Inputs: User enters either a hexadecimal value or a binary value through the menu.
    Outputs: Displays the decimal and converted hexadecimal or binary value, along with any error messages. All results are written to the console and the output file.
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// Custom exception class for invalid hexadecimal
class HexFormatException extends Exception {
    public HexFormatException(String message) { // Constructor to accept the error message
        super(message); // Pass exception message to parent Exception class
    }
}

// Custom exception class for invalid binary
class BinFormatException extends Exception {
    public BinFormatException(String message) {
        super(message);
    }
}

// Custom exception class for invalid menu selection
class MenuSelectionException extends Exception {
    public MenuSelectionException(String message) {
        super(message);
    }
}

// Custom overflow Exception if values are too large
class ValueOverflowException extends Exception {
    public ValueOverflowException(String message) {
        super(message);
    }
}

public class Chap12PP_Ranjel {
    public static void main(String[] args) {

        // Try-with-resources to close the output file  and scanner automatically 
        try (Scanner scanner = new Scanner(System.in);
          PrintWriter outputFile = new PrintWriter(new File("Chap12_Output.txt"))) {
            boolean isAppRunning = true;    // Flag to keep application running until exit is selected

            // Main application loop
            while (isAppRunning) {
                displayAndLog("\n Conversion Menu", outputFile);
                displayAndLog("1. Enter a Hexadecimal value", outputFile);  // Option 1
                displayAndLog("2. Enter a Binary value", outputFile);   // Option 2
                displayAndLog("3. Exit", outputFile);   // Option 3

                requestAndLog("Choose an option: ", outputFile);    // Prompt user user for selection

                String choice = scanner.nextLine().trim();  // Read user input
                displayAndLog(choice, outputFile);  // Log user input

                try {
                    // Switch statement to jump to matching case from user choice
                    switch (choice) {
                        case "1":
                            requestAndLog("Enter a Hexadecimal value: ", outputFile);   // Prompts user for input
                            String hexString = scanner.nextLine().trim();   // Reads user input
                            displayAndLog(hexString, outputFile);   // Logs user input
                            hexConv(hexString, outputFile); // Calls method to hexadecimal conversion
                            break;  // Stops switch statment

                        case "2":
                            requestAndLog("Enter a Binary value: ", outputFile);
                            String binString = scanner.nextLine().trim();
                            displayAndLog(binString, outputFile);
                            BinConv(binString, outputFile);
                            break;

                        case "3":
                            displayAndLog("Exiting the program.", outputFile);
                            isAppRunning = false;
                            break;

                            default:
                                throw new MenuSelectionException("Invalid selection. Please choose 1, 2, or 3.");   // Throws exception if input does not match any case
                    }

                } catch (HexFormatException e) {    // Catches custom exception
                    displayAndLog("Not a hex number.", outputFile);  // Displays error message and logs error
                } catch (BinFormatException e) {
                    displayAndLog("Not a binary number.", outputFile);
                } catch (MenuSelectionException e) {
                    displayAndLog(e.getMessage(), outputFile);
                } catch (ValueOverflowException e) {
                    displayAndLog(e.getMessage(), outputFile);
                }
            }
        } catch (IOException e) {   // Catches system level errors
            System.out.println("Error writing to the output file: " + e.getMessage());  // Prints error message
        }
    }

    // Hexadecimal conversion method
    public static void hexConv(String hex, PrintWriter outputFile) throws HexFormatException, ValueOverflowException {  // Throws custom exception
        if (hex.isEmpty()) {    // Is input empty?
            throw new HexFormatException("Hexadecimal value is empty.");    // Throws custom exception message
        }

        // Checks if value is too large
        if (hex.length() > 15) {
            throw new ValueOverflowException("Hex value too large to compute.");
        }

        long decimalValue = 0;  // Stores decimal

        for (int i = 0; i < hex.length(); i++) {    // Loops each character
            char c = Character.toUpperCase(hex.charAt(i));  // Converts to uppercase
            int value;  // Stores the numeric value

            if (c >= '0' && c <= '9') { // If character is 0-9
                value = c - '0';    // Convert it to a number
            } else if (c >='A' && c <= 'F') {   // If character if A-F
                value = c - 'A' + 10;   // Convert it to the number 10-15
            } else {
                throw new HexFormatException("Invalid hexadecimal character: " + c);    // Throws custom exception for invaid character
            }

            decimalValue = decimalValue * 16 + value;   // Updates decimal value
        }

        //Conversion to binary
        String binaryString = "";   // Stores binary value
        long tempDecimal = decimalValue;    // Copies decimal value

        // Binary loop
        if (tempDecimal == 0) {
            binaryString = "0";
        } else {
            
            // division loop to get a 0 or 1
            while (tempDecimal > 0) {
                binaryString = (tempDecimal % 2) + binaryString;    // Adds remainder to front
                tempDecimal /= 2;   // Chops last digit off
            }
        }

        displayAndLog("Decimal equivalent: " + decimalValue, outputFile);
        displayAndLog("Binary equivalent: " + binaryString, outputFile);
    } 

    // Binary conversion method
    public static void BinConv(String bin, PrintWriter outputFile) throws BinFormatException, ValueOverflowException {
        if (bin.isEmpty()) {
            throw new BinFormatException("Binary value is empty.");
        }

        // Checks if value is too large
        if (bin.length() > 63) {
            throw new ValueOverflowException("Binary value too large to computer.");
        }
        long decimalValue = 0;

        // Binary loop through each character
        for (int i = 0; i < bin.length(); i++) {
            char c = bin.charAt(i);
            
            if (c == '0' || c == '1') { // Valid binary digits
                decimalValue = decimalValue * 2 + (c - '0');    // multiply by 2 to shift over
            } else {
                throw new BinFormatException("Invalid binary character: " + c);
            }
        }

        String hexString = "";  // Stores hexadecimal value
        long tempDecimal = decimalValue;    // Copies value

        // Hexdecimal loop from binary to hexadecimal
        if (tempDecimal == 0) {
            hexString = "0";
        } else {
            while (tempDecimal > 0) {
                long remainder = tempDecimal % 16;  // Gets remainder to convert to hex
                
                // Adds remainder to front to get hexidecimal
                if (remainder < 10) {
                    hexString = remainder + hexString;
                } else {
                    hexString = (char) ('A' + (remainder - 10)) + hexString;    // Converts to letter
                }

                tempDecimal /= 16;  // Chops last digit off
            }
        }

        displayAndLog("Decimal equivalent: " + decimalValue, outputFile);
        displayAndLog("Hexadecimal equivalent: " + hexString, outputFile);
    }

    // Helper method to print in console and write to text file
    public static void displayAndLog(String message, PrintWriter outputFile) {
        System.out.println(message);
        outputFile.println(message);
    }

    // Helper method to ask user for input and write to text file
    public static void requestAndLog(String message, PrintWriter outputFile) {
        System.out.print(message);
        outputFile.print(message);
        outputFile.flush(); // To force write immediately
    }
}