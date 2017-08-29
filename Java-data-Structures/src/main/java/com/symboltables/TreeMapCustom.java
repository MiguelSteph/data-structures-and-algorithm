package com.symboltables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class TreeMapCustom<K, V> implements I_TreeMapCustom<K, V> {

    // declare a static class to encapsulate an entry.
    // In the purpose of the tutorial, we will stay in the logic of a binary
    // tree and take an entry as a Node<K, V>.
    static class Node<K, V> {
        K key;
        V val;
        boolean color;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V val, boolean col) {
            this.key = key;
            this.val = val;
            this.color = col;
        }
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private int size;
    private Node<K, V> root;
    private Comparator<? super K> cmp;

    /* CONSTRUCTORS */
    public TreeMapCustom() {
        size = 0;
        root = null;
        cmp = null;
    }

    public TreeMapCustom(Comparator<? super K> c) {
        size = 0;
        root = null;
        cmp = c;
    }

    /*** {@inheritDoc} */
    @Override
    public V put(K key, V val) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        root = put(root, key, val);
        root.color = BLACK;
        size++;
        return val;
    }

    private Node<K, V> put(Node<K, V> h, K key, V val) {
        if (h == null)
            return new Node<K, V>(key, val, RED);

        int c = compare(key, h.key);
        if (c < 0)
            h.left = put(h.left, key, val);
        else if (c > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsKey(K key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");

        Node<K, V> current = root;
        while (current != null) {
            int c = compare(key, current.key);
            if (c < 0) {
                current = current.left;
            } else if (c > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean containsValue(V val) {
        return containsValue(root, val);
    }

    private boolean containsValue(Node<K, V> x, V val) {
        if (x == null)
            return false;
        if (((val != null) && (val.equals(x.val))) || ((val == null) && (x.val == null))) {
            return true;
        }
        return containsValue(x.left, val) || containsValue(x.right, val);
    }

    /*** {@inheritDoc} */
    @Override
    public V get(K key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");

        Node<K, V> current = root;
        while (current != null) {
            int c = compare(key, current.key);
            if (c < 0) {
                current = current.left;
            } else if (c > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    /*** {@inheritDoc} */
    @Override
    public K ceilingKey(K key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");

        Node<K, V> ceil = ceilingKey(root, key);
        if (ceil == null)
            return null;
        return ceil.key;
    }

    private Node<K, V> ceilingKey(Node<K, V> x, K key) {
        if (x == null)
            return null;

        int c = compare(key, x.key);
        if (c == 0) {
            return x;
        } else if (c > 0) {
            return ceilingKey(x.right, key);
        }

        Node<K, V> t = ceilingKey(x.left, key);
        if (t != null)
            return t;
        return x;
    }

    /*** {@inheritDoc} */
    @Override
    public K floorKey(K key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");

        Node<K, V> floor = floorKey(root, key);
        if (floor == null)
            return null;
        return floor.key;
    }

    private Node<K, V> floorKey(Node<K, V> x, K key) {
        if (x == null)
            return null;

        int c = compare(key, x.key);
        if (c == 0) {
            return x;
        } else if (c < 0) {
            return floorKey(x.left, key);
        }

        Node<K, V> t = floorKey(x.right, key);
        if (t != null)
            return t;
        return x;
    }

    /*** {@inheritDoc} */
    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        keySet(root, result);
        return result;
    }

    private void keySet(Node<K, V> x, Set<K> result) {
        if (x == null)
            return;
        keySet(x.left, result);
        result.add(x.key);
        keySet(x.right, result);
    }

    /*** {@inheritDoc} */
    @Override
    public Collection<V> values() {
        Collection<V> result = new ArrayList<>();
        values(root, result);
        return result;
    }

    private void values(Node<K, V> x, Collection<V> result) {
        if (x == null)
            return;
        values(x.left, result);
        result.add(x.val);
        values(x.right, result);
    }

    /*** {@inheritDoc} */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return size;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public V remove(K key) {
        if (key == null)
            throw new NullPointerException("The given key is null.");
        if (containsKey(key)) {
            V val = get(key);

            // if both children of root are black, set root to red
            if (!isRed(root.left) && !isRed(root.right))
                root.color = RED;

            root = remove(root, key);
            size--;
            if (!isEmpty())
                root.color = BLACK;
            return val;
        }
        return null;
    }

    private Node<K, V> remove(Node<K, V> h, K key) {
        if (compare(key, h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = remove(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (compare(key, h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (compare(key, h.key) == 0) {
                Node<K, V> x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = removeMin(h.right);
            } else
                h.right = remove(h.right, key);
        }
        return balance(h);
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node<K, V> min(Node<K, V> x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node<K, V> removeMin(Node<K, V> h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = removeMin(h.left);
        return balance(h);
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node<K, V> moveRedRight(Node<K, V> h) {
        assert (h != null);
        assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node<K, V> balance(Node<K, V> h) {
        assert (h != null);

        if (isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        return h;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node<K, V> moveRedLeft(Node<K, V> h) {
        assert (h != null);
        assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // make a right-leaning link lean to the left
    private Node<K, V> rotateLeft(Node<K, V> h) {
        assert (h != null) && isRed(h.right);
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    // make a left-leaning link lean to the right
    private Node<K, V> rotateRight(Node<K, V> h) {
        assert (h != null) && isRed(h.left);
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    // flip the colors of a Node<K, V> and its two children
    private void flipColors(Node<K, V> h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right)) || (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /*
     * Compare the two given keys. return value greater than 0 if key1 > key2
     * return value smaller than 0 if key1 < key2 return value equal to 0 if
     * key1 = key2
     */
    @SuppressWarnings("unchecked")
    private int compare(K key1, K key2) {
        if (cmp != null)
            return cmp.compare(key1, key2);
        try {
            Comparable<? super K> cmpKey1 = (Comparable<? super K>) key1;
            return cmpKey1.compareTo(key2);
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    // Check if a Node<K, V> is red
    private boolean isRed(Node<K, V> x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<K> iterator() {
        return new IteratorTreeMapCustom();
    }

    /**
     * IteratorTreeMapCustom implements Iterator interface in order to provide
     * iterable capabilities to the TreeMap through the Key.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorTreeMapCustom implements Iterator<K> {

        /*** Iteration index. */
        private int index;
        private K[] keys;

        /*** Constructs IteratorArrayImplOfStack. */
        @SuppressWarnings("unchecked")
        public IteratorTreeMapCustom() {
            index = 0;
            keys = (K[]) new Object[size];
            InorderTraversal(root, keys, 0);
        }

        /*
         * Perform the InorderTraversal on the binary search tree and put the
         * keys in the keys arrays.
         */
        private int InorderTraversal(Node<K, V> x, K[] keys, int currIndex) {
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
            if (index < size) {
                return true;
            }
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public K next() {
            return keys[index++];
        }

    }

}
