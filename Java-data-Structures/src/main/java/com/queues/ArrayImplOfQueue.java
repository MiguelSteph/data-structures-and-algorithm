package com.queues;

import java.util.Iterator;

/**
 * In computer science, a queue is a particular kind of abstract data type or
 * collection in which the entities in the collection are kept in order and the
 * principal (or only) operations on the collection are the addition of entities
 * to the rear terminal position, known as enqueue, and removal of entities from
 * the front terminal position, known as dequeue. This makes the queue a
 * First-In-First-Out (FIFO) data structure. In a FIFO data structure, the first
 * element added to the queue will be the first one to be removed.
 *
 * {@link https://en.wikipedia.org/wiki/Queue_(abstract_data_type)}
 *
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */

public class ArrayImplOfQueue<Item> implements Queue<Item> {

    /*** Contains the size of the queue. */
    private int queueSize;

    /*** Contains the content of the queue. */
    private Item[] contents;

    /*** Contains the capacity of the queue. */
    private int capacity;

    /*** Contains the index of the top of the queue. */
    private int topIndex;

    /*** Contains the initial capacity of an empty queue. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty queue with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfQueue() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        this.queueSize = 0;
        this.topIndex = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs an queue with an initial capacity sufficient to hold the
     * specified number of elements.
     *
     * @param numElements
     *            the number of elements specified.
     *
     * @exception java.lang.IllegalArgumentException
     *                when the numElements<=0
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfQueue(int numElements) {
        if (numElements <= 0) {
            throw new IllegalArgumentException("Invalid numElements");
        }
        this.capacity = (numElements / INITIAL_CAPACITY) * INITIAL_CAPACITY;
        if (numElements % INITIAL_CAPACITY != 0) {
            this.capacity += INITIAL_CAPACITY;
        }
        contents = (Item[]) new Object[this.capacity];
        this.queueSize = 0;
        this.topIndex = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public void add(Item item) {
        if(item == null)
            throw new NullPointerException("Null Item");
        if (topIndex >= capacity) {
            this.resize(2 * this.capacity);
        }
        contents[topIndex++] = item;
        queueSize++;
    }

    /*** {@inheritDoc} */
    @Override
    public Item poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }
        if ((capacity > INITIAL_CAPACITY) && (queueSize <= capacity / 4)) {
            this.resize(this.capacity / 2);
        }
        Item popped = contents[topIndex-queueSize];
        contents[topIndex-queueSize] = null;
        queueSize--;
        return popped;
    }

    /*** {@inheritDoc} */
    @Override
    public Item peek() {
        if (this.isEmpty())
            throw new IllegalStateException("The queue is empty");
        return contents[topIndex-queueSize];
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if (this.queueSize == 0) {
            return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        this.queueSize = 0;
        this.topIndex = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return this.queueSize;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean contains(Item item) {
        for (int i=0; i<topIndex; i++)
            if(item.equals(contents[i]))
                return true;
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
        for (int i = 0; i < this.topIndex; i++) {
            if(contents[i] != null)
                newContents[i] = this.contents[i];
        }
        this.contents = newContents;
        this.capacity = newCapacity;
        this.topIndex = queueSize;
    }
    
    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new IteratorArrayImplOfQueue();
    }

    /**
     * IteratorArrayImplOfQueue implements Iterator interface in order to
     * provide iterable capabilities to the queue.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorArrayImplOfQueue implements Iterator<Item> {

        private int currentIndex;

        public IteratorArrayImplOfQueue() {
            currentIndex = topIndex - queueSize;
        }

        /*** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if(currentIndex < topIndex)
                return true;
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public Item next() {
            return contents[currentIndex++];
        }

    }
}
