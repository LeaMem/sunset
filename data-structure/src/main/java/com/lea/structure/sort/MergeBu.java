package com.lea.structure.sort;

public class MergeBu extends Merge {

//    @Override
//    public void sort(Comparable[] a) {
//
//        int N = a.length;
//
//        //进行 lgN 次两两归并
//        Comparable[] aux = new Comparable[a.length];
//
//        // sz 子数组大小, 1, 2, 4, 8 ...递增
//        // logN  排序几次
//        for (int sz = 1; sz < N; sz += sz) {
//            //lo: 子数组索引
//            for (int lo = 0; lo < N - sz; lo += sz + sz) {
//                int mid = lo + sz - 1;
//                int tmp = lo + sz + sz - 1;
//                int end = Math.min(tmp, N - 1);
//                merge(a, aux, lo, mid, end);
//            }
//        }
//
//    }

    @Override
    public void sort(Comparable[] a) {

        int N = a.length;

        Comparable[] aux = new Comparable[N];

        // logN 次
        for(int sz = 1; sz < N; sz *= 2){

            for(int start = 0; start < N - sz; start += sz + sz){
                int mid = start + sz - 1;
                int end = start + sz + sz - 1;
                end = Math.min(end, N - 1);
                merge(a, aux, start, mid, end);

            }

        }

    }


    public static void main(String[] args) {
        Example example = new MergeBu();
        example.test();
    }
}
