package com.symboltables;

import java.util.Iterator;

/**
 * This is a HashTable implementation of symbol table.
 * 
 * Here we are using the Linear probing technique do handle collisions.
 * 
 * Linear probing is a scheme in computer programming for resolving collisions
 * in hash tables, data structures for maintaining a collection of key–value
 * pairs and looking up the value associated with a given key. It was invented
 * in 1954 by Gene Amdahl, Elaine M. McGraw, and Arthur Samuel and first
 * analyzed in 1963 by Donald Knuth.
 * 
 * {@link https://en.wikipedia.org/wiki/Linear_probing}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */

public class LinearProbingSymbolTableImpl<Key, Value> implements SymbolTable<Key, Value> {

    /*** Contains the keys of the SymbolTable. */
    private Key[] keys;

    /*** Contains the value associated to the keys of the SymbolTable. */
    private Value[] values;

    /*** Contains the size of the SymbolTable. */
    private int stSize;

    /*** Contains the capacity of the SymbolTable. */
    private int capacity;

    /*** Contains the initial capacity of an empty SymbolTable. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty SymbolTable with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public LinearProbingSymbolTableImpl() {
        keys = (Key[]) new Object[INITIAL_CAPACITY];
        values = (Value[]) new Object[INITIAL_CAPACITY];
        stSize = 0;
        capacity = INITIAL_CAPACITY;
    }

    /*** {@inheritDoc} */
    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        if (value == null)
            throw new NullPointerException("The given value is null.");
        if (stSize == capacity - 1)
            resize(2 * capacity);
        int index;
        for (index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
        }
        keys[index] = key;
        values[index] = value;
        stSize++;
    }

    /*** {@inheritDoc} */
    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        for (int index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
            if (key.equals(keys[index]))
                return values[index];
        }
        return null;
    }

    /*** {@inheritDoc} */
    @Override
    public Value delete(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");

        Value val = null;
        boolean isInside = false;
        int index;
        for (index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
            if (key.equals(keys[index])) {
                isInside = true;
                break;
            }
        }
        if (isInside == true) {
            val = values[index];
            keys[index] = null;
            values[index] = null;
            stSize--;
            if ((capacity > INITIAL_CAPACITY) && (stSize <= capacity / 4))
                resize(capacity / 2);
            else
                resize(capacity);
        }

        return val;
    }

    /*** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        keys = (Key[]) new Object[INITIAL_CAPACITY];
        values = (Value[]) new Object[INITIAL_CAPACITY];
        stSize = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsKey(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        for (int index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
            if (key.equals(keys[index]))
                return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsValue(Value value) {
        if (value == null)
            throw new NullPointerException("The given value is null.");
        for (int i = 0; i < capacity; i++) {
            if (value.equals(values[i]))
                return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if (stSize == 0)
            return true;
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return stSize;
    }

    /*
     * Compute the hash code of the given key.
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * Resize the SymbolTable.
     *
     * @param newCapacity
     *            the new capacity of the SymbolTable
     */
    @SuppressWarnings({ "unchecked" })
    private void resize(int newCapacity) {
        Key[] newKeys = (Key[]) new Object[newCapacity];
        Value[] newValues = (Value[]) new Object[newCapacity];
        int index;
        int oldCapacity = capacity;
        capacity = newCapacity;
        for (int i = 0; i < oldCapacity; i++) {
            if (keys[i] != null) {
                for (index = hash(keys[i]); newKeys[index] != null; index = (index + 1) % newCapacity)
                    continue;
                newKeys[index] = keys[i];
                newValues[index] = values[i];
            }
        }
        keys = newKeys;
        values = newValues;
        newKeys = null;
        newValues = null;
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Key> iterator() {
        return new IteratorLinearProbingSymbolTableImpl();
    }

    /**
     * IteratorLinearProbingSymbolTableImpl implements Iterator interface in
     * order to provide iterable capabilities to the SymbolTable through the
     * Key.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorLinearProbingSymbolTableImpl implements Iterator<Key> {

        /*** Iteration index. */
        private int index;
        private Key[] allKeys;

        /*** Constructs IteratorLinearProbingSymbolTableImpl. */
        @SuppressWarnings({ "unchecked" })
        IteratorLinearProbingSymbolTableImpl() {
            index = 0;
            allKeys = (Key[]) new Object[stSize];
            retrieveAllKeys();
        }

        /*
         * Retrieve all the keys and fill the allKeys array with them.
         */
        private void retrieveAllKeys() {
            int indx = 0;
            for (int i = 0; (i < capacity) && (indx < stSize); i++) {
                if (keys[i] != null) {
                    allKeys[indx++] = keys[i];
                }
            }
        }

        /*** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if (index < stSize) {
                return true;
            }
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public Key next() {
            return allKeys[index++];
        }

    }

}
