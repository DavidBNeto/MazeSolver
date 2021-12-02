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
    public void play(String path) {
        this.maze = setMaze(Builder.build(path));
        explore();
    }

    private Maze setMaze(LinkedList<Point> points) {
        int size = ((int) points.remove(0).getCoins());
        Point[][] maze = new Point[size][size];
        points.forEach(point -> maze[point.getI()][point.getJ()] = point);
        return new Maze(size,maze);
    }

    private boolean move(double[] outputLayer) {
        printOutput(outputLayer);
        int move = 0;
        for(int i=1; i < outputLayer.length; i++) {
            if (outputLayer[i] > outputLayer[move]) move = i;
        }
        //up-left-down-right
        if (move == 0) {
            currI = currI - 1;
            System.out.println("Cima");
        } else if (move == 1) {
            currJ = currJ - 1;
            System.out.println("Esquerda");
        } else if (move == 2) {
            currI = currI + 1;
            System.out.println("Baixo");
        } else {
            currJ = currJ + 1;
            System.out.println("Direita");
        }
        if(currJ < 0 || currI < 0) return false;
        double coins = maze.getPoint(currI, currJ).getCoins();
        if (coins == 0.01) {
            score = score - 10;
        } else {
            score = score + (int) coins;
        }
        if (coins == 5.0) {
            currCoins = currCoins + (int) coins;
            this.maze.setPoint(currI,currJ,this.maze.getPoint(currI,currJ).setCoins(0.01));
        }
        return true;
    }

    private void explore() {
        maze.setUp();
        maze.updateFoV(currI, currJ);
        System.out.println("START!");
        printStuff();
        if (!move(maze.propagate())) {
            System.out.println("Game over!");
        } else
            while (currI < maze.getSize() - 1 || currJ < maze.getSize() - 1) {
                maze.updateFoV(currI, currJ);
                printStuff();
                if (!move(maze.propagate())) {
                    System.out.println("Game over!");
                    break;
                }
            }
    }

    private void printStuff(){
        System.out.println(String.format("Pontos: %d  |  Moedas: %d  |  Posição: %d,%d", this.score,this.currCoins, this.currI, this.currJ));
        System.out.println(String.format("----------------------------------------------------"));
        maze.printFoV();
        System.out.println(String.format("----------------------------------------------------"));
        maze.printNN();
    }

    private void printOutput(double[] output) {
        for (int i = 0; i < 4; i++) {
            System.out.printf("Neuronio %d: %.7f \n", i, output[i]);
        }
    }
}
