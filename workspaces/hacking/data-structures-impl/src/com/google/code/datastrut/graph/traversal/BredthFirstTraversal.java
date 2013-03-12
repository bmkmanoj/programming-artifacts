package com.google.code.datastrut.graph.traversal;

import com.google.code.datastrut.graph.Graph;
import com.google.code.datastrut.graph.Vertex;
import com.google.code.datastrut.queue.Queue;
import com.google.code.datastrut.queue.QueueImpl;

/**
 * 
 * The BredthFirstTraversal is the traversal algorithm in a graph that uses a queue for visiting
 * the vertexes as described on http://www.youtube.com/watch?v=or9xlA3YYzo.
 *
 * <p>Mar 11, 2013 9:45:09 PM</p>
 * @author Marcello de Sales (marcello.desales@gmail.com)
 *
 * @param <Type>
 */
public class BredthFirstTraversal<Type> extends AbstractGraphTraversal<Type> {

  /**
   * The queue to hold the neighbor connections from the initial chosen vertex.
   */
  private Queue<Vertex<Type>> queue = new QueueImpl<>();

  protected BredthFirstTraversal(Graph<Type> newGraph) {
    super(newGraph);
  }

  @Override
  protected void traverse(Vertex<Type> node) {
    // initially visit the node.
    if (!node.hasBeenVisited()) {
      node.setAsVisited();
      resultValues.add(node.getValue());
    }

    // visit all its connecting vertexes
    for (Type connectionValue : node.getConnections()) {
      Vertex<Type> vertexConnection = graph.getVertexInstance(connectionValue);
      if (!vertexConnection.hasBeenVisited()) {
        resultValues.add(vertexConnection.getValue());
        vertexConnection.setAsVisited();
        queue.enqueue(vertexConnection);
      }
    }

    // visit all vertexes from the queue
    while (!queue.isEmpty()) {
      traverse(queue.dequeue());
    }
  }

}
