/*
    Name: Donnie Ranjel
    Date: 3/6/2026
    File Name: Chap9_PP_Ranjel.java
    Program Name: 2x2 Linear Equation Solver
    Program Description: This program uses a Linear_Equation class to represent and solve a 2x2 system of linear equations. After reading a, b, c, d, e, f from the user, the program computes the determinant and either diplays x and y or reports that the system has no solution.
    Inputs: Six coefficients given by th user for the system of linear equations.
    Outputs: The determinant, and either the values of x and y or a message indicating that no solution exists.
 */

import java.util.Scanner;

public class Chap9_PP_Ranjel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Enter: a, b, c, d, e, f: ");

        // Prompts the user to enter a, b, c, d, e, and f.
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        double e = scanner.nextDouble();
        double f = scanner.nextDouble();

        Linear_Equation equation = new Linear_Equation(a, b, c, d, e, f);

        // Displays the determinant
        System.out.println("Determinant: " + equation.GetDeterminant());

        if (equation.NotSolvable()) {

            // Displays if 𝑎𝑑 − 𝑏𝑐 is 0, report that “The equation has no solution.”
            System.out.println("The equation has no solution.");
        } else {

            // Displays the result.
            System.out.println("x is " + equation.getX() + " and y is " + equation.getY());
        }

        // Closing scanner.
        scanner.close();
    }
}

// class named Linear_Equation for a 2 X 2 system of linear equations.
class Linear_Equation { 

    // Private data fields a, b, c, d, e, and f for encapsulation.
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    // A constructor with the arguments for a, b, c, d, e, and f.
    public Linear_Equation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    // Six getter methods for a, b, c, d, e, and f to read the private fields.
    public double getA() {
        return a;
    }

    public double getB() { 
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }

    // A method named GetDeterminant() that returns 𝑎𝑑 − 𝑏𝑐.
    public double GetDeterminant() {
        return (this.a * this.d) - (this.b * this.c);
    }

    // A method named NotSolvable() that returns true if 𝑎𝑑 − 𝑏𝑐 is 0.
    public boolean NotSolvable() {
        return GetDeterminant() == 0;
    }

    // Methods getX() that return the solution for the equation.
    public double getX() {
        return ((this.e * this.d) - (this.b * this.f)) / GetDeterminant();
    }

    // Methods getY() that return the solution for the equation.
    public double getY() {
        return ((this.a * this.f) - (this.e * this.c)) / GetDeterminant();
    }
}