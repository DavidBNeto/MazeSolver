package com.iadlpc.mazesolver;

import java.util.LinkedList;

public class Game {

    //options
    private boolean continuePlaying;
    private boolean autoWalking;
    private int maxAttempts;
    private int waitTime; //millis
    private int maxScore;

    //global
    private int attempt;
    private Maze maze;


    //player
    private int score;
    private int currI;
    private int currJ;
    private int currCoins;
    private LinkedList<Point> visitedPoints;

    //runnerMode greedyMode

    public Game(boolean autoWalking){
        this.currI = this.currJ = this.currCoins = 0;
        this.attempt = 1;
        this.score = 0;
        this.maxScore = 0;
        this.visitedPoints = new LinkedList<>();
        this.autoWalking = autoWalking;
        this.continuePlaying = true;
    }

    //this is where the magic happens
    public void Play(String path) {
        this.maze = setMaze(Builder.build(path));
        explore();
        while (continuePlaying && attempt <= maxAttempts) {
            collectAndExit();
        }
    }

    private Maze setMaze(LinkedList<Point> points) {
        int size = points.remove(0).getCoins();
        Point[][] maze = new Point[size][size];
        points.forEach(point -> maze[point.getI()][point.getJ()] = point);
        return new Maze(size,maze);
    }

    private void collectAndExit() {
        while (currI != currJ || currI != 9) {
            move();
        }
    }

    private void move() {

    }

    private void explore() {

    }
}
