package com.sorting;

/**
 * In computer science, counting sort is an algorithm for sorting a collection
 * of objects according to keys that are small integers; that is, it is an
 * integer sorting algorithm. It operates by counting the number of objects that
 * have each distinct key value, and using arithmetic on those counts to
 * determine the positions of each key value in the output sequence. Its running
 * time is linear in the number of items and the difference between the maximum
 * and minimum key values, so it is only suitable for direct use in situations
 * where the variation in keys is not significantly greater than the number of
 * items. However, it is often used as a subroutine in another sorting
 * algorithm, radix sort, that can handle larger keys more efficiently.
 * 
 * {@link https://en.wikipedia.org/wiki/Counting_sort}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class CountingSort {

    public static int[] sort(int[] A) {
        if (A == null)
            return A;

        // find min and max
        int min = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }

        // fill the counting array
        int countArraySize = max - min + 1;
        int[] count = new int[countArraySize];
        for (int i = 0; i < A.length; i++) {
            count[A[i] - min] = count[A[i] - min] + 1;
        }

        // compute the partial sum of the counting array
        for (int i = 1; i < countArraySize; i++) {
            count[i] = count[i] + count[i - 1];
        }

        int[] B = new int[A.length];
        for (int i= A.length-1; i>=0; i--) {
            count[A[i] - min] = count[A[i] - min] - 1;
            B[count[A[i] - min]] = A[i];
        }
        
        return B;
    }

}
