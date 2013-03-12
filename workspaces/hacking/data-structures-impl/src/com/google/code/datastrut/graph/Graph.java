package com.google.code.datastrut.graph;

import java.util.Iterator;

public interface Graph<Type> {

    /**
     * @return the DFS iterator of the graph as described at http://www.youtube.com/watch?v=or9xlA3YYzo
     */
    Iterator<Type> depthFirstSearchIterator();

    /**
     * @return the BFS iterator of the graph as described at the second half http://www.youtube.com/watch?v=or9xlA3YYzo
     */
    Iterator<Type> breadthFirstSearchIterator();

    /**
     * 
     * <p>Mar 11, 2013 9:02:32 PM</p> 
     * @return the root vertex of the graph that initialized the graph. 
     */
    Vertex<Type> getRootVertex();

    /**
     * 
     * <p>Mar 11, 2013 9:02:48 PM</p> 
     * @param value is the value of the graph.
     * @return the vertex reference that has the value.
     */
    Vertex<Type> getVertexInstance(Type value);

    /**
     * Resets all the vertexes for not visited.
     * <p>Mar 11, 2013 9:58:54 PM</p>
     */
    void resetVisitedStates();
}
