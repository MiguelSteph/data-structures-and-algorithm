package com.stacks;

import java.util.Iterator;

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
public class ArrayImplOfStack<Item> implements Stack<Item>, Iterable<Item> {

    /*** Contains the size of the stack. */
    private int arraySize;

    /*** Contains the content of the stack. */
    private Item[] contents;

    /*** Contains the capacity of the stack. */
    private int capacity;

    /*** Contains the initial capacity of an empty stack. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty stack with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfStack() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        this.arraySize = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs an stack with an initial capacity sufficient to hold the
     * specified number of elements.
     *
     * @param numElements
     *            the number of elements specified.
     *
     * @exception java.lang.IllegalArgumentException
     *                when the numElements<=0
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfStack(int numElements) {
        if (numElements <= 0) {
            throw new IllegalArgumentException("Invalid numElements");
        }
        this.capacity = (numElements / INITIAL_CAPACITY) * INITIAL_CAPACITY;
        if (numElements % INITIAL_CAPACITY != 0) {
            this.capacity += INITIAL_CAPACITY;
        }
        contents = (Item[]) new Object[this.capacity];
        this.arraySize = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public Item pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        if ((capacity > INITIAL_CAPACITY) && (arraySize <= capacity / 4)) {
            this.resize(this.capacity / 2);
        }
        Item popped = contents[--arraySize];
        contents[arraySize + 1] = null;
        return popped;
    }

    /*** {@inheritDoc} */
    @Override
    public Item peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        return contents[arraySize - 1];
    }

    /*** {@inheritDoc} */
    @Override
    public void push(Item item) {
        if (arraySize >= capacity) {
            this.resize(2 * this.capacity);
        }
        contents[arraySize++] = item;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if (this.arraySize == 0) {
            return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        this.arraySize = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return this.arraySize;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean contains(Item item) {
        for (int i = 0; i < this.arraySize; i++) {
            if (item.equals(contents[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resize the array.
     *
     * @param newCapacity
     *            the new capacity of the array
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Item[] newContents = (Item[]) new Object[newCapacity];
        for (int i = 0; i < this.arraySize; i++) {
            newContents[i] = this.contents[i];
        }
        this.contents = newContents;
        this.capacity = newCapacity;
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new IteratorArrayImplOfStack();
    }

    /**
     * IteratorArrayImplOfStack implements Iterator interface in order to
     * provide iterable capabilities to the stack.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorArrayImplOfStack implements Iterator<Item> {

        /*** Iteration index. */
        private int index;

        /*** Constructs IteratorArrayImplOfStack. */
        IteratorArrayImplOfStack() {
            index = arraySize;
        }

        /*** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if (index > 0) {
                return true;
            }
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public Item next() {
            return contents[--index];
        }

    }

}
