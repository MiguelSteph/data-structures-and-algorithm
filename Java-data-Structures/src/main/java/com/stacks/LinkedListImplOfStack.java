package com.stacks;

import java.util.Iterator;

/**
 * In computer science, a stack is an abstract data type that serves as a
 * collection of elements, with two principal operations: push, which adds an
 * element to the collection, and pop, which removes the most recently added
 * element that was not yet removed. The order in which elements come off a
 * stack gives rise to its alternative name, LIFO (for last in, first out).
 * Additionally, a peek operation may give access to the top without modifying
 * the stack.
 *
 * {@link https://en.wikipedia.org/wiki/Stack_(abstract_data_type)}
 *
 * This is a Linked List implementation of the Stack API
 *
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public class LinkedListImplOfStack<Item> implements Stack<Item> {

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

    /*** A reference to the Node on the top of the stack. */
    private Node topStack;

    /*** Contains the size of the stack. */
    private int listSize;

    /**
     * Constructs an empty stack.
     */
    public LinkedListImplOfStack() {
        this.listSize = 0;
        this.topStack = null;
    }

    /*** {@inheritDoc} */
    @Override
    public Item pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        Item poppedItem = topStack.item;
        topStack = topStack.next;
        listSize--;
        return poppedItem;
    }

    /*** {@inheritDoc} */
    @Override
    public Item peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }
        return topStack.item;
    }

    /*** {@inheritDoc} */
    @Override
    public void push(Item item) {
        topStack = new Node(item, topStack);
        listSize++;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        if(topStack == null)
            return true;
        return false;
    }

    /*** {@inheritDoc} */
    @Override
    public void clear() {
        topStack = null;
        listSize = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return listSize;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean contains(Item item) {
        Node top = topStack;
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
        return new IteratorLinkedListImplOfStack();
    }

    /**
     * IteratorLinkedListImplOfStack implements Iterator interface in order to
     * provide iterable capabilities to the stack.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class IteratorLinkedListImplOfStack implements Iterator<Item> {

        /*** Reference on the current node in the iteration. */
        private Node currentNode;

        /*** Constructs IteratorLinkedListImplOfStack. */
        public IteratorLinkedListImplOfStack() {
            currentNode = topStack;
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
