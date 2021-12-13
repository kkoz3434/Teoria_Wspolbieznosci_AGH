package scheduling;



import java.util.ArrayList;
import java.util.List;

import com.company.operations.A;
import com.company.operations.B;
import com.company.operations.C;
import com.company.operations.Operation;

public class Scheduler {
    float[][] matrix;
    String filename;
    int N;
    List<List<Operation>> FNF = new ArrayList<>();
    float[][] m;
    float[][][] n;


    public Scheduler(float[][] matrix, String filename) {
        this.matrix = matrix;
        this.filename = filename;
        this.N = matrix.length;
        m = new float[N][N+1];
        n = new float[N][N][N+1]; // new arrays with zeros inside
    }

    public void createFNF(){
        // makes FNF subclasses for given N matrix
        int i = 0;
        while(i<N){
            List<Operation> operationsA = new ArrayList<>();
            for (int k = i+1; k < N; k++) {
                operationsA.add(new A(i, k));
            }

            List<Operation> operationsB = new ArrayList<>();
            List<Operation> operationsC = new ArrayList<>();
            for (int k = i+1; k < N; k++) {
                for (int j = i; j < N+1; j++) {
                    operationsB.add(new B(i,k,j));
                    operationsC.add(new C(i,k,j));
                }
            }

            printFNF(operationsA, operationsB, operationsC);
            i++;
        }
    }



    void makeMagic(){

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
