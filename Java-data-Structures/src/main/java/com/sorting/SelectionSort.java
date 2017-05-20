package com.sorting;

/**
 * In computer science, selection sort is a sorting algorithm, specifically an
 * in-place comparison sort. It has O(n2) time complexity, making it inefficient
 * on large lists, and generally performs worse than the similar insertion sort.
 * Selection sort is noted for its simplicity, and it has performance advantages
 * over more complicated algorithms in certain situations, particularly where
 * auxiliary memory is limited.
 * 
 * {@link https://en.wikipedia.org/wiki/Selection_sort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class SelectionSort {

    /**
     * Use the selection sort method to sort the given array A
     * 
     * @param A
     *            Array of Comparable that contains the object to sort
     */
    public static void sort(Comparable[] A) {
        if (A == null)
            return;
        int indice;
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            indice = minIndice(A, i, len);
            swap(A, indice, i);
        }
    }

    private static boolean less(Comparable[] A, int i, int j) {
        int cmp = A[i].compareTo(A[j]);
        if (cmp < 0)
            return true;
        return false;
    }

    private static void swap(Comparable[] A, int i, int j) {
        Comparable inter = A[i];
        A[i] = A[j];
        A[j] = inter;
    }

    private static int minIndice(Comparable[] A, int lo, int hi) {
        int indice = lo;
        for (int i = lo + 1; i < hi; i++) {
            if (less(A, i, indice))
                indice = i;
        }
        return indice;
    }

}
