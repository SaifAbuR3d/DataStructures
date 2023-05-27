
package Queue;

import java.util.ArrayList;


public class Queue<E> {        // inefficient , no space restriction 
    private ArrayList<E> data;
    private int start;
    public  Queue () {
        data = new ArrayList<>();
        start = 0;
    }
    public boolean isEmpty() {
        return start >= data.size();
    }
    public boolean enqueue(E e) {  // add last
        data.add(e);
        return true;
    }
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return data.get(start++);
    }
    public E front() {  // or peek()
        if (isEmpty()) {
            return null;
        }
        return data.get(start);
    }
    public String toString() {
        String s= ""; 
        for (int i =start;i <data.size();i++) {
            s += data.get(i);
        }
        return s;
    }
}
