package com.han.natty.nio.filter;

import java.util.ArrayList;

public class SyncTest extends SyncTestFather {

    @Override
    public synchronized void doSomething() {
        System.out.println("dnig");
        super.doSomething();
    }

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        syncTest.doSomething();

    }
}
