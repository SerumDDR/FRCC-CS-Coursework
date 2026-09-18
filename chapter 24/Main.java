/*
    Name: Donnie Ranjel
    Date: 4/15/2026
    File Name: Main.java
    Assignment: Chapter 24 Programming Project
    Program Name: Custom List Data Structures
    Program Description: This program implements and tests advanced custom linked list data structures, including an extended singly linked list and a fully functional doubly linked list with bidirectional iteration.
    Inputs: Hardcoded string and integer test values.
    Outputs: Console print statements displaying the state of the lists and the return values of various operations to visually confirm correctness.

    <-- RUN THIS FILE -->
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("<-- Testing MyLinkedListExtra -->");

        // Create an instance of MyLinkedListExtra and add some elements to it
        MyList<String> list1 = new MyLinkedListExtra<>();
        list1.add("Colorado");
        list1.add("Texas");
        list1.add("Arizona");

        // Display the state of the list
        System.out.println("Initial List: " + Arrays.toString(list1.toArray()));

        // Verify that the list can successfully locate an existing element
        System.out.println("Contains 'Texas': " + list1.contains("Texas"));
        
        
        ArrayList<String> extraStates = new ArrayList<>(Arrays.asList("California", "Florida"));

        // Bulk inject a separate collection of states into our linked list
        list1.addAll(extraStates);
        System.out.println("After addAll: "+ Arrays.toString(list1.toArray()));

        // Purge the list of any states that exist within the extraStates collection
        list1.removeAll(extraStates);
        System.out.println("After removeAll: " + Arrays.toString(list1.toArray()));

        // Overwrite 'Texas' with 'New Mexico', then verify that 'Texas' is no longer found
        list1.set(1, "New Mexico");
        System.out.println("After set(1, 'New Mexico'): " + Arrays.toString(list1.toArray()));
        System.out.println("indexOf('Texas'): " + list1.indexOf("Texas"));

        // Package the linked list data into an array format
        String[] stateArray = new String[list1.size()];
        stateArray = list1.toArray(stateArray);
        System.out.println("toArray(T[]): " + Arrays.toString(stateArray));

        // Perform an intersection keep only the element that match the provided safe list
        ArrayList<String> keepState = new ArrayList<>(Arrays.asList("Colorado", "New Mexico"));
        list1.retainAll(keepState);
        System.out.println("After retainAll (keeping Colorado and New Mexico): " + Arrays.toString(list1.toArray()));

        // Wipe the list and verify that the size drops to zero
        list1.clear();
        System.out.println("After clear(), size is: " + list1.size());

        System.out.println("\n<-- Testing TwoWayLinkedList -->");

        // Initialize the doubly linked list and populate it with integer data
        TwoWayLinkedList<Integer> list2 = new TwoWayLinkedList<>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        list2.add(40);

        // Print the elements of the list
        System.out.println("Initial Doubly Linked List: " + Arrays.toString(list2.toArray()));

        // Inject an element into the middle of the list, then remove a specific element by its index
        list2.add(2, 25);
        System.out.println("After add(2, 25): " + Arrays.toString(list2.toArray()));
        list2.remove(1);
        System.out.println("After remove(1): " + Arrays.toString(list2.toArray()));

        // Summon a bidirectional cursor to traverse through the list starting from the beginning
        System.out.print("ListIterator Forward: ");
        ListIterator<Integer> iterator = list2.listIterator();

        // Traverse sequentially from head to tail
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // Traverse sequentially in reverse from tail to head
        System.out.print("ListIterator Backward: ");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
        System.out.println();

        // Spawn a new cursor starting at the middle of the list (index 2)
        System.out.println("listIterator starting at index 2: ");
        ListIterator<Integer> midIterator = list2.listIterator(2);

        // Traverse foward from the middle insertion point
        while (midIterator.hasNext()) {
            System.out.print(midIterator.next() + " ");
        }
        System.out.println();

        // Overwrite the element at index 1 and print the resulting list state
        list2.set(1, 99);
        System.out.println("After set(1, 99): " + Arrays.toString(list2.toArray()));

        // Retrieve and display the element currently residing at index 2
        System.out.println("get(2): " + list2.get(2));

        // Add a duplicate element to verify the list can distinguish between first and last occurences
        list2.add(99);
        System.out.println("Added another 99: " + Arrays.toString(list2.toArray()));
        System.out.println("indexOf(99): " + list2.indexOf(99));
        System.out.println("lastIndexOf(99): " + list2.lastIndexOf(99));

        // Wipe the doubly linked list and confirm destruction
        list2.clear();
        System.out.println("After clear(), size is: " + list2.size());
    }
}