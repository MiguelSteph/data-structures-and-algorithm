package com.stacks;

/**
 * In computer science, a stack is an abstract data type that serves as a
 * collection of elements, with two principal operations: push, which adds an
 * element to the collection, and pop, which removes the most recently added
 * element that was not yet removed. The order in which elements come off a
 * stack gives rise to its alternative name, LIFO (for last in, first out).
 * Additionally, a peek operation may give access to the top without modifying
 * the stack.
 *
 * {@link https://en.wikipedia.org/wiki/Stack_(abstract_data_type)}
 *
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public interface Stack<Item> extends Iterable<Item> {

    /**
     * Removes and return the object at the top of this stack.
     *
     * @return item the object at the top of this stack.
     *
     * @exception java.lang.IllegalStateException
     *                if the Stack is empty
     */
    Item pop();

    /**
     * Looks at the object at the top of this stack without removing it.
     *
     * @return item the object at the top of this stack.
     *
     * @exception java.lang.IllegalStateException
     *                if the Stack is empty
     */
    Item peek();

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item
     *            the item to push on the top of the stack
     *            
     * @exception java.lang.IllegalStateException
     *                if item is null
     */
    void push(Item item);

    /**
     * Tests if this stack is empty.
     *
     * @return true if the stack empty and false if not.
     */
    boolean isEmpty();

    /**
     * Removes all of the elements from this stack.
     */
    void clear();

    /**
     * Returns the number of elements in this stack.
     *
     * @return size the number of elements in this stack.
     */
    int size();

    /**
     * Returns true if this stack contains the specified element.
     *
     * @param item
     *            the item to look for.
     *
     * @return true if the stack contains the item and false if not.
     */
    boolean contains(Item item);
}
