package com.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Edge list representation of a graph.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class GraphEdgeListRepresentation {

    /*** Intern class for edge representation */
    public static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        public Edge(int begin, int end) {
            this.u = begin;
            this.v = end;
            this.w = 1;
        }

        public Edge(int begin, int end, int weight) {
            this.u = begin;
            this.v = end;
            this.w = weight;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + u;
            result = prime * result + v;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge other = (Edge) obj;
            if (u != other.u)
                return false;
            if (v != other.v)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Edge [u=" + u + ", v=" + v + ", w=" + w + "]";
        }

        @Override
        public int compareTo(Edge other) {
            if (this.w < other.w)
                return -1;
            else if (this.w > other.w)
                return 1;
            else
                return 0;
        }
    }

    /*** Size of vertices of the graph */
    private int V_Size;

    /*** Size of edges of the graph */
    private int E_Size;

    private boolean isDirected;

    /*** List of Edge that represent the graph */
    private List<Edge> edgeList;

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
    public GraphEdgeListRepresentation(int V_Size, boolean isDirected) {
        if (V_Size <= 0)
            throw new IllegalArgumentException("Invalid Argument");
        this.V_Size = V_Size;
        this.isDirected = isDirected;
        E_Size = 0;
        edgeList = new ArrayList<>();
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
            edgeList.add(new Edge(u, v));
        } else {
            edgeList.add(new Edge(u, v));
            edgeList.add(new Edge(v, u));
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
            edgeList.add(new Edge(u, v, w));
        } else {
            edgeList.add(new Edge(u, v, w));
            edgeList.add(new Edge(v, u, w));
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
     * @return the weight of the given edge or Integer.MIN_VALUE if the given
     *         edge doesn't exist.
     * 
     * @exception IllegalArgumentException
     *                if u or v is smaller than 0 or greater or equal to the
     *                number of vertices.
     */
    public int getWeight(int u, int v) {
        Edge edge = new Edge(u, v);
        for (Edge e : edgeList) {
            if (edge.equals(e))
                return e.w;
        }
        return Integer.MIN_VALUE;
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
        Edge edge = new Edge(u, v);
        return edgeList.contains(edge);
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
     * Test if this is directed or not.
     * 
     * @return true if this graph is directed.
     */
    public boolean isDirectedGraph() {
        return isDirected;
    }
    
    /**
     * @return The list of edge of the graph sorted by weight.
     */
    public List<Edge> getSortedEdgeListByWeight() {
        Collections.sort(edgeList);
        return edgeList; 
    }

    /**
     * The list of edge of the graph.
     * 
     * @return the list of edge of the graph.
     */
    public List<Edge> getEdgeslist() {
        return edgeList;
    }

    @Override
    public String toString() {
        return edgeList.toString();
    }

}
