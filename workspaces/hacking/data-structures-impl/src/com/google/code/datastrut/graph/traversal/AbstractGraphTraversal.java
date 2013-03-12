package com.google.code.datastrut.graph.traversal;

import java.util.Iterator;

import com.google.code.datastrut.graph.Graph;
import com.google.code.datastrut.graph.GraphImpl;
import com.google.code.datastrut.graph.Vertex;
import com.google.code.datastrut.list.ArrayList;
import com.google.code.datastrut.list.List;

/**
 * 
 * The AbstractBinaryTreeTraversal is the traversal implementation.
 *
 * <p>Mar 11, 2013 9:24:58 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */
public abstract class AbstractGraphTraversal<Type> implements Iterable<Type> {

  /**
   * The binary tree to traverse.
   */
  protected final Graph<Type> graph;
  
  private final Type initialValue;

  /**
   * The result of the traverse.
   */
  protected final List<Type> resultValues;

  /**
   * The different sorting algorithms to be used.
   * 
   * @author Marcello de Sales (marcello.desales@gmail.com)
   *
   */
  public static enum TraverseAlgorithm {
      BREDTH_FIRST_SEARCH,
      DEPTH_FIRST_SEARCH
  }

  /**
   * Traverses from the root element.
   * <p>Mar 11, 2013 9:06:03 PM</p>
   * @param newGraph
   */
  protected AbstractGraphTraversal(Graph<Type> newGraph) {
    this(newGraph, newGraph.getRootVertex().getValue());
  }

  /**
   * Constructs a new traversal with the given graph from the initial value.
   * <p>Apr 9, 2012 12:28:47 PM</p>
   * @param newTree is a binary tree.
   */
  protected AbstractGraphTraversal(Graph<Type> newGraph, Type initialValue){
    this.graph = newGraph;
    this.graph.resetVisitedStates();
    resultValues = new ArrayList<>();
    this.initialValue = initialValue;
  }

  /**
   * Traverses the given tree node based on the current implementation algorithm.
   * <p>Apr 9, 2012 12:29:09 PM</p> 
   * @param node is a given node.
   */
  protected abstract void traverse(Vertex<Type> node);

  @Override
  public Iterator<Type> iterator() {
    traverse(this.graph.getVertexInstance(this.initialValue));
    return resultValues.iterator();
  }

  public static void main(String[] args) {
    String[] graphVertexes = new String[]{"A", "B", "C", "D", "E"};
    boolean[][] adjacencyMatrix = GraphImpl.makeRandomAdjacencyMatrix(graphVertexes);
    GraphImpl.printAdjacencyMatrix(graphVertexes, adjacencyMatrix);

    System.out.println("Vertex Set with Connections based on the above connections matrix (-> = root)");
    GraphImpl<String> letterGraph = GraphImpl.makeNewGraph(graphVertexes, 2, adjacencyMatrix);
    System.out.println(letterGraph);

    System.out.println("The Depth First Search (DFS) elements WITH ALGORITHM IMPLEMENTATION");
    AbstractGraphTraversal<String> traversal = new DepthFirstTraversal<>(letterGraph);
    Iterator<String> dfsIterator2 =  traversal.iterator();
    while (dfsIterator2.hasNext()) {
        String value = dfsIterator2.next();
        if (dfsIterator2.hasNext()) {
            System.out.print(value + " -> ");
        } else {
            System.out.print(value);
        }
    }

    System.out.println("");
    System.out.println("The Breadth First Search (BFS) elements WITH ALGORITHM IMPLEMENTATION");
    AbstractGraphTraversal<String> traversal2 = new BredthFirstTraversal<>(letterGraph);
    Iterator<String> bfsIterator2 =  traversal2.iterator();
    while (bfsIterator2.hasNext()) {
        String value = bfsIterator2.next();
        if (bfsIterator2.hasNext()) {
            System.out.print(value + " -> ");
        } else {
            System.out.print(value);
        }
    }
  }

}
