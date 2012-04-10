package com.google.code.datastrut.dictionary.tree;

import java.util.Iterator;

import com.google.code.datastrut.dictionary.tree.traversal.InOrderTraversal;
import com.google.code.datastrut.dictionary.tree.traversal.PostOrderTraversal;
import com.google.code.datastrut.dictionary.tree.traversal.PreOrderTraversal;
import com.google.code.datastrut.list.ArrayList;
import com.google.code.datastrut.list.List;
import com.google.code.datastrut.sort.Comparator;
import com.google.code.datastrut.sort.ComparatorFactory;

/**
 * 
 * The AbstractBinaryTreeTraversal is the traversal implementation.
 *
 * <p>Apr 9, 2012 12:24:58 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public abstract class AbstractBinaryTreeTraversal<Key, Value> implements Iterable<Value> {

  /**
   * The binary tree to traverse.
   */
  private final BinarySearchTree<Key, Value> tree;

  /**
   * The result of the traverse.
   */
  protected final List<Value> resultValues;

  /**
   * The different sorting algorithms to be used.
   * 
   * @author Marcello de Sales (marcello.desales@gmail.com)
   *
   */
  public static enum TraverseAlgorithm {

      PRE_ORDER,
      IN_ORDER,
      POST_ORDER;

      /**
       * <p>Apr 9, 2012 12:28:02 PM</p> 
       * @param tree is the tree to traverse.
       * @return the iterable result based on the traversal performed.
       */
      public <Key, Value> Iterable<Value> traverse(BinarySearchTree<Key, Value> tree) {
        switch (this) {
          case IN_ORDER: return new InOrderTraversal<Key, Value>(tree);
          case PRE_ORDER: return new PreOrderTraversal<Key, Value>(tree);
          case POST_ORDER: return new PostOrderTraversal<Key, Value>(tree);
          default:
            return null;
        }
      }

  }

  /**
   * Constructs a new traversal with the given tree.
   * <p>Apr 9, 2012 12:28:47 PM</p>
   * @param newTree is a binary tree.
   */
  protected AbstractBinaryTreeTraversal(BinarySearchTree<Key, Value> newTree){
    this.tree = newTree;
    resultValues = new ArrayList<Value>(tree.size());
    treaverse(this.tree.getRoot());
  }

  /**
   * Traverses the given tree node based on the current implementation algorithm.
   * <p>Apr 9, 2012 12:29:09 PM</p> 
   * @param node is a given node.
   */
  protected abstract void treaverse(TreeNode<Key, Value> node);

  @Override
  public Iterator<Value> iterator() {
    return resultValues.iterator();
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
    for(String values : TraverseAlgorithm.IN_ORDER.traverse(tree)) {
      System.out.print(values + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("Pre order");
    for(String values : TraverseAlgorithm.PRE_ORDER.traverse(tree)) {
      System.out.print(values + ", ");
    }
    System.out.println();
    System.out.println();

    System.out.println("Post order");
    for(String values : TraverseAlgorithm.POST_ORDER.traverse(tree)) {
      System.out.print(values + ", ");
    }
  }

}
