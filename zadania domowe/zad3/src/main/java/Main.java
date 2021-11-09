import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        int[] Ns = {5, 10, 100};
        int[] Ms = {10, 50, 100};
        for (var N : Ns) {
            for (var M : Ms) {
                FileWriter obj;
                try {
                    obj = new FileWriter("1data_" + N + "_" + M);
                    Table table = new Table(N);
                    Thread[] threads = new Thread[N];
                    Philosopher[] philosophers = new Philosopher[N];
                    for (int i = 0; i < N; i++) {
                        philosophers[i] = new Philosopher(i, N, M);
                    }
                    for (int i = 0; i < N; i++) {
                        int finalI = i;
                        threads[i] = new Thread(() -> {
                            philosophers[finalI].life1(table);
                        });
                    }
                    for (int i = 0; i < N; i++) {
                        threads[i].start();
                    }

                    for (int i = 0; i < N; i++) {
                        try {
                            threads[i].join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < N; i++) {
                        philosophers[i].writeData(obj);
                    }
                    obj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    exit(1);
                }
            }
        }
    }
}
