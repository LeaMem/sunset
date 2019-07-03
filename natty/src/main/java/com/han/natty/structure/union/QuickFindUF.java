package com.han.natty.structure.union;

public class QuickFindUF implements UF {

    // id[i] = component identifier of i
    private int[] id;

    // number of components
    private int count;

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 " + (n - 1));
        }
    }


    @Override
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pId = id[p];
        int qId = id[q];

        if(pId == qId){
            return ;
        }

        for(int i = 0; i < id.length; i++){
            if(id[i] == pId){
                id[i] = pId;
            }
        }

        count--;
    }

    @Override
    public int find(int p) {
        validate(p);
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    @Override
    public int count() {
        return count;
    }
}
