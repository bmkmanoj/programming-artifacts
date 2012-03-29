package com.google.code.datastrut.map;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * The HashMapTest is the test case for the HashMap implementation.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 29, 2012 7:52:54 AM 
 *
 */
public class HashMapTest {

  @Test
  public void testInitialState() {
    Map<Integer, String> usersByAge = new HashMap<Integer, String>();
    assertTrue("The number of elements of an initial instance must be zero.", usersByAge.size() == 0);
    assertTrue("The map must be empty upon its creation.", usersByAge.isEmpty());
  }

  @Test
  public void testPut() {
    Map<Integer, String> usersByAge = new HashMap<Integer, String>();
    usersByAge.put(32, "Marcello");
    assertTrue(usersByAge.size() == 1);
    assertFalse(usersByAge.isEmpty());
    assertNotNull("The get method is the wrong value for a given key.", usersByAge.get(32));
    assertSame("The get method is the wrong value for a given key.", "Marcello", usersByAge.get(32));

    usersByAge.put(30, "Leandro");
    assertTrue(usersByAge.size() == 2);
    assertFalse(usersByAge.isEmpty());
    assertNotNull("The get method is the wrong value for a given key.", usersByAge.get(30));
    assertSame("The get method is the wrong value for a given key.", "Leandro", usersByAge.get(30));
  }

}
