package com.han.natty.structure.sort;

public class Quick extends Example {

    @Override
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);

        sort(a, lo, j -1);
        sort(a, j + 1, hi);

    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {

            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while(less(v, a[--j])){
                if(j == lo){
                    break;
                }
            }

            if(i >= j){
                break;
            }

            exch(a, i, j);

        }

        exch(a, lo, j);

        return j;
    }

    public static void main(String[] args) {
        String[] a = {"K", "R", "A", "T", "E", "L",
        "E", "P", "U", "I", "M", "Q", "C", "X", "O", "S"};
        Example example = new Quick();
        example.sort(a);
        example.test();
    }
}
