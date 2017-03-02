package com.lists;

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
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 *
 */
public interface List<Item> extends Iterable<Item> {

    /**
     * Appends the specified item to the end of this list.
     *
     * @param item
     *            item to be appended to this list
     */
    void add(Item item);

    /**
     * Inserts the specified item at the specified position in this list.
     *
     * @param index
     *            index at which the specified item is to be inserted
     * @param item
     *            item to be appended to this list
     *            
     * @exception IndexOutOfBoundsException if the index is out of range
     */
    void add(int index, Item item);

    /**
     * Replaces the item at the specified index with the new item.
     *
     * @param index
     *            index of the item to replace
     * @param item
     *            item to be stored at the specified position
     * 
     * @exception IndexOutOfBoundsException if the index is out of range
     * 
     */
    void set(int index, Item item);

    /**
     * Returns the item at the specified position in this list.
     *
     * @param index index of the item to return
     * @return the item at the specified position in this list
     *
     * @exception IndexOutOfBoundsException if the index is out of range
     */
    Item get(int index);

    /**
     * Removes the item at the specified position in this list.
     *
     * @param index the index of the item to be removed
     * @return the item that was removed from the list
     *
     * @exception IndexOutOfBoundsException if the index is out of range
     */
    Item remove(int index);

    /**
     * Removes the first occurrence of the specified item from this list, if it is present.
     *
     * @param item item to be removed from this list, if present
     * @return the index from which the item is deleted, -1 if the item is not in the list.
     */
    int remove(Item item);

    /**
     * Returns true if this list contains no items.
     *
     * @return true if this list contains no items
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified item.
     *
     * @param item
     *            the item to look for.
     *
     * @return true if the list contains the item and false if not.
     */
    boolean contains(Item item);

    /**
     * Removes all of the items from this list.
     */
    void clear();

    /**
     * Returns the number of items in this list.
     *
     * @return size the number of items in this list.
     */
    int size();

}
