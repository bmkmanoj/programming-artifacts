package com.google.code.datastrut.graph.traversal;

import com.google.code.datastrut.graph.Graph;
import com.google.code.datastrut.graph.Vertex;
import com.google.code.datastrut.stack.Stack;
import com.google.code.datastrut.stack.StackImpl;

/**
 * 
 * The DepthFirstTraversal is the implementation of the depth first traversal using a stack of
 * elements as shown in http://www.youtube.com/watch?v=or9xlA3YYzo
 *
 * <p>Mar 11, 2013 9:42:04 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Type>
 */
public class DepthFirstTraversal<Type> extends AbstractGraphTraversal<Type> {

  /**
   * The stack of vertexes where the elements are marked to be visited.
   */
  private Stack<Vertex<Type>> stack = new StackImpl<>();

  protected DepthFirstTraversal(Graph<Type> newGraph) {
    super(newGraph);
  }

  protected DepthFirstTraversal(Graph<Type> newGraph, Type initialValue) {
    super(newGraph, initialValue);
  }

  @Override
  protected void traverse(Vertex<Type> node) {
    // visit if not visited before
    if (!node.hasBeenVisited()) {
      resultValues.add(node.getValue());
      node.setAsVisited();
      stack.push(node);
    }

    // for all its connections as deep as it can go
    for (Type connectionValue : node.getConnections()) {
      Vertex<Type> vertexConnection = graph.getVertexInstance(connectionValue);
      if (!vertexConnection.hasBeenVisited()) {
        stack.push(vertexConnection);
        traverse(vertexConnection);
      }
    }

    // when all the connections have been visited just traverse through the stack elements
    // and continues the process before
    while (!stack.isEmpty()) {
      traverse(stack.pop());
    }
  }

}
