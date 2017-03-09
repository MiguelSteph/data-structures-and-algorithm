package com.symboltables;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Symbol table is an important data structure created and maintained by
 * compilers in order to store information about the occurrence of various
 * entities such as variable names, function names, objects, classes,
 * interfaces, etc. Symbol table is used by both the analysis and the synthesis
 * parts of a compiler. A symbol table may serve the following purposes
 * depending upon the language in hand: - To store the names of all entities in
 * a structured form at one place. - To verify if a variable has been declared.
 * - To implement type checking, by verifying assignments and expressions in the
 * source code are semantically correct. - To determine the scope of a name
 * (scope resolution).
 * 
 * A symbol table can be implemented in one of the following ways: - Linear
 * (sorted or unsorted) list - Binary Search Tree - Hash table
 * 
 * 
 * {@link https://www.tutorialspoint.com/compiler_design/compiler_design_symbol_table.htm}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public class BstSymbolTableImpl<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    /*
     * Structure of each node of the Binary Search Tree.
     */
    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
        }
    }

    /*** Contains the size of the SymbolTable. */
    private int stSize;

    /*** A reference to the root node of the Binary Search Tree. */
    private Node root;

    /**
     * the comparator used by the Binary Search Tree. If not provided, the
     * Binary Search Tree use the natural order.
     */
    private Comparator<Key> comparator;

    /**
     * Constructs an empty SymbolTable.
     */
    public BstSymbolTableImpl() {
        stSize = 0;
        root = null;
        comparator = null;
    }

    /**
     * Constructs an empty SymbolTable in which the Binary Search Tree use the
     * given comparator.
     */
    public BstSymbolTableImpl(Comparator<Key> com) {
        stSize = 0;
        root = null;
        comparator = com;
    }

    /*** {@inheritDoc} */
    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        if (value == null)
            throw new NullPointerException("The given value is null.");
        root = put(root, key, value);
        stSize++;
    }

    /*
     * Recursive method to put a key-value in the SymbolTable.
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value, 0);
        int cmp = compare(x.key, key);
        if (cmp > 0) {
            x.left = put(x.left, key, value);
        } else if (cmp < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        return x;
    }

    /*** {@inheritDoc} */
    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        Node x = root;
        int cmp;
        while (x != null) {
            cmp = compare(x.key, key);
            if (cmp > 0)
                x = x.left;
            else if (cmp < 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    /*** {@inheritDoc} */
    @Override
    public Value delete(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        Value value = get(key);
        if (value != null) {
            root = delete(root, key);
            stSize--;
        }
        return value;
    }

    /*
     * Recursive method to delete the specified key from the SymbolTable. Here
     * we implement the HIBBARD DELETION method.
     */
    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = compare(x.key, key);
        if (cmp > 0)
            x.left = delete(x.left, key);
        else if (cmp < 0)
            x.right = delete(x.right, key);
        else {
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;
            Node t = x;
            x = getMin(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    /*
     * Retrieve the node with the minimum key in the subtree rooted by the given
     * node x.
     */
    private Node getMin(Node x) {
        while (x.left != null)
            x = x.left;
        return x;
    }

    /*
     * Delete the node with the minimum key in the subtree rooted by the given
     * node x.
     */
    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    /*** {@inheritDoc} */
    @Override
    public void clear() {
        stSize = 0;
        root = null;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsKey(Key key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        Node x = root;
        while (x != null) {
            int cmp = compare(x.key, key);
            if (cmp > 0)
                x = x.left;
            else if (cmp < 0)
                x = x.right;
            else
                return true;
        }
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsValue(Value value) {
        if (value == null)
            throw new NullPointerException("The given value is null.");
        return containsValue(root, value);
    }

    /*** {@inheritDoc} */
    private boolean containsValue(Node x, Value value) {
        if (x == null)
            return false;
        if (x.val.equals(value))
            return true;
        else
            return containsValue(x.left, value) || containsValue(x.right, value);
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
     * Compare the two given keys.
     */
    private int compare(Key key1, Key key2) {
        if (comparator != null)
            return comparator.compare(key1, key2);
        return key1.compareTo(key2);
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Key> iterator() {
        return new IteratorBstSymbolTableImpl();
    }

    /**
     * IteratorBstSymbolTableImpl implements Iterator interface in order to
     * provide iterable capabilities to the SymbolTable through the Key.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorBstSymbolTableImpl implements Iterator<Key> {

        /*** Iteration index. */
        private int index;
        private Key[] keys;

        /*** Constructs IteratorArrayImplOfStack. */
        @SuppressWarnings("unchecked")
        IteratorBstSymbolTableImpl() {
            index = 0;
            keys = (Key[]) new Comparable[stSize];
            InorderTraversal(root, keys, 0);
        }

        /*
         * Perform the InorderTraversal on the binary search tree and put the
         * keys in the keys arrays.
         */
        private int InorderTraversal(Node x, Key[] keys, int currIndex) {
            if (x == null)
                return currIndex;
            int indexLeft = InorderTraversal(x.left, keys, currIndex);
            keys[indexLeft] = x.key;
            indexLeft++;
            return InorderTraversal(x.right, keys, indexLeft);
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
            return keys[index++];
        }

    }

}
