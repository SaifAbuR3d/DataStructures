package myarraylist;

import java.util.*;

public class MyArrayList<E> {

    private final int INITIAL_CAPACITY = 10;
    private E[] data = (E[]) new Object[10];
    private int size = 0;

    public MyArrayList() {

    }

    public MyArrayList(E[] data) {
        for (E data1 : data) {
            add(data1);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }

    public void ensureCap() {
        if (size >= data.length) {
            int newSize = data.length * 2;
            data = Arrays.copyOf(data, newSize);
        }

    }

    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    public void add(E e) {           // O(n)
        add(e, size);
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index = " + index + "size = " + size);
        }
        ensureCap();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public E remove(int index) {     // O (n)
        E element = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return element;
    }

    public boolean remove(E e) {
        if (indexOf(e) != -1) {
            remove(indexOf(e));
            return true;
        } else {
            return false;
        }
    }

    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    public E get(int index) {  // O(1)
        return data[index];
    }

    public void set(int index, E e) {
        data[index] = e;
    }

    @Override
    public String toString() {
        String s = "";
        s += size;
        s += "\n";
        for (int i = 0; i < size; i++) {
            s += data[i] + " ";
        }
        s += "\n";
        return s;
    }


}
