package com.test.sorting;

import com.sorting.MergeSort;

import junit.framework.TestCase;

public class TestMergeSort extends TestCase {

    public void testMergeSort() {

        String[] A = { "zs", "qw", "qr", "miguel", "rtd", "fgs", "qq", "mk", "slao", "oiu", "vf", "xs", "er", "qw",
                "bv" };
        Integer[] B = { 2, 4, 7, 542, 0, 1, 3, 0, 6, 2, 7, 9, 10, 2, 4, 6, 0, 3, 2, 9, 2, 77, 33, 22, 5, 4, 2, 2, 2 };

        MergeSort.sort(A);
        MergeSort.sort(B);

        String[] sortedA = { "bv", "er", "fgs", "miguel", "mk", "oiu", "qq", "qr", "qw", "qw", "rtd", "slao", "vf",
                "xs", "zs" };
        Integer[] sortedB = { 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 6, 7, 7, 9, 9, 10, 22, 33, 77,
                542 };

        for (int i = 0; i < A.length; i++) {
            assertEquals(true, A[i].equals(sortedA[i]));
        }

        for (int i = 0; i < B.length; i++) {
            assertEquals(true, B[i].equals(sortedB[i]));
        }

    }
    
}
