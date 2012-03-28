package com.google.code.datastrut.sort;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

/**
 * The AbstractSortStrategyTest is the test case.
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * Mar 27, 2012 8:41:22 PM 
 *
 */
public class AbstractSortStrategyTest {

    @Test
    public void testingGetSubArray() {
        // make an array with random elements
        int numbers = 10;
        Random rand = new Random();
        Integer[] arrayIntegers = new Integer[numbers];
        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }

        // Get a sub array of the array
        Integer[] subArray = AbstractSortStrategy.getSubArray(arrayIntegers, 0, 3);

        // verify the elements of the sub array
        for (int i = 0; i < subArray.length; i++) {
            assertEquals("The sub array element is incorrect at index " + i, arrayIntegers[i], subArray[i]);
        }

        int start = 4;
        int end = 9;
        // Get a sub array of the array
        Integer[] subArray2 = AbstractSortStrategy.getSubArray(arrayIntegers, start, end);

        // verify the elements of the sub array
        for (int i = 0; i < subArray2.length; i++) {
            assertEquals("The sub array element is incorrect at index " + i, arrayIntegers[i + start], subArray2[i]);
        }
    }

}
