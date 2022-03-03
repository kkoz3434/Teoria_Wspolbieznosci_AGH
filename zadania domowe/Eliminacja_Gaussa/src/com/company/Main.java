package com.company;

import com.company.matrixPrepareService.PrepareMatrixService;
import scheduling.Scheduler;

import java.io.FileNotFoundException;

public class Main {
    /***
     * Program does Gaussian Elimination concurrently, using Foaty normal form
     *
     */

    public static void main(String[] args) {

       for (int i = 1; i < 7; i++) {
            Scheduler scheduler = new Scheduler("matrix"+i+".txt" , true);
            scheduler.execute();
        }
    }
}

//21.5