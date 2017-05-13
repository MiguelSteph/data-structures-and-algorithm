package com.test.linkedlist;

import com.linkedlist.LinkedList;
import com.linkedlist.ShingleLinkedList;

import junit.framework.TestCase;

public class TestShingleLinkedList extends TestCase{

    public void testEmpty() {
        // test empty list
        LinkedList<String> list = new ShingleLinkedList<String>();
        assertEquals(0, list.size());
        assertEquals(true, list.empty());
    }
    
    public void testListOperations() {
        LinkedList<String> list = new ShingleLinkedList<String>();
        list.push_back("me");
        list.insert(0, "love");
        assertEquals("me", list.back());
        list.push_back("you");
        list.insert(list.size(), "and");
        assertEquals(4, list.size());
        assertEquals("you", list.value_at(2));
        assertEquals("and", list.value_at(3));
        list.insert(3, "yes");
        assertEquals("yes", list.value_at(3));
        list.push_front("java");
        assertEquals("java", list.front());
        assertEquals("and", list.back());
        
        list.pop_front();
        list.pop_back();
        assertEquals("love", list.front());
        assertEquals("yes", list.back());
        
        list.push_front("java");
        list.push_back("and");
        
        list.erase(0);
        list.erase(list.size()-1);
        assertEquals("love", list.front());
        assertEquals("yes", list.back());
        
        assertEquals("you", list.value_n_from_end(1));
        
        list.erase(1);
        assertEquals("you", list.value_at(1));
        
        list.push_front("java");
        list.push_back("and");
        for (String s : list) {
            System.out.print(s+" ");
        }
        
        list.reverse();
        assertEquals("yes", list.value_at(1));
        assertEquals("and", list.front());
        assertEquals("java", list.back());
        
        list.remove_value("yes");
        assertEquals("you", list.value_at(1));
        
    }
    
}
