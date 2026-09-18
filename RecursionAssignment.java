/*
    Name: Donnie Ranjel
    File Name: RecursionAssignment.java
    Date: 10/11/2025
    Class: CSC 1060-503
*/

import java.util.Scanner;

/*
    The RecursionAssignment class demonstrates three fundamental recursion methods:
    factorial, greatest common divisor (GCD), and Fibonacci sequence generation.
*/
public class RecursionAssignment {

    /*
        Computes the factorial of a non-negative integer n recursively.
        Base Case: factorial(0) = 1
        Recursive Step: n * factorial(n - 1)
    
        @param n The non-negative integer.
        @return The factorial of n.
    */
    public static long factorial(int n) {
        if (n < 0) {
            // Factorial is not defined for negative numbers.
            throw new IllegalArgumentException("Factorial is only defined for non-negative numbers.");
        }
        if (n == 0) {
            // Base case
            return 1;
        } else {
            // Recursive step: n * factorial(n - 1)
            return n * factorial(n - 1);
        }
    }

    /*
        Finds the Greatest Common Divisor (GCD) of two non-negative integers a and b using the Euclidean algorithm.
        Base Case: gcd(a, 0) = a
        Recursive Step: gcd(b, a % b)
    
        @param a The first non-negative integer.
        @param b The second non-negative integer.
        @return The GCD of a and b.
    */
    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
             throw new IllegalArgumentException("GCD is only defined for non-negative numbers.");
        }
        if (b == 0) {
            // Base case
            return a;
        } else {
            // Recursive step: gcd(b, a % b)
            return gcd(b, a % b);
        }
    }

    /*
        Generates the nth term of a Fibonacci sequence recursively.
        Base Cases: fibonacci(0) = 0, fibonacci(1) = 1
        Recursive Step: fibonacci(n - 1) + fibonacci(n - 2)
    
        @param n The position in the sequence (n >= 0).
        @return The Fibonacci number at position n.
    */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci position must be non-negative.");
        }
        if (n == 0) {
            // Base case 1
            return 0;
        } else if (n == 1) {
            // Base case 2
            return 1;
        } else {
            // Recursive step: fibonacci(n - 1) + fibonacci(n - 2)
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Factorial Method Call
        System.out.print("Enter a number to calculate its factorial: ");
        int factorialNum = scnr.nextInt();
        long factorialResult = factorial(factorialNum);
        System.out.println("Factorial of " + factorialNum + " is: " + factorialResult);

        // GCD Method Call
        System.out.print("Enter two numbers to find their GCD: ");
        int gcdNum1 = scnr.nextInt();
        int gcdNum2 = scnr.nextInt();
        int gcdResult = gcd(gcdNum1, gcdNum2);
        System.out.println("GCD of " + gcdNum1 + " and " + gcdNum2 + " is: " + gcdResult);

        // Fibonacci Method Call
        System.out.print("Enter a number to get its Fibonacci value: ");
        int fibNum = scnr.nextInt();
        int fibResult = fibonacci(fibNum);
        System.out.println("Fibonacci number at position " + fibNum + " is: " + fibResult);

        scnr.close();
    }
}