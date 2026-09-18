/*
    Name: Donnie Ranjel
    Date: 4/15/2026
    File Name: TwoWayLinkedList.java
    Assignment: Chapter 24 Programming Project
    Program Name: Custom List Data Structures
    File Description: This program implements a doubly linked list structure that allows for bidirectional traversal. It includes a robust set of list operations and a custom ListIterator to efficiently navigate, insert, modify, and remove elements from either direction.
    Inputs: Generic objects (E) to be stored in the list, and integer indices for specific positional operations.
    Outputs: Boolean confirmations of list modifications, requested generic objects (E), and integer index values representing element locations.
*/

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail; // Head and tail nodes of the linked list
    private int size = 0; // Tracks the total number of active elements

    // Internal node structure holding data and bidirectional pointers
    private static class Node<E> {
        E element; // The data stored in the node
        Node<E> next; // Reference to the subsequent node in the sequence
        Node<E> previous; // Reference to the preceding node in the sequence

        // Constructor to encapsulate the element inside a new node
        public Node(E element) {
            this.element = element;
        }
    }

    // Default constructor to initialize an empty doubly linked list
    public TwoWayLinkedList() {}

    // Navigates the list to retrieve the node object residing at a specific index
    private Node<E> getNode(int index) {

        // Ensure that the index is within bounds to prevent memory access errors
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // To optimize traversal, we start from the head or tail depending on which half the index falls in
        if (index < size / 2) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
                return current;

          // If index is in the second half of the list, start from the tail and move backwards
        } else {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
                return current;
          }
    }

    // Inserts a new node securely between two existing nodes by updating their bidirectional pointers
    private void insertBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<>(e); // Create a new node for the incoming element
        newNode.previous = predecessor; // Anchor the new node to the node before it
        newNode.next = successor; // Anchor the new node to the node after it
        
        // If predecessor is null, we are adding at the beginning of the list, so we update the head
        if (predecessor == null) {
            head = newNode;
        } else {
            predecessor.next = newNode;
        }

        // If successor is null, we are adding at the end of the list, so we update the tail
        if (successor == null) {
            tail = newNode;
        } else {
            successor.previous = newNode;
        }
        size++;
    }

    // Isolates a target node by bridging the gap between its immediate neighbors, safely removing it
    private E unlink(Node<E> target) {
        E element = target.element; // Cache the data to hand back after the node is destroyed
        Node<E> prevNode = target.previous; // Identify the neighbor to the left
        Node<E> nextNode = target.next; // Identify the neighbor to the right

        // If the removed node was the head, shift the head pointer to the right
        if (prevNode == null) {
            head = nextNode;

          // Otherwise, route the left neighbor's next pointer to skip over the target node
        } else {
            prevNode.next = nextNode;
        }

        // If the removed node was the tail, shift the tail pointer to the left
        if (nextNode == null) {
            tail = prevNode;
        
          // Otherwise, route the right neighbor's previous pointer to skip back over the target node
        } else {
            nextNode.previous = prevNode;
        }
        size--;
        return element;
    }

    // Appends a new element to the very end of the list
    @Override
    public boolean add(E e) {
        insertBetween(e, tail, null);
        return true;
    }

    // Prepends a new element to the beginning of the list
    public void addFirst(E e) {
        insertBetween(e, null, head);
    }

    // Appends a newelement to the end of the list
    public void addLast(E e) {
        insertBetween(e, tail, null);
    }

    // Injects an element into the list at a specific, user-defined index
    @Override
    public void add(int index, E e) {

        // Ensure the requested index is a valid insertion point
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // If index exactly matches size, we are appending to the end
        if (index == size) {
            addLast(e);

        // Otherwise, locate the current node at that index and squeeze the new element in just before it
        } else {
            Node<E> current = getNode(index);
            insertBetween(e, current.previous, current);
        }
    }

    // Wipe the list clean by dropping the head and tail references, resetting the state
    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    // Evaluates whether a specific element exists anywhere in the list
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    // Retrives the underlying data from the node located at the specified index
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    // Scans from head to tail to locate the first occurrence of a target element
    @Override
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if ((e == null && current.element == null) || (e != null && e.equals(current.element))) {
                return i;
            }
            current = current.next;
        }
        return -1; // Return -1 if the element was never found
    }

    // Scans backward from the tail to locate the most recent occurrence of a target element
    @Override
    public int lastIndexOf(E e) {
        Node<E> current = tail;
        for (int i = size - 1; i >= 0; i--) {

            // Check for equality, handling null values appropriately
            if ((e == null && current.element == null) || (e != null && e.equals(current.element))) {
                return i;
            }
            current = current.previous;
        }
        return -1; // Return -1 if the element was never found
    }

    // Locates the node at a specific index and removes it from the list
    @Override
    public E remove(int index) {

        // Ensure that the index is within bounds to prevent memory access errors
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return unlink(getNode(index)); // Unlink the node and hand back its data
    }

    // Scans the list to find a specific element and, if found, destroys its node
    @Override
    public boolean remove(Object e) {
        Node<E> current = head;

        // Traverse sequentially unitl we hit the end of the list
        while (current != null) {
            // If we find a match, unlink it and exit
            if ((e == null && current.element == null) || (e != null && e.equals(current.element))) {
                unlink(current);
                return true;
            }
            current = current.next; // Move to the next node in the list
        }
        return false;
    }

    // Method to pop the first element off the list
    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        return unlink(head);
    }

    // Method to pop the last element off the list
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        return unlink(tail);
    }

    // Overwrites the data inside a specific node, caching the original data to hand back
    @Override
    public E set(int index, E e) {
        Node<E> target = getNode(index); // Locate the target node
        E oldElement = target.element; // Cache the original data
        target.element = e; // Overwrite with new data
        return oldElement; // Return the cache data
    }

    // Reports the total number of active elements residing in the list
    @Override
    public int size() {
        return size;
    }

    // Generates a standard iterator starting from the beginning of the list
    @Override
    public java.util.Iterator<E> iterator() {
        return listIterator();
    }

    // Generates a two-way iterator starting from the beginning
    public java.util.ListIterator<E> listIterator() {
        return new TwoWayListIterator(0);
    }

    // Generates a two-way iterator starting from a specific index
    public java.util.ListIterator<E> listIterator(int index) {
        return new TwoWayListIterator(index);
    }

    // Inner class that acts as a cursor to walk back and forth through the doubly linked list
    private class TwoWayListIterator implements java.util.ListIterator<E> {
        private Node<E> current; // Node ahead of the cursor
        private Node<E> lastReturned; // The node most recently hopped over by next() or previous()
        private int index; // Index representing the cursor's current boundary

        // Constructor to spawn the cursor at a specific starting position
        public TwoWayListIterator(int index) {

            // Ensure the requested starting position is a valid boundary
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }

            // If index is equal to size, the cursor sits at the end (current is null)
            if (index == size) {
                current = null;

              // Otherwise, position the cursor just before the requested node
            } else {
                current = getNode(index);
            }
            this.index = index;
        }

        // Evaluates whether there are still unvisited nodes ahead of the cursor
        @Override
        public boolean hasNext() {
            return index < size;
        }

        // Retrieves the next element in the sequence and advances the cursor one step foward
        @Override
        public E next() {

            // Halt if the cursor has already reached the absolute end of the list
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
                lastReturned = current; // Remember this node so we can potentially remove/set it
                current = current.next; // Shift the cursor forward
                index++; // Increment the boundary index
                return lastReturned.element;
        }

        // Evaluates whether the cursor can move backward
        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        // Shifts the cursor one step backward and returns the element it just passed over
        @Override
        public E previous() {

            // Halt if the cursor is already at the beginning of the list
            if (!hasPrevious()) {
                throw new java.util.NoSuchElementException();
            }

            // If we are at the end (current is null), stepping back places us on the tail
            if (current == null) {
                current = tail;

              // Otherwise, step back one node
            } else {
                current = current.previous;
            }
            lastReturned = current; // Remember this node
            index--; // Decrement the boundary index
            return lastReturned.element;
        }

        // Calculates the index of the element ahead of the cursor
        @Override
        public int nextIndex() {
            return index;
        }

        // Calculates the index of the element behind the cursor
        @Override
        public int previousIndex() {
            return index - 1;
        }

        // Deletes the last item returned by the cursor from the list
        @Override
        public void remove() {

            // We can only remove an item if we just hopped over one using next() or previous()
            if (lastReturned == null) {
                throw new IllegalStateException("next() or previous() must be called before remove()");
            }

            // Identify what comes after the node we are about to destroy
            Node<E> nextNode = lastReturned.next;
            unlink(lastReturned); // Destroy the node

            // If we just called previous() and then remove(), the current pointer needs adjusting
            if (current == lastReturned) {
                current = nextNode;

              // If we called next() and then remove(), current is fine, but the index shrinks
            } else {
                index--;
            }

            lastReturned = null; // Reset state to prevent double-removals
        }

        // Injects a new element into the list before the cursor's current position
        @Override
        public void add(E e) {

            // If sitting at the end, inject right after the tail
            if (current == null) {
                insertBetween(e, tail,null);

              // Otherwise, inject it squarely between the current node and anything behind
                insertBetween(e, current.previous, current);
            }
            index++; // Expand the boundary index to account for the new item
            lastReturned = null; // Reset state, as add() invalidates recent cursor history
        }

        // Overwrites the data inside the node returned by the cursor
        @Override
        public void set(E e) {

            // Halt if we have not hopped over any node yet
            if (lastReturned == null) {
                throw new IllegalStateException("next() or previous() must be called before set()");
            }
            lastReturned.element = e; // Perform the overwrite
        }
    }
}