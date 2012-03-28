package com.google.code.datastrut.sort.algorithm;

import com.google.code.datastrut.sort.AbstractSortStrategy;
import com.google.code.datastrut.sort.Comparator;
import com.google.common.base.Preconditions;

/**
 * Sorts the given arrayList with the given comparator.
 * 
 * The description of the algorithm can be seen at http://www.youtube.com/watch?v=eTdeeofZzDQ and 
 * http://www.youtube.com/watch?v=XaqR3G_NVoo (very creative German video!!! worth watching)
 *
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 */
public final class MergeSortStrategy extends AbstractSortStrategy {

    public static final MergeSortStrategy singleton = new MergeSortStrategy();

    private MergeSortStrategy() {

    }

    /**
     * @return The singleton instance of this sort strategy.
     */
    public static MergeSortStrategy getInstance() {
        return singleton;
    }

    /**
     * Sorts the given arrayList with the given comparator.
     * The description of the algorithm can be seen a thttp://www.youtube.com/watch?v=eTdeeofZzDQ
     * The algorithm sorts in O(n log n) average and worse cases
     * @param <Type> any type.
     * @param arrayList the array list of the given type.
     * @param comparator is the comparator that compares elements of the given type.
     */
    public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
        Preconditions.checkArgument(arrayList != null, "The array list must be provided to swap.");
        Preconditions.checkArgument(comparator != null, "The comparator must be provided.");

        // empty array or with one element is already sorted. 
        if (arrayList.length == 0 || arrayList.length == 1) {
            return;
        }

        // swap the elements in case there are two elements in the array.
        if (arrayList.length == 2) {
            if (comparator.compare(arrayList[0], arrayList[1]) > 0) {
                swap(arrayList, 0, 1);
            }
            return;
        }

        // get the sub array from the beginning to the middle (with inclusive index)
        Type[] leftArray =  getSubArray(arrayList, 0, (arrayList.length / 2) - 1);

        // get the sub array from the middle to the end (with inclusive index)
        Type[] rightArray =  getSubArray(arrayList, (arrayList .length / 2), arrayList.length - 1);

        // sort each side of the arrays
        sort(leftArray, comparator);
        sort(rightArray, comparator);

        // perform the merge of the sorted arrays using the original reference.
        mergeSortedArrays(leftArray, rightArray, arrayList, comparator);
    }

    /**
     * O(arrayA.length + arrayB.length) http://www.youtube.com/watch?v=0uOB-PsmlKA
     * @param arrayA is the first array.
     * @param arrayB is the second array.
     * @param comparator compares two elements of the arrays.
     * @return the Merged arrays maintaining the sorted elements.
     * Mar 27, 2012 7:31:55 PM
     */
    public static <Type> void mergeSortedArrays(Type[] arrayA, Type[] arrayB, Type[] m, Comparator<Type> comparator) {
        Preconditions.checkArgument(arrayA != null, "ArrayA cannot be null.");
        Preconditions.checkArgument(arrayB != null, "ArrayB cannot be null.");
        Preconditions.checkArgument(m != null, "merge array cannot be null.");

        // the indexes for A, B and merged array
        int idxA = 0, idxB = 0, i = 0;

        // to address the end cases
        boolean stillA = true, stillB = true;

        while (stillA || stillB) {

            // if the current element on A is smaller than B, or if it is still in A in case B fails.
            if (comparator.compare(arrayA[idxA], arrayB[idxB]) < 0 && stillA || (stillA && !stillB)) {
                m[i++] = arrayA[idxA];
                // only increment the idxA if it is still in the length size.
                if (idxA + 1 < arrayA.length) {
                    idxA++;
                } else {
                    stillA = false;
                }

            } else if (stillB || !stillA) {
                // always larger values will land here anyways.
                m[i++] = arrayB[idxB];
                if (idxB + 1 < arrayB.length) {
                    idxB++;
                } else {
                    stillB = false;
                }
            }
        }
    }

}
