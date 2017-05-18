package com.graphs;

import java.util.Arrays;
import java.util.LinkedList;

import com.graphs.GraphEdgeListRepresentation.Edge;

/**
 * Given a graph G and a vertex S, we want to find the shortest path from S to
 * any other vertex. Here we use the Bellman Ford algorithm.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ShortestPathWithBellmanFord {

    /*** The Source vertex */
    private int S;

    /*** Array that contains the distance from the source to other vertices */
    private int[] dist;

    /*** Array that contains the path from the source to other vertices */
    private int[] prev;

    /**
     * Constructor that take the graph and the source vertex.
     * 
     * @param graph
     * @param S
     * @exception throw
     *                IllegalArgumentException if the given graph contains
     *                negative edge weight.
     */
    public ShortestPathWithBellmanFord(GraphEdgeListRepresentation graph, int S) {
        int v_size = graph.getV_Size();
        if ((S < 0) || (S >= v_size))
            throw new IllegalArgumentException("Invalid Argument");
        this.S = S;
        dist = new int[v_size];
        prev = new int[v_size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        bellManFord(graph);

    }

    /*
     * Private method that perform the Bellman-Ford algorithm.
     */
    private void bellManFord(GraphEdgeListRepresentation graph) {
        dist[S] = 0;
        int v_size = graph.getV_Size();
        for (int i = 0; i < v_size - 1; i++) {
            for (Edge edge : graph.getEdgeslist()) {
                relax(edge);
            }
        }
    }

    /*
     * Relax the given edge.
     */
    private void relax(Edge edge) {
        int u = edge.u;
        int v = edge.v;
        int w = edge.w;
        if ((dist[u] != Integer.MAX_VALUE) && (dist[v] > dist[u] + w)) {
            dist[v] = dist[u] + w;
            prev[v] = u;
        }
    }

    /**
     * Return the value of the shortest path distance from the source vertex to
     * a given vertex.
     * 
     * @param v
     * @return return the shortest path distance value.
     */
    public int getShortestDistance(int v) {
        return dist[v];
    }

    /**
     * Return the shortest path from the source vertex to the given vertex.
     * 
     * @param v
     * @return The shortest path
     */
    public LinkedList<Integer> getShortestPath(int v) {
        if (dist[v] == Integer.MAX_VALUE)
            return null;
        LinkedList<Integer> directPath = new LinkedList<>();
        while (v != S) {
            directPath.addFirst(v);
            v = prev[v];
        }
        directPath.addFirst(S);
        return directPath;
    }

}
