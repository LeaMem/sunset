package com.han.natty.nio.filter;

public class SyncTestFather {

    public synchronized void doSomething(){
        System.out.println("father");
    }
}
