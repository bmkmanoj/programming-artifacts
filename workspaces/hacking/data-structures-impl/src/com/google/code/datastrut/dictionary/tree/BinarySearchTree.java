package com.google.code.datastrut.dictionary.tree;

import java.util.Iterator;

import com.google.code.datastrut.ReverseIterator;
import com.google.code.datastrut.dictionary.Dictionary;
import com.google.code.datastrut.dictionary.DictionaryEntry;
import com.google.code.datastrut.dictionary.tree.AbstractBinaryTreeTraversal.TraverseAlgorithm;
import com.google.code.datastrut.sort.Comparator;
import com.google.code.datastrut.sort.ComparatorFactory;
import com.google.common.base.Preconditions;

/**
 * 
 * The BinarySearchTree is the implementation of an unbalanced tree to be used as a dictionary. It supports the
 * basic operations, along with the 3 traverse algorithms (See {@link AbstractBinaryTreeTraversal.TraverseAlgorithm}).
 * 
 * <p>Apr 8, 2012 19:05:36 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key> is a key
 * @param <Value> is a value
 */
public class BinarySearchTree<Key, Value> implements Dictionary<Key, Value> {

  /**
   * The root node of the tree.
   */
  private TreeNode<Key, Value> root;
  /**
   * The comparator used to evaluate the entries.
   */
  private Comparator<Key> comparator;
  /**
   * The current number of entries.
   */
  private int numberOfEntries;

  /**
   * Builds a new binary tree with the given comparator.
   * <p>Apr 9, 2012 12:21:49 PM</p>
   * @param newComparator is the comparator based on the key value.
   */
  public BinarySearchTree(Comparator<Key> newComparator) {
    Preconditions.checkArgument(newComparator != null, "The comparator must be provided.");

    this.comparator = newComparator;
  }

  /**
   * <p>Apr 9, 2012 12:22:18 PM</p> 
   * @return The root node of the tree.
   */
  public TreeNode<Key, Value> getRoot() {
    return root;
  }

  @Override
  public void put(Key key, Value value) {
    Preconditions.checkArgument(key != null, "The key must be provided.");
    Preconditions.checkArgument(value != null, "The value must be provided.");

    if (root == null) {
      root = new TreeNode<Key, Value>(null, key, value);
    } else {
      TreeNode<Key, Value> current = root;
      TreeNode<Key, Value> parent = null;
      boolean left = false;
      while(current != null) {
        parent = current;
        if (comparator.compare(key, current.getKey()) < 1) {
          current = current.getLeft();
          left = true;
        } else {
          left = false;
          current = current.getRight();
        }
      }
      current = new TreeNode<Key, Value>(parent, key, value);
      if (left) {
        parent.setLeft(current);
      } else {
        parent.setRight(current);
      }
    }
    numberOfEntries++;
  }

  @Override
  public DictionaryEntry<Key, Value> get(Key k) {
    Preconditions.checkArgument(k != null, "The key must be provided.");

    TreeNode<Key, Value> current = root;
    while(current != null) {
      if (comparator.compare(k, current.getKey()) < 1) { // if the value is on the left
        current = current.getLeft();

      } else if (comparator.compare(k, current.getKey()) > 1) { // if the value is on the right
        current = current.getRight();

      } else return current; // found the value
    }
    return null; // not found... douh!
  }

  @Override
  public boolean remove(DictionaryEntry<Key, Value> value) {
    Preconditions.checkArgument(value != null, "The value must be provided.");

    if (isEmpty()) {
      return false;
    }

    // TODO Auto-generated method stub
    numberOfEntries--;
    return false;
  }

  @Override
  public DictionaryEntry<Key, Value> max() {
    TreeNode<Key, Value> current = root;
    while (current != null && current.getRight() != null) {
      current = current.getRight();
    }
    return current;
  }

  @Override
  public DictionaryEntry<Key, Value> min() {
    TreeNode<Key, Value> current = root;
    while (current != null && current.getLeft() != null) {
      current = current.getLeft();
    }
    return current;
  }

  @Override
  public DictionaryEntry<Key, Value> predecessor() {
    // TODO Auto-generated method stub
    return root.getLeft();
  }

  @Override
  public DictionaryEntry<Key, Value> successor() {
    // TODO Auto-generated method stub
    return root.getRight();
  }

  @Override
  public int size() {
    return numberOfEntries;
  }

  @Override
  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  @Override
  public ReverseIterator<Dictionary<Key, Value>> getReverseIterator() {
    // TODO Auto-generated method stub
    return null;
  }

  public int getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Iterator<Dictionary<Key, Value>> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * <p>Apr 9, 2012 12:23:53 PM</p> 
   * @param traverseAlgo is the traversal algorithm to traverse the tree.
   * @return an iterable with the result of the traversal.
   */
  public Iterable<Value> traverse(TraverseAlgorithm traverseAlgo) {
    return traverseAlgo.traverse(this);
  }

  public static void main(String[] args) {

    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);
    tree.put(32, "Marcello");
    tree.put(30, "Leandro");
    tree.put(26, "Thiago");
    tree.put(36, "Camilla");
    tree.put(41, "Candy");
    tree.put(34, "Ivan");
    tree.put(28, "Priscila");
    tree.put(25, "Livia");

    System.out.println("In order: sorted values");
    for(String values : tree.traverse(TraverseAlgorithm.IN_ORDER)) {
      System.out.print(values + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("Pre order");
    for(String values : tree.traverse(TraverseAlgorithm.PRE_ORDER)) {
      System.out.print(values + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("Post order");
    for(String values : tree.traverse(TraverseAlgorithm.POST_ORDER)) {
      System.out.print(values + ", ");
    }
    System.out.println();
    System.out.println();

    String min = tree.min().getValue();
    System.out.println("Min: " + min);

    String max = tree.max().getValue();
    System.out.println("Max: " + max);

  }

}
