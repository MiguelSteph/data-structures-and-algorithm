package com.test.stacks;


import com.stacks.LinkedListImplOfStack;
import com.stacks.Stack;

import junit.framework.TestCase;

public class TestLinkedListImplOfStack extends TestCase{

    public void testStack() {
        Stack<String> stack = new LinkedListImplOfStack<>();
        assertEquals(true, stack.isEmpty());
        
        // testing push
        stack.push("quick");
        stack.push("brown");
        stack.push("fox");
        assertEquals(false, stack.isEmpty());
        assertEquals(3, stack.size());
        
        stack.clear();
        assertEquals(true, stack.isEmpty());
        
        stack.push("quick");
        stack.push("brown");
        stack.push("fox");
        assertEquals(false, stack.isEmpty());
        assertEquals(3, stack.size());
        
        // testing pop
        assertEquals("fox", stack.pop());
        assertEquals("brown", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("quick", stack.pop());
        assertEquals(true, stack.isEmpty());
        assertEquals(0, stack.size());
        
        // testing peek
        stack.clear();
        stack.push("over");
        stack.push("little");
        stack.push("lazy");
        stack.push("dog");
        assertEquals("dog",stack.peek());       
        assertEquals(4,  stack.size());     
        stack.pop();
        assertEquals("lazy",stack.peek());
        
        //testing Iterable
        stack.clear();
        stack.push("quick");
        stack.push("brown");
        stack.push("fox");
        stack.push("over");
        stack.push("little");
        stack.push("lazy");
        stack.push("dog");
        
        String[] expected = {"dog", "lazy", "little", "over", "fox", "brown", "quick"};
        String[] result = new String[7];
        int i=0;
        for(String s : stack){
            result[i] = s;
            assertEquals(expected[i], result[i]);
            i++;
        }
        
        // testing underflow
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        } catch (Exception e) {
            assertNull(e);
        }
    }
}
