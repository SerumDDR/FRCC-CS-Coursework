/*
    Name: Donnie Ranjel
    Date: 3/29/2026
    File Name: Chap13PP_Ranjel.java
    Assignment: Chapter 13 Programming Project
    Program Name: Rational Calculator
    Program Description: This program tests and uses a custom Rational class to perform arithmetic, comparisons, and decimal conversions on fractions. it allows the user to enter two rational numbers nd displays their sum, difference, product, quotent, and decimal values.
    Inputs: Numertors and denominators for two fractions, nd a user-choice to continue.
    Outputs: Arithmetic results, decimal values, equality and comparison results, and printed test output for Rational class methods.
*/

import java.util.Scanner;

public class Chap13PP_Ranjel {
    public static void main(String[] args) {

        // Testing with hard coded values
        System.out.println("<-- Starting Tests -->");

        Rational r1 = new Rational(4, 2); // First fraction
        Rational r2 = new Rational(2, 3); // Second fraction

        // Display testing results
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " = " + r2.doubleValue() + "\n"); 

        Rational r3 = new Rational();
        System.out.println(r3.getNumerator() + "/" + r3.getDenominator());

        Rational r4 = new Rational(2 , 1);
        System.out.println("Is " + r1 + " equal to " + r4 + "? " + r1.equals(r4));

        System.out.println("Comparing " + r1 + " to " + r2 + " (>0 means greater): " + r1.compareTo(r2));

        // Testing Type Conversions
        Rational numTest = new Rational(5, 2); // Test fraction
        System.out.println("Testing Type Conversions for " + numTest + ":");
        System.out.println("  -> intValue(): " + numTest.intValue()); // Convert fraction to integer
        System.out.println("  -> longValue(): " + numTest.longValue()); // Convert fraction to long integer
        System.out.println("  -> floatValue(): " + numTest.floatValue() + "\n"); // Convert fraction to decimal number


        System.out.println("<-- End of Tests -->");

        // Application for user input
        Scanner scanner = new Scanner(System.in);

        // Pause and pressing enter when ready
        System.out.print("Press enter to launch Rational Calculator.");
        scanner.nextLine();

        System.out.println("<-- Rational Calculator -->");

        boolean isAppRunning = true; // Flag to keep application running

        // Main application loop
        while (isAppRunning) {
            // try catch for to catch error for user input
            try {
                // First fraction
                System.out.print("Enter numerator for first fraction: ");
                long n1 = scanner.nextLong();
                System.out.print("Enter denominator for the first fraction: ");
                long d1 = scanner.nextLong();
                Rational userFraction1 = new Rational(n1, d1);

                //Second fraction
                System.out.print("\nEnter numerator for the second fraction: ");
                long n2 = scanner.nextLong();
                System.out.print("Enter denominator for the second fraction: ");
                long d2 = scanner.nextLong();
                Rational userFraction2 = new Rational(n2, d2);

                // Display Results
                System.out.println("\n<-- Results -->");
                System.out.println(userFraction1 + " + " + userFraction2 + " = " + userFraction1.add(userFraction2));
                System.out.println(userFraction1 + " - " + userFraction2 + " = " + userFraction1.subtract(userFraction2));
                System.out.println(userFraction1 + " * " + userFraction2 + " = " + userFraction1.multiply(userFraction2));
                System.out.println(userFraction1 + " / " + userFraction2 + " = " + userFraction1.divide(userFraction2));

                // Convert to decimal and display
                System.out.println("\n<-- Decimal Values -->");
                System.out.println("Fraction 1 (" + userFraction1 + ") = " + userFraction1.doubleValue());
                System.out.println("Fraction 2 (" + userFraction2 + ") = " + userFraction2.doubleValue());

            } catch (ArithmeticException e) { // Catches division by zero error
                System.out.println("\nMath Error: " + e.getMessage());
            } catch (Exception e) { // Catches other errors
                System.out.println("\nInput Error: Please enter numbers only.");
            }

            // To use again or close application
            System.out.print("\nWould you like to calculate another fraction? (y/n): ");
            String userChoice = scanner.next();
            if (!userChoice.equalsIgnoreCase("y")) {
                isAppRunning = false;
            }

        }

        scanner.close();
        System.out.println("\n<-- Closing Program! -->");

    }
}

// 
class Rational extends Number implements Comparable<Rational> {

    private long[] r = new long[2]; // Array data fields for numerator = r[0] and denominator = [1]


    // Constructor with default properties
    public Rational() {
        this(0, 1);
    }

    // Constructs a Rational and automatically reduces the fracion to lowest terms
    public Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }

        // Calculate the greatest common divisor (GCD) and reduce the fraction
        long gcd = gcd(numerator, denominator);
        this.r[0] = ((denominator > 0 ? 1 : -1 ) * numerator /gcd);
        this.r[1] = (Math.abs(denominator) / gcd);
    }

    // Helper method to calculate the greatest common divisor
    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;
    
        // Computes the GCD by checking all commen divisors
        for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k;
            }
        }

        return gcd;

    }

    // Getter methods for numerator
    public long getNumerator() {
        return r[0];
    }

    // Getter methods for denominator
    public long getDenominator() {
        return r[1];
    }

    public Rational add(Rational secondRational) {
        long n = r[0] * secondRational.getDenominator() + r[1] * secondRational.getNumerator(); // Calculate the numerator
        long d = r[1] * secondRational.getDenominator(); // Calculate the denominator
        return new Rational(n, d); // Create and return a new Rational object
    }

    public Rational subtract(Rational secondRational) {
        long n = r[0] * secondRational.getDenominator() - r[1] * secondRational.getNumerator();
        long d = r[1] * secondRational.getDenominator();
        return new Rational(n, d);
    }

    public Rational multiply(Rational secondRational) {
        long n = r[0] * secondRational.getNumerator();
        long d = r[1] * secondRational.getDenominator();
        return new Rational(n, d);
    }

    public Rational divide(Rational secondRational) {
        long n = r[0] * secondRational.getDenominator();
        long d = r[1] * secondRational.getNumerator();
        return new Rational(n, d);
    }

    // Override toString method to display the fraction
    @Override
    public String toString() {
        if (r[1] == 1) {
            return r[0] + "";
        } else {
            return r[0] + "/" + r[1];
        }
    }

    // Override equals method to compare fractions
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Rational)) {
            return false;
        } 

        return (this.subtract((Rational)(other))).getNumerator() == 0; // Check if the fractions are equal

    }

    // Override intValue, longValue, floatValue, and doubleValue methods
    @Override
    public int intValue() {
        return (int)doubleValue();
    }

    @Override
    public long longValue() {
        return (long)doubleValue();
    }

    @Override
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override
    public double doubleValue() {
        return r[0] * 1.0 / r[1];
    }

    // Override compareTo method to compare two rationals by subtracting them and comparing the numerator
    @Override
    public int compareTo(Rational o) {
        if (this.subtract(o).getNumerator() > 0) {
            return 1;
        } else if (this.subtract(o).getNumerator() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}