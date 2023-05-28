package Deque;

public class DoublyLinkedList<E> implements Deque {

    class Node<E> {

        E data;
        Node<E> next;
        Node<E> pre;

        public Node(E e) {
            data = e;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(Object e) {
        Node<E> temp = new Node(e);
        if (head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head.pre = temp;
            head = temp;
        }
        size++;
    }

    @Override
    public void addLast(Object e) {
        Node temp = new Node(e);
        if (head == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            temp.pre = tail;
            tail = temp;
        }
        size++;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            return null;
        }
        E returned = tail.data;
        if (head.next == null) {
            head = tail = null;
        } else {
            tail = tail.pre;
        }
        size--;
        return returned;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E returned = head.data;
        if (head.next == null) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
        return returned;
    }

    @Override
    public String toString() {
        String s = "";
        Node curr = head;
        while (curr != null) {
            s += curr.data + " ";
            curr = curr.next;
        }
        return s;
    }

}
