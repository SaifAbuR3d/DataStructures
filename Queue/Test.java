package Queue;

import java.util.Queue;
import java.util.LinkedList;

public class Test {

    static int findMax(Queue<Integer> q) {
        int size = q.size();
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        while (cnt < size) {
            int curr = q.peek();
            max = Integer.max(curr, max);
            q.add(q.remove());
            cnt++;
        }
        return max;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(6);
        q.add(9);
        q.add(122222);
        q.add(12222);
        q.add(100);
        System.out.println(findMax(q));

        //        CircularQueue<Integer> q = new CircularQueue<>(6);
        //        System.out.println(q.enQueue(1));
        //        System.out.println(q.enQueue(2));
        //        System.out.println(q.enQueue(3));
        //        System.out.println(q.enQueue(4));
        //        System.out.println(q.enQueue(5));
        //        System.out.println(q.enQueue(6));
        //        System.out.println(q.enQueue(7));      // 
        //        System.out.println(q);
        //        System.out.println(q.deQueue());
        //        System.out.println(q.deQueue());
        //        System.out.println(q.deQueue());
        //        System.out.println(q);
    }
}
