import java.util.concurrent.Semaphore;

public class Table {
    public int N;
    public Semaphore[] semaphores;
    public Semaphore waiter = new Semaphore(1);

    public Table(int n) {
        N = n;
        this.semaphores = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            this.semaphores[i] = new Semaphore(1);
        }
    }

    public boolean askForForks(int id) {
        int leftFork = id;
        int rightFork = (id + 1) % N;
        if (semaphores[leftFork].availablePermits() == 1 && semaphores[rightFork].availablePermits() == 1) {
            try {
                semaphores[leftFork].acquire();
                semaphores[rightFork].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
