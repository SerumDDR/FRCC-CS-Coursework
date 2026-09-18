/*
    Name: Donnie Ranjel
    File Name: PriceArrayHandles.java
    Date: 09/12/2025
    Class: CSC 1060-503
*/

/*
    A simple Java program demonstrating method creation, method overloading,
    and basic arithmetic operations. This program follows assignment requirements,
    including initializing a result variable, printing before and after calls,
    and handling division by zero.
*/

public class Calculator {

    public static void main(String[] args) {

        // Step 2: Create variables for numbers and result
        int num1 = 12;
        int num2 = 4;
        int num3 = 2; // Used for overloaded methods
        int result;

        // Step 3 & 4: Call standard methods with two integers

        // Addition
        result = 0;
        printResultInitialization(result);
        result = add(num1, num2);
        printResult(num1 + " + " + num2, result);

        // Subtraction
        result = 0;
        printResultInitialization(result);
        result = subtract(num1, num2);
        printResult(num1 + " - " + num2, result);

        // Multiplication
        result = 0;
        printResultInitialization(result);
        result = multiply(num1, num2);
        printResult(num1 + " * " + num2, result);

        // Division
        result = 0;
        printResultInitialization(result);
        result = divide(num1, num2);
        printResult(num1 + " / " + num2, result);

        // Step 5 & 6: Call overloaded methods with three integers

        // Overloaded Addition
        result = 0;
        printResultInitialization(result);
        result = add(num1, num2, num3);
        printResult(num1 + " + " + num2 + " + " + num3, result);

        // Overloaded Subtraction
        result = 0;
        printResultInitialization(result);
        result = subtract(num1, num2, num3);
        printResult(num1 + " - " + num2 + " - " + num3, result);

        // Overloaded Multiplication
        result = 0;
        printResultInitialization(result);
        result = multiply(num1, num2, num3);
        printResult(num1 + " * " + num2 + " * " + num3, result);

        // Overloaded Division
        result = 0;
        printResultInitialization(result);
        result = divide(num1, num2, num3);
        printResult(num1 + " / " + num2 + " / " + num3, result);
    }

    // Helper method to print initialization
    public static void printResultInitialization(int result) {
        System.out.println("Initialization: Result = " + result);
    }

    // Helper method to print operation and result
    public static void printResult(String operation, int result) {
        System.out.println(operation + " = " + result + "\n");
    }

    // Step 1: Standard Methods

    // Add two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Overloaded add for three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // Subtract two integers
    public static int subtract(int a, int b) {
        return a - b;
    }

    // Overloaded subtract for three integers
    public static int subtract(int a, int b, int c) {
        return a - b - c;
    }

    // Multiply two integers
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Overloaded multiply for three integers
    public static int multiply(int a, int b, int c) {
        return a * b * c;
    }

    // Divide two integers (handles divide by zero)
    public static int divide(int a, int b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero. Returning 0.");
            return 0;
        }
        return a / b;
    }

    // Overloaded divide for three integers (handles divide by zero)
    public static int divide(int a, int b, int c) {
        if (b == 0 || c == 0) {
            System.out.println("Error: Cannot divide by zero. Returning 0.");
            return 0;
        }
        return (a / b) / c;
    }
}