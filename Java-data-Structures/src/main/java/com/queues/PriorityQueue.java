package com.queues;

import java.util.Comparator;

/**
 * In computer science, a priority queue is an abstract data type which is like
 * a regular queue or stack data structure, but where additionally each element
 * has a "priority" associated with it. In a priority queue, an element with
 * high priority is served before an element with low priority. If two elements
 * have the same priority, they are served according to their order in the
 * queue.
 * 
 * {@link https://en.wikipedia.org/wiki/Priority_queue}
 *
 * The elements of the priority queue are ordered according to their natural
 * ordering, or by a Comparator provided at queue construction time, depending
 * on which constructor is used. A priority queue does not permit null elements.
 * A priority queue relying on natural ordering also does not permit insertion
 * of non-comparable objects (doing so may result in ClassCastException).
 *
 * {@link http://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public class PriorityQueue<Item extends Comparable<Item>> {

    /**
     * Contains the content of the PriorityQueue. For simplicity reason, the
     * contents array is 1 based array. So when a parent Item is at index k, its
     * children are at index 2*k and 2*k + 1.
     */
    private Item[] contents;

    /*** Contains the size of the PriorityQueue. */
    private int pqSize;

    /***
     * Store the provided Comparator for the construction of the PriorityQueue.
     */
    private Comparator<Item> comparator;

    /*** Contains the capacity of the PriorityQueue. */
    private int capacity;

    /*** Contains the initial capacity of an empty PriorityQueue. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty priorityQueue that use the natural order and with an
     * initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        pqSize = 0;
        contents = (Item[]) new Comparable[INITIAL_CAPACITY];
        comparator = null;
        capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs an empty priorityQueue that use the given Comparator and with
     * an initial capacity of 16.
     * 
     * @param com
     *            The Comparator that the priorityQueue will use
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<Item> com) {
        pqSize = 0;
        contents = (Item[]) new Comparable[INITIAL_CAPACITY];
        comparator = com;
        capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs a priorityQueue that use the natural order and fill him with
     * the given items.
     * 
     * @param items
     *            array of Items to put in the priorityQueue.
     * 
     * @exception java.lang.NullPointerException
     *                if items is null
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Item[] items) {
        if (items == null) {
            throw new NullPointerException("Items is null");
        }
        pqSize = items.length;
        capacity = ((pqSize + 1) / INITIAL_CAPACITY) * INITIAL_CAPACITY;
        if ((pqSize + 1) % INITIAL_CAPACITY != 0) {
            capacity += INITIAL_CAPACITY;
        }

        contents = (Item[]) new Comparable[capacity];
        for (int i = 0; i < pqSize; i++) {
            contents[i + 1] = items[i];
        }
        heapify();
    }

    /**
     * Constructs a priorityQueue that use the given Comparator and fill him with
     * the given items.
     * 
     * @param items
     *            array of Items to put in the priorityQueue.
     * 
     * @exception java.lang.NullPointerException
     *                if items is null
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Item[] items, Comparator<Item> com) {
        if (items == null) {
            throw new NullPointerException("Items is null");
        }
        pqSize = items.length;
        capacity = ((pqSize + 1) / INITIAL_CAPACITY) * INITIAL_CAPACITY;
        if ((pqSize + 1) % INITIAL_CAPACITY != 0) {
            capacity += INITIAL_CAPACITY;
        }

        contents = (Item[]) new Comparable[capacity];
        for (int i = 0; i < pqSize; i++) {
            contents[i + 1] = items[i];
        }
        comparator = com;
        heapify();
    }

    
    /**
     * Add the specified element in the priorityQueue.
     *
     * @param item
     *            the element to add
     * 
     * @exception java.lang.NullPointerException
     *                if item is null
     */
    public void add(Item item) {
        if(item == null)
            throw new NullPointerException("Null Item");
        if (pqSize+1 >= capacity) {
            resize(2 * capacity);
        }
        contents[++pqSize] = item;
        swim(pqSize);
    }

    /**
     * Retrieves and removes the item at the top of this priorityQueue.
     *
     * @return the top of this priorityQueue
     *
     * @exception java.lang.IllegalStateException
     *                if the priorityQueue is empty
     */
    public Item poll() {
        if (isEmpty()) {
            throw new IllegalStateException("The PriorityQueue is empty");
        }
        if ((capacity > INITIAL_CAPACITY) && (pqSize <= capacity / 4)) {
            this.resize(this.capacity / 2);
        }
        Item top = contents[1];
        exchKeys(1, pqSize--);
        sink(1);
        contents[pqSize + 1] = null;
        return top;
    }

    /**
     * Retrieves but not removes the item at the top of this priorityQueue.
     *
     * @return the top of this priorityQueue
     *
     * @exception java.lang.IllegalStateException
     *                if the priorityQueue is empty
     */
    public Item peek() {
        if (isEmpty())
            throw new IllegalStateException("The queue is empty");
        return contents[1];
    }
    
    /**
     * Tests if this priorityQueue is empty.
     *
     * @return true if the priorityQueue is empty and false if not.
     */
    public boolean isEmpty() {
        if (pqSize == 0)
            return true;
        return false;
    }

    /**
     * Removes all of the elements from this priorityQueue.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        pqSize = 0;
        contents = (Item[]) new Comparable[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }
    
    /**
     * Returns the number of elements in this priorityQueue.
     *
     * @return size the number of elements in this priorityQueue.
     */
    public int size() {
        return pqSize;
    }

    /*
     * Convert the array contents into a into a Heap data structure.
     */
    private void heapify() {
        for (int k = pqSize / 2; k >= 1; k--) {
            sink(k);
        }
    }

    /*
     * Restore the Heap logic when a parent is greater than one or both its children.
     */
    private void sink(int k) {
        int j;
        while (k <= pqSize / 2) {
            j = 2 * k;
            if ((j + 1 <= pqSize) && isLess(j + 1, j, comparator))
                j++;
            if (isLess(k, j, comparator))
                break;
            exchKeys(k, j);
            k = j;
        }
    }

    /*
     * Restore the Heap logic when a child is greater than his parent.
     */
    private void swim(int k) {
        while ((k > 1) && isLess(k, k / 2, comparator)) {
            exchKeys(k, k / 2);
            k = k / 2;
        }
    }

    /*
     * check is A[i] is less than A[j] ?
     */
    private boolean isLess(int k, int j, Comparator<Item> com) {
        int compareValue;
        if (com == null) 
            compareValue = contents[k].compareTo(contents[j]);
        else 
            compareValue = com.compare(contents[k], contents[j]);
        if (compareValue < 0)
            return true;
        return false;
    }

    /*
     * Swap the item at positions i and j.
     */
    private void exchKeys(int i, int j) {
        Item item = contents[i];
        contents[i] = contents[j];
        contents[j] = item;
    }

    /*
     * Resize the array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Item[] newContents = (Item[]) new Comparable[newCapacity];
        for (int i = 1; i <= pqSize; i++) {
            newContents[i] = contents[i];
        }
        contents = newContents;
        newContents = null;
        this.capacity = newCapacity;
    }

}
