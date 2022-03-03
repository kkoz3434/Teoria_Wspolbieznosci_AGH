import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        int N = 100;
        int Z = 10;

        MyLinkedList myLinkedList = new MyLinkedList();

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < Z; j++) {
                    myLinkedList.add("NODE"+j+"_" + Thread.currentThread());

                }
                for (int j = 1; j < Z; j++) {
                    myLinkedList.remove("NODE"+j+"_" + Thread.currentThread());
                }
            });
            threads.add(thread);
        }
        long start = System.currentTimeMillis();
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
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed time: "+ (stop-start) + "ms with " + N + " threads with "+ 2*Z + " operations");
        //System.out.println(myLinkedList.countElements());
        myLinkedList.printList();

    }
}
