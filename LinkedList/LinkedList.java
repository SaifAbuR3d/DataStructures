package linkedlist;

//import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Node<E> {

    E data;
    Node<E> next;

    public Node(E data) {
        this.data = data;
    }
}

public class LinkedList<E> {

    Node<E> head, tail;
    int size;

    public LinkedList() {

    }

    public void addFirst(E e) {
        Node<E> temp = new Node(e);
        temp.next = head;
        head = temp;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> temp = new Node(e);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    public void add(int index, E e) {
        if (index > size + 1 || index < 1) {
            return;
        }
        if (index == 1) {
            addFirst(e);
            return;
        } else if (index == size + 1) {
            addLast(e);
            return;
        }
        Node<E> temp = new Node(e);
        int i = 1;
        Node current = head;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        temp.next = current.next;
        current.next = temp;

    }

    public void addAll(int index, E[] arr) {
        if (index < 1 || index > size + 1 || head == null) {
            return;
        }
        Node c1 = head;
        int i = 1;
        while (i < index - 1) {
            c1 = c1.next;
            i++;
        }
        Node c2 = c1.next;

        Node arrhead = new Node<>(arr[0]);
        Node cur = arrhead;
        for (int j = 1; j < arr.length; j++) {     // make the the array nodes and link it.
            Node n = new Node(arr[j]);
            cur.next = n;
            cur = cur.next;
        }
        if (index == 1) {
            head = arrhead;
            cur.next = c2;
        } else {
            c1.next = arrhead;
            cur.next = c2;
        }

        Node<E> tmp = tail;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tail = tmp;
    }

    public E getFirst() {
        return (E) (head.data);
    }

    public E getLast() {
        return (E) tail.data;
    }

    public E removeFirst() {
        if (head == null) {
            return null;
        }
        E e = (E) head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return e;
    }

    public E removeLast() {
        if (tail == null) {
            return null;
        }
        E e = (E) tail.data;
        if (head == tail) {
            head = tail = null;
            size--;
            return e;
        }
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        size--;
        return e;
    }

    public E remove(int index) {
        if (index < 1 || index > size) {
            throw new RuntimeException();
        }
        if (index == 1) {
            removeFirst();
        } else if (index == size) {
            removeLast();
        }

        Node current = head;
        int i = 1;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        E e = (E) current.next.data;
        current.next = current.next.next;
        return e;
    }

    public void rotateRight(int k) {    // keep in mind rotateLeft(k) == rotateRight(size-k)  ,, if k > size -> k = k%size
        if (k % size == 0) {
            return;
        }
        k = k % size;
        tail.next = head;
        Node current = head;
        int i = 1;
        while (i++ < size - k) {
            current = current.next;
        }
        head = current.next;
        tail = current;
        tail.next = null;
    }

    public void reverse() {
        Node pointer = head;
        Node current = null, previous = null;
        // pointer to traverse the list, 
        // current is the current node 
        // previous is the previous node
        while (pointer != null) {
            current = pointer;
            pointer = pointer.next;
            current.next = previous;
            previous = current;
            head = current;
        }
    }

    public E findDublicate() {
        if (head == null) {
            throw new RuntimeException();
        }
        Node p1 = head, p2 = head;
        while (p1 != null) {
            p2 = head;
            while (p2 != null) {
                if (p1.data == p2.data && p1 != p2) {
                    return (E) p1.data;
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        return null;
    }

    public void swap() { // swap first dublicate with head and second dublicate with tail
        E data = findDublicate();
        Node curpre = head;
        while (curpre.next.data != data) {
            curpre = curpre.next;
        }
        // swap first dup with head

        Node cur = curpre.next, curnext = curpre.next.next;
        cur.next = head.next;
        curpre.next = head;
        head.next = curnext;
        head = cur;

        // to get the another dup
        Node curpre2 = head.next;
        while (curpre2.next.data != data) {
            curpre2 = curpre2.next;
        }
        Node tailpre = head;
        while (tailpre.next.next != null) {
            tailpre = tailpre.next;
        }
        Node cur2 = curpre2.next;
        tailpre.next = curpre2.next;
        tail.next = curpre2.next.next;
        curpre2.next = tail;
        tail = cur2;
        tail.next = null;

    }

    public boolean isCircular() {
        Node p = head;
        for (int i = 1; i <= size; i++) {
            p = p.next;
        }
        return p == head;
    }

    public void swapFirstWithLast(LinkedList list2) {
        Node prevy = list2.head;
        while (prevy.next.next != null) {
            prevy = prevy.next;
        }
        Node y = prevy.next;
        Node x = head;

        prevy.next = x;
        head = y;

        // swap x.next with y.next
        Node temp = x.next;
        x.next = y.next;  // x.next = null; 
        y.next = temp;
    }

    public void removeDublicates() { // from a sorted list
        Node p1 = head;
        Node p2 = head.next;
        while (p2 != null) {
            if (p1.data == p2.data) {
                if (p2.next == null) {
                    tail = p1;
                }
                p1.next = p2.next;
                p2.next = null;
                p2 = p1.next;
                size--;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }

        }
    }

    public LinkedList<E> nextLargerNode() {
        Node p = head;
        while (p != null) {
            Node p2 = p.next;
            while (p2 != null && (Integer) p2.data < (Integer) p.data) {
                p2 = p2.next;
            }
            p.data = (p2 == null) ? -1 : p2.data;
            p = p.next;
        }
        return this;
    }

    public boolean contains(E e) {
        Node p = head;
        while (p != null) {
            if (p.data.equals(e)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "[";
        Node current = head;
        while (current != null) {
            s += current.data;
            s += ", ";
            current = current.next;
        }
        return s.length() > 2 ? s.substring(0, s.length() - 2) + "]" : "[ ]";
    }

    public <E> boolean containsCycle() {
        Set<Node<E>> s = new HashSet<>();
        Node p = head;
        int i = 0;
        while (p != null) {
            if (s.contains(p)) {
                return true;
            }
            s.add(p);
            p = p.next;
        }
        return false;
    }

    public static void PrintList(Node head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public static <E> void addAllAtIndex(LinkedList<E> list, ArrayList<E> arr, int index) {
        if (index < 0 || index > list.size) {
            throw new RuntimeException();
        }
        if (list.size == 0 || arr.size() == 0) {
            return;
        }

        // construct a linked list from the arraylist
        Node dummy = new Node(0);
        Node p = dummy;
        for (int i = 0; i < arr.size(); i++) {
            p.next = new Node(arr.get(i));
            p = p.next;
        }
        p.next = null;

        if (index == 0) {   // add first
            p.next = list.head;
            list.head = dummy.next;
        } else {
            int i = 1;
            Node cur = list.head;
            while (i < index - 1) {
                i++;
                cur = cur.next;
            }
            Node next = cur.next;
            cur.next = dummy.next;
            p.next = next;
        }
    }

    public static <E> void InserstSecondAfterFirst(LinkedList<E> list, E first, E second) {
        Node<E> p = list.head;
        while (p != null) {
            if (p.data.equals(first)) {
                Node temp = p.next;
                p.next = new Node(second);
                p.next.next = temp;
            }
            p = p.next;
        }
    }

    public static <E> LinkedList<Integer> allOccureneceOfItem(LinkedList<E> list, E item) {
        int i = 1; // 1 indexed list
        LinkedList<Integer> occurences = new LinkedList<>();
        Node<E> p = list.head;
        while (p != null) {
            if (p.data.equals(item)) {
                occurences.addLast(i);
            }
            i++;
            p = p.next;
        }
        return occurences;
    }

    public void swapHeadWithTail() {
        if (head == null || head.next == null) {
            return;
        }
        if (head.next.next == null) {
            Node tail = head.next;
            tail.next = head;
            head.next = null;
            head = tail;
            return;
        }
        Node pre = head;
        while (pre.next.next != null) {
            pre = pre.next;
        }
        Node taill = pre.next;
        taill.next = head.next;
        head.next = null;
        pre.next = head;
        head = taill;
    }

    public static <E> LinkedList<Integer> intersect(LinkedList<Integer> list1,
            LinkedList<Integer> list2) { // complexity not important
        LinkedList<Integer> res = new LinkedList<>();
        Node p1 = list1.head;
        while (p1 != null) {
            Node p2 = list2.head;
            while (p2 != null) {
                if (p1.data.equals(p2.data) && !res.contains((Integer) p1.data)) {
                    res.addLast((Integer) p1.data);
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        return res;
    }

    public static LinkedList<Integer> multiplicationOfTwoLinkedList(LinkedList<Integer> list1,
            LinkedList<Integer> list2) {
        return intToList(listToInteger(list1) * listToInteger(list2));
    }

    public static int listToInteger(LinkedList<Integer> list) {
        Node p = list.head;
        int sum = 0;
        while (p != null) {
            sum = sum * 10 + (Integer) p.data;
            p = p.next;
        }
        return sum;
    }

    public static LinkedList<Integer> intToList(int n) {
        LinkedList<Integer> res = new LinkedList<>();
        while (n != 0) {
            res.addFirst(n % 10);
            n /= 10;
        }
        return res;
    }
    //Q1 - Given a linked list and two items, insert the second item after every occurrence of the first item in the list.
    //Q2 - Given an item and a linked list, return a linked list giving the positions of all occurrences of the item in the list argument.
    //Q3 - Merge two linked lists of numbers, including in the final list only those numbers that occur in both lists.
    //Q4 - Given a linked list, return the nth element from the end of the linked list .
    //Q5 - Delete all occurrences of an item from a linked list
    //Q6 - Delete the first occurrence of an item from a linked list.
    //Q7 - Delete the last occurrence of an item from a linked list.
    //Q8 - Merge two linked lists of numbers which are in ascending order.
    //Q9 - Merge two linked lists of numbers, but if a number occurs in both lists, only include it once in the final list.

    public static void recReverse(Node head) {
        if (head.next == null) {
            return;
        }
        Node curr = head;
        recReverse(head.next);

        Node first = curr, second = curr.next;
        second.next = first;
        first.next = null;
    }

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        l.addLast(4);
        l.addLast(5);
        System.out.println(l);
    }

}
