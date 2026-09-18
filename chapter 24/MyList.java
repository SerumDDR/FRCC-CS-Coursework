/*
    Name: Donnie Ranjel
    Date: 4/15/2026
    File Name: MyList.java
    Assignment: Chapter 24 Programming Project
    Program Name: Custom List Data Structures
    File Description: This interface extends Collection to establish a blueprint for custom list data structures. It defines mandatory positional operationsand provides implementations for bulk operations to prevent redundant code in implementing subclasses.
    Inputs: Generic objects (E) for storage, generic collections for bulk operations, and integer indices for positioning.
    Outputs: Boolean success flags, integer index values, generic objects (E), and typed arrays.
*/

import java.util.Collection;

public interface MyList<E> extends Collection<E> {

    // Injects a new element into the list at a specified index
    void add (int index, E e);

    // Retrieves the data from the node located at the specified index
    E get(int index);

    // Scans from head to tail to locate the very first occurrence of a target element
    int indexOf(Object e);

    // Scans backward from the tail to locate the most recent occurence of  target element
    int lastIndexOf(E e);

    // Locates the node at a specific node, caching the original data to hand back
    E remove(int index);

    // Overwrites the data inside a specific node, caching the original data to hand back
    E set(int index, E e);

    // Evaluates whether the list currently holds zero active elements
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }


    // References every item in the incoming collection against our list. Fails when any single item is missing
    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object e : c) {

            // Halt and return false when discrepancy is found 
            if (!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    // Packages all elements sequentially into an Object array
    @Override
    default Object[] toArray() {
        Object[] array = new Object[size()]; // Create an array to hold the elements of the list
        int i = 0; // Index for the array

        // Copy elements from the list to the array
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    // Loops through a provided collection and appends each of its items to the end of our current list
    default boolean addAll(Collection<? extends E> c) {
        boolean changed = false; // Flag to track is the list grew

        // Sequentially inject element from the collection to the list
        for (E e : c) {
            this.add(e);
            changed = true;
        }
        return changed; // Return true if the list was modified
    }

    // Sweeps through the list, safely deleting any element that also exist in the provided backlist collection
    @Override
    default boolean removeAll(Collection<?> c) {
        boolean changed = false;
        java.util.Iterator<E> iterator = this.iterator(); // Summon a cursor to traverse the list

        // Iterate through the list
        while (iterator.hasNext()) {

            //If the current element appears in the target collection, destroy its node
            if (c.contains(iterator.next())) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    // Sweeps through the list, safely deleting any element that is not found in the provided keep list collection
    @Override
    default boolean retainAll(Collection<?> c) {
        boolean changed = false;
        java.util.Iterator<E> iterator = this.iterator();

        // Iterate through the list and remove elements that are not in the collection
        while (iterator.hasNext()) {
            E element = iterator.next();

            // If the current element is missing from the safe list, destroy its node 
            if (!c.contains(element)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    // Packages all active elements sequentially into an array of a specific runtime type
    @Override
    default <T> T[] toArray(T[] a) {

        // If the provided array container is too small, use arrays copyOf to create a new one of the exact same type but correct size
        if (a.length < size()) {
            a = java.util.Arrays.copyOf(a, size());
        }
        int i = 0;

        // Copy elements from the list to the array
        for (E e : this) {
            a[i++] = (T) e;
        }

        // If the array has more elements than the list, set the next element to null
        if (a.length > size()) {
            a[size()] = null;
        }
        return a; // Return the array containing the elements of the list
    }
}