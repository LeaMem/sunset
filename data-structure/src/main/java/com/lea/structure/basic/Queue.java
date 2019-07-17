package com.lea.structure.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<Item> implements Iterable<Item> {

    //beginning of queue
    private Node<Item> first;

    //end of queue
    private Node<Item> last;

    //number of elements on queue
    private int n;

    public Queue(){
        first = null;
        last = null;
        n = 0;
    }

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    /**
     *      return the item least recently added to this queue.
     * @return
     */
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue underflow");
        }
        return first.item;
    }

    /**
     *      尾插
     *      Adds the item to this queue.
     * @param item
     */
    public void enqueue(Item item){
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;

        if(isEmpty()){
            first = last;
        }else{
            oldlast.next = last;
        }

        n++;
    }

    /**
     *      Removes and returns the item on this queue that was least recently added.
     * @return
     */
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty()){
            //to avoid loitering
            last = null;
        }
        return item;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Item item : this){
            builder.append(item);
            builder.append(" ");
        }
        return builder.toString();
    }



    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }


    private class ListIterator implements Iterator<Item>{

        private Node<Item> current;

        public ListIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()){
                throw new UnsupportedOperationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        try{
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                queue.enqueue(scanner.next());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("queue size is " + queue.size());
        System.out.println(queue.toString());
    }
}
