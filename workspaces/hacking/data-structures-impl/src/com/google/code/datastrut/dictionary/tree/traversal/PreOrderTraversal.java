package com.google.code.datastrut.dictionary.tree.traversal;

import com.google.code.datastrut.dictionary.tree.AbstractBinaryTreeTraversal;
import com.google.code.datastrut.dictionary.tree.BinarySearchTree;
import com.google.code.datastrut.dictionary.tree.TreeNode;

/**
 * The PreOrderTraversal is the traversal that is used in arithmetic expressions to read mathematical expressions
 * evaluation order. The traverse first process the value to then visit the left side and the right side of the node.
 *
 * <p>Apr 9, 2012 12:30:02 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public class PreOrderTraversal<Key, Value> extends AbstractBinaryTreeTraversal<Key, Value> {

  public PreOrderTraversal(BinarySearchTree<Key, Value> newTree) {
    super(newTree);
  }

  @Override
  protected void treaverse(TreeNode<Key, Value> node) {
    if (node != null) {
      resultValues.add(node.getValue());
      treaverse(node.getLeft());
      treaverse(node.getRight());
    }
  }

}
