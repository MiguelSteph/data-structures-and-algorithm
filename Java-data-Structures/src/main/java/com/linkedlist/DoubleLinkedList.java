package com.linkedlist;

import java.util.Iterator;

/**
 * Double LinkedList implementation of the LinkedList API.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public class DoubleLinkedList<Item> implements LinkedList<Item> {

    /*
     * Structure of each node of the LinkedList.
     */
    private class Node {
        Item item;
        Node next;
        Node prev;

        private Node(Item item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /*** {@inheritDoc} */
    @Override
    public int size() {
        return size;
    }

    /*** {@inheritDoc} */
    @Override
    public boolean empty() {
        return head == null;
    }

    /*** {@inheritDoc} */
    @Override
    public Item value_at(int index) {
        if (empty())
            throw new NullPointerException("Empty List");
        if ((index < 0) || (index >= size))
            throw new IllegalArgumentException("Illegal index");
        boolean fromHead = true;
        if (2 * (index + 1) > size)
            fromHead = false;
        if (fromHead) {
            Node x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.item;
        } else {
            index = size - index - 1;
            Node x = tail;
            for (int i = 0; i < index; i++) {
                x = x.prev;
            }
            return x.item;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public void push_front(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null Item");
        Node x = new Node(item);
        x.next = head;
        if (head != null) {
            head.prev = x;
        }
        head = x;
        if (tail == null) {
            tail = x;
        }
        size++;
    }

    /*** {@inheritDoc} */
    @Override
    public Item pop_front() {
        if (head == null)
            throw new NullPointerException("Empty List");
        Node x = head;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return x.item;
    }

    /*** {@inheritDoc} */
    @Override
    public void push_back(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null Item");
        Node x = new Node(item);
        x.next = null;
        x.prev = tail;
        if (tail == null) {
            head = x;
            tail = x;
        } else {
            tail.next = x;
            tail = x;
        }
        size++;
    }

    /*** {@inheritDoc} */
    @Override
    public Item pop_back() {
        if (head == null)
            throw new NullPointerException("Empty List");
        Node x = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return x.item;
    }

    /*** {@inheritDoc} */
    @Override
    public Item front() {
        if (head == null)
            throw new NullPointerException("Empty List");
        return head.item;
    }

    /*** {@inheritDoc} */
    @Override
    public Item back() {
        if (tail == null)
            throw new NullPointerException("Empty List");
        return tail.item;
    }

    /*** {@inheritDoc} */
    @Override
    public void insert(int index, Item item) {
        if ((index < 0) || (index > size))
            throw new IllegalArgumentException("Illegal index");
        if (item == null)
            throw new IllegalArgumentException("Null item");
        if (index == 0) {
            this.push_front(item);
        } else if (index == size) {
            this.push_back(item);
        } else {
            boolean fromHead = true;
            if (2 * index > size)
                fromHead = false;
            if (fromHead) { // forward iteration
                Node node = head;
                for (int i = 0; i < index - 1; i++) {
                    node = node.next;
                }
                Node x = new Node(item);
                x.next = node.next;
                x.prev = node;
                node.next = x;
                x.next.prev = x;
            } else { // backward iteration
                index = size - index - 1;
                Node node = tail;
                for (int i = 0; i < index - 1; i++) {
                    node = node.prev;
                }
                Node x = new Node(item);
                x.next = node;
                x.prev = node.prev;
                node.prev = x;
                x.prev.next = x;
            }
            size++;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public void erase(int index) {
        if ((index < 0) || (index >= size))
            throw new IllegalArgumentException("Illegal index");
        if (index == 0) {
            this.pop_front();
        } else if (index == size - 1) {
            this.pop_back();
        } else {
            boolean fromHead = true;
            if (2 * index >= size)
                fromHead = false;
            if (fromHead) { // forward iteration
                Node node = head;
                for (int i = 0; i < index - 1; i++) {
                    node = node.next;
                }
                Node x = node.next;
                node.next = node.next.next;
                node.next.prev = node;
                x.next = null;
                x.prev = null;
            } else { // backward iteration
                index = size - index - 1;
                Node node = tail;
                for (int i = 0; i < index - 1; i++) {
                    node = node.prev;
                }
                Node x = node.prev;
                node.prev = x.prev;
                x.prev.next = x.next;
                x.prev = null;
                x.next = null;
            }
            size--;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public Item value_n_from_end(int backIndex) {
        if (empty())
            throw new NullPointerException("Empty List");
        if ((backIndex < 0) || (backIndex >= size))
            throw new IllegalArgumentException("Illegal index");
        boolean fromTail = true;
        if (2 * (backIndex + 1) > size)
            fromTail = false;
        if (fromTail) {
            Node x = tail;
            for (int i = 0; i < backIndex; i++) {
                x = x.prev;
            }
            return x.item;
        } else {
            backIndex = size - backIndex - 1;
            Node x = head;
            for (int i = 0; i < backIndex; i++) {
                x = x.prev;
            }
            return x.item;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public void reverse() {
        if (head == null)
            return;
        Node x = head;
        Node temp = null;
        while (x != null) {
            temp = x.next;
            x.next = x.prev;
            x.prev = temp;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    /*** {@inheritDoc} */
    @Override
    public void remove_value(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null item");
        if (item.equals(head.item)) {
            this.pop_front();
            return;
        }
        Node x = head;
        while ((x.next != null) && (!x.next.item.equals(item))) {
            x = x.next;
        }
        if (x.next != null) {
            if (x.next == tail) {
                this.pop_back();
            } else {
                Node t = x.next;
                x.next = t.next;
                t.next.prev = x;
                t.next = null;
                t.prev = null;
                size--;
            }
        }
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new DoubleLinkedListIterator();
    }

    /**
     * DoubleLinkedListIterator implements Iterator interface in order to
     * provide iterable capabilities to the list.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class DoubleLinkedListIterator implements Iterator<Item> {

        private Node current;

        public DoubleLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
