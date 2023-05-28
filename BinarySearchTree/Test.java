package BST;

public class Test {

    public static void main(String[] args) {
        BST tree = new BST();
        tree.add(10);
        tree.add(15);
        tree.add(222);
        tree.add(1123);
        tree.add(121);
        tree.add(21122);
        tree.add(811);
        tree.add(32);
        tree.add(-22124);
        tree.add(-111);
        tree.printKPathUtil(247);
    }

}
