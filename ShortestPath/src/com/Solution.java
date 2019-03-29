package com;

import com.api.GpsNavigator;
import com.api.Path;
import com.impl.GPSNavigator;
import com.impl.Graph.Edge;

public class Solution {

    public static void main(String[] args) {

        final GpsNavigator navigator = new GPSNavigator<>(Edge.class, edge -> edge.dist*edge.price);
        navigator.readData("D:\\GPS\\roadMap.txt");

        final Path path = navigator.findPath("A", "C");
        System.out.println(path);

    }
}

