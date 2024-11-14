package it.unimi.di.sweng;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DoublyLinkedList<T> {
    private @Nullable Node head;
    private @Nullable Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }
    public void push(T val){
        Objects.requireNonNull(val);
        Node node = new Node(val);
        if(head==null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
    }

    public T pop() {
        if (tail == null) throw new IllegalStateException("Empty List: illegal pop operation");
        T res = tail.value;
        tail = tail.prev;
        return res;
    }

    public T shift() {
        if (head == null || tail == null) {
            throw new IllegalStateException("Empty List: illegal shift operation");
        }

        if (head == tail) {
            Node res = head;
            head = null;
            tail = null;
            return res.value;
        }

        Node res = head;
        head = head.next;
        return res.value;

    }

    public void unshift(T c) {
        Objects.requireNonNull(c);
        Node node = new Node(c);
        if( head == null) {
            head=node;
            tail=node;
        } else {
            node.next = head;
            head.prev=node;
            head=node;

        }

    }

    class Node {
        T value;
        Node prev;
        Node next;
        public Node(T val){
            Objects.requireNonNull(val);
            value=val;
        }
    }
}
