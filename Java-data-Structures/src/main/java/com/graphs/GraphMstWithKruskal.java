package com.graphs;

import java.util.ArrayList;
import java.util.List;

import com.disjointsets.DisjointSet;
import com.graphs.GraphEdgeListRepresentation.Edge;

/**
 * Given an undirected graph, we want to find the Minimum Spanning Tree using
 * KRUSKAL algorithm.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class GraphMstWithKruskal {

    public static List<Edge> mst(GraphEdgeListRepresentation graph) {

        if (graph.isDirectedGraph())
            throw new IllegalArgumentException("Invalid Argument");

        List<Edge> result = new ArrayList<>();
        DisjointSet<Integer> disjointSet = new DisjointSet<>();
        int v_size = graph.getV_Size();

        for (int u = 0; u < v_size; u++)
            disjointSet.makeSet(u);

        List<Edge> sortedListOfEdges = graph.getSortedEdgeListByWeight();
        for (Edge edge : sortedListOfEdges) {
            if (!disjointSet.find(edge.u).equals(disjointSet.find(edge.v))) {
                result.add(edge);
                disjointSet.union(edge.u, edge.v);
            }
        }

        return result;
    }

}
