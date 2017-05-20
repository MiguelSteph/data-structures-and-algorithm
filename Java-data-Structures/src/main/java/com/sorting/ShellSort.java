package com.sorting;

/**
 * Shellsort, also known as Shell sort or Shell's method, is an in-place
 * comparison sort. It can be seen as either a generalization of sorting by
 * exchange (bubble sort) or sorting by insertion (insertion sort).[3] The
 * method starts by sorting pairs of elements far apart from each other, then
 * progressively reducing the gap between elements to be compared. Starting with
 * far apart elements, it can move some out-of-place elements into position
 * faster than a simple nearest neighbor exchange. Donald Shell published the
 * first version of this sort in 1959.[4][5] The running time of Shellsort is
 * heavily dependent on the gap sequence it uses.
 * 
 * {@link https://en.wikipedia.org/wiki/Shellsort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ShellSort {

    /**
     * Use the shell sort method to sort the given array A
     * 
     * @param A
     *            Array of Comparable that contains the object to sort
     */
    public static void sort(Comparable[] A) {
        if (A == null)
            return;
        int h = computeMaxH(A.length);
        while (h >= 1) {
            hInsertionSort(A, h);
            h = h / 3;
        }
    }

    private static void hInsertionSort(Comparable[] A, int h) {
        Comparable curVal;
        int j;
        for (int i = h; i < A.length; i = i + h) {
            curVal = A[i];
            j = i - h;
            while ((j >= 0) && less(curVal, A[j])) {
                A[j + h] = A[j];
                j = j - h;
            }
            A[j + h] = curVal;
        }
    }

    private static int computeMaxH(int len) {
        int h = 1;
        while (h < len / 3)
            h = 3 * h + 1;
        return h;
    }

    private static boolean less(Comparable a, Comparable b) {
        int cmp = a.compareTo(b);
        if (cmp < 0)
            return true;
        return false;
    }

}
