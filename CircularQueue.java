package Queue;

public class CircularQueue<E> { // efficient , space restriction

    private int head, tail;
    private final int capacity;
    private final E[] data;

    public CircularQueue(int capacity) {
        data = (E[]) new Object[capacity];
        head = -1;
        tail = -1;
        this.capacity = capacity;
    }

    public boolean enQueue(E e) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0; 
        }
        tail = (tail + 1) % capacity;
        data[tail] = e;
        return true;
    }

    public E deQueue() {
        if (isEmpty()) {
            return null;
        }
        E temp = data[head];
        if (head == tail) {   // there is only one element 
            head = tail = -1;
        } else {
            head = (head + 1) % capacity;
        }
        return temp;
    }

    public E Front() {
        if (isEmpty()) {
            return null;
        }
        return data[head];
    }

    public E Rear() {
         if (isEmpty()) {
            return null;
        }
        return data[tail];
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean isFull() {
        return (tail+1)%capacity == head;
    }
    
    @Override
    public String toString() {  // using another CircularQueue
        String s ="";
        CircularQueue <E> temp = new CircularQueue<>(capacity);
        E el;
        while (!this.isEmpty()) {
            el = this.deQueue();
            temp.enQueue(el);
            s += el + " ";
        }
        while (!temp.isEmpty()) {
            this.enQueue(temp.deQueue());
        }
        return s;
    }
}
