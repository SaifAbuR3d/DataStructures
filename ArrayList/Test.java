package myarraylist;

import java.util.ArrayList;

public class Test {

    public static void removeConsecutive(ArrayList<Integer> list, int index, int k) {
        for (int i = 0; i < k; i++) {
            list.remove(index);
        }
    }

    public static int calx(ArrayList<String> list) {
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).charAt(0) == '+' || list.get(i).charAt(2) == '+') {
                x++;
            } else {
                x--;
            }
        }
        return x;
    }

    public static ArrayList<Integer> runingSum(ArrayList<Integer> list) {
        int sum = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
            res.add(sum);
        }
        return res;
    }

    public static ArrayList<Integer> decoded(ArrayList<int[]> incoded) { // 2 4 4 4 3 3 1 1 1 1 1
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < incoded.size(); i++) {
            for (int j = 0; j < incoded.get(i)[0]; j++) {
                res.add(incoded.get(i)[1]);
            }
        }
        return res;
    }

    public static void moveZeros(ArrayList<Integer> list) {
        // O(n) solution
        int cnt = 0; // no. of nonzero elements. 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 0) {
                list.set(cnt++, list.get(i));      //a[cnt] = a[i]
            }
        }
        while (cnt < list.size()) {
            list.set(cnt++, 0);
        }
    }

    public static void leftRotate(ArrayList<Integer> list) {

        Integer temp = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            list.set(i, list.get(i + 1)); // a[i] = a[i+1]
        }
        list.set(list.size() - 1, temp);

    }

    public static void rightRotate(ArrayList<Integer> list) {

        Integer temp = list.get(list.size() - 1);
        for (int i = list.size() - 1; i > 0; i--) {
            list.set(i, list.get(i - 1)); // a[i] = a[i-1]
        }
        list.set(0, temp);

    }

    public static int fun(ArrayList<Integer> list) { // maximun no. of right rotations in order to obtain the largest sequence of even nums.
        if (list.get(0) % 2 != 0 || list.get(list.size() - 1) % 2 != 0) {
            return 0;
        }
        int ans = 0, maxans = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                ans++;
            } else {
                maxans = Math.max(ans, maxans);
                ans = 0;
            }
        }
        maxans = Math.max(ans, maxans);

        if (maxans == list.size()) {
            return 0;
        }

        int fback = 0, fbegin = 0;
        int i = list.size() - 1;
        while (i > 0 && list.get(i) % 2 == 0) {
            fback++;
            i--;
        }
        i = 0;
        while (i < list.size() && list.get(i) % 2 == 0) {
            fbegin++;
            i++;
        }

        if (fback + fbegin < maxans) {
            return 0;
        }
        return fback;
    }

    private static int countEvens(ArrayList<Integer> list) { //helper to fun2 method 
        int ans = 0, maxans = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                ans++;
            } else {
                maxans = Math.max(ans, maxans);
                ans = 0;
            }
        }
        maxans = Math.max(ans, maxans);
        return maxans;
    }

    public static int fun2(ArrayList<Integer> list) {  // maximun no. of right rotations in order to obtain the largest sequence of even nums.
        int ans = countEvens(list), res = 0;
        for (int i = 0; i < list.size(); i++) {
            rightRotate(list);
            if (countEvens(list) > ans) {
                ans = countEvens(list);
                res = i + 1;
            }
        }
        return res;
    }

    public static <E> ArrayList<E> reverse(ArrayList<E> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            //swap(a[i] , a[n-1-i] )
            E temp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set(list.size() - 1 - i, temp);
        }
        return list;
    }

    public static <E> ArrayList<E> removeDup(ArrayList<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    j--;
                }

            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(4);
        list.add(8);
        System.out.println(list);
        System.out.println(removeDup(list));

    }

}
