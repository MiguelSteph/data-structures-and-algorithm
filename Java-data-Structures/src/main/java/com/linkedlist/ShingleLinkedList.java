package com.linkedlist;

import java.util.Iterator;

/**
 * Shingle LinkedList implementation of the LinkedList API.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Item>
 */
public class ShingleLinkedList<Item> implements LinkedList<Item> {

    /*
     * Structure of each node of the LinkedList.
     */
    private class Node {
        Item item;
        Node next;

        private Node(Item item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public ShingleLinkedList() {
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
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    /*** {@inheritDoc} */
    @Override
    public void push_front(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null Item");
        Node x = new Node(item);
        x.next = head;
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
        if (head == null)
            tail = null;
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
            Node node = head;
            while (node.next.next != null) {
                node = node.next;
            }
            node.next = null;
            tail = node;
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
            Node node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            Node x = new Node(item);
            x.next = node.next;
            node.next = x;
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
            Node node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            size--;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public Item value_n_from_end(int backIndex) {
        if ((backIndex < 0) || (backIndex >= size))
            throw new IllegalArgumentException("Illegal index");
        if (backIndex == 0) {
            return this.back();
        } else if (backIndex == size - 1) {
            return this.front();
        } else {
            Node node = head;
            for (int i = 0; i < backIndex; i++) {
                node = node.next;
            }
            Node x = head;
            while (node != tail) {
                node = node.next;
                x = x.next;
            }
            return x.item;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public void reverse() {
        if (head == null)
            return;
        tail = head;
        reverse(head);
    }

    private void reverse(Node x) {
        if (x.next == null) {
            head = x;
            return;
        }
        reverse(x.next);
        x.next.next = x;
        x.next = null;
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
                x.next = x.next.next;
                tail = x;
            } else {
                x.next = x.next.next;
            }
            size--;
        }
    }

    /*** {@inheritDoc} */
    @Override
    public Iterator<Item> iterator() {
        return new ShingleLinkedListIterator();
    }
    
    /**
     * ShingleLinkedListIterator implements Iterator interface in order to
     * provide iterable capabilities to the list.
     *
     * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
     *
     */
    private class ShingleLinkedListIterator implements Iterator<Item> {

        private Node current;

        public ShingleLinkedListIterator() {
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
