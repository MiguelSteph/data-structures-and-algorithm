package com.sorting;

/**
 * In computer science, heapsort is a comparison-based sorting algorithm.
 * Heapsort can be thought of as an improved selection sort: like that
 * algorithm, it divides its input into a sorted and an unsorted region, and it
 * iteratively shrinks the unsorted region by extracting the largest element and
 * moving that to the sorted region. The improvement consists of the use of a
 * heap data structure rather than a linear-time search to find the maximum.
 * 
 * {@link https://en.wikipedia.org/wiki/Heapsort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class HeapSort {

    /**
     * Use the Heap sort method to sort the given array A
     * 
     * @param A
     *            Array of Comparable that contains the object to sort
     */
    public static void sort(Comparable[] A) {
        if (A == null)
            return;
        heapify(A);
        for (int k = A.length; k >= 1; k--) {
            exch(A, 1, k);
            sink(A, 1, k - 1);
        }
    }

    private static void heapify(Comparable[] A) {
        for (int k = A.length / 2; k >= 1; k--)
            sink(A, k, A.length);
    }

    private static void sink(Comparable[] A, int indice, int len) {
        int j;
        while (indice <= len / 2) {
            j = 2 * indice;
            if ((j < len) && (less(A, j, j + 1)))
                j++;
            if (!less(A, indice, j))
                break;
            exch(A, indice, j);
            indice = j;
        }
    }

    private static void exch(Comparable[] A, int i, int j) {
        i = getArrayIndex(i);
        j = getArrayIndex(j);
        Comparable k = A[i];
        A[i] = A[j];
        A[j] = k;
    }

    private static boolean less(Comparable[] A, int i, int j) {
        i = getArrayIndex(i);
        j = getArrayIndex(j);
        if (A[i].compareTo(A[j]) < 0)
            return true;
        return false;
    }

    private static int getArrayIndex(int indice) {
        return --indice;
    }

}
