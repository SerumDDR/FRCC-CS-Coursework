/*
    Name: Donnie Ranjel
    Date: 3/17/2026
    File Name: MyString1.java
    Assignment: Chapter 10 Programming Project
    Program Name: Custom String Class
    Program Description: This program implements a custom immutable string class, MyString1, using an encapsulted character array. It provides string operations such as character access, length retrieval, substring creation, lowercase conversion, equlity coparison, and integer to string conversion.
    Inputs: A char[] used to create the string, and various integer parameters used by the class methods.
    Outputs: New MyString1 objects, individual characters, integer lengths, boolean equality results, and printed character output.
*/

public class MyString1 {
    // Encapsulation of a primitive char array to ensure immutability
    private char[] chars;

    // Constructor that takes a char array and initializes the MyString1 object
    public MyString1(char[] chars) {
        this.chars = new char [chars.length];   // Brand new array box

        // Deep copy and manually copy each character
        for (int i = 0; i < chars.length; i++) {
            this.chars[i] = chars[i];
        }
    }

    // Accessor method to get the character at a specific index
    public char charAt(int index) {
        return chars [index];
    }

    // Method to return the length of the string
    public int length() {
        return chars.length;
    }

    // Substring method
    public MyString1 substring(int begin, int end) {
        int newLength = end - begin;    // Determine the length of the substring
        char[] subChars = new char[newLength];  // New array for the substring

        // Logic to copy the relevant character from the original to the new array
        for (int i = 0; i < newLength; i++) {
            subChars[i] = this.chars[begin + i];
        }

        return new MyString1(subChars); // Returns substring as object
    }

    // Method to convert to lowercase
    public MyString1 toLowerCase() {
        char[] lowerChars = new char[chars.length]; // New array for lowercase
        for (int i = 0; i < chars.length; i++) {    // Iterate through each character
            if (chars[i] >= 'A' && chars[i] <= 'Z') {   // Is it uppercase?
                lowerChars[i] = (char)(chars[i] + 32);  // The conversion
            } else {
                lowerChars[i] = chars[i];   // Copies i already lowercase
            }
        }

        return new MyString1(lowerChars);
    }
    
    // Copares both strings for equality
    public boolean equals(MyString1 s) {
        if (this.length() != s.length()) {
            return false;
        }

        // Compares letter by letter
        for (int i = 0; i < chars.length; i++) {
            if (this.chars[i] != s.charAt(i)) { // Looks for a mismatch
                return false;
            }
        }

        return true;    // True if they are equal
    }

    // Static method to convert an integer to a MyString1 object
    public static MyString1 valueOf(int i) {
        if (i == 0) {
            return new MyString1(new char[] {'0'}); // Special case for 0
        }

        int temp = i;   // Temporary variable for the integer value
        int length = 0; // Length of string of the integer
        boolean isNegative = (i < 0);   // Is the integer negative?

        if (isNegative) {
            length++;   // addnspace for the negative sign
            temp = -temp;   // Make temp positive to correctly calculate length and characters
        }

        int countTemp = temp;   // Variable to count without modifying temp
        while (countTemp > 0) {
            length++;   // increment length for each digit
            countTemp /= 10;    // Remove the last digit
        }

        char[] result = new char[length];   // Array to hold characters o the integer
        int index = length - 1; // Fills from the end to the beginning

        while (temp > 0) {
            int lastDigit = temp % 10;  // Get the last digit
            result[index] = (char)(lastDigit + '0');    // Converts it to a character

            temp /= 10; // Removes the last digit
            index--;    // Moves pointer to the left
        }

        // Place the minus sign in front
        if (isNegative) {
            result[0] = '-';
        }

        return new MyString1(result);
    }

    // Method to display the characters of the MyString1 object
    public void display() {
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }

        System.out.println();
    }
}