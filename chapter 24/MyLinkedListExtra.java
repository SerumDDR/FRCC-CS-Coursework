/*
    Name: Donnie Ranjel
    Date: 4/15/2026
    File Name: MyLinkedListExtra.java
    Assignment: Chapter 24 Programming Project
    Program Name: Custom List Data Structures
    File Description: This class extends the base MyLinkedList class to provide implementations for previously omitted methods. It adds functionality to check for specified elements, retrieve elements by index, find the first and last occurrences of an element, update existing elements, and provides a robust iterator capable of removing elements during traversal.
    Inputs: Generic objects (E) to be stored, retrieved, or evaluated within the linked list structure, along with integer indices for positioning.
    Outputs: Boolean values for existence checks, generic objects (E) when retrieving or replacing data, and integer index positions.
*/

public class MyLinkedListExtra<E> extends MyLinkedListTest<E> {

    // Constructor to initialize the linked list
    public MyLinkedListExtra() {
        super();
    }

    // Checks if the list contains the specified element
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    // Retrieves the element at the specified index
    @Override
    public E get(int index) {

        // Ensure that the index is within bounds to prevent memory access errors
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Traverse to the node at the specified index
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    // Scans the list to find the index of the first occurrence of the specified element
    @Override
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {

            // Check for equality, handling null values appropriately
            if ((e == null && current.element == null) || (e != null && e.equals(current.element))) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    // Scans the list from start to finish and remembers the position of the most recently found match
    @Override
    public int lastIndexOf(E e) {
        int lastIndex = -1;
        Node<E> current = head;

        // Traverse the list to find the last occurrence of the element
        for (int i = 0; i < size; i++) {
           if ((e == null && current.element == null) || (e != null && e.equals(current.element))) {
            lastIndex = i;
           }
           current = current.next;
        }
        return lastIndex;
    }

    // Overwrites the data inside the target node with a new value, caching the original data to hand back
    @Override
    public E set(int index, E e) {

        // Ensure that the index is within bounds to prevent memory access errors
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Traverse to the node at the specified index
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // Store the old element before replacing it with the new element
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }

    // Returns an iterator that allows for iterating over the elements of the linked list
    @Override
    public java.util.Iterator<E> iterator() {
        
        // Return an anonymous inner class that implements the Iterator interface
        return new java.util.Iterator<E>() {
            private Node<E> current = head; // Node that is currently being iterated over
            private Node<E> previous = null; // Node that was last returned by next()
            private Node<E> previousPrevious = null; // Node before the previous node
            private boolean canRemove = false; // Flag to track if remove() can be called

            // Returns true if there are more elements to iterate over
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // Returns the next element in the iteration and advances the iterator
            @Override
            public E next() {

                // Ensure that there is a next element to return
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }

                previousPrevious = previous; // Store the node before the previous node
                previous = current; // Store the current node before moving to the next
                current = current.next; // Move to the next node
                canRemove = true; // Allow remove() to be called after next()
                return previous.element; // Return the element of the node that was just returned by next()
            }

            // Removes the last element returned by next()
            @Override
            public void remove() {

                // Ensure that next() has been called before allowing remove()
                if (!canRemove) {
                    throw new IllegalStateException("next() must be called before remove()");
                }
                
                // Bridge the gap: link the node BEFORE the removed item directly to the node AFTER it, leaving the targeted node behind for garbage collection
                if (previousPrevious == null) {
                    head = current;
                } else {
                    previousPrevious.next = current;
                }

                // If the removed node is the tail, update the tail
                if (current == null) {
                    tail = previousPrevious;
                }

                // If the list becomes empty after removal, reset head and tail
                if (size == 1) {
                    head = tail = null;
                }

                // Clear the removed node's references
                size--;
                previous = previousPrevious;
                canRemove = false;
            }
        };
    }
}