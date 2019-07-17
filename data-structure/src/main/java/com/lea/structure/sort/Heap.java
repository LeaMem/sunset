package com.lea.structure.sort;

public class Heap extends Example {


    /**
     *      这里有点bug
     *      demo中是从1开始递增的，我这里是从0开始的
     *      会有数组越界报错
     *      但是思路是这样就可以了
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }

        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private void sink(Comparable[] a, int k, int n) {

        while (2 * k < n) {
            int j = 2 * k;
            if (j < n && less(a[j], a[j + 1])) {
                j++;
            }
            if (!less(a[k], a[j])) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        Example example = new Heap();
        example.test();
    }
}
