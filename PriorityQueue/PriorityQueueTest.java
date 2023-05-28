package PriorityQueueTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueTest {

    public static int minProfit2(int n, int[] emptySeats) { // O(sum(emptySeats))
        Arrays.sort(emptySeats);
        int ans = 0, i = 0, m = emptySeats.length;
        while (i < m && n > 0) {
            if (emptySeats[i] > 0) {
                ans += emptySeats[i];
                emptySeats[i]--;
                n--;
            } else {
                i++;
            }
        }
        return ans;
    }

    public static int sumFromTo(int from, int to) {
        return (to - from + 1) * (from + to) / 2;
    }

    public static int minProfit(int n, int[] emptySeats) { // better approach O(n)
        Arrays.sort(emptySeats);
        int ans = 0, i = 0, m = emptySeats.length;
        while (i < m && n > 0) {
            int seat = emptySeats[i];
            if (seat > n) {
                ans += sumFromTo(seat - n + 1, seat);
                n = 0;
            } else {
                ans += sumFromTo(0, seat);
                n -= seat;
            }
            i++;
        }
        return ans;
    }

    public static int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(a);
        pq.add(b);
        pq.add(c);
        int score = 0;
        int max1, max2;
        while (true) {
            max1 = pq.remove();
            max2 = pq.remove();
            if (max1 == 0 || max2 == 0) {
                break;
            }
            pq.add(max1 - 1);
            pq.add(max2 - 1);
            score++;
        }
        return score;
    }

    public static void main(String[] args) {
        // System.out.println(minProfit(4, new int[]{2, 1, 4}));
        System.out.println(maximumScore(1, 8, 8));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                if (a1[0] > a2[0]) {
                    return 1;
                } else if (a1[0] == a2[0]) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        // default pq in java is min heap
        pq.add(new int[]{1,2,3});
        pq.add(new int[]{3,1,2,3});
        pq.add(new int[]{9,1,2,3});
        pq.add(new int[]{-3,1,2,3});
        pq.add(new int[]{9,1,2,3});
        System.out.println(Arrays.toString(pq.remove()));
        System.out.println(Arrays.toString(pq.remove()));

    }
}
