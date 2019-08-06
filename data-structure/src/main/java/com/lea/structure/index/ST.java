package com.lea.structure.index;

public interface ST<Key extends Comparable<Key>, Value>{

    /**
     *      将键值存入表中，若值为空则将键 key 删除
     * @param key
     * @param val
     */
    void put(Key key, Value val);

    /**
     *  获取 Key 对应的值 （key不存在返回空）
     * @param key
     * @return
     */
    Value get(Key key);

    /**
     *      冲表中删除键 key (及其对应的值)
     * @param key
     */
    void delete(Key key);

    /**
     *      键 key 是否存在于表中
     * @param key
     * @return
     */
    boolean contains(Key key);

    /**
     *      表是否为空
     * @return
     */
    boolean isEmpty();

    int size();

    Key min();

    Key max();

    /**
     *      小于等于 key 的最大键
     * @param key
     * @return
     */
    Key floor(Key key);


    /**
     *      大于等于 key 的最小键
     * @param key
     * @return
     */
    Key ceiling(Key key);

    /**
     *      小于 key 的键的数量
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     *      排名为 k 的键
     * @param k
     * @return
     */
    Key select(int k);

    void deleteMin();

    void deleteMax();

    /**
     *      [lo .. hi] 之间的键的数量
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi);

    /**
     *      [lo .. hi] 之间的所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    Iterable<Key> keys(Key lo, Key hi);

    /**
     *      表中的所有键的集合，已排序
     * @return
     */
    Iterable<Key> keys();
}