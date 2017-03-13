package com.test.symboltables;

import com.lists.ArrayImplOfList;
import com.lists.List;
import com.symboltables.LinearProbingSymbolTableImpl;
import com.symboltables.SymbolTable;

import junit.framework.TestCase;

public class TestLinearProbingSymbolTableImpl extends TestCase{

    public void testEmpty() {
        // test empty SymbolTable
        SymbolTable<Integer, String> st = new LinearProbingSymbolTableImpl<Integer, String>();
        assertEquals(0, st.size());
        assertEquals(true, st.isEmpty());
        assertEquals(null, st.delete(3));
        assertEquals(null, st.get(3));
        assertEquals(false, st.containsKey(3));
        assertEquals(false, st.containsValue("3"));
    }
    
    public void testListOperations() {
        SymbolTable<Integer, String> st = new LinearProbingSymbolTableImpl<Integer, String>();
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
        
        st = new LinearProbingSymbolTableImpl<Integer, String>();
        st.put(20, "life");
        st.put(3, "good");
        st.put(4, "best");
        st.put(6, "java");
        st.put(7, "c++");
        st.put(30, "javascript");
        st.put(55, "datastructure");
        st.put(18, "bst");
        
        List<Integer> contentKeyList = new ArrayImplOfList<>();
        contentKeyList.add(3);
        contentKeyList.add(4);
        contentKeyList.add(6);
        contentKeyList.add(7);
        contentKeyList.add(18);
        contentKeyList.add(20);
        contentKeyList.add(30);
        contentKeyList.add(55);
        
        for(Integer key : st){
            assertEquals(true, contentKeyList.contains(key));
            contentKeyList.remove(key);
        }
        
        st = new LinearProbingSymbolTableImpl<Integer, String>();
        for (int i=0; i<3000; i++) {
            st.put(201+i, "life");
        }
        assertEquals(3000, st.size()); 
        
    }
    
}
