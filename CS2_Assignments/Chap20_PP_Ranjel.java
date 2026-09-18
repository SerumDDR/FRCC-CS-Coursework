/*
    Name: Donnie Ranjel
    Date: 4/10/2026
    File Name: Chap20_PP_Ranjel.java
    Assignment: Chapter 20 Programming Project
    Program Name: Infix to Postfix Converter
    Program Description: This program accepts an infix arithmetic expression from the command line, converts the expression into postfix notation using a stack-based algorithm, and then evaluates the resulting postfix expression to compute its numercial value. It displays both the original infix expression and the resulting postfix expression, along with the computed results.
    Inputs: A single infix expression entered as a command-line argument. The expression may include integers, parentheses, the operators + - * /, whitespace, and unary minus.
    Outputs: The original infix expression with its computed result,and the corresponding postfix expressionwith the computed result.
*/

import java.util.ArrayDeque;

public class Chap20_PP_Ranjel {

    public static void main(String[] args) {

        // Check for command-line arguments, and if none are persent then display instructions and exit.
        if (args.length == 0) {
            System.out.println("Error: No expression provided.");
            System.out.println(" Usage: java Chap20_PP_Ranjel.java \"(1+2) * 4 - 3\"");
            return;
        }

        // Join the input arguments into one string
        String infixExpression = String.join(" ", args);

        System.out.println("<-- Infix to Postfix Converter -->");

        try {

            // Convert the infix expression to postfix
            String postfixExpression = infixToPostfix(infixExpression);

            // Evaluate the postfix expression
            int mathResult = evaluatePostfix(postfixExpression);

            // Display results of both infix and postfix expressions
            System.out.println("Original Infix Expression: " + infixExpression + " = " + mathResult);
            System.out.println("Converted result of postfix: " + postfixExpression + " = " + mathResult);

          // Catches custom exception for invalid input
        } catch (IllegalArgumentException e) { 
            System.out.println("\nError: " + e.getMessage());

          // Catches calculation errors
          } catch (ArithmeticException e) { 
            System.out.println("\nMath Error: " + e.getMessage());

          // Catches other errors
        } catch (Exception e) { 
            System.out.println("\nError: The expression is not formatted correctly or incomplete.");
        }
    }

    // Infix to postfix conversion method
    public static String infixToPostfix(String infixExpression) {

        // Reject empty expression
        if (infixExpression == null || infixExpression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty.");
        }

        // Stack for storing operators
        ArrayDeque<Character> operatorStack = new ArrayDeque<>();

        // Stores the postfix expression
        StringBuilder postfixExpression = new StringBuilder();

        // Stores multi-digit numbers
        StringBuilder numberBuffer = new StringBuilder();

        // Loop through each character in the infix expression to tokenize
        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);

            // Ignore whitespace
            if (Character.isWhitespace(c)) {
                continue; 
            }

            // Builds up multi-digit numbers
            if (Character.isDigit(c)) {
                numberBuffer.append(c);
                continue;
            }
              
            // Output the completed number before handling the next token
            if (numberBuffer.length() > 0) {
                postfixExpression.append(numberBuffer.toString()).append(" ");
                numberBuffer.setLength(0);
            }

            // Handle unary minus
            if (c == '-') {
                // Checks if it is a minus
                int prev = i - 1;

                // Start looking backward from previous character
                while (prev >= 0 && Character.isWhitespace(infixExpression.charAt(prev))) {
                    prev--;
                }

                // Determines if it is unary minus at the start of expression, it follows another operator or an opening parentheses
                if (prev < 0 || isOperator(infixExpression.charAt(prev)) || infixExpression.charAt(prev) == '(') {
                    numberBuffer.append('-'); // Builds negative number
                    continue;
                }
            }

            // Handle opening parentheses
            if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {

                // Pop all remaining operators before the opening parentheses
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    char op = operatorStack.pop();
                    postfixExpression.append(op).append(" ");
                }
                
                // Check for mismatched parentheses
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses: Missing a '('");
                }
                operatorStack.pop();
              // If character is an operator, then pop operator 
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && precedence(operatorStack.peek()) >= precedence(c)) {
                    char op = operatorStack.pop();
                    postfixExpression.append(op).append(" ");
                }
                operatorStack.push(c);
              // Rejects invalid characters
            } else {
                throw new IllegalArgumentException("Invalid character detected: '" + c + "'");
            }
        }

        // Append remaining number buffer
        if (numberBuffer.length() > 0) {
            postfixExpression.append(numberBuffer.toString()).append(" ");
        }

        // Pop remaining operators from the stack
        while (!operatorStack.isEmpty()) {
            char topOperator = operatorStack.pop();

            // Check for mismatched parentheses
            if (topOperator == '(' || topOperator == ')') {
                throw new IllegalArgumentException("Mismatched parentheses: Extra Parentheses detected.");
            }
            postfixExpression.append(topOperator).append(" "); // Append the remaining operators
        }

        // Cleaned trimmed postfix expression
        return postfixExpression.toString().trim();
    }

    // Helper method to check if a character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Helper method to determine operator precedence
    private static int precedence(char operator) {

        // For addition and subtraction lower precedence
        if (operator == '+' || operator == '-') {
            return 1;
          //  For multiplication and division higher precedence
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1; // Invalid operator
    }

    // Postfix evaluation method
    public static int evaluatePostfix(String postfix) {

        // Stack for storing operands
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // Split the postfix expression into tokens
        String[] tokens = postfix.split("\\s+");

        // Loop through each token
        for (String token : tokens) {
            if (token.isEmpty()) { // Ignore empty tokens
                continue;
            }

            // Check if token is a number
            if (isNumeric(token)) {
                stack.push(Integer.valueOf(token));
            }

            // Check if token is an operator
            else if (token.length() == 1 && isOperator(token.charAt(0))) {

                // Remove and return top element of stack
                int rightOperand = stack.pop();
                int leftOperand = stack.pop();

                // Switch statement to perform the operator's operation
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(leftOperand + rightOperand);
                        break;
                    case '-': 
                        stack.push(leftOperand - rightOperand);
                        break;
                    case '*':
                        stack.push(leftOperand * rightOperand);
                        break;
                    case '/':
                        stack.push(leftOperand / rightOperand);
                        break;
                }
            }
        }
        return stack.pop(); // Returns the final result
    }

    // Helper method to check if a string is a number
    private static boolean isNumeric(String str) {

        try {
            Integer.parseInt(str); // Converts a string to an integer
            return true;
          // Catches errors that are not numbers
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
