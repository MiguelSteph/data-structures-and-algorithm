package com.test.graphs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.graphs.GraphAdjacencyListRepresentation;
import com.graphs.ReachabilityInGraph;

import junit.framework.TestCase;

public class TestReachabilityInGraph extends TestCase {

    public void testReachabilityInDirectedGraph() {
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
        
//        graph.addEdge(11, 4);
        graph.addEdge(11, 12);
        
        graph.addEdge(12, 9);
        
        Collection<Integer> reach = ReachabilityInGraph.reachable(graph, 12);
        Set<Integer> rReach = new HashSet<>();
        rReach.add(9);
        rReach.add(10);
        rReach.add(11);
        rReach.add(12);
        assertEquals(4, reach.size());
        for (Integer v : reach) {
            assertEquals(true, rReach.contains(v));
        }
    }
    
    public void testReachabilityInUndirectedGraph() {
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
        
        graph.addEdge(6, 4);
        graph.addEdge(6, 8);
        graph.addEdge(6, 0);
        
        graph.addEdge(7, 6);
        
        graph.addEdge(8, 6);
        
        graph.addEdge(9, 11);
        graph.addEdge(9, 10);
        
        graph.addEdge(10, 12);
        
        graph.addEdge(11, 12);
        
        graph.addEdge(12, 9);
        
        Collection<Integer> reach = ReachabilityInGraph.reachable(graph, 12);
        Set<Integer> rReach = new HashSet<>();
        rReach.add(9);
        rReach.add(10);
        rReach.add(11);
        rReach.add(12);
        assertEquals(4, reach.size());
        for (Integer v : reach) {
            assertEquals(true, rReach.contains(v));
        }
    }
    
}
