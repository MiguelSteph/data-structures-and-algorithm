package com.queues;

import java.util.Iterator;

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
public class LinkedListImplOfQueue<Item> implements Queue<Item>{

    /*
     * Structure of each node of the linked list
     */
    private class Node {
        Item item;
        Node next;
        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    
    /*** A reference to the Node on the top of the Queue. */
    private Node topQueue;
    
    /*** A reference to the Node at the end of the Queue. */
    private Node endQueue;
    
    /*** Contains the size of the Queue. */
    private int queueSize;
    
    /**
     * Constructs an empty Queue.
     */
    public LinkedListImplOfQueue() {
        topQueue = null;
        endQueue = null;
        queueSize = 0;
    }
    
    /*** {@inheritDoc} */
    @Override
    public void add(Item item) {
        if(item == null)
            throw new NullPointerException("Null Item");
        Node newNode = new Node(item, null); 
        if (queueSize == 0) {
            topQueue = newNode;
            endQueue = topQueue;
        }else{
            endQueue.next = newNode;
            endQueue = endQueue.next;
        }
        queueSize++;
    }

    /*** {@inheritDoc} */
    @Override
    public Item poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }
        Item polledItem = topQueue.item;
        topQueue = topQueue.next;
        queueSize--;
        if (queueSize == 0) {
            endQueue = null;
        }
        return polledItem;
    }

    /*** {@inheritDoc} */
    @Override
    public Item peek() {
        if (this.isEmpty())
            throw new IllegalStateException("The queue is empty");
        return topQueue.item;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if(queueSize == 0)
            return true;
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public void clear() {
        topQueue = null;
        endQueue = null;
        queueSize = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return queueSize;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean contains(Item item) {
        Node top = topQueue;
        while (top != null) {
            if (top.item.equals(item))
                return true;
            top = top.next;
        }
        return false;
    }
    
    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new IteratorLinkedListImplOfQueue();
    }
    
    /**
     * IteratorLinkedListImplOfQueue implements Iterator interface in order to
     * provide iterable capabilities to the Queue.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorLinkedListImplOfQueue implements Iterator<Item> {

        /*** Reference on the current node in the iteration. */
        private Node currentNode;

        /*** Constructs IteratorLinkedListImplOfQueue. */
        public IteratorLinkedListImplOfQueue() {
            currentNode = topQueue;
        }

        /*** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            if (currentNode != null)
                return true;
            return false;
        }

        /*** {@inheritDoc} */
        @Override
        public Item next() {
            Item result = currentNode.item;
            currentNode = currentNode.next;
            return result;
        }
    }

}
