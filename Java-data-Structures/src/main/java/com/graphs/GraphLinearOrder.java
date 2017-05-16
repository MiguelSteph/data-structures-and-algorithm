package com.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Linear ordering of graph. Solve the problem of linear ordering with
 * Topological sort. Given a directed graph, put the vertices in order such that
 * all its directed edges point from a vertex earlier in the order to a vertex
 * later in the order (or report that doing so is not possible).
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class GraphLinearOrder {

    /**
     * Given a directed graph, put the vertices in order such that all its
     * directed edges point from a vertex earlier in the order to a vertex later
     * in the order (or report that doing so is not possible).
     * 
     * @param graph
     *            A directed graph
     * @return The linear order of the vertices of null if the graph have cycle.
     */
    public static List<Integer> linearOrder(GraphAdjacencyListRepresentation graph) {
        if (!graph.isDirectedGraph())
            throw new IllegalArgumentException("Invalid Argument");
        if (DetectCycle.haveCycle(graph))
            return null;
        int V_Size = graph.getV_Size();
        NodePostOrder[] postOrder = new NodePostOrder[V_Size];
        boolean[] visited = new boolean[V_Size];
        // run dfs on the reverse graph
        int po = 0;
        for (int u = 0; u < V_Size; u++) {
            if (!visited[u]) {
                po = explore(graph, u, visited, po, postOrder);
            }
        }

        // sort vertices in the reverse order of their post order
        Arrays.sort(postOrder, Collections.reverseOrder());
        
        List<Integer> list = new ArrayList<>(V_Size);
        for (int u = 0; u < V_Size; u++) { 
            list.add(postOrder[u].vertice);
        }

        return list;
    }
    
    private static int explore(GraphAdjacencyListRepresentation graph, int u, boolean[] visited, int num,
            NodePostOrder[] postOrder) {
        visited[u] = true;
        int po = num;
        for (int v : graph.getAdjNode(u)) {
            if (!visited[v]) {
                po = explore(graph, v, visited, po + 1, postOrder);
            }
        }
        postOrder[u] = new NodePostOrder(u, po);
        return po + 1;
    }

    private static class NodePostOrder implements Comparable<NodePostOrder> {
        int vertice;
        int postOrder;

        public NodePostOrder(int v, int po) {
            vertice = v;
            postOrder = po;
        }

        @Override
        public int compareTo(NodePostOrder other) {
            if (this.postOrder < other.postOrder)
                return -1;
            else if (this.postOrder > other.postOrder)
                return 1;
            return 0;
        }

    }

}
