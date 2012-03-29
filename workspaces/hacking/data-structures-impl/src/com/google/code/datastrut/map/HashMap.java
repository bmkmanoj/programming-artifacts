package com.google.code.datastrut.map;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Iterator;

import com.google.code.datastrut.list.List;
import com.google.code.datastrut.list.SinglyLinkedList;

/**
 * 
 * The HashMap is the implementation of an association of a key to a value.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 29, 2012 7:43:41 AM 
 *
 * @param <Key>
 * @param <Value>
 */
public class HashMap<Key, Value> implements Map<Key, Value> {

    /**
     * The bucket with elements in the form of [bucketIndex]->[entries]
     */
    private List<MapEntry<Key, Value>>[] bucket;
    /**
     * The current size of entries.
     */
    private int numberOfEntries;

    private static Class<?> bucketComponentType;

    static {
      for (Field field : HashMap.class.getDeclaredFields()) {
        if (field.getName().equals("bucket")) {
          bucketComponentType = field.getType().getComponentType();
        }
      }
    }
 
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        bucket = (List<MapEntry<Key, Value>>[])Array.newInstance(bucketComponentType, initialCapacity);
    }

    public HashMap() {
        this(34);
    }

    /**
     * @param key is the key instance.
     * @return the index position in the bucket based on the key's hashCode value.
     * Mar 29, 2012 6:51:42 AM
     */
    private int hashFunction(Key key) {
        return key.hashCode() % bucket.length;
    }

    /**
     * @return the table's load factor, the ratio n/s between n and the size s of its array of buckets.
     * Mar 29, 2012 6:51:19 AM
     */
    private float getCurrentLoadFactor() {
        return this.numberOfEntries / bucket.length;
    }

    /**
     * With a good hash function, the average lookup cost is nearly constant as the load factor increases from 0 up to 
     * 0.7(about 2/3 full) or so. Beyond that point, the probability of collisions and the cost of handling them 
     * increases.
     * @return Whether the current fill factor has been reached.
     * Mar 29, 2012 6:50:34 AM
     */
    private boolean hasTheFillFactorBeenReached() {
        return this.getCurrentLoadFactor() >= 0.7;
    }

    @Override
    public void put(Key key, Value value) {
        if (!hasTheFillFactorBeenReached()) {
            int bucketIndex = hashFunction(key);
            if (bucket[bucketIndex] == null) {
                bucket[bucketIndex] = new SinglyLinkedList<MapEntry<Key,Value>>();
            }
            bucket[bucketIndex].add(MapEntry.makeNew(key, value));
        }
        this.numberOfEntries++;
    }

    @Override
    public Value get(Key key) {
        int bucketIndex = hashFunction(key);
        if (bucket[bucketIndex] == null) {
          return null;
        }
        for (MapEntry<Key, Value> entry : bucket[bucketIndex]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void remove(Key key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Key> keyList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Value> valueList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<MapEntry<Key, Value>> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
      return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

}
