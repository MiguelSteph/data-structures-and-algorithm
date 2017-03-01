package com.queues;

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

public interface Queue<Item> extends Iterable<Item> {

    /**
     * Inserts the specified element at the end of this queue.
     *
     * @param item
     *            the element to add
     * 
     * @exception java.lang.NullPointerException
     *                if item is null
     */
    void add(Item item);

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue
     *
     * @exception java.lang.IllegalStateException
     *                if the Queue is empty
     */
    Item poll();

    /**
     * Retrieves but not removes the head of this queue.
     *
     * @return the head of this queue
     *
     * @exception java.lang.IllegalStateException
     *                if the Queue is empty
     */
    Item peek();

    /**
     * Tests if this Queue is empty.
     *
     * @return true if the Queue empty and false if not.
     */
    boolean isEmpty();

    /**
     * Removes all of the elements from this Queue.
     */
    void clear();

    /**
     * Returns the number of elements in this Queue.
     *
     * @return size the number of elements in this Queue.
     */
    int size();

    /**
     * Returns true if this Queue contains the specified element.
     *
     * @param item
     *            the item to look for.
     *
     * @return true if the Queue contains the item and false if not.
     */
    boolean contains(Item item);

}
