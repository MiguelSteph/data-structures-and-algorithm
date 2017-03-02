package com.test.queues;

import java.util.Collections;

import com.queues.PriorityQueue;

import junit.framework.TestCase;

public class TestPriorityQueue extends TestCase{

    public void testEmpty() {
        // test empty PriorityQueue
        PriorityQueue<String> pq = new PriorityQueue<String>();
        assertEquals(0, pq.size());
        assertEquals(true, pq.isEmpty());
    }
    
    public void testPQOperation() {
        Integer[] data = {12, 5, 3, 6, 4, 2, 0, 23, -23, 982, 6028, 4039, 123, 9, 89, 2, 4, 9, 8, 54, 17, 90};
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(data);
        assertEquals(false, pq.isEmpty());
        assertEquals(data.length, pq.size());
        assertEquals(new Integer(-23), pq.peek());
        pq.add(-300);
        assertEquals(new Integer(-300), pq.poll());
        assertEquals(new Integer(-23), pq.poll());
        assertEquals(data.length-1, pq.size());
        
        pq.clear();
        assertEquals(true, pq.isEmpty());
        
        pq = new PriorityQueue<Integer>(data, Collections.reverseOrder());
        assertEquals(new Integer(6028), pq.peek());
        pq.add(30000);
        assertEquals(new Integer(30000), pq.poll());
        assertEquals(new Integer(6028), pq.poll());
        assertEquals(data.length-1, pq.size());
        
        pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for (int i=0; i<300; i++) {
            pq.add(234 + i);
        }
        assertEquals(300, pq.size());
        assertEquals(new Integer(234+299), pq.poll());
        
        for (int i=0; i<250; i++) {
            pq.poll();
        }
        assertEquals(49, pq.size());
        
    }
    
}
