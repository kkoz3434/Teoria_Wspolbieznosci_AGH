package com.company.operations;

public class C implements Operation {
    int i;
    int k;
    int j;
    double[][] matrix;
    double[][] m;
    double[][][] n;

    public C(int i, int k, int j, double[][] matrix, double[][] m, double[][][] n) {
        this.i = i;
        this.k = k;
        this.j = j;
        this.matrix = matrix;
        this.m = m;
        this.n = n;
    }

    public void execute() {
        matrix[k][j] = matrix[k][j] - n[i][k][j];
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "C" + i + "" + k + "" + j;
    }
}
