/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deque;

public interface Deque <E>{    // add/remove first/last in O(1)
    public int size();
    public void addFirst(E e);
    public void addLast(E e);
    public E removeLast();
    public E removeFirst();
    @Override
    public String toString();
}
