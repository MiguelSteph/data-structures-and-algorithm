package com.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a graph G and a vertex S, we use the BFS to find the most direct path
 * between any vertex and S.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class MostDirectPath {

    /*** The Source vertex of the BFS */
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
     */
    public MostDirectPath(GraphAdjacencyListRepresentation graph, int S) {
        int v_size = graph.getV_Size();
        if ((S < 0) || (S >= v_size))
            throw new IllegalArgumentException("Invalid Argument");
        this.S = S;
        dist = new int[v_size];
        prev = new int[v_size];
        Arrays.fill(dist, -1);
        Arrays.fill(prev, -1);

        bfs(graph);

    }

    /*
     * Private method that perform the Breath First Search on the graph
     */
    private void bfs(GraphAdjacencyListRepresentation graph) {
        dist[S] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(S);
        int u;
        while (!queue.isEmpty()) {
            u = queue.poll();
            for (int v : graph.getAdjNode(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    prev[v] = u;
                    queue.add(v);
                }
            }
        }
    }

    /**
     * Return the value of the most direct distance from the source vertex to a
     * given vertex.
     * 
     * @param v
     * @return return the most direct distance value.
     */
    public int getDistance(int v) {
        return dist[v];
    }

    /**
     * Return the most direct path from the source vertex to the given vertex.
     * 
     * @param v
     * @return The most direct path
     */
    public LinkedList<Integer> getMostDirectPath(int v) {
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

}
