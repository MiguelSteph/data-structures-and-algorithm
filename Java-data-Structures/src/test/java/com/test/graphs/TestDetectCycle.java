package com.test.graphs;

import com.graphs.DetectCycle;
import com.graphs.GraphAdjacencyListRepresentation;

import junit.framework.TestCase;

public class TestDetectCycle extends TestCase {

    public void testConnectedComponentsInDirectedGraph() {
        GraphAdjacencyListRepresentation graph = new GraphAdjacencyListRepresentation(13, true);

        graph.addEdge(0, 5);
        graph.addEdge(0, 1);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);

        graph.addEdge(4, 3);
        graph.addEdge(4, 2);

        graph.addEdge(5, 4);

        graph.addEdge(6, 9);
        graph.addEdge(6, 4);
        graph.addEdge(6, 8);
        graph.addEdge(6, 0);

        graph.addEdge(7, 6);
        graph.addEdge(7, 9);

        graph.addEdge(8, 6);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);

        graph.addEdge(10, 12);

        graph.addEdge(11, 4);
        graph.addEdge(11, 12);

        graph.addEdge(12, 9);
        
        assertEquals(true, DetectCycle.haveCycle(graph));
        
        
        graph = new GraphAdjacencyListRepresentation(13, true);

        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 6);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 5);

        graph.addEdge(5, 4);

        graph.addEdge(6, 9);
        graph.addEdge(6, 4);

        graph.addEdge(7, 6);

        graph.addEdge(8, 7);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);

        graph.addEdge(11, 12);

        assertEquals(false, DetectCycle.haveCycle(graph));
        
    }
    
}
