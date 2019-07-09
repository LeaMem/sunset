package com.han.natty.structure.sort;

public class Shell extends Example {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;

        //寻找增量
        while (h < N / 3) {
            //1, 4, 13, 40, 121, 364, 1093
            h = 3 * h + 1;
        }

        while (h >= 1) {

            //将数组变成h有序
            for (int i = h; i < N; i++) {
                //将 a[i] 插入到 a[i - h], a[i - 2*h], a[i - 3 * h] 中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }

            h /= 3;

        }

    }

    public static void main(String[] args) {
        Example example = new Shell();
        example.test();
    }
}
