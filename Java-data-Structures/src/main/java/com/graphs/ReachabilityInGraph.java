package com.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Solve the problem of reachability in graph. Description : Given a graph G and
 * a vertex S, we want to output the collection of vertices v of G so that there
 * is a path from S to v
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ReachabilityInGraph {

    /**
     * Solve the problem of reachability in graph. Description : Given a graph G
     * and a vertex S, we want to output the collection of vertices v of G so
     * that there is a path from S to v
     * 
     * @param graph
     *            the Adjacency list representation of the graph
     * @param S
     *            The source node
     * @return collection of vertices v reachable from S
     * 
     * @exception IllegalArgumentException
     *                if S < 0 or S >= number of vertices
     */
    public static Queue<Integer> reachable(GraphAdjacencyListRepresentation graph, int S) {
        int V_Size = graph.getV_Size();
        if ((S < 0) || (S >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        boolean[] visited = new boolean[V_Size];
        Queue<Integer> queue = new ArrayDeque<>();
        explore(graph, S, visited, queue);
        return queue;
    }

    private static void explore(GraphAdjacencyListRepresentation graph, int u, boolean[] visited, Queue<Integer> queue) {
        visited[u] = true;
        queue.add(u);
        for (int v : graph.getAdjNode(u)) {
            if (!visited[v]) {
                explore(graph, v, visited, queue);
            }
        }
    }

}
