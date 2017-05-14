package com.linkedlist;

/**
 * In computer science, a linked list is a linear collection of data elements,
 * called nodes, each pointing to the next node by means of a pointer. It is a
 * data structure consisting of a group of nodes which together represent a
 * sequence. Under the simplest form, each node is composed of data and a
 * reference (in other words, a link) to the next node in the sequence. This
 * structure allows for efficient insertion or removal of elements from any
 * position in the sequence during iteration. More complex variants add
 * additional links, allowing efficient insertion or removal from arbitrary
 * element references.
 * 
 * {@link https://en.wikipedia.org/wiki/Linked_list}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public interface LinkedList<Item> extends Iterable<Item> {

    /**
     * returns number of data elements in list.
     * 
     * @return the size of the linked list
     */
    int size();

    /**
     * Returns true if this linked list contains no items.
     *
     * @return true if this linked list contains no items
     */
    boolean empty();

    /**
     * Returns the value of the nth item (starting at 0 for first).
     * 
     * @param index
     * @return The value of the nth item (starting at 0 for first).
     * 
     * @exception IllegalArgumentException
     *                when index < 0 or index >=size
     * @exception NullPointerException
     *                when the list is empty
     */
    Item value_at(int index);

    /**
     * Adds an item to the front of the list.
     * 
     * @param item
     *            Item to add to the front of this Linked list
     * 
     * @exception IllegalArgumentException
     *                when item is null
     * 
     */
    void push_front(Item item);

    /**
     * Remove front item and return its value
     * 
     * @return The front item
     * 
     * @exception NullPointerException
     *                when the list is empty
     * 
     */
    Item pop_front();

    /**
     * adds an item at the end of the linked list
     * 
     * @param item
     *            Item to add to the end of this Linked list
     * 
     * @exception IllegalArgumentException
     *                when item is null
     * 
     */
    void push_back(Item item);

    /**
     * removes end item and returns its value.
     * 
     * @return the end item
     * 
     * @exception NullPointerException
     *                when the list is empty
     * 
     */
    Item pop_back();

    /**
     * Get value of front item
     * 
     * @return the front item
     * 
     * @exception NullPointerException
     *                when the list is empty
     * 
     */
    Item front();

    /**
     * Get value of end item
     * 
     * @return the end item
     * 
     * @exception NullPointerException
     *                when the list is empty
     * 
     */
    Item back();

    /**
     * insert the given item at index, so current item at that index is pointed
     * to by new item at index
     * 
     * @param index
     *            The index to insert into.
     * @param item
     *            The item to insert at index.
     * 
     * @exception IllegalArgumentException
     *                when index < 0 or index >size or when item is null
     * 
     */
    void insert(int index, Item item);

    /**
     * removes item at given index
     * 
     * @param index
     * 
     * @exception IllegalArgumentException
     *                when index < 0 or index >= size
     * 
     */
    void erase(int index);

    /**
     * returns the value of the node at backIndex position from the end of the
     * list
     * 
     * @param n
     * @return
     * 
     * @exception IllegalArgumentException
     *                when index < 0 or index > size
     * 
     */
    Item value_n_from_end(int backIndex);

    /**
     * reverses the list
     */
    void reverse();

    /**
     * removes the first item in the list with this value
     * 
     * @param item
     * 
     * @exception IllegalArgumentException
     *                when item is null
     * 
     */
    void remove_value(Item item);

}
