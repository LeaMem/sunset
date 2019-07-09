package com.han.natty.structure.sort;

public class Insertion extends Example{


    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for(int i = 1; i < N; i++){
            //将 a[i] 插入到 a[i - 1], a[i - 2], a[i - 3]...中
            for(int j = i; j > 0 && less(a[j], a[j - 1]); j--){
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Example example = new Insertion();
        example.test();
    }
}
