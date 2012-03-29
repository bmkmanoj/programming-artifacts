package com.google.code.datastrut.map;

import com.google.common.base.Preconditions;

/**
 * 
 * The MapEntry is the entry of key and value.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 29, 2012 7:36:38 AM 
 *
 * @param <Key> is the key.
 * @param <Value> is the associated value.
 */
public class MapEntry<Key, Value> {

    /**
     * The key of the map.
     */
    public final Key key;
    /**
     * The associated value.
     */
    public final Value value;

    protected MapEntry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Factory method.
     * @param key is the key.
     * @param value is the value.
     * @return
     * Mar 29, 2012 7:37:19 AM
     */
    public static <Key, Value> MapEntry<Key, Value> makeNew(Key key, Value value) {
        Preconditions.checkArgument(key != null, "The key must be not null.");
        Preconditions.checkArgument(value != null, "The value must be not null.");

        return new MapEntry<Key, Value>(key, value);
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
}
