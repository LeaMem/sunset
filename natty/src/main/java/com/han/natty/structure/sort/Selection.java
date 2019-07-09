package com.han.natty.structure.sort;

public class Selection extends Example {

    @Override
    public void sort(Comparable[] a) {
        //将 a[] 升序排序
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //将a[i] 和 a[i+1..N] 中最小的元素交换
            int min = i;
            for(int j = i + 1; j < N; j++){
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);

        }
    }

    public static void main(String[] args) {
        Example example = new Selection();
        example.test();
    }
}
