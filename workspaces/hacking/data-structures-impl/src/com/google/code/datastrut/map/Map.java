package com.google.code.datastrut.map;

import com.google.code.datastrut.Sizable;
import com.google.code.datastrut.list.List;

/**
 * 
 * The Map is the associative array data structure mapping a key to a value.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 29, 2012 4:00:43 AM 
 *
 * @param <Key> is the key. Must implement a method called hashCode() and must be Non-null.
 * @param <Value> is the valye. 
 */
public interface Map<Key, Value> extends Sizable<MapEntry<Key, Value>>, Iterable<MapEntry<Key, Value>> {

    public Value get(Key key);
    public void put(Key key, Value value);
    public void remove(Key key);
    public List<Key> keyList();
    public List<Value> valueList();
}
