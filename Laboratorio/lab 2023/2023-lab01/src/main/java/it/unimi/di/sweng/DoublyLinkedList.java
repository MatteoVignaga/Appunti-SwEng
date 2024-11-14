package it.unimi.di.sweng;

import java.util.Objects;

public class DoublyLinkedList<T> {
    Node<T> front = null;
    Node<T> back = null;
    private int length = 0;

    public DoublyLinkedList() {}

    /**
     * Inserts a value at the back of the list.
     * @param value the value to insert.
     */
    public void push(T value) {
        Objects.requireNonNull(value, "Push: cannot insert null objects.");
        Node<T> newNode = new Node<>(value);

        if (this.length == 0) {
            front = newNode;
            back = newNode;
        } else {
            back.next = newNode;
            newNode.prev = back;
            back = newNode;
        }

        this.length++;
        return;
    }

    /**
     * Deletes the value at the end of the list and returns it.
     * @return the value at the end of the list.
     * @throws IllegalStateException if the list is empty.
     */
    public T pop() throws IllegalStateException {
        if (this.length == 0) throw new IllegalStateException("Cannot call pop() on empty list.");
        T res = back.value;
        back = back.prev;
        back.next = null;
        this.length--;
        return res;
    }

    /**
     * Inserts value at front.
     * @param value value to insert.
     */
    public void unshift(T value) {
        Node<T> newNode = new Node<>(value);
        if (this.length > 0) {
            front.prev = newNode;
            newNode.next = front;
        }
        front = newNode;
        this.length++;
    }

    /**
     * Removes value at front and returns it.
     * @return value at front of the list.
     * @throws IllegalStateException if the list is empty.
     */
    public T shift() {
        if (this.length == 0) throw new IllegalStateException("Cannot call shift() on empty list.");

        T res = front.value;
        if (this.length == 1) {
            front = null;
            back = null;
        } else {
            front = front.next;
            front.prev = null;
        }

        this.length--;
        return res;
    }

    /**
     * Class that implements each node of the list.
     * @param <T>
     */
    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        /**
         * Creates a new Node object containing a value of type T.
         * @param value the value to contain.
         */
        private Node(T value) {
            this.value = value;
        }
    }
}
