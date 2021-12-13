package com.company.operations;

public class A implements Operation{
    int i;
    int k;
    public A(int i, int k){
        this.i = i;
        this.k = k;
    }
    float execute(float[][] matrix){
        return matrix[i][i]/matrix[k][i];
    }
    @Override
    public String toString(){
        return "A"+i+""+k;
    }

}
