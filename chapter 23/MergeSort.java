/*
    Name: Donnie Ranjel
    Date: 4/25/2026
    File Name: MergeSort.java
    Assignment: Chapter 23 Programming Project 
    Program Name: Enhanced Sorting Algorithms
    File Description: This file implements the merge sorting algorithm using an index-based recursive approach that avoids creating temporary arrays for the left and right halves. Instead, the algorithm recursively sorts the first half and second half of the array in place, then merges the two sorted halves into a single temporary array before copying the merged results back into the original array.
    Inputs: An integer array passed into the mergeSort(int[] list) method.
    Outputs: The same integer array, sorted in ascending order.
*/

public class MergeSort {

    // Start merge sort on the entire array
    public static void mergeSort(int[] list) {
        mergeSort(list, 0, list.length - 1);
    }

    // Recursively split the array and merge the sorted halves
    private static void mergeSort(int[] list, int first, int last) {
        if (first < last) {
            int mid = first + (last - first) / 2;

            // Sort the left half
            mergeSort(list, first, mid);

            // Sort the right half
            mergeSort(list, mid + 1, last);

            // Merge the two sorted halves
            merge(list, first, mid, last);
        }
    }

    // Merge the two sorted halves into one sorted segment
    private static void merge(int[] list, int first, int mid, int last) {

        // Temporary array to hold merged values
        int [] temp = new int[last - first + 1];

        int current1 = first; // Start of the left half
        int current2 = mid + 1; // Start of the right half
        int current3 = 0; // Position in the temporary array

        // Merge the smaller element from each half into temp
        while (current1 <= mid && current2 <= last) {
            if (list[current1] < list[current2]) {
                temp[current3++] = list[current1++];
            } else {
                temp[current3++] = list[current2++];
            }
        }

        // Copy any remaining elements from the left half
        while (current1 <= mid) {
            temp[current3++] = list[current1++];
        }

        // Copy any remaining elements from the right half
        while (current2 <= last) {
            temp[current3++] = list[current2++];
        }

        // Copy the merged elements back into the original array
        for (int i = 0; i < temp.length; i++) {
            list[first + i] = temp[i];
        }
    }
}