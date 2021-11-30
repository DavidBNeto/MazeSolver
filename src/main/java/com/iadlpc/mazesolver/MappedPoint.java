package com.iadlpc.mazesolver;

public class MappedPoint extends Point implements Comparable<MappedPoint>{

    private int weight;
    private int distanceToExit;

    public MappedPoint(int i, int j, int coins) {
        super(i,j,coins);
        this.weight = 0;
        this.distanceToExit = 18 - i - j;
    }

    public int getWeight() {
        return weight;
    }

    public int getDistanceToExit() { return distanceToExit; }

    public void setWeight(int weight) { this.weight = weight;}

    @Override
    public int compareTo(MappedPoint o) {
        return o.weight - this.weight;
    }
}
