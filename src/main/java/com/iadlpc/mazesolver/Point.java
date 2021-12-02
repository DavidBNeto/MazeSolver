package com.iadlpc.mazesolver;

public class Point {

    private int i;
    private int j;
    private double coins;

    public Point(int i, int j, double coins) {
        this.i = i;
        this.j = j;
        this.coins = coins;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public double getCoins() {
        return coins;
    }

    @Override
    public String toString() {
        return "Point{" +
                "i=" + i +
                ", j=" + j +
                ", coins=" + coins +
                '}';
    }

}
