package com.disjointsets;

import java.util.HashMap;
import java.util.Map;

/**
 * In mathematics, two sets are said to be disjoint if they have no element in
 * common. Equivalently, disjoint sets are sets whose intersection is the empty
 * set. For example, {1, 2, 3} and {4, 5, 6} are disjoint sets, while {1, 2, 3}
 * and {3, 4, 5} are not.
 * 
 * {@link https://en.wikipedia.org/wiki/Disjoint_sets}
 * 
 * This class is to Implement Disjoint set data structure. In computing, a
 * disjoint-set data structure is a data structure that keeps track of a set of
 * elements partitioned into a number of disjoint (nonoverlapping) subsets. A
 * union-find algorithm is an algorithm that performs two useful operations on
 * such a data structure: Find: Determine which subset a particular element is
 * in. This can be used for determining if two elements are in the same subset.
 * Union: Join two subsets into a single subset.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public class DisjointSet<Item> {

    /*** Contains each items and a reference to its parent in the tree. */
    private Map<Item, Item> map;

    /*** Contains the size of the tree rooted by an item. */
    private Map<Item, Integer> weightMap;

    /*** Keep track of the number of sets in the DisjointSet data structure. */
    private int setsSize;

    /**
     * Construct an empty disjoint set.
     */
    public DisjointSet() {
        map = new HashMap<>();
        weightMap = new HashMap<>();
        setsSize = 0;
    }

    /**
     * Construct a disjoint set and populate it with the element contains in the
     * given items arrays. Each element is put into a single set .
     * 
     * @exception java.lang.IllegalArgumentException
     *                if there are two or more same items in the given items
     *                array. The constructor use the equal function to check if
     *                two items are same.
     */
    public DisjointSet(Item[] items) {
        map = new HashMap<>();
        weightMap = new HashMap<>();
        for (Item item : items) {
            if (map.containsKey(item))
                throw new IllegalArgumentException("The items array contains at least two same items.");
            map.put(item, item);
            weightMap.put(item, 1);
        }
        setsSize = items.length;
    }

    /**
     * Creates a new set with one element {item}.
     * 
     * @param item
     *            the item to put into the new created set
     * @return true if the set is successfully created and contains the given
     *         item. And false if not.
     * @exception java.lang.NullPointerException
     *                if the given item is null.
     */
    public boolean makeSet(Item item) {
        if (item == null)
            throw new NullPointerException("The given item is null.");
        if (contains(item))
            return false;
        map.put(item, item);
        weightMap.put(item, 1);
        setsSize++;
        return true;
    }

    /**
     * Returns true if this DisjointSet contains no set.
     *
     * @return true if this DisjointSet contains no set
     */
    public boolean isEmpty() {
        if (map.isEmpty())
            return true;
        return false;
    }

    /**
     * Returns the number of items in this DisjointSet.
     *
     * @return size the number of items in this DisjointSet.
     */
    public int itemsSize() {
        return map.size();
    }

    /**
     * Returns the number of sets in this DisjointSet.
     *
     * @return size the number of sets in this DisjointSet.
     */
    public int itemsSetSize() {
        return setsSize;
    }

    /**
     * Merge into one set the set that contains item item1 and the set that
     * contains item item2 (item1 and item2 are in different sets). The original
     * sets will be destroyed.
     * 
     * @param item1
     *            the first item of union
     * @param item2
     *            the second item of union
     * 
     * @exception java.lang.NullPointerException
     *                if one of the given items if null.
     * @exception java.lang.IllegalArgumentException
     *                if one of the given items is not in the DisjointSet.
     */
    public void union(Item item1, Item item2) {
        if ((item1 == null) || (item2 == null))
            throw new NullPointerException("One of the given items is null.");
        if ((!contains(item1)) || (!contains(item2)))
            throw new IllegalArgumentException("Illegal Argument.");
        Item rootItem1 = find(item1);
        Item rootItem2 = find(item2);
        if (rootItem1 != rootItem2) {
            int weightRootItem1 = weightMap.get(rootItem1);
            int weightRootItem2 = weightMap.get(rootItem2);
            if (weightRootItem1 >= weightRootItem2) {
                map.put(rootItem2, rootItem1);
                weightMap.put(rootItem1, weightRootItem1 + weightRootItem2);
            } else {
                map.put(rootItem1, rootItem2);
                weightMap.put(rootItem2, weightRootItem1 + weightRootItem2);
            }
            setsSize--;
        }
    }

    /**
     * Returns the representative or a pointer to the representative of the set
     * that contains the given item.
     * 
     * @param item
     *            the item for which we want to the representative of its set.
     * @return Returns the representative or a pointer to the representative of
     *         the set that contains the given item and Null if the given Item
     *         is not in the DisjointSet.
     * 
     * @exception java.lang.NullPointerException
     *                if the given items if null.
     * @exception java.lang.IllegalArgumentException
     *                if the given item is not in the DisjointSet.       
     */
    public Item find(Item item) {
        if (item == null)
            throw new NullPointerException("The given item is null.");
        if (!contains(item))
            throw new IllegalArgumentException("Illegal Argument.");
        Item root = item;
        while (!root.equals(map.get(root)))
            root = map.get(root);
        return root;
    }

    /**
     * Returns true if this DisjointSet contains the specified item.
     *
     * @param item
     *            the item to look for.
     *
     * @return true if the DisjointSet contains the item and false if not.
     * 
     * @exception java.lang.NullPointerException
     *                if the given items if null.
     */
    public boolean contains(Item item) {
        if (item == null)
            throw new NullPointerException("The given item is null.");
        return map.containsKey(item);
    }

}
