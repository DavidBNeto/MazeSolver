package com.iadlpc.mazesolver;

import java.util.Random;

public class Maze {

    private int size;
    private double[] fov;
    private Point[][] maze;
    private Network nn;

    public Maze(int size, Point[][] maze){
        this.size = size;
        this.maze = maze;
        this.fov = new double[8]; // 2 * quantidade de movimentos
    }

    public void setUp(){
        this.nn = new Network(8,4);  // topologia da rede: 8 neurônios na camada oculta e 4 na de saída

        Random generator = new Random();
        int weightCount = 9 * 8 + 9 * 4;  // 9= 8 entradas + bias , 8 neuronios na camada oculta e 4 na camada de saída
        double[] chromosome = new double[weightCount];

        for(int i = 0; i< chromosome.length; i++){
            chromosome[i]=generator.nextDouble();
            if(generator.nextBoolean()) chromosome[i] = chromosome[i] * -1;
        }

        nn.balanceNetwork(8, chromosome);
    }

    public double[] updateFoV(int currI, int currJ) {
        int index=0;

        if(currI - 1 < 0) { fov[index] = 0.0007; }
        else { fov[index] = getPoint(currI - 1,currJ).getCoins(); }
        index++;
        fov[index] = 1.0 / (distance(currI-1,currJ,size - 1, size - 1));
        index++;

        if(currJ - 1 < 0) { fov[index] = 0.0007; }
        else { fov[index] = getPoint(currI,currJ-1).getCoins(); }
        index++;
        fov[index] = 1.0 / (distance(currI,currJ-1,size - 1, size - 1));
        index++;

        if(currI + 1 >= size) { fov[index] = 0.0007; }
        else { fov[index] = getPoint(currI + 1,currJ).getCoins(); }
        index++;
        fov[index] = 1.0 / (distance(currI+1,currJ,size - 1, size - 1));
        index++;

        if(currJ + 1 >= size) { fov[index] = 0.0007; }
        else { fov[index] = getPoint(currI,currJ + 1).getCoins(); }
        index++;
        fov[index] = 1.0 / (distance(currI,currJ+1,size - 1, size - 1));
        index++;
        return fov; //[up,up-distance,left,left-distance,down,down-distance,right,right-distance]
    }

    public int distance(int originI, int originJ, int destinationI, int destinationJ){
        return Math.abs(originI - destinationI) + Math.abs(originJ - destinationJ);
    }

    public void printFoV(){
        System.out.println(String.format("Acima    : %s | Distância -> Saída : %.7f", getMeaning(fov[0]), fov[1]));
        System.out.println(String.format("Esquerda : %s | Distância -> Saída : %.7f", getMeaning(fov[2]), fov[3]));
        System.out.println(String.format("Abaixo   : %s | Distância -> Saída : %.7f", getMeaning(fov[4]), fov[5]));
        System.out.println(String.format("Direita  : %s | Distância -> Saída : %.7f", getMeaning(fov[6]), fov[7]));
    }

    public String getMeaning(double i) {
        if (i == 10.0) return "Saída";
        if (i == 5.0) return "Moeda";
        if (i == 0.1) return "Caminho";
        if (i == 0.0) return "Entrada";
        if (i == 0.01) return "Parede";
        if (i == 0.0007) return "Fora do Labirinto";
        return "erro";
    }

    public Point getPoint(int i, int j) { return maze[i][j]; }

    public void setPoint(int i, int j, Point point) { maze[i][j] = point; }

    public int getSize() { return this.size; }

    public double[] propagate() {
        double[] output = this.nn.propagate(this.fov);
        for (int i = 0; i < fov.length; i = i+2) {
            fov[i] = output[i/2];
        }
        return output;
    }

    public void printNN() { System.out.println(this.nn.toString()); }

}
