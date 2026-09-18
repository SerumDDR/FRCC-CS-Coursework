/*
    Name: Donnie Ranjel
    Date: 4/1/2026
    File Name: Recursion_PP_Ranjel.java
    Assignment: Chapter 18 Programming Project
    Program Name: Recursion Converter
    Program Description: A menu-based converter that uses recursion to convert between decimal, binary, and hexadecimal values; with custom exception handling for invalid inputs.
    Inputs: Menu choice (1-5), decimal for two values for two conversion, a binary string, and  hexadecimal string.
    Outputs: Binary, hexdecimal, and decimal results based on the user's selected conversion option.
*/

import java.util.Scanner;

public class Recursion_PP_Ranjel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isAppRunning = true; // Flag to keep application running


        System.out.println("<-- Recursion Converter -->");

        // Main application menu loop
        while (isAppRunning) {
            System.out.println("\n<-- Main Menu -->");
            System.out.println("1. Convert Decimal to Binary");
            System.out.println("2. Convert Decimal to Hexadecimal");
            System.out.println("3. Convert Binary to Decimal");
            System.out.println("4. Convert Hexadecimal to decimal");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String userChoice = scanner.next();

            // Switch statement to jump to matching conversion case from user choice using recursion
            try{
                switch (userChoice) {
                    case "1":
                        System.out.print("Enter a decimal value: ");
                        int decimalToBinary = scanner.nextInt();
                        System.out.println("Binary: " + dec2Bin(decimalToBinary));
                        break;
                    case "2":
                        System.out.print("Enter a decimal value: ");
                        int decimalToHexadecimal = scanner.nextInt();
                        System.out.println("Hexadecimal: " + dec2Hex(decimalToHexadecimal));
                        break;
                    case "3":
                        System.out.print("Enter a binary value: ");
                        String binaryToDecimal = scanner.next();
                        System.out.println("Decimal: " + bin2Dec(binaryToDecimal));
                        break;
                    case "4":
                        System.out.print("Enter a hexadecimal value: ");
                        String hexadecimalToDecimal = scanner.next();
                        System.out.println("Decimal: " + hex2Dec(hexadecimalToDecimal));
                        break;
                    case "5":
                        isAppRunning = false;
                        System.out.println("Exiting. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1-5.");
                        break;
                }
            } catch (InvalidFormatException e) {    // Catches custom exception for invalid character
                System.out.print("\nError: " + e.getMessage());
            } catch (Exception e) {   // Catches other errors
                System.out.println("Error: Enter a valid number.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Decimal to Binary conversion
    public static String dec2Bin(int value) {
        // Base case: 0 is converted to "0"
        if (value == 0) {
            return "0";
        }

        // Handle negative values
        if (value < 0) {
            return "-" + decToBin(Math.abs(value), "");
        }

        // Initial call to start recursion
        return decToBin(value, "");
    }

    // Helper method to convert decimal to binary
    private static String decToBin(int value, String result) {
        // Base case: No more division needed                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
        if (value == 0) {
            return result;
        }
        // Recursive case: divide by 2 and build binary string
        return decToBin(value / 2, (value % 2) + result);
    }

    // Decimal to Hexadecimal conversion
    public static String dec2Hex(int value) {
        // Base case: 0 is converted to "0"
        if (value == 0) {
            return "0";
        }
        // Initial call to start recursion
        return decToHex(value, "");
    }

    // Helper method to convert decimal to hexadecimal
    private static String decToHex(int value, String result) {
        // Base case: No more division needed  
        if (value == 0) {
            return result;
        }

        int remainder = value % 16; // Get the remainder for the next hex digit
        char hexChar; // Holds the hex character from the remainder

        // Switch statement to convert remainder to hexadecimal character
        switch (remainder) {
            case 10: 
                hexChar = 'A'; 
                break;
            case 11: 
                hexChar = 'B';
                break;
            case 12: 
                hexChar = 'C';
                break;
            case 13: 
                hexChar = 'D';
                break;
            case 14: 
                hexChar = 'E';
                break;
            case 15:
                hexChar = 'F';
                break;
            // Digital character for 0-9 
            default:
                hexChar = (char)(remainder + '0');
                break;
        }

        // Recursive case: divide by 16 and build hexadecimal string
        return decToHex(value / 16, hexChar + result);

    }

    // Binary to Decimal conversion
    public static int bin2Dec(String binaryString) {

        // Initial call to start recursion
        return binToDec(binaryString, 0, 0);
    }

    // Helper method to convert binary to decimal
    private static int binToDec(String s, int index, int result) {
        // Base case: reached end of string
        if (index == s.length()) {
            return result;
        }

        char currentChar = s.charAt(index); // Get the current character
        
        // Check if the character is a valid binary value
        if (currentChar != '0' && currentChar != '1') {
            throw new InvalidFormatException("Invalid binary character: '" + currentChar + "'");
        }

        int digit = currentChar - '0'; // Convert character to integer

        // Recursive case: multiply current result by 2 and add next digit
        return binToDec(s, index + 1, (result * 2) + digit);
    }

    // Hexadecimal to Decimal conversion
    public static int hex2Dec(String hexString) {

        // Initial call to start recursion
        return hexToDec(hexString.toUpperCase(), 0, 0);
    }

    // Helper method to convert hexadecimal to decimal
    private static int hexToDec(String s, int index, int result) {
        // Base case: reached end of string
        if (index == s.length()) {
            return result;
        }

        // Get numeric value of the character
        String hexValues = "0123456789ABCDEF";
        char currentChar = s.charAt(index);
        int digit = hexValues.indexOf(currentChar);

        // Check if the character is a valid hexadecimal value
        if (digit == -1) {
            throw new InvalidFormatException("Invalid hexadecimal character: '" + currentChar + "'");
        }

        // Recursive case: multiply current result by 16 and add next hex value
        return hexToDec(s, index + 1, (result * 16) + digit);
    }
}

// Custom exception for invalid format
class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String message) {
        super(message);
    }
}