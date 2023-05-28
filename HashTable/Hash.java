package Hash;

import java.util.*;

public class Hash<K, V> {

    class HashElement<K, V> implements Comparable<HashElement<K, V>> {

        K key;
        V value;

        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(HashElement<K, V> o) {
            return ((Comparable<K>) this.key).compareTo(o.key);
        }

        @Override
        public String toString() {
            return "(" + key.toString() + " , " + value.toString() + ")";
        }
    }
    private final double maxLoadFactor = 0.75;
    private double loadFactor;
    private int tablesize; // num of linked lists
    private int numElements;
    private LinkedList<HashElement<K, V>>[] hashTable;

    public Hash(int tablesize) {
        this.tablesize = tablesize;
        numElements = 0;
        hashTable = (LinkedList<HashElement<K, V>>[]) new LinkedList[tablesize];
        for (int i = 0; i < tablesize; i++) {
            hashTable[i] = new LinkedList<HashElement<K, V>>();
        }

    }

    public double loadFactor() {
        return (double)numElements / tablesize;
    }

    public int hcode(K key) { //index
        int x = key.hashCode();
        x = x & 0x7FFFFFFF;   // make it positive
        x = x % tablesize;    // make it in the table bounds
        return x;
    }

    public void resize() {
        int newSize = tablesize * 2;
        hashTable = Arrays.copyOf(hashTable, newSize);
        tablesize = newSize;
        rehash();
    }

    public void rehash() {
        LinkedList<HashElement<K, V>>[] newHashTable = (LinkedList<HashElement<K, V>>[]) new LinkedList[tablesize];
        for (int i = 0; i < tablesize; i++) {
            newHashTable[i] = new LinkedList<HashElement<K, V>>();
        }
        for (LinkedList<HashElement<K, V>> list : hashTable) {
            for (HashElement<K, V> el : list) {
                int newIndex = hcode(el.key);
                newHashTable[newIndex].add(el);
            }
        }
        hashTable = newHashTable;
    }

    public void add(K key, V val) {
        if (loadFactor() > maxLoadFactor || numElements > tablesize-1) {
            resize();
        }
        if (getValue(key) != null) {
            remove(key);
            numElements--;
        }
        int index = hcode(key);
        HashElement<K, V> temp = new HashElement(key, val);
        hashTable[index].add(temp);
        numElements++;
    }

    public HashElement<K, V> remove(K key) {
        int index = hcode(key);
        if (getValue(key) == null) {
            throw new RuntimeException();
        }
        HashElement<K, V> temp = new HashElement<>(key, getValue(key));  // to be returned
        for (HashElement<K, V> el : hashTable[index]) {
            if (((Comparable<K>) key).compareTo(el.key) == 0) {
                hashTable[index].remove(el);
                break;
            }

        }
        return temp;
    }
    public int size() {
        return numElements;
    }

    public V getValue(K key) {
        int index = hcode(key);
        for (HashElement<K, V> el : hashTable[index]) {
            if (((Comparable<K>) key).compareTo(el.key) == 0) {
                return el.value;
            }
        }
        return null;
    }

}
