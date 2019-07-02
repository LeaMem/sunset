package com.han.natty.structure;

public class BinarySearch {

    public static int rank(int key, int[] a) {

        //数组必须是有序的
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {

            //被查找的键要么不存在，要么必然存在于 a[lo..hi] 之中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

//    private Integer age;
//
//    public BinarySearch(Integer age) {
//        this.age = age;
//    }
//
//    public void change(BinarySearch d) {
//        d.age = 44;
//        d = new BinarySearch(3);
//    }
//
//    public static void main(String[] args) {
//        BinarySearch k = new BinarySearch(55);
//        k.change(k);
//        System.out.println(k.age);
//    }
}
