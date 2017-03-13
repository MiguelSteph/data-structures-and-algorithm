package com.symboltables;

import java.util.Iterator;

/**
 * This is a HashTable implementation of symbol table.
 * 
 * Here we are using the Separate chaining technique do handle collisions.
 * 
 * In the method known as separate chaining, each bucket is independent, and has
 * some sort of list of entries with the same index. The time for hash table
 * operations is the time to find the bucket (which is constant) plus the time
 * for the list operation.In a good hash table, each bucket has zero or one
 * entries, and sometimes two or three, but rarely more than that. Therefore,
 * structures that are efficient in time and space for these cases are
 * preferred.
 * 
 * {@link https://en.wikipedia.org/wiki/Hash_table}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */

public class SeparateChainingSymbolTableImpl<Key, Value> implements SymbolTable<Key, Value> {

    /*
     * Structure of each node of the LinkedList.
     */
    private static class Node {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.value = val;
            this.next = next;
        }
    }

    /*** Arrays of Linked List */
    private Node[] contents;

    /*** Contains the size of the SymbolTable. */
    private int stSize;

    /*** Contains the capacity of the SymbolTable. */
    private int capacity;

    /*** Contains the initial capacity of an empty SymbolTable. */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Constructs an empty SymbolTable with an initial capacity of 16.
     */
    public SeparateChainingSymbolTableImpl() {
        contents = new Node[INITIAL_CAPACITY];
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
        if ((stSize / capacity) >= 5)
            resize(2 * capacity);
        int index = hash(key);
        for (Node x = contents[index]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        contents[index] = new Node(key, value, contents[index]);
        stSize++;
    }

    /*** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        for (Node x = contents[hash(key)]; x != null; x = x.next) {
            if (key.equals(x.key))
                return (Value) x.value;
        }
        return null;
    }

    /*** {@inheritDoc} */
    @Override
    public Value delete(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        Value deletedVal = get(key);
        if (deletedVal != null) {
            int index = hash(key);
            contents[index] = delete(contents[index], key);
            stSize--;
            if ((capacity > INITIAL_CAPACITY) && (stSize <= capacity / 4))
                resize(capacity / 2);
        }
        return deletedVal;
    }

    /*
     * Recursive method to delete the node with the given key from it linked
     * list.
     */
    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        if (key.equals(x.key))
            x = x.next;
        else
            x.next = delete(x.next, key);
        return x;
    }

    /*** {@inheritDoc} */
    @Override
    public void clear() {
        contents = new Node[INITIAL_CAPACITY];
        stSize = 0;
        capacity = INITIAL_CAPACITY;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsKey(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        for (Node x = contents[hash(key)]; x != null; x = x.next) {
            if (key.equals(x.key))
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
            for (Node x = contents[i]; x != null; x = x.next) {
                if (value.equals(x.value))
                    return true;
            }
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

    /**
     * Resize the SymbolTable.
     *
     * @param newCapacity
     *            the new capacity of the SymbolTable
     */
    private void resize(int newCapacity) {
        Node[] newContents = new Node[newCapacity];
        int index;
        int oldCapacity = capacity;
        capacity = newCapacity;
        for (int i = 0; i < oldCapacity; i++) {
            for (Node x = contents[i]; x != null; x = x.next) {
                index = hash(x.key);
                newContents[index] = new Node(x.key, x.value, newContents[index]);
            }
        }
        contents = newContents;
        newContents = null;
    }

    /*
     * Compute the hash code of the given key.
     */
    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Key> iterator() {
        return new IteratorSeparateChainingSymbolTableImpl();
    }

    /**
     * IteratorSeparateChainingSymbolTableImpl implements Iterator interface in
     * order to provide iterable capabilities to the SymbolTable through the
     * Key.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorSeparateChainingSymbolTableImpl implements Iterator<Key> {

        /*** Iteration index. */
        private int index;
        private Key[] allKeys;

        /*** Constructs IteratorLinearProbingSymbolTableImpl. */
        @SuppressWarnings({ "unchecked" })
        IteratorSeparateChainingSymbolTableImpl() {
            index = 0;
            allKeys = (Key[]) new Object[stSize];
            retrieveAllKeys();
        }

        /*
         * Retrieve all the keys and fill the allKeys array with them.
         */
        @SuppressWarnings("unchecked")
        private void retrieveAllKeys() {
            int indx = 0;
            for (int i = 0; (i < capacity) && (indx < stSize); i++) {
                for (Node x = contents[i]; x != null; x = x.next) {
                    allKeys[indx++] = (Key) x.key;
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
