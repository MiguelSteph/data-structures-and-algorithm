package com.test.graphs;

import com.graphs.AllShortestPathWithFloydWarshall;
import com.graphs.GraphEdgeListRepresentation;

import junit.framework.TestCase;

public class TestAllShortestPathWithFloydWarshall extends TestCase {

    public void testAllShortestPathWithFloydWarshall() {

        GraphEdgeListRepresentation graph = new GraphEdgeListRepresentation(13, true);
        graph.addEdge(0, 5, 3);
        graph.addEdge(0, 1, 9);

        graph.addEdge(2, 0, 2);
        graph.addEdge(2, 3, 15);

        graph.addEdge(3, 5, 14);
        graph.addEdge(3, 2, 8);

        graph.addEdge(4, 3, 44);
        graph.addEdge(4, 2, 6);

        graph.addEdge(5, 4, 1);

        graph.addEdge(6, 4, 2);
        graph.addEdge(6, 8, 3);
        graph.addEdge(6, 0, 11);
        graph.addEdge(6, 9, 23);

        graph.addEdge(7, 6, 9);

        graph.addEdge(8, 6, 3);

        graph.addEdge(9, 11, 8);
        graph.addEdge(9, 10, 5);

        graph.addEdge(10, 12, 17);

        graph.addEdge(11, 12, 33);

        graph.addEdge(12, 9, 10);

        AllShortestPathWithFloydWarshall sp = new AllShortestPathWithFloydWarshall(graph);
        assertEquals(0, sp.getShortestDistance(6, 6));
        assertEquals(23, sp.getShortestDistance(6, 3));

    }

}
