
package Deque;

public class Test {
    
    public static void main(String[] args) {
       Deque <Integer> de = new DoublyLinkedList<>();
       de.addLast(12);
       de.addLast(12);
       System.out.println(de.removeLast() + de.removeFirst());
    }
}
