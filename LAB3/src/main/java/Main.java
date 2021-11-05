public class Main {

    public static int N = 15;
    public static void main(String[] argv){
        boolean[] forks = new boolean[N];  // true if fork available
        Philosopher[] philosophers = new Philosopher[N];

        Thread[] threads = new Thread[N];

        for(int i=0; i<N; i++){
            forks[i]=true;
            philosophers[i]= new Philosopher(i, N);
        }

        for(int i=0; i<N; i++){
            int finalI = i;
            threads[i] = new Thread(()->{
                philosophers[finalI].life1(forks);
            });
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
    /// just git comment
}
