public class Main {

    static int N=100;

    public static void main(String args[]){
        Table table = new Table(N);
        Thread[] threads = new Thread[N];
        Philosopher[] philosophers = new Philosopher[N];
        for(int i=0; i<N; i++){
            philosophers[i]= new Philosopher(i, N);
        }
        for(int i=0; i<N; i++){
            int finalI = i;
            threads[i] = new Thread(()->{
                philosophers[finalI].life2(table);
            });
            //threads[i].start();
        }
        for(int i=0; i<N; i++){
            threads[i].start();
        }

        for(int i=0; i<N; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
