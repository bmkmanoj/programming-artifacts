package com.google.code.datastrut.dictionary.tree.traversal;

import com.google.code.datastrut.dictionary.tree.AbstractBinaryTreeTraversal;
import com.google.code.datastrut.dictionary.tree.BinarySearchTree;
import com.google.code.datastrut.dictionary.tree.TreeNode;

/**
 * The PostOrderTraversal is the traversal that is used in arithmetic expressions to do the actual assembly
 * calculations on a stack. The traverse first visits the left side, the right side of the node, to finally 
 * process the value.
 *
 * <p>Apr 9, 2012 12:30:02 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public class PostOrderTraversal<Key, Value> extends AbstractBinaryTreeTraversal<Key, Value> {

  public PostOrderTraversal(BinarySearchTree<Key, Value> newTree) {
    super(newTree);
  }

  @Override
  protected void treaverse(TreeNode<Key, Value> node) {
    if (node != null) {
      treaverse(node.getLeft());
      treaverse(node.getRight());
      resultValues.add(node.getValue());
    }
  }

}
