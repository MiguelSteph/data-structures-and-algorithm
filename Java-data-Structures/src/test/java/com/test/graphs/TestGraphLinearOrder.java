package com.test.graphs;

import java.util.ArrayList;
import java.util.List;

import com.graphs.GraphAdjacencyListRepresentation;
import com.graphs.GraphLinearOrder;

import junit.framework.TestCase;

public class TestGraphLinearOrder extends TestCase{

    public void testGraphLinearOrder() {
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
        
        List<Integer> list = GraphLinearOrder.linearOrder(graph);
        assertEquals(null, list);
        
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
        
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(8);
        expectedList.add(7);
        expectedList.add(2);
        expectedList.add(3);
        expectedList.add(0);
        expectedList.add(6);
        expectedList.add(9);
        expectedList.add(10);
        expectedList.add(11);
        expectedList.add(12);
        expectedList.add(1);
        expectedList.add(5);
        expectedList.add(4);
        
        list = GraphLinearOrder.linearOrder(graph);
        
        System.out.println(list);
        
        assertEquals(true, list.equals(expectedList));
        
        
    }
    
}
