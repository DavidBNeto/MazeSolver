package com.iadlpc.mazesolver;

import java.util.LinkedList;

public class Maze {

    private int size;
    private Point[][] maze;
    private MappedPoint[][] mappedPoints;

    public Maze(int size, Point[][] maze){
        this.size = size;
        this.maze = maze;
        this.mappedPoints = new MappedPoint[size][size];
    }

    public void updatePoints(){

    }

    public Point getPoint(int i, int j) { return maze[i][j]; }


}
