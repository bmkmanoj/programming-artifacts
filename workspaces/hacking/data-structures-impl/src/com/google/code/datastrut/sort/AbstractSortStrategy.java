package com.google.code.datastrut.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import com.google.code.datastrut.list.ArrayList;
import com.google.code.datastrut.sort.algorithm.BubbleSortStrategy;
import com.google.code.datastrut.sort.algorithm.CocktailSortStrategy;
import com.google.code.datastrut.sort.algorithm.InsertionSortStrategy;
import com.google.code.datastrut.sort.algorithm.SelectionSortStrategy;
import com.google.code.datastrut.sort.algorithm.BucketInsertionSortStrategy;
import com.google.common.base.Preconditions;

public abstract class AbstractSortStrategy {

    /**
     * The different sorting algorithms to be used.
     * 
     * @author Marcello de Sales (marcello.desales@gmail.com)
     *
     */
    public static enum Algorithm {

        SELECTION_SORT(SelectionSortStrategy.getInstance()),

        BUBBLE_SORT(BubbleSortStrategy.getInstance()),

        COCKTAIL_SORT(CocktailSortStrategy.getInstance()),

        INSERTION_SORT(InsertionSortStrategy.getInstance()),

        BUCKET_SORT(BucketInsertionSortStrategy.getInstance()),

        // TODO: to be implemented (Modified MergeSort from OpenJDK)
        MERGE_SORT(null),

        // TODO: Implement 1 pivot, "Dual-Pivot" (Java 6)
        QUICK_SORT(null),

        // TODO: Implemented in Python, Android and Java 7.
        TIM_SORT(null);

        /**
         * The selected strategy.
         */
        private AbstractSortStrategy strategy;

        /**
         * Constructs one of the enumerated sorting algorithms with a given strategy.
         * @param algorithmStrategy is the algorithm strategy implementation.
         */
        private Algorithm(AbstractSortStrategy algorithmStrategy) {
            this.strategy = algorithmStrategy;
        }

        /**
         * Sorts the given array list with the given comparator using associated 
         * implementation.
         * @param <Type>
         * @param arrayList
         * @param comparator
         */
        public <Type> void sort(Type[] arrayList, Comparator<Type> comparator) {
            this.strategy.sort(arrayList, comparator);
        }
    }

    public abstract <Type> void sort(Type[] arrayList, Comparator<Type> comparator);

    /**
     * Swaps the elements of index 1 and index 2 two elements from the given array list.
     * @param <Type>
     * @param arrayList is the array list.
     * @param index1 is the index 1
     * @param index2 is the index 2
     */
    public static <Type> void swap(Type[] arrayList, int index1, int index2) {
        Preconditions.checkArgument(arrayList != null, "The array list must be provided to swap.");
        Preconditions.checkElementIndex(index1, arrayList.length, "The index 1 is out of the array bounds.");
        Preconditions.checkElementIndex(index2, arrayList.length, "The index 2 is out of the array bounds.");

        Type aux = arrayList[index2];
        arrayList[index2] = arrayList[index1];
        arrayList[index1] = aux;
    }

    /**
     * Creates a new array of a given class type.
     * @param clazz is the class of a given type
     * @param length is the length of the array.
     * @return the new array with the given number of slots.
     * Mar 27, 2012 7:06:01 PM
     */
    @SuppressWarnings({"unchecked"})
    public static <Type> Type[] newArray(Class<Type[]> clazz, int length) {
        Preconditions.checkArgument(clazz != null, "The class of the array must be provided.");
        Preconditions.checkArgument(length > -1, "The length must be greater or equals to 0.");

        return (Type[])Array.newInstance(clazz.getComponentType(), length);
    }

    /**
     * @param arrayList is the array list.
     * @param startIndex is the start index.
     * @param endIndex is the end index (inclusive - 1).
     * @return The sub array from the given array using the start and end index.
     * Mar 27, 2012 7:50:18 PM
     */
    public static <Type> Type[] getSubArray(Type[] arrayList, int startIndex, int endIndex) {
        Preconditions.checkArgument(arrayList != null, "ArrayA cannot be null.");
        Preconditions.checkArgument(arrayList.length > endIndex, "End index is out of bounds.");

        @SuppressWarnings("unchecked")
        Class<Type[]> arrayClass = (Class<Type[]>)arrayList.getClass();
        int totalSize = (endIndex - startIndex) + 1;
        Type[] subArray = newArray(arrayClass, totalSize);
        for (int i = 0; startIndex <= endIndex; i++) {
            subArray[i] = arrayList[startIndex++];
        }
        return subArray;
    }

    public static void main(String[] args) {
        int numbers = 10;
        System.out.println(" -> Sorting... ");
        // verify the sorting
        Random rand = new Random();
        Integer[] arrayIntegers = new Integer[numbers];
        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        ArrayList<Integer> randomList = ArrayList.makeNewArrayList(arrayIntegers);
        System.out.println("Bubble sorting the list " + randomList);
        randomList.bubbleSort(ComparatorFactory.makeIntegerComparator());
        System.out.println("Bubble Sorted list: " + randomList);

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Selection sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.SELECTION_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Selection Sorted array: " + Arrays.toString(arrayIntegers));

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Bubble sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.BUBBLE_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Bubble Sorted array: " + Arrays.toString(arrayIntegers));

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Cocktail sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.COCKTAIL_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Cocktail Sorted array: " + Arrays.toString(arrayIntegers));

        for (int i = 0; i < numbers; i++) {
            arrayIntegers[i] = rand.nextInt(100);
        }
        System.out.println("------------------------------");
        System.out.println("Insertion sorting the array " + Arrays.toString(arrayIntegers));

        AbstractSortStrategy.Algorithm.INSERTION_SORT.sort(arrayIntegers, ComparatorFactory.makeIntegerComparator());
        System.out.println("Insertion Sorted array: " + Arrays.toString(arrayIntegers));

        String[] languages = new String[]{"Smalltalk", "Java", "C++", "Python", "LaTex", "Go", "JADE", "Scala", "Basic", "perl", 
                "Erlang", "C", "Groovy", "R", "Javascript", "PHP", "XML", "Ada", "HTML", "Lisp", "Pascal", 
                "Objective C", "Fortran", "ASP", "ML", "Haskel", "Assembly", "Octave", "Lua", 
                "Visual Basic"};
        System.out.println("------------------------------");
        System.out.println("Bucket Sorting the array " + Arrays.toString(languages));

        AbstractSortStrategy.Algorithm.BUCKET_SORT.sort(languages, ComparatorFactory.makeStringComparator());
        System.out.println("Bucket Sorted array: " + Arrays.toString(languages));
    }
}
