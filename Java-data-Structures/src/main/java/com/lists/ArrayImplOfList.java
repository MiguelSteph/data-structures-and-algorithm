package com.lists;

import java.util.Iterator;

/**
 * In computer science, a list or sequence is an abstract data type that
 * represents a countable number of ordered values, where the same value may
 * occur more than once. An instance of a list is a computer representation of
 * the mathematical concept of a finite sequence; the (potentially) infinite
 * analog of a list is a stream. Lists are a basic example of containers, as
 * they contain other values. If the same value occurs multiple times, each
 * occurrence is considered a distinct item.
 *
 * {@link https://en.wikipedia.org/wiki/List_(abstract_data_type)}
 *
 * This is the resizing array implementation of the List API
 *
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 *
 */
public class ArrayImplOfList<Item> implements List<Item> {

    /*** Contains the size of the list. */
    private int listSize;

    /*** Contains the content of the list. */
    private Item[] contents;

    /*** Contains the capacity of the list. */
    private int capacity;

    /*** Contains the initial capacity of an empty list. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty list with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfList() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        listSize = 0;
        capacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs a list with an initial capacity sufficient to hold the
     * specified number of elements.
     *
     * @param numElements
     *            the number of elements specified.
     *
     * @exception java.lang.IllegalArgumentException
     *                when the numElements<=0
     */
    @SuppressWarnings("unchecked")
    public ArrayImplOfList(int numElements) {
        if (numElements <= 0) {
            throw new IllegalArgumentException("Invalid numElements");
        }
        capacity = (numElements / INITIAL_CAPACITY) * INITIAL_CAPACITY;
        if (numElements % INITIAL_CAPACITY != 0) {
            capacity += INITIAL_CAPACITY;
        }
        contents = (Item[]) new Object[capacity];
        listSize = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public void add(Item item) {
        if (listSize >= capacity) {
            resize(2 * capacity);
        }
        contents[listSize++] = item;
    }

    /*** {@inheritDoc} */
    @Override
    public void add(int index, Item item) {
        if ((index < 0) || (index >= listSize)) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (listSize >= capacity) {
            resize(2 * capacity);
        }
        int i = listSize;
        while (i > index) {
            contents[i] = contents[i-1];
            i--;
        }
        contents[i] = item;
        listSize++;
    }

    /*** {@inheritDoc} */
    @Override
    public void set(int index, Item item) {
        if ((index < 0) || (index >= listSize)) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        contents[index] = item;
    }

    /*** {@inheritDoc} */
    @Override
    public Item get(int index) {
        if ((index < 0) || (index >= listSize)) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return contents[index];
    }

    /*** {@inheritDoc} */
    @Override
    public Item remove(int index) {
        if ((index < 0) || (index >= listSize)) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if ((capacity > INITIAL_CAPACITY) && (listSize <= capacity / 4)) {
            this.resize(this.capacity / 2);
        }
        Item itemToRemove = contents[index];
        int i = index+1;
        while (i < listSize) {
            contents[i-1] = contents[i];
            i++;
        }
        listSize--;
        return itemToRemove;
    }

    /*** {@inheritDoc} */
    @Override
    public int remove(Item item) {
        int indexRemoved = -1;
        for (int i=0; i<listSize; i++) {
            if (contents[i].equals(item)) {
                indexRemoved = i;
                break;
            }
        }
        if (indexRemoved == -1) {
            return -1;
        }
        remove(indexRemoved);
        return indexRemoved;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if (listSize == 0)
            return true;
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean contains(Item item) {
        for (int i=0; i<listSize; i++) {
            if (contents[i].equals(item))
                return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        contents = (Item[]) new Object[INITIAL_CAPACITY];
        listSize = 0;
        capacity = INITIAL_CAPACITY;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return listSize;
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
        for (int i = 0; i < listSize; i++) {
            newContents[i] = this.contents[i];
        }
        this.contents = newContents;
        this.capacity = newCapacity;
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new IteratorArrayImplOfList();
    }

    /**
     * IteratorArrayImplOfList implements Iterator interface in order to
     * provide iterable capabilities to the list.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorArrayImplOfList implements Iterator<Item> {

        /*** Iteration index. */
        private int index;

        /*** Constructs IteratorArrayImplOfList. */
        IteratorArrayImplOfList() {
            index = 0;
        }

        /*** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if (index < listSize) {
                return true;
            }
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public Item next() {
            return contents[index++];
        }

    }

}
