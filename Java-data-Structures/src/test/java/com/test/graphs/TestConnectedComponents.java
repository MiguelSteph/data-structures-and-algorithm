package com.test.graphs;

import java.util.ArrayList;
import java.util.List;

import com.graphs.ConnectedComponents;
import com.graphs.GraphAdjacencyListRepresentation;

import junit.framework.TestCase;

public class TestConnectedComponents extends TestCase {

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

        List<List<Integer>> stronglyConnectedComponents = ConnectedComponents.getConnectedComponents(graph);

        List<Integer> cc0 = new ArrayList<>();
        cc0.add(1);

        List<Integer> cc1 = new ArrayList<>();
        cc1.add(0);
        cc1.add(2);
        cc1.add(3);
        cc1.add(4);
        cc1.add(5);

        List<Integer> cc2 = new ArrayList<>();
        cc2.add(6);
        cc2.add(8);

        List<Integer> cc3 = new ArrayList<>();
        cc3.add(7);

        List<Integer> cc4 = new ArrayList<>();
        cc4.add(9);
        cc4.add(10);
        cc4.add(11);
        cc4.add(12);

        assertEquals(5, stronglyConnectedComponents.size());
        
        for (List<Integer> cc : stronglyConnectedComponents) {
            if (cc.contains(0)) {
                assertEquals(true, cc.contains(2));
                assertEquals(true, cc.contains(3));
                assertEquals(true, cc.contains(4));
                assertEquals(true, cc.contains(5));
            } else if (cc.contains(6)) {
                assertEquals(true, cc.contains(8));
            } else if (cc.contains(9)){
                assertEquals(true, cc.contains(10));
                assertEquals(true, cc.contains(11));
                assertEquals(true, cc.contains(12));
            }
        }        
        
        // the expected strongly connected components are {1}, {0, 2, 3, 4, 5},
        // {6, 8}, {7}, and {9, 10, 11, 12}

    }

    public void testConnectedComponentsInUnDirectedGraph() {

        GraphAdjacencyListRepresentation graph = new GraphAdjacencyListRepresentation(13, false);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);

        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);

        graph.addEdge(4, 3);
        graph.addEdge(4, 2);

        graph.addEdge(5, 4);

        // graph.addEdge(6, 4);
        graph.addEdge(6, 8);
        // graph.addEdge(6, 0);

        graph.addEdge(7, 6);

        graph.addEdge(8, 6);

        graph.addEdge(9, 11);
        graph.addEdge(9, 10);

        graph.addEdge(10, 12);

        graph.addEdge(11, 12);

        graph.addEdge(12, 9);

        List<List<Integer>> connectedComponents = ConnectedComponents.getConnectedComponents(graph);
        assertEquals(3, connectedComponents.size());
        List<Integer> cc0 = new ArrayList<>();
        cc0.add(0);
        cc0.add(1);
        cc0.add(2);
        cc0.add(3);
        cc0.add(4);
        cc0.add(5);

        List<Integer> cc1 = new ArrayList<>();
        cc1.add(6);
        cc1.add(7);
        cc1.add(8);

        List<Integer> cc2 = new ArrayList<>();
        cc2.add(9);
        cc2.add(10);
        cc2.add(11);
        cc2.add(12);

        for (List<Integer> cc : connectedComponents) {
            if (cc.contains(0)) {
                assertEquals(true, cc.contains(1));
                assertEquals(true, cc.contains(2));
                assertEquals(true, cc.contains(3));
                assertEquals(true, cc.contains(4));
                assertEquals(true, cc.contains(5));
            } else if (cc.contains(6)) {
                assertEquals(true, cc.contains(7));
                assertEquals(true, cc.contains(8));
            } else {
                assertEquals(true, cc.contains(9));
                assertEquals(true, cc.contains(10));
                assertEquals(true, cc.contains(11));
                assertEquals(true, cc.contains(12));
            }
        }

        // the expected connected components are {0, 1, 2, 3, 4, 5}, {6, 7, 8},
        // and {9, 10, 11, 12}

    }

}
