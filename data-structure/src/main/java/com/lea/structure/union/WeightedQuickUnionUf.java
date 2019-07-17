package com.lea.structure.union;

/**
 * 加权的 QuickFind
 */
public class WeightedQuickUnionUf implements UF {

    /**
     * 分量id (以触点作为索引)
     */
    private int id[];


    /**
     * (由触点索引的) 各个节点所对应的分量大小
     */
    private int[] sz;


    /**
     * 分量数量
     */
    private int count;


    public WeightedQuickUnionUf(int n) {
        this.count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if(i == j){
            return;
        }
        //将小数作为大数的子树
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
