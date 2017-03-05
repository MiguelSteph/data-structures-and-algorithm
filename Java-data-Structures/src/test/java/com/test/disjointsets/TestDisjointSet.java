package com.test.disjointsets;

import com.disjointsets.DisjointSet;

import junit.framework.TestCase;

public class TestDisjointSet extends TestCase{

    public void testEmpty() {
        // test empty queue
        DisjointSet<Integer> ds = new DisjointSet<Integer>();
        assertEquals(0, ds.itemsSetSize());
        assertEquals(0, ds.itemsSize());
        assertEquals(true, ds.isEmpty());
    }
    
    public void testListOperations() {
        try {
            new DisjointSet<Integer>(new Integer[] {1,2,3,3});
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        DisjointSet<Integer> ds = new DisjointSet<Integer>(new Integer[] {1, 2, 3, 4, 5});
        assertEquals(5, ds.itemsSetSize());
        assertEquals(5, ds.itemsSize());
        assertEquals(true, ds.contains(3));
        assertEquals(new Integer(4), ds.find(4));
        
        try {
            ds.union(5, 9);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        ds.union(5, 3);
        ds.union(2, 4);
        assertEquals(3, ds.itemsSetSize());
        assertEquals(5, ds.itemsSize());
        
        ds.union(3, 2);
        assertEquals(2, ds.itemsSetSize());
        
        assertEquals(false, ds.makeSet(4));
        
        assertEquals(true, ds.makeSet(6));
        assertEquals(3, ds.itemsSetSize());
        assertEquals(6, ds.itemsSize());
        assertEquals(false, ds.isEmpty());
        
        try {
            ds.makeSet(null);
        } catch (NullPointerException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        try {
            ds.find(null);
        } catch (NullPointerException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        try {
            ds.find(8);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        
    }
    
}
