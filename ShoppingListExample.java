/*
    Name: Donnie Ranjel
    File Name: ShoppingListExample.java
    Date: 09/26/2025
    Class: CSC 1060-503
 */

import java.util.LinkedList;

public class ShoppingListExample {
    public static void main(String[] args) {

        // Step 1: Create a LinkedList of Strings
        LinkedList<String> shoppingList = new LinkedList<>();

        // Step 2: Add 5 items to the list
        shoppingList.add("Jelly");
        shoppingList.add("Bread");
        shoppingList.add("Sausage");
        shoppingList.add("Bacon");
        shoppingList.add("Eggs");

        // Output the list
        System.out.println(shoppingList);

        // Step 3: Remove 3 items from the list
        shoppingList.remove("Jelly");
        shoppingList.remove("Bread");
        shoppingList.remove("Sausage");

        // Output the list
        System.out.println(shoppingList);

        // Step 4: Add 4 items to the list
        shoppingList.addFirst("Chicken");       // Adds to the start
        shoppingList.add(1, "Potato Chips");    // Adds at index 1
        shoppingList.addFirst("Butter");        // Adds to the start
        shoppingList.add(2, "Cheese");          // Insert at index 2

        // Output the list
        System.out.println(shoppingList);

        // Step 5: Remove 2 items from the list
        shoppingList.remove("Chicken");
        shoppingList.remove("Potato Chips");

        // Output the list
        System.out.println(shoppingList);

        // Step 6: Add an item to the second position in the list
        shoppingList.add(1, "Spaghetti");   // Insert at index 1

        // Output the list
        System.out.println(shoppingList);
    }
}
