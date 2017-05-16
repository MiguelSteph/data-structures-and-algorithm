package com.graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Detect if a given graph have a cycle or not
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class DetectCycle {

    /**
     * Detect if the given graph contains a cycle.
     * 
     * @param graph
     * 
     * @return true if the graph contains cycle and false if not.
     */
    public static boolean haveCycle(GraphAdjacencyListRepresentation graph) {
        /* contains the vertices that hasn't been explored yet. */
        Set<Integer> whiteSet = new HashSet<>();

        /* contains the vertices that are currently being explored. */
        Set<Integer> graySet = new HashSet<>();

        /* contains the vertices that have been already explored. */
        Set<Integer> blackSet = new HashSet<>();

        int V_Size = graph.getV_Size();
        for (int u = 0; u < V_Size; u++) {
            whiteSet.add(u);
        }
        boolean cycle = false;
        for (int u = 0; u < V_Size; u++) {
            cycle = cycle | hasCycle(graph, u, false, whiteSet, graySet, blackSet);
        }
        return cycle;
    }

    private static boolean hasCycle(GraphAdjacencyListRepresentation graph, int u, boolean detectCycle,
            Set<Integer> whiteSet, Set<Integer> graySet, Set<Integer> blackSet) {
        if (detectCycle)
            return detectCycle;
        whiteSet.remove(new Integer(u));
        graySet.add(u);
        boolean b = false;
        for (int v : graph.getAdjNode(u)) {

            if (graySet.contains(new Integer(v))) {
                return true;
            }

            if (!blackSet.contains(new Integer(v))) {
                b = b | hasCycle(graph, v, b, whiteSet, graySet, blackSet);
            }
        }
        graySet.remove(new Integer(u));
        blackSet.add(u);
        return b;
    }

}
