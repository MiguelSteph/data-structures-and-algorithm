package com.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Adjacency list representation of a graph.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class GraphAdjacencyListRepresentation {

    /*** Size of vertices of the graph */
    private int V_Size;

    /*** Size of edges of the graph */
    private int E_Size;

    private boolean isDirected;

    private boolean hasNegativeEdge;

    /*** Adjacency list that represent the graph */
    private List<List<Integer>> adjList;

    /*** Adjacency weight list */
    private List<List<Integer>> adjWeightList;

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
    public GraphAdjacencyListRepresentation(int V_Size, boolean isDirected) {
        if (V_Size <= 0)
            throw new IllegalArgumentException("Invalid Argument");
        this.V_Size = V_Size;
        this.isDirected = isDirected;
        E_Size = 0;
        hasNegativeEdge = false;
        adjList = new ArrayList<>(V_Size);
        adjWeightList = new ArrayList<>(V_Size);
        for (int i = 0; i < V_Size; i++) {
            adjList.add(new ArrayList<>());
            adjWeightList.add(new ArrayList<>());
        }
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
            adjList.get(u).add(v);
            adjWeightList.get(u).add(1);
        } else {
            adjList.get(u).add(v);
            adjWeightList.get(u).add(1);

            adjList.get(v).add(u);
            adjWeightList.get(v).add(1);
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
     *                number of vertices.
     */
    public void addEdge(int u, int v, int w) {
        if ((u < 0) || (u >= V_Size) || (v < 0) || (v >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        if (isDirectedGraph()) {
            adjList.get(u).add(v);
            adjWeightList.get(u).add(w);
        } else {
            adjList.get(u).add(v);
            adjWeightList.get(u).add(w);

            adjList.get(v).add(u);
            adjWeightList.get(v).add(w);
        }
        if (w < 0)
            this.hasNegativeEdge = true;
        E_Size++;
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
        if (adjList.get(u).contains(v))
            return true;
        return false;
    }

    /**
     * Get the adjacent node of a given node.
     * 
     * @param u
     *            given node
     * 
     * @return the adjacent node of the given node
     * 
     * @exception IllegalArgumentException
     *                if u is smaller than 0 or greater or equal to the number
     *                of vertices.
     */
    public List<Integer> getAdjNode(int u) {
        if ((u < 0) || (u >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        return adjList.get(u);
    }

    /**
     * Get the adjacent weight of a given node.
     * 
     * @param u
     *            given node
     * 
     * @return the adjacent weight of the given node
     * 
     * @exception IllegalArgumentException
     *                if u is smaller than 0 or greater or equal to the number
     *                of vertices.
     */
    public List<Integer> getAdjWeight(int u) {
        if ((u < 0) || (u >= V_Size))
            throw new IllegalArgumentException("Invalid Argument");
        return adjWeightList.get(u);
    }

    /**
     * Reverse this graph
     * 
     * @return return the reverse graph;
     */
    public GraphAdjacencyListRepresentation reverseGraph() {
        if (isDirectedGraph()) {
            GraphAdjacencyListRepresentation reverseGr = new GraphAdjacencyListRepresentation(this.V_Size,
                    this.isDirected);
            List<Integer> adj, adjWeight;
            for (int i = 0; i < this.adjList.size(); i++) {
                adj = adjList.get(i);
                adjWeight = adjWeightList.get(i);
                for (int j = 0; j < adj.size(); j++)
                    reverseGr.addEdge(adj.get(j), i, adjWeight.get(j));
            }
            return reverseGr;
        } else {
            return this;
        }
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

    /**
     * Test if this graph has negative weight or nott.
     * 
     * @return true if this graph has negative weight.
     */
    public boolean hasNegativeWeightEdge() {
        return hasNegativeEdge;
    }

    /**
     * Test if this graph is directed or not.
     * 
     * @return true if this graph is directed.
     */
    public boolean isDirectedGraph() {
        return isDirected;
    }

}
