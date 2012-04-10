package com.google.code.datastrut.dictionary.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.code.datastrut.dictionary.tree.AbstractBinaryTreeTraversal.TraverseAlgorithm;
import com.google.code.datastrut.sort.Comparator;
import com.google.code.datastrut.sort.ComparatorFactory;

public class BinarySearchTreeTests {

  @Test
  public void testInitialState() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);
    assertNull("The tree can't have a root node upon its creation.", tree.getRoot());
    assertNull("The tree can't have a min node upon its creation.", tree.min());
    assertNull("The tree can't have a max node upon its creation.", tree.max());
    assertTrue("The tree must be empty.", tree.isEmpty());
    assertTrue("The size of the tree must be zero.", tree.size() == 0);
  }
  
  @Test
  public void testRootNodeProperties() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");

    TreeNode<Integer, String> root = tree.getRoot();
    assertNotNull("The tree must have a root node upon its creation.", root);
    assertEquals("The root key is incorrect", Integer.valueOf(32), root.getKey());
    assertEquals("The root value is incorrect", "Marcello", root.getValue());
    assertNull("The root node can't never have a root node upon its creation.", root.getParent());
    assertNull("The left node must be null after its creation.", root.getLeft());
    assertNull("The right node must be null after its creation.", root.getRight());
  }

  @Test
  public void testAddingSmallerElement() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    TreeNode<Integer, String> root = tree.getRoot();
    tree.put(30, "Leandro");
    TreeNode<Integer, String> left = root.getLeft();

    assertNotNull("The tree must have a root node upon its creation.", root);
    assertNotNull("The root key must exist", root.getKey());
    assertEquals("The root key is incorrect", Integer.valueOf(32), root.getKey());
    assertNotNull("The root value must exist", root.getValue());
    assertEquals("The value key is incorrect", "Marcello", root.getValue());

    assertNotNull("The left node must be null after its creation.", left);
    assertNotNull("The left key must exist", left.getKey());
    assertEquals("The left key is incorrect", Integer.valueOf(30), left.getKey());
    assertNotNull("The left value must exist", left.getValue());
    assertEquals("The left key is incorrect", "Leandro", left.getValue());
    assertSame("The parent object must be the same reference", root, left.getParent());
  }

  @Test
  public void testAddingLargerElement() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    TreeNode<Integer, String> root = tree.getRoot();
    tree.put(41, "Candie");
    TreeNode<Integer, String> right = root.getRight();

    assertNotNull("The tree must have a root node upon its creation.", root);
    assertNotNull("The root key must exist", root.getKey());
    assertEquals("The root key is incorrect", Integer.valueOf(32), root.getKey());
    assertNotNull("The root value must exist", root.getValue());
    assertEquals("The value key is incorrect", "Marcello", root.getValue());

    assertNotNull("The right node must be null after its creation.", right);
    assertNotNull("The right key must exist", right.getKey());
    assertEquals("The right key is incorrect", Integer.valueOf(41), right.getKey());
    assertNotNull("The right value must exist", right.getValue());
    assertEquals("The right key is incorrect", "Candie", right.getValue());
    assertSame("The parent object must be the same reference", root, right.getParent());
  }

  @Test
  public void testCreationWithElements() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    tree.put(30, "Leandro");
    tree.put(26, "Thiago");
    tree.put(36, "Camilla");
    tree.put(41, "Candy");
    tree.put(34, "Ivan");
    tree.put(28, "Priscila");
    tree.put(25, "Livia");

    TreeNode<Integer, String> root = tree.getRoot();
    assertNotNull("The tree must have a root node upon its creation.", root);
    assertEquals("The root node must have a valid key.", Integer.valueOf(32), root.getKey());
    assertEquals("The root node must have a valid value.", "Marcello", root.getValue());

    TreeNode<Integer, String> minNode = (TreeNode<Integer, String>)tree.min();
    assertNotNull("The tree can't have a min node upon its creation.", minNode);
    assertEquals("The min value is incorrect.", Integer.valueOf(25), minNode.getKey());
    assertEquals("The min value is incorrect.", "Livia", minNode.getValue());

    TreeNode<Integer, String> maxNode = (TreeNode<Integer, String>)tree.max();
    assertNotNull("The tree can't have a min node upon its creation.", maxNode);
    assertEquals("The max key is incorrect.", Integer.valueOf(41), maxNode.getKey());
    assertEquals("The max value is incorrect.", "Candy", maxNode.getValue());

    assertFalse("The tree must be empty.", tree.isEmpty());
    assertTrue("The size of the tree must be zero.", tree.size() == 8);
  }

  @Test
  public void testInOrderTraversal() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    tree.put(30, "Leandro");
    tree.put(26, "Thiago");
    tree.put(36, "Camilla");
    tree.put(41, "Candy");
    tree.put(34, "Ivan");
    tree.put(28, "Priscila");
    tree.put(25, "Livia");

    assertNotNull("The assert iterable must be not null", tree.traverse(TraverseAlgorithm.IN_ORDER));

    StringBuilder builder = new StringBuilder();
    for(String value : tree.traverse(TraverseAlgorithm.IN_ORDER)) {
      builder.append(value + "-");
    }
    String traversalResult = builder.deleteCharAt(builder.length()-1).toString();
    String expected = "Livia-Thiago-Priscila-Leandro-Marcello-Ivan-Camilla-Candy";

    assertEquals("The in-order traversal is incorrect", expected, traversalResult);
  }

  @Test
  public void testPreOrderTraversal() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    tree.put(30, "Leandro");
    tree.put(26, "Thiago");
    tree.put(36, "Camilla");
    tree.put(41, "Candy");
    tree.put(34, "Ivan");
    tree.put(28, "Priscila");
    tree.put(25, "Livia");

    assertNotNull("The assert iterable must be not null", tree.traverse(TraverseAlgorithm.PRE_ORDER));

    StringBuilder builder = new StringBuilder();
    for(String value : tree.traverse(TraverseAlgorithm.PRE_ORDER)) {
      builder.append(value + "-");
    }
    String traversalResult = builder.deleteCharAt(builder.length()-1).toString();
    String expected = "Marcello-Leandro-Thiago-Livia-Priscila-Camilla-Ivan-Candy";

    assertEquals("The pre-order traversal is incorrect", expected, traversalResult);
  }

  @Test
  public void testPostOrderTraversal() {
    Comparator<Integer> ageComparator = ComparatorFactory.makeIntegerComparator();
    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>(ageComparator);

    assertNotNull("The tree must be created with valid input.", tree);

    tree.put(32, "Marcello");
    tree.put(30, "Leandro");
    tree.put(26, "Thiago");
    tree.put(36, "Camilla");
    tree.put(41, "Candy");
    tree.put(34, "Ivan");
    tree.put(28, "Priscila");
    tree.put(25, "Livia");

    assertNotNull("The assert iterable must be not null", tree.traverse(TraverseAlgorithm.POST_ORDER));

    StringBuilder builder = new StringBuilder();
    for(String value : tree.traverse(TraverseAlgorithm.POST_ORDER)) {
      builder.append(value + "-");
    }
    String traversalResult = builder.deleteCharAt(builder.length()-1).toString();
    String expected = "Livia-Priscila-Thiago-Leandro-Ivan-Candy-Camilla-Marcello";

    assertEquals("The post-order traversal is incorrect", expected, traversalResult);
  }

}
