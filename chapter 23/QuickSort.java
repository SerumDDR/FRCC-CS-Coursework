/*
    Name: Donnie Ranjel
    Date: 4/25/2026
    File Name: QuickSort.java
    Assignment: Chapter 23 Programming Project 
    Program Name: Enhanced Sorting Algorithms
    File Description: This file implements the quick sort algorithm using the median-of-three pivot selection technique. The algorithm selects the median of the first, middle, and last elements as the pivot, partitions the array around that pivot, and recursively sorts the left and right sublists.
    Inputs: An integer array passed into the quickSort(int[] list) method.
    Outputs: The same integer array, sorted in ascending order.
*/

public class QuickSort {

    // Method to start the quick sort algorithm
    public static void quickSort(int[] list) {

        // Call the recursive method to sort the entire array
        quickSort(list, 0, list.length - 1);
    }

    // Recursively sorts the portion of the array between first and last
    private static void quickSort(int[] list, int first, int last) {

        // Only sort if this section has more than one element
        if (last > first) {

            // Partition the array and get the pivot's final position
            int pivotIndex = partition(list, first, last);

            // Recursively sort the left side of the pivot
            quickSort(list, first, pivotIndex - 1);

            // Recursively sort the right side of the pivot
            quickSort(list, pivotIndex + 1, last);
        }
    }

    // Partitions the array around the pivot
    private static int partition(int[] list, int first, int last) {

        // Find the pivot index
        int mid = first + (last - first) / 2;

        // If the first element is larger than the middle element, swap them so the smaller value moves toward the front
        if (list[first] > list[mid]) {
            swap(list, first, mid);
        }

        // If the middle element is larger than the last element, swap them so the larger value moves to the end
        if (list[mid] > list[last]) {
            swap(list, mid, last);
        }

        // Rechecking and swap if the first value is larger than the middle value
        if (list[first] > list[mid]) {
            swap(list, first, mid);
        }

        // Move the smaller value to the front
        swap(list, first, mid);

        int pivot = list[first]; // The pivot value
        int low = first + 1; // Start scanning from the element right after the pivot
        int high = last;// Start scanning from the end of the current section

        // Continue until the two pointers cross
        while (high > low) {

            // Move low right while elements are smaller or equal to the the pivot
            while (low <= high && list[low] <= pivot) {
                low++;
            }

            // Move high left while elements are larger than the pivot
            while (low <= high && list[high] > pivot) {
                high--;
            }

            // If the two pointers have not crossed, swap the out-of-place values
            if (high > low) {
                swap(list, low, high);
            }
        }

        // Move high left while elements are larger or equal to the pivot
        while (high > first && list[high] >= pivot)  {
            high--;
        }

        // Place the pivot in its correct position and return its index
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    // Swap the two elements at positions i and j
    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}