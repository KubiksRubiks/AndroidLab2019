package com.impl;

import java.awt.*;
import java.util.*;
import java.util.function.Function;

public class Graph {
    private final Map<String, Vertex> graph;
    private int optim;
    private ArrayList<String> verts = new ArrayList<>();

    public int getOptim(){
        return optim;
    }

    public ArrayList<String> getVerts() {
        return verts;
    }

    /** One edge of the graph (only used by Graph constructor) */
    public class Edge {
        public final String v1, v2;
        public final int dist, price;
        public Edge(String v1, String v2, int dist, int price){
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
            this.price = price;
        }
    }

    /** One vertex of the graph, complete with mappings to neighbouring vertices */
    public class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        private void Path() {
            if (this == this.previous) {
                verts.add(this.name);
            } else if (this.previous == null) {
                throw new IllegalArgumentException("Exeption");
            } else {
                this.previous.Path();
                verts.add(this.name);
            }
        }

        public int compareTo(Vertex other) {
            return Integer.compare(dist, other.dist);
        }
    }

    /** Builds a graph from a set of edges */
    public <T extends Edge> Graph(T[] edges, Function<T, Integer> typeOptim) {
        graph = new HashMap<>(edges.length);

        for (T e : edges) {
            if (!graph.containsKey(e.v1)) {
                graph.put(e.v1, new Vertex(e.v1));
            }
            if (!graph.containsKey(e.v2)) {
                graph.put(e.v2, new Vertex(e.v2));
            }
        }

        for (T e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), typeOptim.apply(e));
        }
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            throw new IllegalArgumentException("Exeption");
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {
            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE) {
                break;
            }

            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    public void getPath(String endName) {
        if (!graph.containsKey(endName)) {
            throw new IllegalArgumentException("Exeption");
        }
        graph.get(endName).Path();
    }

}
