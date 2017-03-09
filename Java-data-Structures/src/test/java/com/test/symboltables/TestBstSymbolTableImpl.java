package com.test.symboltables;

import java.util.Collections;

import com.symboltables.BstSymbolTableImpl;
import com.symboltables.SymbolTable;

import junit.framework.TestCase;

public class TestBstSymbolTableImpl extends TestCase{

    public void testEmpty() {
        // test empty SymbolTable
        SymbolTable<Integer, String> st = new BstSymbolTableImpl<Integer, String>();
        assertEquals(0, st.size());
        assertEquals(true, st.isEmpty());
        assertEquals(null, st.delete(3));
        assertEquals(null, st.get(3));
        assertEquals(false, st.containsKey(3));
        assertEquals(false, st.containsValue("3"));
    }
    
    public void testListOperations() {
        SymbolTable<Integer, String> st = new BstSymbolTableImpl<Integer, String>();
        try {
            st.put(2, null);
        } catch (NullPointerException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
        
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
        
        assertEquals(false, st.isEmpty());
        assertEquals(8, st.size());
        assertEquals("good", st.get(3));
        assertEquals(true, st.containsKey(3));
        assertEquals(true, st.containsValue("java"));
        assertEquals(false, st.containsKey(980));
        assertEquals(false, st.containsValue("computer"));
        assertEquals(null, st.delete(4536));
        assertEquals("javascript", st.delete(30));
        assertEquals(7, st.size());
        
        assertEquals("life", st.delete(20));
        assertEquals(6, st.size());
        assertEquals("good", st.delete(3));
        assertEquals(5, st.size());
        assertEquals(true, st.containsKey(4));
        assertEquals("best", st.delete(4));
        assertEquals("java", st.delete(6));
        assertEquals("c++", st.delete(7));
        assertEquals(true, st.containsValue("datastructure"));
        assertEquals("datastructure", st.delete(55));
        assertEquals(false, st.containsValue("datastructure"));
        assertEquals("bst", st.delete(18));
        assertEquals(true, st.isEmpty());
        
        st.put(3, "good");
        st.put(4, "best");
        assertEquals(2, st.size());
        st.clear();
        assertEquals(true, st.isEmpty());
        
        st = new BstSymbolTableImpl<Integer, String>();
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
        
        st = new BstSymbolTableImpl<Integer, String>(Collections.reverseOrder());
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
