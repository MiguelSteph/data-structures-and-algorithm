package com.test.graphs;

import java.util.ArrayList;
import java.util.List;

import com.graphs.GraphEdgeListRepresentation;
import com.graphs.GraphEdgeListRepresentation.Edge;
import com.graphs.GraphMstWithKruskal;

import junit.framework.TestCase;

public class TestGraphMstWithKruskal extends TestCase {

    public void testGraphMstWithKruskal() {

        GraphEdgeListRepresentation graph = new GraphEdgeListRepresentation(13, false);
        graph.addEdge(0, 5, 3);
        graph.addEdge(0, 1, 7);

        graph.addEdge(2, 0, 9);
        graph.addEdge(2, 3, 12);

        graph.addEdge(3, 5, 32);
        graph.addEdge(3, 2, 4);

        graph.addEdge(4, 3, 6);
        graph.addEdge(4, 2, 9);

        graph.addEdge(5, 4, 8);

        graph.addEdge(6, 4, 2);
        graph.addEdge(6, 8, 17);
        graph.addEdge(6, 0, 45);
        graph.addEdge(6, 9, 98);

        graph.addEdge(7, 6, 1);

        graph.addEdge(8, 6, 2);

        graph.addEdge(9, 11, 4);
        graph.addEdge(9, 10, 6);

        graph.addEdge(10, 12, 18);

        graph.addEdge(11, 12, 11);

        graph.addEdge(12, 9, 7);
        
        List<Edge> expectedMst = new ArrayList<>();
        
        expectedMst.add(new Edge(9, 10, 6));
        expectedMst.add(new Edge(0, 1, 7));
        expectedMst.add(new Edge(12, 9, 7));
        expectedMst.add(new Edge(5, 4, 8));
        expectedMst.add(new Edge(6, 9, 98));
        expectedMst.add(new Edge(0, 5, 3));
        expectedMst.add(new Edge(3, 2, 4));
        expectedMst.add(new Edge(4, 3, 6));
        expectedMst.add(new Edge(7, 6, 1));
        expectedMst.add(new Edge(6, 4, 2));
        expectedMst.add(new Edge(8, 6, 2));
        expectedMst.add(new Edge(9, 11, 4));
        
        List<Edge> mst = GraphMstWithKruskal.mst(graph);
        
        for (Edge edge : mst ) {
            assertEquals(true, expectedMst.contains(edge));
        }
        
        assertEquals(expectedMst.size(), mst.size());
        
    }

}
