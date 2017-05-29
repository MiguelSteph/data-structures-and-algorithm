package com.test.sorting;

import com.sorting.CountingSort;

import junit.framework.TestCase;

public class TestCountingSort extends TestCase{

    public void testCountingSort() {
        
        int[] A = { 2, 4, 7, 542, 0, 1, 3, 0, 6, 2, 7, 9, 10, 2, 4, -29, 6, 0, 3, 2, 9, 2, 77, 33, 22, 5, 4, 2, 2, 2 };
        
        A = CountingSort.sort(A);
        
        int[] sortedA = { -29, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 6, 7, 7, 9, 9, 10, 22, 33, 77,
                542 };
        
        for (int i = 0; i < A.length; i++) {
            assertEquals(A[i], sortedA[i]);
        }
        
    }
    
}
