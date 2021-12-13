package com.company.matrixPrepareService;

import java.io.*;
import java.util.Scanner;

public class PrepareMatrixService {
    public static double[][] loadfromFile(String path) throws FileNotFoundException {
            Scanner input = new Scanner(new BufferedReader(new FileReader(path)));
            int N = Integer.parseInt(input.nextLine());
            double[][] result = new double[N][N+1];
            for (int i = 0; i < N; i++) {
                String[] line = input.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    result[i][j] = Double.parseDouble(line[j]);
                }
            }
            String[] line = input.nextLine().trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                result[j][N] = Double.parseDouble(line[j]);
            }
            return result;
    }

    public static void saveToFile(String fileName, double[][] matrix){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                builder.append(matrix[i][j]);
                if(j < matrix.length - 1)
                    builder.append(" ");
            }
            builder.append("\n");
        }
        for(int j = 0; j < matrix.length; j++) {
            builder.append(matrix[j][matrix.length] + " ");
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(builder.toString());//save the string representation of the matrix
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
