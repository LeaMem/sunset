package com.han.natty.structure.union;

public interface UF {

    /**
     *      联合
     * @param p
     * @param q
     */
    void union(int p, int q);

    /**
     *      查询分量标识符
     * @param p
     * @return
     */
    int find(int p);

    /**
     *      如果 p q 连接返回 true
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q);

    /**
     *      连通分量的数量
     * @return
     */
    int count();

}
