package Stack;

import java.util.ArrayList;

public class Stack<E> {

    private ArrayList<E> data;
    public int size = 0;

    public Stack() {
        data = new ArrayList<>();
    }

    public boolean push(E e) {
        data.add(e);
        size++;
        return true;
    }

    public E pop() {
        if (size < 1) {
            return null;
        }
        E temp = data.get(size - 1);
        data.remove(size - 1);
        size--;
        return temp;
    }
    public E peek() {
        if (size < 1) {
            return null;
        }
        return data.get(size-1);
    }

}
