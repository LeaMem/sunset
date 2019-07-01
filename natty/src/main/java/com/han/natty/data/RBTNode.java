package com.han.natty.data;

public class RBTNode<T extends Comparable<T>> {

    boolean color; //颜色
    T key;

    RBTNode<T> left;

    RBTNode<T> right;

    RBTNode<T> parent;

    public RBTNode(boolean color, T key, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
        this.color = color;
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public T getKey() {
        return key;
    }

    public String toString() {
        return ""+key+(this.color?"(B)":"R");
    }
}
