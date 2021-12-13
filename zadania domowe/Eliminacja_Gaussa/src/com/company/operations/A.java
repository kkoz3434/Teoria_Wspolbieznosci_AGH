package com.company.operations;

public class A implements Operation{
    int i;
    int k;
    double[][] matrix;
    double[][] m;
    public A(int i, int k, double[][] matrix, double[][] m){
        this.i = i;
        this.k = k;
        this.matrix = matrix;
        this.m =m;
    }
    public void execute(){
        m[i][k] = matrix[k][i]/matrix[i][i];
    }
    @Override
    public String toString(){
        return "A"+i+""+k;
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
}
