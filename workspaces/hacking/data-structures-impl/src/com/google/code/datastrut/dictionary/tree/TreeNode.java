package com.google.code.datastrut.dictionary.tree;

import com.google.code.datastrut.dictionary.DictionaryEntry;
import com.google.common.base.Objects;

public class TreeNode<Key, Value> extends DictionaryEntry<Key, Value> {

  private TreeNode<Key, Value> parent;
  private TreeNode<Key, Value> left;
  private TreeNode<Key, Value> right;

  public TreeNode(TreeNode<Key, Value> parent, TreeNode<Key, Value> left, TreeNode<Key, Value> right, Key newKey, Value newValue) {
    super(newKey, newValue);
    this.parent = parent;
    this.left = left;
    this.right = right;
  }

  public TreeNode(TreeNode<Key, Value> parent, Key newKey, Value newValue) {
    super(newKey, newValue);
    this.parent = parent;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("key", getKey()).add("value", getValue()).add("parent", getParent()).
      add("left", getLeft()).add("right", getRight()).toString();
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @return the parent
   */
  public TreeNode<Key, Value> getParent() {
    return parent;
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @param parent the parent to set
   */
  public void setParent(TreeNode<Key, Value> parent) {
    this.parent = parent;
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @return the left
   */
  public TreeNode<Key, Value> getLeft() {
    return left;
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @param left the left to set
   */
  public void setLeft(TreeNode<Key, Value> left) {
    this.left = left;
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @return the right
   */
  public TreeNode<Key, Value> getRight() {
    return right;
  }

  /**
   * <p>Apr 8, 2012 7:15:00 PM</p>
   * @param right the right to set
   */
  public void setRight(TreeNode<Key, Value> right) {
    this.right = right;
  }

}
