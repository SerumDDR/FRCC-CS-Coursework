/*
    Name: Donnie Ranjel
    Date: 3/17/2026
    File Name: MyString2.java
    Assignment: Chapter 10 Programming Project
    Program Name: String Wrapper Class
    Program Description: this program implements MyString2, a custom string wrapper class that stores a Java String and provides methods for comparison, substring creation, uppercase conversion, character array extraction, boolean to string conversion.
    Inputs: A String for constructing MyString2 objects, plus String, integer, and boolean values used by its methods.
    Outputs: Integer comparison results, new MyString2 objects, character arrays, and string representations.
 */

public class MyString2 {

    // Encapsulated string wrapper class
    private String s;

    // Constructor
    public MyString2(String s) {
        this.s = s;
    }

    // Compare method
    public int compare(String s) {
        int minLength = Math.min(this.s.length(), s.length());  // Finds the shorter length for comparison

        // compares character by character
        for (int i = 0; i < minLength; i++) {
            char c1 = this.s.charAt(i); // grabs character from original string
            char c2 = s.charAt(i);  // grabs the other string's character

            // If different return ASCII difference to determine order
            if (c1 != c2) {
                return c1 - c2;
            }
        }

        // If characters matched, then the shorter string comes first
        return this.s.length() - s.length();
    }

    // Substring method
    public MyString2 substring (int begin) {

        String newStr = ""; // String to build substring
        for (int i = begin; i < s.length(); i++) {
            newStr += s.charAt(i);  // Accumulate original string characters
        }

        return new MyString2(newStr);
    }

    // Uppercase conversion method
    public MyString2 toUpperCase() {
        String upperStr = "";   // String to build converted uppercase string

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);   // Grabs character at index i

            // Is it lowercase?
            if (c >= 'a' && c <= 'z') {
                upperStr += String.valueOf((char)(c - 32)); // subtracts to convert to uppercase
            } else {
                upperStr += c;  // Keeps the same if not lowercase
            }
        }

        return new MyString2(upperStr);
    }

    // Method to convert the string to a char array
    public char[] toChars() {
        char[] chars = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i); // Pulls each character out of the string wrapper
        }

        return chars;
    }

    // Method to convert a boolean to a MyString2 object
    public static MyString2 valueOf(boolean b) {
        if (b == true) {
            return new MyString2("true");
        } else {
            return new MyString2("false");
        }
    }

    // Method to return the string representation of the MyString2nobject
    public String toString() {
        return this.s;
    }

}