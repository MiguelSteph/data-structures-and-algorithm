package com.test.queues;

import com.queues.ArrayImplOfQueue;
import com.queues.Queue;

import junit.framework.TestCase;

public class TestArrayImplOfQueue extends TestCase{

    public void testEmpty() {
        // test empty queue
        Queue<String> que = new ArrayImplOfQueue<String>();
        assertEquals(0, que.size());
        assertEquals(true, que.isEmpty());
    }
    
    public void testQueueOperations() {
        Queue<String> que = new ArrayImplOfQueue<String>(87);
        assertEquals(0, que.size());
        
        // test add front 
        que.add("qwerty");
        que.add("nice");
        que.add("lion");
        que.add("love java");
        assertEquals(4, que.size());
        assertEquals(false, que.isEmpty());
        
        //check null condition in addFront
        try {
            que.add(null);
        } catch (NullPointerException e) {
            assertNotNull(e);;
        } catch (Exception e) {
            assertNull(e);
        }
    
        // test remove front
        assertEquals("qwerty", que.poll());
        assertEquals("nice", que.poll());
        assertEquals("lion", que.poll());
        assertEquals(1, que.size());
        assertEquals("love java", que.poll());
        assertEquals(true, que.isEmpty());
        
        // check underflow condition
        try {
            que.poll();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        que.add("reach there");
        que.poll();
        assertEquals(true, que.isEmpty());
        assertEquals(0, que.size());
    
        //test clear method
        que.clear();
        assertEquals(true, que.isEmpty());
        
        // test add Last
        assertEquals(0, que.size());
        que.add("qwerty");
        que.add("nice");
        que.add("lion");
        que.add("love java");
        que.add("spring");
        que.add("more");
        que.add("work word");
        assertEquals(false, que.isEmpty());
        assertEquals(7, que.size());
        
        //Test iterable
        String[] expected = {"qwerty", "nice", "lion", "love java", "spring", "more", "work word"};
        String[] result = new String[7];
        int i=0;
        for(String s : que){
            result[i] = s;
            assertEquals(expected[i], result[i]);
            i++;
        }
    }
}
