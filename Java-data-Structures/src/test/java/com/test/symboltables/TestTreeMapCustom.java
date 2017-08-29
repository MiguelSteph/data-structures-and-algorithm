package com.test.symboltables;


import java.util.Collections;

import com.symboltables.I_TreeMapCustom;
import com.symboltables.TreeMapCustom;

import junit.framework.TestCase;

public class TestTreeMapCustom extends TestCase {

    public void testEmpty() {
        I_TreeMapCustom<Integer, String> st = new TreeMapCustom<Integer, String>();
        assertEquals(0, st.size());
        assertEquals(true, st.isEmpty());
        assertEquals(null, st.remove(3));
        assertEquals(null, st.get(3));
        assertEquals(false, st.containsKey(3));
        assertEquals(false, st.containsValue("3"));
    }
    
    public void testListOperations() {
        I_TreeMapCustom<Integer, String> st = new TreeMapCustom<Integer, String>();
        
        try {
            st.put(null, "1");
        } catch (NullPointerException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
        st.put(20, "life");
        st.put(3, "good");
        st.put(4, "best");
        st.put(6, "java");
        st.put(7, "c++");
        st.put(30, "javascript");
        st.put(55, "datastructure");
        st.put(18, "bst");
        
        st.keySet();
        
        assertEquals(false, st.isEmpty());
        assertEquals(8, st.size());
        assertEquals("good", st.get(3));
        assertEquals(true, st.containsKey(3));
        assertEquals(true, st.containsValue("java"));
        assertEquals(false, st.containsKey(980));
        assertEquals(false, st.containsValue("computer"));
        assertEquals(null, st.remove(4536));
        assertEquals("javascript", st.remove(30));
        assertEquals(7, st.size());
        
        assertEquals(true, st.containsKey(3));
        assertEquals("life", st.remove(20));
        assertEquals(6, st.size());
        assertEquals("good", st.remove(3));
        assertEquals(5, st.size());
        assertEquals(true, st.containsKey(4));
        assertEquals("best", st.remove(4));
        assertEquals("java", st.remove(6));
        assertEquals("c++", st.remove(7));
        assertEquals(true, st.containsValue("datastructure"));
        assertEquals("datastructure", st.remove(55));
        assertEquals(false, st.containsValue("datastructure"));
        assertEquals("bst", st.remove(18));
        assertEquals(true, st.isEmpty());
        
        st.put(3, "good");
        st.put(4, "best");
        assertEquals(2, st.size());
        st.clear();
        assertEquals(true, st.isEmpty());
        
        st = new TreeMapCustom<Integer, String>();
        st.put(20, "life");
        st.put(3, "good");
        st.put(4, "best");
        st.put(6, "java");
        st.put(7, "c++");
        st.put(30, "javascript");
        st.put(55, "datastructure");
        st.put(18, "bst");
        
        Integer[] expected = {3, 4, 6, 7, 18, 20, 30, 55};
        Integer[] result = new Integer[8];
        int i=0;
        for(Integer key : st){
            result[i] = key;
            assertEquals(expected[i], result[i]);
            i++;
        }
        
        st = new TreeMapCustom<Integer, String>(Collections.reverseOrder());
        st.put(20, "life");
        st.put(3, "good");
        st.put(4, "best");
        st.put(6, "java");
        st.put(7, "c++");
        st.put(30, "javascript");
        st.put(55, "datastructure");
        st.put(18, "bst");
        
        Integer[] expected1 = {55, 30, 20, 18, 7, 6, 4, 3};
        result = new Integer[8];
        i=0;
        for(Integer key : st){
            result[i] = key;
            assertEquals(expected1[i], result[i]);
            i++;
        }
        
    }

}
