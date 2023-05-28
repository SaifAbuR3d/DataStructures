package BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class BST<E> {

    class Node<E> implements Comparable<E> {

        Node left, right;
        E data;

        public Node(E val) {
            this.data = val;
        }

        public Node() {
        }

        @Override
        public int compareTo(E other) {
            return ((Comparable<E>) this.data).compareTo(other);
        }

    }
    private Node root;
    private int size;

    public void recAdd(E val) {
        if (root == null) {
            root = new Node<>(val);
        } else {
            recAdd(val, root);
        }
        size++;
    }

    private <E> void recAdd(E val, Node<E> current) {
        if (((Comparable<E>) current.data).compareTo(val) < 0) {  // current.data < val
            if (current.right == null) { // current node is a leaf
                current.right = new Node(val);
            } else { // go right
                recAdd(val, current.right);
            }
        } else {  // current.data > val
            if (current.left == null) { // // current node is a leaf
                current.left = new Node(val);
            } else { // go left
                recAdd(val, current.left);
            }

        }
    }

    public void add(E val) {
        if (root == null) {
            root = new Node(val);
            return;
        }

        // first determine the parent 
        Node parent = root;
        Node current = root;
        while (current != null) {
            if (((Comparable<E>) current.data).compareTo(val) > 0) {// current.data > val
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }

        // connect the new child with his parent
        if (((Comparable<E>) parent.data).compareTo(val) > 0) {
            parent.left = new Node(val);
        } else {
            parent.right = new Node(val);
        }
        size++;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println("");
    }

    private void inOrder(Node<E> root) {
        //left
        //root
        //right
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public void postOrder() {
        postOrder(root);
        System.out.println("");
    }

    private void postOrder(Node<E> root) {
        //left
        //right
        //rott
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public void preOrder() {
        preOrder(root);
        System.out.println("");
    }

    private void preOrder(Node<E> root) {
        //root
        //left
        //right
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void delete(E val) {

    }

    public boolean search(E val) {
        return search(val, root);
    }

    private boolean search(E val, Node root) {
        if (root == null) {
            return false;
        }
        if (((Comparable<E>) root.data).compareTo(val) > 0) {
            return search(val, root.left);
        } else if (((Comparable<E>) root.data).compareTo(val) < 0) {
            return search(val, root.right);
        } else {
            return true;
        }
    }

    public E maxVal() {
        if (root == null) {
            return null;
        }
        return maxVal(root);
    }

    private E maxVal(Node root) {
        if (root.right == null) {
            return (E) root.data;
        }
        return maxVal(root.right);
    }

    public E minVal() {
        if (root == null) {
            return null;
        }
        return minVal(root);
    }

    private E minVal(Node root) {
        if (root.left == null) {
            return (E) root.data;
        }
        return minVal(root.left);
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public void printLeaves() {
        printLeaves(root);
        System.out.println();
    }

    private void printLeaves(Node root) {
        if (isLeaf(root)) {
            System.out.print(root.data + " ");
            return;
        }
        if (root == null) {
            return;
        }
        printLeaves(root.left);
        printLeaves(root.right);
    }

    private boolean isLeaf(Node tar) {
        if (tar == null) {
            return false;
        }
        return tar.left == null && tar.right == null;
    }

    public void printLevels() { // BFS    O(n)
        printLevels(root);
    }

    private void printLevels(Node root) { // BFS 
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.remove();
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
            System.out.print(temp.data + " ");
        }
    }

    public void printLevelBylevel() {        // O(n^2)
        printLevelBylevel(root);
    }

    private void printLevelBylevel(Node root) {
        int height = height();
        for (int level = 1; level <= height; level++) {
            printCurrentLevel(level);
            System.out.println("");
        }
    }

    public void printCurrentLevel(int level) {
        printCurrentLevel(1, level, root);
    }

    private void printCurrentLevel(int i, int level, Node root) {
        if (root == null) {
            return;
        }
        if (i == level) {
            System.out.print(root.data + " ");
            return;
        } else {
            printCurrentLevel(i + 1, level, root.right);
            printCurrentLevel(i + 1, level, root.left);
        }
    }

    private boolean isParent(Node tar) {
        if (tar == null) {
            return false;
        }
        return tar.left != null || tar.right != null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int sumLeaves() {
        return sumLeaves(root);
    }

    private int sumLeaves(Node root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return (int) root.data;
        }
        return sumLeaves(root.left) + sumLeaves(root.right);
    }

    public boolean hasPathSum(int target) {
        return hasPathSum(target, 0, root);
    }

    private boolean hasPathSum(int target, int sum, Node root) {
        if (root == null) {
            return target == sum;
        }
        if ((sum + (int) root.data) == target && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(target, sum + (int) root.data, root.left)
                || hasPathSum(target, sum + (int) root.data, root.right);
    }

    public int sumOfLeftLeaves() {
        return sumOfLeftLeaves(root, false);
    }

    private int sumOfLeftLeaves(Node root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null && isLeft) {
            return (int) root.data;
        }
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }

    public List<Integer> getSortedList() {
        List<Integer> list = new ArrayList<>();
        return getSortedList(root, list);
    }

    private List<Integer> getSortedList(Node root, List<Integer> list) {
        if (root != null) {
            getSortedList(root.left, list);
            list.add((int) root.data);
            getSortedList(root.right, list);
        }
        return list;
    }

    public boolean isSameTree(BST tree2) {
        return isSameTree(root, tree2.root);
    }

    private boolean isSameTree(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.data != root2.data) {
            return false;
        }
        return isSameTree(root1.right, root2.right) && isSameTree(root1.left, root2.left);
    }

    public int sumInRange(int low, int high) {
        return sumInRange(root, low, high);
    }

    private int sumInRange(Node root, int low, int high) {
        int sum = 0;
        if (root == null) {
            return 0;
        } else if ((int) root.data >= low && (int) root.data <= high) {
            sum += (int) root.data;
        }
        if ((int) root.data > low) {
            sum += sumInRange(root.left, low, high);        // these two conditions are to avoid some   
        }                                                    //unnecessary calls. work just for BST
        if ((int) root.data < high) {
            sum += sumInRange(root.right, low, high);
        }
        return sum;
    }

    public int iterativeInOrder(int k) {    // return kth smallest element
        if (root == null) {
            return -1;
        }
        return iterativeInOrder(root, k);
    }

    private int iterativeInOrder(Node root, int k) {
        Stack<Node> st = new Stack<>();
        while (root != null || !st.empty()) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            if (--k == 0) {// for different type of problems change these condition (operation)
                break;
            }
            root = root.right;
        }
        return (int) root.data;
    }

    public String pathToV(int v) {
        if (!search((E) (Integer) v)) {
            return "does not exist";
        }
        if (root == null) {
            return "";
        }
        return pathToV(root, v);
    }

    private String pathToV(Node root, int v) {  // BST just
        if ((int) root.data == v) {
            return root.data + "";
        }
        if ((int) root.data < v) {
            return root.data + "->" + pathToV(root.right, v);
        } else {
            return root.data + "->" + pathToV(root.left, v);
        }
    }

    public int inOrderSuccessor(int k) { // smallest element larger than k
        return inOrderSuccessor(root, k);
    }

    private int inOrderSuccessor(Node root, int k) {
        Node pre = null;
        Node cur = root;
        while (cur != null) {
            if ((int) cur.data > k) {
                pre = cur;
                cur = cur.left;
            } else if ((int) cur.data < k) {
                pre = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        if (cur == null) {
            return Integer.MAX_VALUE;  // there is no k       
        }
        if (cur.right == null) {
            if (pre == null || pre.right == cur) { // k is the largest
                return Integer.MAX_VALUE;  // there is no element larger than k       
            }
            if (pre.left == cur) {
                return (int) pre.data;
            }
        }
        cur = cur.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        return (int) cur.data;
    }

//it even works for elements  wich does not exist in the tree
// to restrict it to existed elements , search for the element first
    public int inOrderSuccessor2(int k) { // smallest element larger than k
        Node ret = new Node(Integer.MAX_VALUE);            // a little trick to pass it by reference
        inOrderSuccessor2(root, k, ret);
        return (int) ret.data;
    }

    private void inOrderSuccessor2(Node root, int k, Node ret) { // inOrder traversal;
        if (root != null) {
            inOrderSuccessor2(root.left, k, ret);
            if ((int) root.data > k && (int) ret.data == Integer.MAX_VALUE) {// we want just the first element greater than k
                ret.data = (int) root.data;
                return;
            }
            inOrderSuccessor2(root.right, k, ret);
        }
    }

    public int findPredecessor(int k) {      // here return int data to test the program
        return (int) findPredecessor(root, null, k).data;
    }

    private Node findPredecessor(Node root, Node preDec, int key) {
        // the largest in its left subtree
        // if the left subtree is null , then the preDec is its parent
        if (root == null) {
            return null;            // the tree is empty
        }

        if ((int) root.data > key) {
            return findPredecessor(root.left, preDec, key);
        } else if ((int) root.data < key) {
            preDec = root;
            return findPredecessor(root.right, preDec, key);
        } else {
            if (root.left == null) {
                return preDec;
            }
            Node cur = root.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur;
        }
    }

    public int findPredecessor2(int k) {      // here return int data to test the program
        // iterative version
        if (root == null) {
            return -1;
        }
        return (int) findPredecessor2(root, k).data;
    }

    private Node findPredecessor2(Node root, int key) {
        Node preDec = null;
        Node cur = root;
        while (cur != null) {
            if ((int) cur.data > key) {
                cur = cur.left;
            } else if ((int) cur.data < key) {
                preDec = cur;
                cur = cur.right;
            } else {
                break;
            }
        }
        // now we our target
        // find the largest in its left subtree (if there is any)
        // or return the current preDec
        if (cur == null) {  // in case of the key is not found
            return null;
        }
        if (cur.left == null) {
            return preDec;
        }
        cur = cur.left;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    public int maximumSumRootToLeaf() {    // may not be a BST
        Node MaxSumNode = new Node(Integer.MIN_VALUE); // a trick to pass by reference
        maximumSumRootToLeaf(root, MaxSumNode, 0);
        return (int) MaxSumNode.data;
    }

    private void maximumSumRootToLeaf(Node root, Node maxSumNode, int curSum) {  // pre order traversal
        if (root == null) {
            return;
        }
        curSum += (int) root.data;
        if (root.left == null && root.right == null && curSum > (int) maxSumNode.data) {
            maxSumNode.data = curSum;
        }
        maximumSumRootToLeaf(root.left, maxSumNode, curSum);
        maximumSumRootToLeaf(root.right, maxSumNode, curSum);
    }

    public int maximumSumRootToLeaf2() {    // may not be a BST
        return maximumSumRootToLeaf2(root);
    }

    private int maximumSumRootToLeaf2(Node root) {  // post order
        if (root == null) {
            return 0;
        }
        return (int) root.data + Math.max(maximumSumRootToLeaf2(root.left),
                maximumSumRootToLeaf2(root.right));
    }

    //If node x lies in the BST, then both floor and ceil are equal to that node;
    //otherwise, the ceil is equal to the next greater node (if any) in the BST,
    //and the floor is equal to the previous greater node (if any) in the BST.
    public int floor(int key) {
        if (root == null) {
            return -1;
        }
        return floor(root, key);
    }

    private int floor(Node root, int key) {
        int floor = -1;
        while (root != null) {
            if ((int) root.data == key) {
                return (int) root.data;
            } else if ((int) root.data > key) {
                root = root.left;
            } else if ((int) root.data < key) {
                floor = (int) root.data;
                root = root.right;
            }
        }
        return floor;
    }

    public int ceil(int key) {
        if (root == null) {
            return -1;
        }
        return ceil(root, key);
    }

    private int ceil(Node root, int key) {
        int ceil = -1;
        while (root != null) {
            if ((int) root.data == key) {
                return (int) root.data;
            } else if ((int) root.data > key) {
                ceil = (int) root.data;
                root = root.left;
            } else if ((int) root.data < key) {
                root = root.right;
            }
        }
        return ceil;
    }

    //Given a binary tree,
    //in-place replace each node’s value to the sum of all elements present in its left and right subtree.
    //You may assume the value of an empty child node to be 0.
    public void convertToSumTree() {
        convertToSumTree(root);
    }

    private void convertToSumTree(Node root) {
        if (root == null) {
            return;
        }
        root.data = sumOfNodes(root) - (int) root.data;
        convertToSumTree(root.right);
        convertToSumTree(root.left);
    }

    private int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return (int) root.data + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    public void printAnccestors(int target) {
        printAnccestors(root, target); // we send root.right.left just to test
    }

    private boolean printAnccestors(Node root, int target) {
        // can be optimized by : we don't need to recur for 
        // right subtree if we find the target in the left. So,
        //if(!left) {boolean right = printAncc....}

        //just like the problem below --->>>>
        if (root == null) {
            return false;
        }
        if ((int) root.data == target) {
            return true;
        }
        boolean left = printAnccestors(root.left, target);
        boolean right = printAnccestors(root.right, target);
        if (left || right) {
            System.out.println(root.data + " ");
        }
        return left || right;
    }

    public List<Integer> pathToTarget(int target) {   // for any type of tree
        List<Integer> list = new ArrayList<>();
        pathToTarget(root, target, list);
        return list;
    }

    private boolean pathToTarget(Node root, int target, List<Integer> list) {
        // optimized 
        if (root == null) {
            return false;
        }
        if ((int) root.data == target) {
            return true;
        }
        boolean left = pathToTarget(root.left, target, list);
        boolean right = false;
        if (!left) {
            right = pathToTarget(root.right, target, list);
        }
        if (left || right) {
            list.add((int) root.data);
        }
        return left || right;
    }

    private boolean pathToTarget2(Node root, int target, List<Integer> list) {
        if (root == null) {
            return false;
        }
        if ((int) root.data == target) {
            return true;
        }
        boolean left = pathToTarget(root.left, target, list);
        boolean right = pathToTarget(root.right, target, list);
        if (left || right) {
            list.add((int) root.data);
        }
        return left || right;
    }

    public void printKPathUtil(int k) {
        List<Integer> path = new ArrayList<>();
        printKPathUtil(root, k, path);
    }

    private void printKPathUtil(Node root, int k, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add((int) root.data);
        printKPathUtil(root.left, k, path);
        printKPathUtil(root.right, k, path);
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == k) {
                System.out.println(path.subList(i, path.size()));
            }
        }
        path.remove(path.size() - 1);
    }

    public void printDiagonals() {
        if (root != null) {
            List<String> diagonals = new LinkedList<>();
            printDiagonals(root, 0, diagonals);

            for (String diagonal : diagonals) {
                System.out.println(diagonal);
            }
        }
    }

    public void printDiagonals(Node root, int diagonal, List<String> diagonals) {
        if (root == null) {
            return;
        }
        if (diagonals.size() <= diagonal) {
            diagonals.add(root.data + "");
        } else {
            diagonals.set(diagonal, diagonals.get(diagonal) + " " + root.data);
        }
        printDiagonals(root.left, diagonal + 1, diagonals);
        printDiagonals(root.right, diagonal, diagonals);
    }
}

/*
class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    public BSTIterator(TreeNode root) {
        // fill the stack 
        TreeNode cur = root; 
        while(cur!=null) {
            st.push(cur);
            cur = cur.left;
        }
    }
    
    public int next() {
        TreeNode ret = st.pop();
        // fill the stack 
        TreeNode cur = ret.right; 
        while (cur!=null) {
            st.push(cur);
            cur = cur.left; 
        }
        return ret.val;
    }
    
    public boolean hasNext() {
        return !st.empty();
    }
}

 */
