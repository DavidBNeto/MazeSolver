package com.iadlpc.mazesolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Builder {

    public static LinkedList<Point> build(String path) {
        LinkedList<Point> maze = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            maze.add(new Point(-1,-1, scanner.nextInt()));
            scanner.nextLine();
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
            if(point.equals("0")) points.add(new Point(i, points.size(), 0.1));
            else if(point.equals("1")) points.add(new Point(i, points.size(), 0.01));
            else if(point.equals("M")) points.add(new Point(i, points.size(), 5.0));
            else if(point.equals("E")) points.add(new Point(i, points.size(), 0.0));
            else points.add(new Point(i, points.size(), 10.0));
        });
        return points;
    }


}
