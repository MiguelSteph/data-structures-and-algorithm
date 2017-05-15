package com.test.graphs;

import com.graphs.GraphAdjacencyListRepresentation;

import junit.framework.TestCase;

public class TestGraphAdjacencyListRepresentation extends TestCase {

    public void testGraphAdjacencyListRepresentationOperations() {

        // Undirected graph
        GraphAdjacencyListRepresentation graph = new GraphAdjacencyListRepresentation(10, false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 6);
        graph.addEdge(0, 8);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(1, 9);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 8);
        graph.addEdge(4, 5);
        graph.addEdge(4, 9);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        assertEquals(15, graph.getE_Size());
        assertEquals(10, graph.getV_Size());
        assertEquals(true, graph.hasEdge(7, 8));
        assertEquals(true, graph.hasEdge(8, 7));
        assertEquals(false, graph.hasEdge(0, 5));

        // Directed graph
        graph = new GraphAdjacencyListRepresentation(10, true);
        graph.addEdge(0, 1);
        graph.addEdge(0, 6);
        graph.addEdge(0, 8);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(1, 9);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 8);
        graph.addEdge(4, 5);
        graph.addEdge(4, 9);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        assertEquals(15, graph.getE_Size());
        assertEquals(10, graph.getV_Size());
        assertEquals(true, graph.hasEdge(7, 8));
        assertEquals(false, graph.hasEdge(8, 7));
        assertEquals(false, graph.hasEdge(0, 5));

        GraphAdjacencyListRepresentation reverseGraph = graph.reverseGraph();
        assertEquals(false, reverseGraph.hasEdge(7, 8));
        assertEquals(true, reverseGraph.hasEdge(8, 7));
        assertEquals(false, reverseGraph.hasEdge(0, 5));
        
    }

}
