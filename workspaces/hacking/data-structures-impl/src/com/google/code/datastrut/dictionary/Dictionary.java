package com.google.code.datastrut.dictionary;

import com.google.code.datastrut.Navigable;
import com.google.code.datastrut.Sizable;

/**
 * Pages 74-75.
 * The Dictionary is a data structure to map a key to a value. It can be constructed with contiguous data structures
 * (arrays) in the following complexity:
 * <p>
 * <table border=1>
 *  <tr><td>Dictionary operation</td><td>Unsorted Array</td><td>Sorted Array</td></tr>
 *  <tr><td>Search</td><td>O(n)</td><td>O(log n) binary search</td></tr>
 *  <tr><td>Insert</td><td>O(1) end of array</td><td>O(n)</td></tr>
 *  <tr><td>Delete</td><td>O(1) replacing with the last one</td><td>O(n)</td></tr>
 *  <tr><td>Successor</td><td>O(n)</td><td>O(1)</td></tr>
 *  <tr><td>Predecessor</td><td>O(n)</td><td>O(1)</td></tr>
 *  <tr><td>Minimum</td><td>O(n)</td><td>O(1)</td></tr>
 *  <tr><td>Maximum</td><td>O(n)</td><td>O(1)</td></tr>
 * </table>
 * 
 * Dictionaries can also be implemented using linked data structures.
 * <p>
 * 
 * <table border=1>
 *  <tr><td>Dictionary operation</td><td>A Singly-linked unsorted list</td><td>A doubly-linked unsorted list</td>
 *      <td>A singly-linked sorted list.</td><td>A doubly-linked sorted list</td></tr>
 *  <tr><td>Search</td><td>O(n)</td><td>O(n)</td><td>O(n)</td><td>O(n)</td></tr>
 *  <tr><td>Insert</td><td>O(1) at head</td><td>O(1) at head</td><td>O(n)</td><td>O(n)</td></tr>
 *  <tr><td>Delete</td><td>O(n) delete first</td><td>O(1)</td><td>O(n)*</td><td>O(1)</td></tr>
 *  <tr><td>Successor</td><td>O(n)</td><td>O(n)</td><td>O(1)</td><td>O(1)</td></tr>
 *  <tr><td>Predecessor</td><td>O(n)</td><td>O(n)</td><td>O(n) keep looking</td><td>O(1)</td></tr>
 *  <tr><td>Minimum</td><td>O(n)</td><td>O(n)</td><td>O(1)</td><td>O(1)</td></tr>
 *  <tr><td>Maximum</td><td>O(n)</td><td>O(n)</td><td>O(1) keep pointer</td><td>O(1)</td></tr>
 * </table>
 * 
 * <p>Apr 8, 2012 11:20:12 AM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public interface Dictionary<Key, Value> extends Sizable<Key>, Navigable<Dictionary<Key, Value>> {

  /**
   * Insert the value into the dictionary based on the key.
   * @param k
   * @param v
   * Apr 8, 2012 10:57:11 AM
   */
  public void put(Key key, Value value);

  /**
   * Searches for the value given key.
   * @param k
   * @return
   * Apr 8, 2012 10:58:05 AM
   */
  public DictionaryEntry<Key, Value> get(Key k);

  /**
   * This method deletes a value from the dictionary.
   * <p>Apr 8, 2012 11:14:09 AM</p> 
   * @param v is the value.
   * @return If the value was removed or not.
   */
  public boolean remove(DictionaryEntry<Key, Value> v);

  /**
   * <p>Apr 8, 2012 11:15:02 AM</p> 
   * @return The item with the largest key from the dictionary.
   */
  public DictionaryEntry<Key, Value> max();

  /**
   * <p>Apr 8, 2012 11:15:35 AM</p> 
   * @return The item with the smallest key from the dictionary.
   */
  public DictionaryEntry<Key, Value> min();

  /**
   * <p>Apr 8, 2012 11:18:41 AM</p> 
   * @return the predecessor of a given value. Must use the next of the reverse iterable.
   */
  public DictionaryEntry<Key, Value> predecessor();

  /**
   * 
   * <p>Apr 8, 2012 11:19:52 AM</p> 
   * @return the successor of a given value. Must use the next of the iterable.
   */
  public DictionaryEntry<Key, Value> successor();

}
