package com.test.lists;

import com.lists.ArrayImplOfList;
import com.lists.List;

import junit.framework.TestCase;

public class TestArrayImplOfList extends TestCase{

    public void testEmpty() {
        // test empty queue
        List<String> list = new ArrayImplOfList<String>();
        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
    }
    
    public void testListOperations() {
        List<String> list = new ArrayImplOfList<String>(87);
        try {
            list.add(0, "Love");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        list.clear();
        assertEquals(true, list.isEmpty());
        list.add("Dragon");
        for (int i=0; i<300; i++) {
            list.add("Code and Code again" + i);
        }
        assertEquals(301, list.size());
        list.add("Dragon");
        list.add("Dragon");
        assertEquals(0, list.remove("Dragon"));
        assertEquals(-1, list.remove("Computer"));
        
        list.add(200, "Run");
        assertEquals("Run", list.get(200));
        list.add(200, "Running");
        list.add(199, "Google");
        assertEquals("Running", list.get(201));
        
        assertEquals(false, list.contains("Love"));
        list.add(14, "Love");
        assertEquals(true, list.contains("Love"));
        
        list.clear();
        list.add("Dragon");
        for (int i=0; i<300; i++) {
            list.add(0 , "Code and Code again" + i);
        }
        assertEquals(301, list.size());
        
        list.set(199, "Google");
        assertEquals("Google", list.get(199));
        
        list.clear();
        list.add("qwerty");
        list.add("work");
        list.add("live");
        list.add("concentred");
        list.add("passion");
        list.add("source");
        list.add(2, "algorithm");
        
        String[] expected = {"qwerty", "work", "algorithm", "live", "concentred", "passion", "source"};
        String[] result = new String[7];
        int i=0;
        for(String s : list){
            result[i] = s;
            assertEquals(expected[i], result[i]);
            i++;
        }
        
    }
    
}
