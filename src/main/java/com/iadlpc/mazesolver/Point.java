package com.iadlpc.mazesolver;

public class Point {

    private int i;
    private int j;
    private int coins;

    public Point(int i, int j, int coins) {
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

    public int getCoins() {
        return coins;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Point) obj).getI() == this.i && ((Point)obj).getJ() == this.j;
    }
}
