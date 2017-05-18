package com.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given a graph G, a source vertex S and a target vertex T, we want to find the
 * shortest path between S and T. We use the Bidirectional Dijkstra algorithm to
 * solve the problem.
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public class ShortestPathWithBidirectionalDijkstra {

    /*** The Source vertex */
    private int S;
    /*** The target vertex */
    private int T;

    /*** Array that contains the distance from the source to other vertices */
    private int dist[];
    /*** Array that contains the path from the source to other vertices */
    private int prev[];

    /*** Array that contains the distance from the target to other vertices */
    private int distR[];
    /*** Array that contains the path from the target to other vertices */
    private int prevR[];

    private Set<Integer> proc;
    private Set<Integer> procR;

    /*** The shortest distance between the source and the target */
    private int distance;
    /*** The shortest path between the source and the target */
    private LinkedList<Integer> path;

    /**
     * Contructor that take the graph, the source vertex and the target vertex.
     * 
     * @param graph
     * @param S
     *            Source
     * @param T
     *            Target
     * 
     * @exception throw
     *                IllegalArgumentException if the given graph contains
     *                negative edge weight.
     */
    public ShortestPathWithBidirectionalDijkstra(GraphAdjacencyListRepresentation graph, int S, int T) {
        int v_size = graph.getV_Size();
        if ((graph.hasNegativeWeightEdge()) || (S < 0) || (S >= v_size) || (T < 0) || (T >= v_size))
            throw new IllegalArgumentException("Invalid Argument");
        this.S = S;
        this.T = T;

        dist = new int[v_size];
        distR = new int[v_size];

        prev = new int[v_size];
        prevR = new int[v_size];

        proc = new HashSet<>();
        procR = new HashSet<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(distR, Integer.MAX_VALUE);

        Arrays.fill(prev, -1);
        Arrays.fill(prevR, -1);

        bidirectionalDijkstra(graph);
    }

    /*
     * Private method that perform the Bidirectional Dijkstra algorithm.
     */
    private void bidirectionalDijkstra(GraphAdjacencyListRepresentation graph) {

        GraphAdjacencyListRepresentation reverseGraph = graph.reverseGraph();

        dist[S] = 0;
        distR[T] = 0;

        PriorityQueue<VerticePriority> pq = new PriorityQueue<>();
        pq.add(new VerticePriority(S, dist[S]));

        PriorityQueue<VerticePriority> pqR = new PriorityQueue<>();
        pqR.add(new VerticePriority(T, dist[T]));

        while ((!pq.isEmpty()) || (!pqR.isEmpty())) {
            int u, v, w;
            List<Integer> adjVertices, adjWeight;

            // forward
            if (!pq.isEmpty()) {
                u = pq.poll().vertice;
                adjVertices = graph.getAdjNode(u);
                adjWeight = graph.getAdjWeight(u);
                for (int i = 0; i < adjVertices.size(); i++) {
                    v = adjVertices.get(i);
                    w = adjWeight.get(i);
                    // relax a forward edge
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        prev[v] = u;
                        pq.add(new VerticePriority(v, dist[v]));
                    }
                }
                proc.add(u);

                if (procR.contains(u)) {
                    computeShortestPath();
                    break;
                }
            }

            // backward
            if (!pqR.isEmpty()) {
                u = pqR.poll().vertice;
                adjVertices = reverseGraph.getAdjNode(u);
                adjWeight = reverseGraph.getAdjWeight(u);
                for (int i = 0; i < adjVertices.size(); i++) {
                    v = adjVertices.get(i);
                    w = adjWeight.get(i);
                    // relax a backward edge
                    if (distR[v] > distR[u] + w) {
                        distR[v] = distR[u] + w;
                        prevR[v] = u;
                        pqR.add(new VerticePriority(v, distR[v]));
                    }
                }
                procR.add(u);

                if (proc.contains(u)) {
                    computeShortestPath();
                    break;
                }
            }

        }

    }

    /*
     * Private method that compute the shortest distance and the shortest path
     */
    private void computeShortestPath() {
        distance = Integer.MAX_VALUE;
        int uBest = -1;
        for (int u : proc) {
            if (procR.contains(u)) {
                if (dist[u] + distR[u] < distance) {
                    uBest = u;
                    distance = dist[u] + distR[u];
                }
            }
        }

        if (uBest == -1) {
            return;
        }

        path = new LinkedList<>();
        int last = uBest;
        while (last != S) {
            path.addFirst(last);
            last = prev[last];
        }
        path.addFirst(S);

        last = uBest;
        while (last != T) {
            last = prevR[last];
            path.addLast(last);
        }
    }

    /**
     * Return the value of the shortest path distance from the source vertex to
     * the target vertex.
     * 
     * @return return the shortest path distance value.
     */
    public int getShortestDistance() {
        return distance;
    }

    /**
     * Return the value of the shortest path from the source vertex to the
     * target vertex.
     * 
     * @return return the shortest path.
     */
    public LinkedList<Integer> getShortestPath() {
        return path;
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
