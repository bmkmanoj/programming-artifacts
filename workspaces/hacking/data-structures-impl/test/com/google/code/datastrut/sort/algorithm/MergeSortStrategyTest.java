package com.google.code.datastrut.sort.algorithm;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.google.code.datastrut.sort.ComparatorFactory;

public class MergeSortStrategyTest {

    @Test
    public void testMergeSortedArrays() {
        // make 2 arrays with random elements
        Integer[] arrayA = new Integer[]{0, 2, 4, 8, 10};
        Integer[] arrayB = new Integer[]{1, 3, 5, 7, 9};

        Integer[] arAB = new Integer[10];
        // get a merged array with the sorted elements of both
        MergeSortStrategy.mergeSortedArrays(arrayA, arrayB, arAB, ComparatorFactory.makeIntegerComparator());

        // verify that the array was sorted as expected.
        for (int i = 1; i < arAB.length; i++) {
            assertTrue("The element at index " + i + " is greater than the previous", arAB[i] > arAB[i - 1]);
        }
    }

    @Test
    public void testMergeSortedArraysDifferentSizes() {
        // make 2 arrays with random elements
        Integer[] arrayA = new Integer[]{10};
        Integer[] arrayB = new Integer[]{8, 9, 12, 14};

        Integer[] arAB = new Integer[arrayA.length + arrayB.length];
        // get a merged array with the sorted elements of both
        MergeSortStrategy.mergeSortedArrays(arrayA, arrayB, arAB, ComparatorFactory.makeIntegerComparator());

        // verify that the array was sorted as expected.
        for (int i = 1; i < arAB.length; i++) {
            assertTrue("The element at index " + i + " is greater than the previous", arAB[i] > arAB[i - 1]);
        }
    }

    @Test
    public void testMergeSortedArraysDifferentSizesInitial() {
        // make 2 arrays with random elements
        Integer[] arrayA = new Integer[]{46};
        Integer[] arrayB = new Integer[]{12, 14};

        Integer[] arAB = new Integer[arrayA.length + arrayB.length];
        // get a merged array with the sorted elements of both
        MergeSortStrategy.mergeSortedArrays(arrayA, arrayB, arAB, ComparatorFactory.makeIntegerComparator());

        // verify that the array was sorted as expected.
        for (int i = 1; i < arAB.length; i++) {
            assertTrue("The element at index " + i + " is greater than the previous", arAB[i] > arAB[i - 1]);
        }
    }

    @Test
    public void testSort() {
        // generate a random list
        int numbers = 10;
        Random rand = new Random();
        Integer[] numbs = new Integer[numbers];
        for (int i = 0; i < numbers; i++) {
            numbs[i] = rand.nextInt(100);
        }

        // sort with numbers
        MergeSortStrategy.getInstance().sort(numbs, ComparatorFactory.makeIntegerComparator());

        // verify that the array was sorted as expected.
        for (int i = 1; i < numbs.length; i++) {
            assertTrue("The element at index " + i + " is greater than the previous", numbs[i - 1] <= numbs[i]);
        }
    }
}
