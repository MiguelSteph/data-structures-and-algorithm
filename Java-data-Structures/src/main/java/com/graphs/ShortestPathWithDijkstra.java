package com.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a graph G and a vertex S, we want to find the shortest path from S to
 * any other vertex.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ShortestPathWithDijkstra {

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
    public ShortestPathWithDijkstra(GraphAdjacencyListRepresentation graph, int S) {
        int v_size = graph.getV_Size();
        if ((graph.hasNegativeWeightEdge()) || (S < 0) || (S >= v_size))
            throw new IllegalArgumentException("Invalid Argument");
        this.S = S;
        dist = new int[v_size];
        prev = new int[v_size];
        Arrays.fill(dist, -1);
        Arrays.fill(prev, -1);

        dijkstra(graph);

    }

    /*
     * Private method that perform the Breath First Search on the graph
     */
    private void dijkstra(GraphAdjacencyListRepresentation graph) {
        dist[S] = 0;
        PriorityQueue<VerticePriority> pq = new PriorityQueue<>();
        pq.add(new VerticePriority(S, dist[S]));
        int u;
        List<Integer> adjVertices, adjWeight;
        while (!pq.isEmpty()) {
            u = pq.poll().vertice;
            int v, w;
            adjVertices = graph.getAdjNode(u);
            adjWeight = graph.getAdjWeight(u);
            for (int i = 0; i < adjVertices.size(); i++) {
                v = adjVertices.get(i);
                w = adjWeight.get(i);
                // relax the edge
                if ((dist[v] == -1) || (dist[v] > dist[u] + w)) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    pq.add(new VerticePriority(v, dist[v]));
                }
            }
        }
    }

    /**
     * Return the value of the shortest path distance from the source vertex to a
     * given vertex.
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
        if (dist[v] == -1)
            return null;
        LinkedList<Integer> directPath = new LinkedList<>();
        while (v != S) {
            directPath.addFirst(v);
            v = prev[v];
        }
        directPath.addFirst(S);
        return directPath;
    }
    
    private class VerticePriority implements Comparable<VerticePriority> {
        int vertice;
        int priority;

        public VerticePriority(int v, int p) {
            vertice = v;
            priority = p;
        }

        @Override
        public int compareTo(VerticePriority other) {
            if (this.priority < other.priority)
                return -1;
            else if (this.priority > other.priority)
                return 1;
            return 0;
        }

    }

}
