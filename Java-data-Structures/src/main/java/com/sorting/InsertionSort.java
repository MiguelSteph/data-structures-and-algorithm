package com.sorting;

/**
 * Insertion sort is a simple sorting algorithm that builds the final sorted
 * array (or list) one item at a time. It is much less efficient on large lists
 * than more advanced algorithms such as quicksort, heapsort, or merge sort.
 * 
 * {@link https://en.wikipedia.org/wiki/Insertion_sort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class InsertionSort {

    /**
     * Use the insertion sort method to sort the given array A
     * 
     * @param A Array of Comparable that contains the object to sort
     */
    public static void sort(Comparable[] A) {
        if (A == null)
            return;
        Comparable curVal;
        int j;
        for (int i = 1; i < A.length; i++) {
            curVal = A[i];
            j = i - 1;
            while ((j >= 0) && less(curVal, A[j])) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = curVal;
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        int cmp = a.compareTo(b);
        if (cmp < 0)
            return true;
        return false;
    }
    
}
