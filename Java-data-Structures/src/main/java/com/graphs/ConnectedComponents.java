package com.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Find the connected component in graph. Two vertices are reachable from one to
 * the second if and only if they are in the same connected components.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ConnectedComponents {

    /**
     * Given a graph G, we want to find and output the connected components in
     * the graph. If G is an undirected graph, the function return the connected
     * components. If G is a directed graph, the function return the strongly
     * connected components.
     * 
     * @param graph
     *            The given graph.
     * @return If G is an undirected graph, the function return the connected
     *         components. If G is a directed graph, this graph return the
     *         strongly connected components.
     */
    public static List<List<Integer>> getConnectedComponents(GraphAdjacencyListRepresentation graph) {
        if (graph == null)
            throw new IllegalArgumentException("Invalid Argument");
        if (graph.isDirectedGraph())
            return getConnectedComponentsForDirectedGraph(graph);
        else
            return getConnectedComponentsForUndirectedGraph(graph);

    }

    private static List<List<Integer>> getConnectedComponentsForDirectedGraph(GraphAdjacencyListRepresentation graph) {
        int V_Size = graph.getV_Size();
        GraphAdjacencyListRepresentation reverseGraph = graph.reverseGraph();
        NodePostOrder[] postOrder = new NodePostOrder[V_Size];
        boolean[] visited = new boolean[V_Size];

        // run dfs on the reverse graph
        int po = 0;
        for (int u = 0; u < V_Size; u++) {
            if (!visited[u]) {
                po = explore(reverseGraph, u, visited, po, postOrder);
            }
        }

        // sort vertices in the reverse order of their post order
        Arrays.sort(postOrder, Collections.reverseOrder());

        // Explore the vertices in the reverse order of their post order
        int[] cc = new int[V_Size];
        Arrays.fill(cc, -1);
        int ccNum = 0;
        for (int u = 0; u < V_Size; u++) {
            if (cc[postOrder[u].vertice] == -1) {
                explore(graph, postOrder[u].vertice, ccNum, cc);
                ccNum++;
            }
        }

        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        for (int i = 0; i < ccNum; i++) {
            stronglyConnectedComponents.add(new ArrayList<>());
        }

        for (int u = 0; u < V_Size; u++) {
            stronglyConnectedComponents.get(cc[u]).add(u);
        }

        return stronglyConnectedComponents;
    }

    private static List<List<Integer>> getConnectedComponentsForUndirectedGraph(
            GraphAdjacencyListRepresentation graph) {
        int ccNum = 0;
        int V_Size = graph.getV_Size();
        int[] cc = new int[V_Size];
        Arrays.fill(cc, -1);
        for (int u = 0; u < V_Size; u++) {
            if (cc[u] == -1) {
                explore(graph, u, ccNum, cc);
                ccNum++;
            }
        }

        List<List<Integer>> connectedComponents = new ArrayList<>();
        for (int i = 0; i < ccNum; i++) {
            connectedComponents.add(new ArrayList<>());
        }

        for (int u = 0; u < V_Size; u++) {
            connectedComponents.get(cc[u]).add(u);
        }

        return connectedComponents;
    }

    private static int explore(GraphAdjacencyListRepresentation graph, int u, boolean[] visited, int num,
            NodePostOrder[] postOrder) {
        visited[u] = true;
        int po = num;
        for (int v : graph.getAdjNode(u)) {
            if (!visited[v]) {
                po = explore(graph, v, visited, num + 1, postOrder);
            }
        }
        postOrder[u] = new NodePostOrder(u, po);
        return po + 1;
    }

    private static void explore(GraphAdjacencyListRepresentation graph, int u, int ccNum, int[] cc) {
        cc[u] = ccNum;
        for (int v : graph.getAdjNode(u)) {
            if (cc[v] == -1) {
                explore(graph, v, ccNum, cc);
            }
        }
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
