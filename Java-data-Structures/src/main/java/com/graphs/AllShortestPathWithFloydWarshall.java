package com.graphs;

import java.util.Arrays;

import com.graphs.GraphEdgeListRepresentation.Edge;

/**
 * Given a graph G, we want to find the shortest path between any two vertices.
 * For that we use the Floyd Warshall algorithm.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class AllShortestPathWithFloydWarshall {

    /*** Array that contains all shortest path distance */
    private int[][] dist;

    /**
     * Constructor that take the graph and compute all shortest path.
     * 
     * @param graph
     * @exception throw
     *                IllegalArgumentException if the given graph contains
     *                negative edge weight.
     */
    public AllShortestPathWithFloydWarshall(GraphEdgeListRepresentation graph) {
        int v_size = graph.getV_Size();
        dist = new int[v_size][v_size];

        floydWarshall(graph);

    }

    /*
     * Private method that perform the Floyd Warshall algorithm.
     */
    private void floydWarshall(GraphEdgeListRepresentation graph) {
        int v_size = graph.getV_Size();

        for (int i = 0; i < v_size; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for (int i = 0; i < v_size; i++)
            dist[i][i] = 0;

        for (Edge edge : graph.getEdgeslist())
            dist[edge.u][edge.v] = edge.w;

        for (int k = 0; k < v_size; k++) {
            for (int i = 0; i < v_size; i++) {
                for (int j = 0; j < v_size; j++) {
                    if ((dist[i][k] != Integer.MAX_VALUE) && (dist[k][j] != Integer.MAX_VALUE)
                            && (dist[i][j] > dist[i][k] + dist[k][j])) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    /**
     * Return the value of the shortest path distance between two given vertices
     * a given vertex. The function return Integer.MAX_VALUE when there is no
     * path between the two vertices.
     * 
     * @param u
     *            the source vertex
     * @param v
     *            the destination vertex
     * @return return the shortest path distance value.
     */
    public int getShortestDistance(int u, int v) {
        return dist[u][v];
    }

}
