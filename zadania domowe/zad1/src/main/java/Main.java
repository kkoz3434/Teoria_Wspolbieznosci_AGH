import static java.lang.System.exit;

public class Main {
    public static boolean isCounting = false;
    public static boolean isSemaphoreOk = true;
    public static int M = 1000;
    public static int N = 1000;
    public static int X = 100;
    public static int common = 0;
    public static ISemafor semafor;

    public static void increment() {
        semafor.P();
        common++;
        //System.out.println("@@@@ Zwiększony do: " + common + "\n");
        semafor.V();
    }

    public static void decrement() {
        semafor.P();
        common--;
        //System.out.println("#### Zmniejszony do: " + common + "\n");
        semafor.V();
    }

    private static void testCSemafor() {
        Thread increment[] = new Thread[M];
        Thread decrement[] = new Thread[M];
        System.out.println(semafor.getState());

        for (int i = 0; i < M; i++) {
            increment[i] = new Thread(() -> {
                for (int k = 0; k < N; k++) {
                    semafor.V();
                }
            });

            decrement[i] = new Thread(() -> {
                for (int k = 0; k < N; k++) {
                    semafor.P();
                }
            });
            increment[i].start();
            decrement[i].start();
        }
        for (int i = 0; i < M; i++) {
            try {
                increment[i].join();
                decrement[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ostateczna wartość semafora: " + semafor.getState() + "\n");
    }

    private static void testSemaphore() {
        Thread increment[] = new Thread[M];
        Thread decrement[] = new Thread[M];

        for (int i = 0; i < M; i++) {
            increment[i] = new Thread(() -> {
                for (int k = 0; k < N; k++) {
                    increment();
                }
            });

            decrement[i] = new Thread(() -> {
                for (int k = 0; k < N; k++) {
                    decrement();
                }
            });
            increment[i].start();
            decrement[i].start();
        }
        for (int i = 0; i < M; i++) {
            try {
                increment[i].join();
                decrement[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ostateczna wartość common: " + common + "\n");
    }

    /***
     * 1. Semafor binarny w klasie semafor
     * 2.w dokumentacji:
     *   A thread can wake up without being notified, interrupted, or timing out, a so-called spurious wakeup.
     *   Dlatego warto oblożyć go whilem, aby po takim przebudzeniu móc sprawdzić warunek.
     *
     * @
     *   Po odpaleniu programu kilkukrotnie na dużych M i N, udało się uzyskać nieprawidłowy licznik.
     * @
     *
     *
     * 3. CountingSemafor zaimplementowany, pilnujący góra-dół przedziału
     *
     *
     * ***/

    public static void main(String[] args) {
        if (isCounting) {
            semafor = new CountingSemafor(X);
            testCSemafor();
        } else {
            if (isSemaphoreOk)
                semafor = new Semafor(true);
            else
                semafor = new NotOkSemafor(true);
            testSemaphore();
        }
    }


}
