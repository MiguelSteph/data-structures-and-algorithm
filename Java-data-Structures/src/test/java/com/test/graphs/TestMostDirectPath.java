package com.test.graphs;

import java.util.LinkedList;

import com.graphs.GraphAdjacencyListRepresentation;
import com.graphs.MostDirectPath;

import junit.framework.TestCase;

public class TestMostDirectPath extends TestCase {

    public void testMostDirectPath() {

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

        graph.addEdge(6, 4);
        graph.addEdge(6, 8);
        graph.addEdge(6, 0);
        graph.addEdge(6, 9);

        graph.addEdge(7, 6);

        graph.addEdge(8, 6);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);

        graph.addEdge(10, 12);

        graph.addEdge(11, 12);

        graph.addEdge(12, 9);
        
        MostDirectPath dp = new MostDirectPath(graph, 6);
        assertEquals(0, dp.getDistance(6));
        assertEquals(-1, dp.getDistance(7));
        assertEquals(2, dp.getDistance(2));
        assertEquals(2, dp.getDistance(5));
        
        LinkedList<Integer> pathTo3 = new LinkedList<>();
        pathTo3.add(6);
        pathTo3.add(4);
        pathTo3.add(3);
        
        System.out.println(dp.getMostDirectPath(3));
        
        assertEquals(true, pathTo3.equals(dp.getMostDirectPath(3)));
        
    }

}
