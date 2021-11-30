package com.iadlpc.mazesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Builder {

    public static LinkedList<Point> build(String path) {
        LinkedList<Point> maze = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            maze.add(new Point(-1,-1, Integer.getInteger(scanner.nextLine())));
            int i = 0;
            while(scanner.hasNext()){
                maze.addAll(parseLine(scanner.nextLine(), i));
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return maze;
    }

    private static LinkedList<Point> parseLine(String line, int i) {
        LinkedList<Point> points = new LinkedList<>();
        List.of(line.split(" ")).forEach( (point) -> {
            if("E0S".contains(point)) points.add(new Point(i, points.size(), 0));
            else if(point.equals("M")) points.add(new Point(i, points.size(), 100));
            else points.add(new Point(i, points.size(), -1000));
        });
        return points;
    }


}
