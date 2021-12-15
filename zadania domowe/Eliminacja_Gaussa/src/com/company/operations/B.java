package com.company.operations;

public class B implements Operation {
    int i;
    int k;
    int j;
    double[][] matrix;
    double[][] m;
    double[][][] n;

    /**
     * @param i diagonal
     * @param k row to substract
     * @param matrix matrix NxN+1
     * @param m array of factors
     * @param n 3D array of numbers after multiplification
     * @param j column index
     */

    public B(int i, int k, int j, double[][] matrix, double[][] m,double[][][] n) {
        this.i = i;
        this.k = k;
        this.j = j;
        this.matrix = matrix;
        this.n = n;
        this.m = m;
    }

    public void execute(){
        n[i][k][j]= matrix[i][j] * m[i][k];
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
        return "B" + i + "" + k + "" + j;
    }
}
