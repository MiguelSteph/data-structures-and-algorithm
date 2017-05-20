package com.sorting;

/**
 * In computer science, merge sort (also commonly spelled mergesort) is an
 * efficient, general-purpose, comparison-based sorting algorithm. Most
 * implementations produce a stable sort, which means that the implementation
 * preserves the input order of equal elements in the sorted output. Mergesort
 * is a divide and conquer algorithm that was invented by John von Neumann in
 * 1945.[1] A detailed description and analysis of bottom-up mergesort appeared
 * in a report by Goldstine and Neumann as early as 1948.
 * 
 * {@link https://en.wikipedia.org/wiki/Merge_sort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class BottomUpMergeSort {

    /**
     * Use the merge sort method to sort the given array A
     * 
     * @param A
     *            Array of Comparable that contains the object to sort
     */
    public static void sort(Comparable[] A) {
        if (A == null)
            return;
        int len = A.length;
        int subSize = 1;
        int lo, mid, hi;
        int maxGap = largestPowerOf2(len) / 2;
        while (subSize <= maxGap) {
            lo = -2 * subSize;
            while (true) {
                lo = lo + 2 * subSize;
                mid = lo + subSize - 1;
                hi = mid + subSize;

                if (mid >= len)
                    break;
                else if (hi >= len)
                    hi = len - 1;
                merge(A, lo, mid, hi);
            }
            subSize *= 2;
        }
    }

    private static void merge(Comparable[] A, int lo, int mid, int hi) {
        int leftSize = mid - lo + 1;
        int rightSize = hi - mid;
        Comparable[] left = new Comparable[leftSize];
        Comparable[] right = new Comparable[rightSize];

        for (int i = 0; i < leftSize; i++)
            left[i] = A[lo + i];

        for (int i = 0; i < rightSize; i++)
            right[i] = A[mid + i + 1];

        int leftPt = 0;
        int rightPt = 0;
        for (int i = lo; i <= hi; i++) {
            if (leftPt >= leftSize) {
                A[i] = right[rightPt];
                rightPt++;
            } else if (rightPt >= rightSize) {
                A[i] = left[leftPt];
                leftPt++;
            } else {
                if (less(right[rightPt], left[leftPt])) {
                    A[i] = right[rightPt];
                    rightPt++;
                } else {
                    A[i] = left[leftPt];
                    leftPt++;
                }
            }
        }
    }

    private static int largestPowerOf2(int n) {
        int expo = (int) (Math.log(n) / Math.log(2));
        int floorCandidate = (int) (Math.pow(2, expo));
        if (n <= floorCandidate)
            return floorCandidate;
        return 2 * floorCandidate;
    }

    private static boolean less(Comparable a, Comparable b) {
        int cmp = a.compareTo(b);
        if (cmp < 0)
            return true;
        return false;
    }

}
