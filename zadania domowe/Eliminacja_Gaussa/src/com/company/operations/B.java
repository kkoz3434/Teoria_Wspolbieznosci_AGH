package com.company.operations;

public class B implements Operation{
    int i;
    int k;
    int j;

    public B(int i, int k, int j){
        this.i = i;
        this.k = k;
        this.j = j;
    }
    float execute(float[][] matrix, float[][] m){
        return matrix[i][j] * m[i][k];
    }
    @Override
    public String toString(){
        return "B"+i+""+k+""+j;
    }
}
