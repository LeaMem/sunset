package com.han.natty.structure.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    // top of stack
    private Node<Item> first;

    // size of the stack
    private int n;

    public Stack() {
        first = null;
        n = 0;
    }

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    /**
     *
     *      头插
     *      Adds the item to this stack.
     * @param item
     */
    public void push(Item item){
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     *      Removes and returns the item most recently added to this stack.
     * @return
     */
    public Item pop(){
        if(isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     *      Returns (but does not remove) the item most recently added to this stack.
     * @return
     */
    public Item peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
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
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
