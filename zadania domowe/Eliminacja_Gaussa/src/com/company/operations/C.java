package com.company.operations;

public class C implements Operation{
    int i;
    int k;
    int j;

    public C(int i, int k, int j){
        this.i = i;
        this.k = k;
        this.j = j;
    }
    float execute(float[][] matrix, float[][][] n){
        return matrix[k][j]-n[i][k][j];
    }
    @Override
    public String toString(){
        return "C"+i+""+k+""+j;
    }
}
