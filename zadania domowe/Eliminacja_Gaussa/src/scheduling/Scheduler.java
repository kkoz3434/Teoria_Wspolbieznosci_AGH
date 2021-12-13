package scheduling;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.company.matrixPrepareService.PrepareMatrixService;
import com.company.operations.A;
import com.company.operations.B;
import com.company.operations.C;
import com.company.operations.Operation;

public class Scheduler {
    List<List<Operation>> FNF = new ArrayList<>();
    double[][] matrix;
    double[][] m;
    double[][][] n;
    String filename;
    int N;
    boolean isSilent;
    /***
    * Scheduler is main Class in this program: preparing matrix, making FNF and execute Gaussian Elimination.
     * isSilent: makes execution silent: on console appears only matrix red from file
     * fileName: file to read from (path to file)
     * matrix: in case of non reading from the file: pass array as parameter
     */


    public Scheduler(double[][] matrix, String filename, boolean isSilent) {
        this.matrix = matrix;
        this.filename = filename;
        this.N = matrix.length;
        m = new double[N][N + 1];
        n = new double[N][N][N + 1]; // new arrays with zeros inside
        this.isSilent = isSilent;
    }

    public Scheduler(String filename, boolean isSilent) {
        this.matrix = null;
        this.filename = filename;
        this.isSilent = isSilent;
    }

    public void execute() {
        prepareMatrix();
        createFNF();
        startMagic();

        if (isSilent) {
            PrepareMatrixService.saveToFile("result_" + filename, matrix);
        } else {
            printMatrix();
        }
    }

    private void prepareMatrix() {
        if (matrix == null) {
            double[][] fromFile;
            try {
                fromFile = PrepareMatrixService.loadfromFile(filename);
                System.out.println("Readed from file:");
                for (double[] doubles : fromFile) {
                    for (int j = 0; j < fromFile[0].length; j++) {
                        System.out.print(doubles[j] + " ");
                    }
                    System.out.println();
                }
                this.matrix = fromFile;
                this.N = matrix.length;
                m = new double[N][N + 1];
                n = new double[N][N][N + 1]; // new arrays with zeros inside

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createFNF() {
        // makes FNF subclasses for given N matrix
        int i = 0;
        while (i < N) {
            // firstly: A operations to get "m" factor later in program
            List<Operation> operationsA = new ArrayList<>();
            for (int k = i + 1; k < N; k++) {
                operationsA.add(new A(i, k, matrix, m));
            }

            // B and C operations: to do smt in our matrix later in program
            List<Operation> operationsB = new ArrayList<>();
            List<Operation> operationsC = new ArrayList<>();
            for (int k = i + 1; k < N; k++) {
                for (int j = i; j < N + 1; j++) {
                    operationsB.add(new B(i, k, j, matrix, m, n));
                    operationsC.add(new C(i, k, j, matrix, m, n));
                }
            }

            // save to list of lists
            FNF.add(operationsA);
            FNF.add(operationsB);
            FNF.add(operationsC);

            //control printing
            if (!isSilent) {
                printFNF(operationsA, operationsB, operationsC);
            }
            i++;
        }
    }

    public void startMagic() {
        while (!FNF.isEmpty()) {
            List<Operation> operations = FNF.get(0);
            executeOperationsList(operations);
            if (!isSilent) {
                printMatrix();
            }
            FNF.remove(0);
        }
    }

    private void executeOperationsList(List<Operation> operations) {
        List<Thread> threads = new ArrayList<>();
        if (!isSilent) {
            for (Operation op : operations) {
                System.out.println(op.toString() + " ");
            }
        }

        for (Operation op : operations) {
            threads.add(new Thread(op::execute));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N + 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printFNF(List<Operation> operationsA, List<Operation> operationsB, List<Operation> operationsC) {
        for (Operation operation : operationsA) {
            System.out.print(operation + " ");
        }
        System.out.println();
        for (Operation operation : operationsB) {
            System.out.print(operation + " ");
        }
        System.out.println();
        for (Operation operation : operationsC) {
            System.out.print(operation + " ");
        }
        System.out.println();
    }

}
