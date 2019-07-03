package com.han.natty.structure.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int n;   //number of elements in bag;

    //helper linked list class
    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    @Override
    public void forEach(Consumer<? super Item> action) {

    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    /**
     *      头插
     * @param item
     */
    public void add(Item item){
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }


    /**
     * Return an iterator that iterates over the item in this bag in arbitrary order.
     *
     */
    private class ListIterator implements Iterator<Item>{

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            this.current = first;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        try{

            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                bag.add(scanner.next());
            }

        }catch (Exception e){
            System.out.println(e.getCause());
        }

        System.out.println("size of bag = " + bag.size());
        for(String s : bag){
            System.out.println(s);
        }
    }
}
