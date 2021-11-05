package com.company;


class Incrementator {
    int value;

    public Incrementator() {
        value = 0;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public synchronized void incrementS() {
        value++;
    }

    public synchronized void decrementS() {
        value--;
    }

}


public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {

        /*
        int N = 100000;
        int M = 1;
        for (int i = 0; i < M; i++) {
            long startTime = System.nanoTime();
            Incrementator incrementator = new Incrementator();
            Thread thread1 = new Thread() {
                public void run() {
                    for (int k = 0; k < N; k++) {
                        for (int i = 0; i < N; i++) {
                            incrementator.increment();
                        }
                    }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int k = 0; k < N; k++) {
                        for (int i = 0; i < N; i++) {
                            incrementator.decrement();
                        }
                    }
                }
            };

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            long time1 = System.nanoTime() - startTime;

            startTime = System.nanoTime();

            Thread thread3 = new Thread() {
                public void run() {
                    for (int k = 0; k < N; k++) {
                        for (int i = 0; i < N; i++) {
                            incrementator.incrementS();
                        }
                    }
                }
            };
            Thread thread4 = new Thread() {
                public void run() {
                    for (int k = 0; k < N; k++) {
                        for (int i = 0; i < N; i++) {
                            incrementator.decrementS();
                        }
                    }
                }
            };

            thread3.start();
            thread4.start();

            thread3.join();
            thread4.join();

            long time2 = System.nanoTime() - startTime;

            System.out.println("  S: " + time2 + "  NS: " + time1 + "   S/NS: " + (time2 / time1));
        }

    }
*/
        for(int i = 0; i>=0; i++){
            Thread thread2 = new Thread() {
                public void run() {
                    int a = 0, b=0;
                    while(1>0){
                        a++;
                        b++;
                        a--;
                        b--;
                    }
                }
                };
            thread2.start();
            System.out.println(i);
            }

        }

}


/***
 * semafor binarny na wait() i notify()
 * projekt maven -> doczytać dokumentację
 * TYLKO ZIP
 */