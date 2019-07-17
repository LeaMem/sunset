package com.lea.structure.sort;

public abstract class Example {

    public abstract void sort(Comparable[] a);

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected void show(Comparable[] a) {
        //单行打印数组
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    protected boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    public void test() {
        Integer[] a = new Integer[10];

//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            a[i] = random.nextInt(100);
//        }
        a[0] = 6;
        a[1] = 5;
        a[2] = 7;
        a[3] = 9;
        a[4] = 0;
        a[5] = 1;
        a[6] = 4;
        a[7] = 3;
        a[8] = 2;
        a[9] = 8;

        sort(a);

        System.out.println("isSorted: " + isSorted(a));

        show(a);

    }

}
