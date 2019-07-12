package com.han.natty.structure.sort.heap;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {

    // store items at indices 1 to n
    private Key[] pq;

    // number of items ont priority queue
    private int n;

    // optional comparator
    private Comparator<Key> comparator;

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }


    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MaxPQ(Key[] keys){
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for(int i = 0; i < n; i++){
            pq[i + 1] = keys[i];
        }

        for(int k = n / 2; k >= 1; k--){
            sink(k);
        }
    }


    public void insert(Key x){
        if(n == pq.length - 1){
            resize(2 * pq.length);
        }
        pq[++n] = x;
        swim(n);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }


    private void swim(int k) {
        while (k > 1 && less(k / 2, k)){
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k){

        while(2  * k <= n){
            int j = 2 * k ;
            if(j < n && less(j, j + 1)){
                j++;
            }
            if(!less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {

        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

}
