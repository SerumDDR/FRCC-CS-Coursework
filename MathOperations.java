/*
    Name: Donnie Ranjel
    Assignment Title: Chapter 2: Variables / Assignments
    Course & Section: CSC (FRCC) 1060 503
 */

public class MathOperations {
    public static void main(String[] args) {
        
        // Declare three integer variables
        int num1;   // first number
        int num2;   // second number
        int answer; // holds results of calculations

        // Assign values to num1 and num2
        num1 = 10;  // value given for num1
        num2 = 20;  // value given for num2

        // Addition
        answer = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + answer);

        // Subtraction
        answer = num1 - num2;
        System.out.println(num1 + " - " + num2 + " = " + answer);

        answer = num2 - num1;
        System.out.println(num2 + " - " + num1 + " = " + answer);

        // Multiplication
        answer = num1 * num2;
        System.out.println(num1 + " * " + num2 + " = " + answer);

        // Division
        answer = num1 / num2;
        System.out.println(num1 + " / " + num2 + " = " + answer);

        answer = num2 / num1;
        System.out.println(num2 + " / " + num1 + " = " + answer);

        // Modulus
        answer = num1 % num2;
        System.out.println(num1 + " % " + num2 + " = " + answer);

        answer = num2 % num1;
        System.out.println(num2 + " % " + num1 + " = " + answer);
    }
}
