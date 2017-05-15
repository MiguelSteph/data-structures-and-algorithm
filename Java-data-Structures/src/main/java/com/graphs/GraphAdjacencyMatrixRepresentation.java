package com.graphs;

import java.util.Arrays;

/**
 * Adjacency Matrix graph representation.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class GraphAdjacencyMatrixRepresentation {

    /*** Size of vertices of the graph */
    private int V_Size;

    /*** Size of edges of the graph */
    private int E_Size;

    private boolean isDirected;

    /*** Adjacency Matrix that represent the graph */
    private int[][] adjMatrix;

    /**
     * Constructor that take the number of vertices in the graph
     * 
     * @param V_Size
     *            number of vertices in the graph
     * @param isDirected
     *            true if the current graph is directed and false if not
     * 
     * @exception IllegalArgumentException
     *                if V_Size <= 0
     */
    public GraphAdjacencyMatrixRepresentation(int V_Size, boolean isDirected) {
        if (V_Size <= 0)
            throw new IllegalArgumentException("Invalid Argument");
        this.V_Size = V_Size;
        this.isDirected = isDirected;
        E_Size = 0;
        adjMatrix = new int[this.V_Size][this.V_Size];
        for (int i = 0; i < V_Size; i++)
            Arrays.fill(adjMatrix[i], Integer.MIN_VALUE);
    }

    /**
     * Add a new edge to the graph and take the weight of the edge as 1.
     * 
     * @param u
     *            Source of the edge
     * @param v
     *            End of the edge
     * 
     * @exception IllegalArgumentException
     *                if u or v is smaller than 0 or greater or equal to the
     *                number of vertices.
     */
    public void addEdge(int u, int v) {
        if ((u < 0) || (u >= V_Size) || (v < 0) || (v >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        if (isDirectedGraph()) {
            adjMatrix[u][v] = 1;
        } else {
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
        E_Size++;
    }

    /**
     * Add a new edge to the graph.
     * 
     * @param u
     *            Source of the edge
     * @param v
     *            End of the edge
     * @param w
     *            Weight of the edge
     * 
     * @exception IllegalArgumentException
     *                if u or v is smaller than 0 or greater or equal to the
     *                number of vertices or if w is equal to Integer.MIN_VALUE.
     */
    public void addEdge(int u, int v, int w) {
        if ((u < 0) || (u >= V_Size) || (v < 0) || (v >= V_Size) || (w == Integer.MIN_VALUE))
            throw new IllegalArgumentException("Invalid Argument");
        if (isDirectedGraph()) {
            adjMatrix[u][v] = w;
        } else {
            adjMatrix[u][v] = w;
            adjMatrix[v][u] = w;
        }
        E_Size++;
    }

    /**
     * Return the weigh of a given edge
     * 
     * @param u
     *            the source of the edge
     * @param v
     *            the destination of the edge
     * 
     * @return the weight of the given edge or Integer.MIN_VALUE if the given
     *         edge doesn't exist.
     * 
     * @exception IllegalArgumentException
     *                if u or v is smaller than 0 or greater or equal to the
     *                number of vertices.
     */
    public int getWeight(int u, int v) {
        return adjMatrix[u][v];
    }

    /**
     * Test if this is directed or not.
     * 
     * @return true if this graph is directed.
     */
    public boolean isDirectedGraph() {
        return isDirected;
    }

    /**
     * Test if the graph contains a given edge
     * 
     * @param u
     *            Source of the Edge
     * @param v
     *            End of the Edge
     * @return true if that edge exist and false if not
     */
    public boolean hasEdge(int u, int v) {
        if ((u < 0) || (u >= V_Size) || (v < 0) || (v >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        return adjMatrix[u][v] != Integer.MIN_VALUE;
    }

    /**
     * Number of vertices of the graph.
     * 
     * @return Number of vertices of the graph.
     */
    public int getV_Size() {
        return V_Size;
    }

    /**
     * Number of edges of the graph.
     * 
     * @return Number of edges of the graph.
     */
    public int getE_Size() {
        return E_Size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i = 0; i < V_Size; i++) {
            for (int j = 0; j < V_Size; j++) {
                if (hasEdge(i, j)) {
                    str.append("[u=" + i + ", v=" + j + ", w=" + getWeight(i, j) + "] ");
                }
            }
        }
        str.append(" ]");
        return str.toString();
    }

}
