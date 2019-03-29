package com.impl;

import com.impl.Graph.Edge;
import com.api.GpsNavigator;
import com.api.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class GPSNavigator<T extends Edge> implements GpsNavigator {

    private ArrayList<Data> data = new ArrayList<Data>();
    private static Edge GRAPH[];
    private Class<T> tClass;
    private Function<T, Integer> typeOptim;

    public GPSNavigator(Class<T> tClass, Function<T, Integer> typeOptim){
        this.tClass = tClass;
        this.typeOptim = typeOptim;
    }

    @Override
    public void readData(String filePath) {
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(" ");
                data.add(new Data(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3])));
            }
            reader.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Path findPath(String pointA, String pointB) {

        T[] GRAPH = null;

        Graph g = new Graph(GRAPH, typeOptim);
        g.dijkstra(pointA);
        g.getPath(pointB);

        int cost = 0;
        String strRez = new String();

        String[] items = strRez.split(",");

        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < GRAPH.length; j++) {
                if (items[i].equals(GRAPH[j].v1) && items[i + 1].equals(GRAPH[j].v2)) {
                    cost += GRAPH[j].dist * GRAPH[j].price;
                }
            }
        }

        return new Path(Arrays.asList(items), cost);
    }

}
