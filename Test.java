package Stack;

import java.util.Stack;

public class Test {

    static void insertInSortedStack(Stack<Integer> st, int element) {
        if (st.empty() || st.peek() <= element) {
            st.push(element);
            return;
        }
        int top = st.pop();
        insertInSortedStack(st, element);
        st.push(top);
    }

    static void recursiveSort(Stack<Integer> st) {
        if (st.empty()) {
            return;
        }
        int top = st.pop();
        recursiveSort(st);
        insertInSortedStack(st, top);
    }

    static void pushAtButtom(Stack<Integer> st, int element) {
        if (st.empty()) {
            st.push(element);
            return;
        }
        int top = st.pop();
        pushAtButtom(st, element);
        st.push(top);
    }

    static void reverse(Stack<Integer> st) {
        if (st.empty()) {
            return;
        }
        int top = st.pop();
        reverse(st);
        pushAtButtom(st, top);
    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // move primes to buttom
    static void movePrimes(Stack<Integer> st) {
        if (st.empty()) {
            return;
        }
        Integer top = st.pop();
        movePrimes(st);
        if (isPrime(top)) {
            pushAtButtom(st, top);
        } else {
            st.push(top);
        }
    }

    static Stack<Integer> mergeTwoSortedStacks(Stack<Integer> st1, Stack<Integer> st2) {  // minimum value on the top
        Stack<Integer> res = new Stack<>();
        while (!st1.empty() && !st2.empty()) {
            Integer el1 = st1.peek();
            Integer el2 = st2.peek();
            if (el1.compareTo(el2) > 0) {
                res.push(st2.pop());
            } else {
                res.push(st1.pop());
            }
        }
        while (!st1.empty()) {
            res.push(st1.pop());
        }
        while (!st2.empty()) {
            res.push(st2.pop());
        }
        // now we havev stack "res" sorted, minimum element in the buttom
        // so reverse it in st1
        while (!res.empty()) {
            st1.push(res.pop());
        }
        return st1;
    }

    static void swap(Stack<Integer> st, int i, int j) {  // i , j are indices
        int e1 = get(st, i);
        int e2 = get(st, j);
        set(i, e2, st);
        set(j, e1, st);
    }

    static void set(int i, int e, Stack<Integer> st) { 
        if (st.size() == i + 1) {
            st.pop();
            st.push(e);
            return;
        }
        int top = st.pop();
        set(i, e, st);
        st.push(top);
    }

    static int get(Stack<Integer> st, int i) {  // return element at index i
        if (st.size() == i + 1) {
            return st.peek();
        }
        int top = st.pop();
        int res = get(st, i);
        st.push(top);
        return res;
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        System.out.println(st);
        swap(st, 2, 3);
        System.out.println(st);
//        Stack<Integer> st2 = new Stack<>();
//        st1.push(12);
//        st1.push(10);
//        st1.push(8);
//        st1.push(7);
//        st2.push(10);
//        st2.push(9);
//        st2.push(2);
//        st2.push(1);
//        System.out.println(st1);
//        System.out.println(st2);
//        System.out.println();
//        System.out.println(mergeTwoSortedStacks(st1, st2));
//        System.out.println(s);
//        recursiveSort(s);
//        System.out.println(s);
//        reverse(s);
//        System.out.println(s);
    }

}
