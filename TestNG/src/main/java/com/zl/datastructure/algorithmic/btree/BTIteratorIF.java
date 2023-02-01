package com.zl.datastructure.algorithmic.btree;



/**
 * Interface BTIteratorIF
 * @author tnguyen
 */
public interface BTIteratorIF <K extends Comparable, V> {
    public boolean item(K key, V value);
}
