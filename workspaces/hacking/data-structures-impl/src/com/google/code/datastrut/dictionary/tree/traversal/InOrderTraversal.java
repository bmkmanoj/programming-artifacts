package com.google.code.datastrut.dictionary.tree.traversal;

import com.google.code.datastrut.dictionary.tree.AbstractBinaryTreeTraversal;
import com.google.code.datastrut.dictionary.tree.BinarySearchTree;
import com.google.code.datastrut.dictionary.tree.TreeNode;

/**
 * The InOrderTraversal is the traversal that gives the sorted order of elements. The traverse first visits the
 * left side, then processes the value, and visits the right side of the node.
 *
 * <p>Apr 9, 2012 12:30:02 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public class InOrderTraversal<Key, Value> extends AbstractBinaryTreeTraversal<Key, Value> {

  /**
   * <p>Apr 9, 2012 12:30:33 PM</p>
   * @param newTree
   */
  public InOrderTraversal(BinarySearchTree<Key, Value> newTree) {
    super(newTree);
  }

  @Override
  protected void treaverse(TreeNode<Key, Value> node) {
    if (node != null) {
      treaverse(node.getLeft());
      resultValues.add(node.getValue());
      treaverse(node.getRight());
    }
  }

}
