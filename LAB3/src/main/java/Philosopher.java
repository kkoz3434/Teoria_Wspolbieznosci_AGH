import java.util.concurrent.TimeUnit;

import static java.lang.Math.random;

public class Philosopher extends Object {
    public boolean isEating = false;
    public boolean isHungry = true;

    int myNumber;
    int amount;


    public Philosopher(int number, int amount) {
        this.myNumber = number;
        this.amount = amount;
    }

    public synchronized boolean getLeftFork(boolean[] forks) {
        if (forks[myNumber] == true) {
            forks[myNumber] = false;
            System.out.println("Phil: " + myNumber + " got left fork");
            return true;
        }
        return false;
    }

    public synchronized void releaseLeftFork(boolean[] forks){
        forks[myNumber] = true;
    }


    public synchronized boolean getRightFork(boolean[] forks) {
        if ((forks[(myNumber + 1) % amount]) == true) {
            forks[(myNumber + 1) % amount] = false;
            System.out.println("Phil: " + myNumber + " got right fork");
            return true;
        }
        return false;
    }

    public synchronized void releaseRigthFork(boolean[] forks){
        forks[(myNumber + 1) % amount] = true;
    }

    public synchronized boolean getBothForks(boolean[] forks) {
        if ((forks[myNumber] == true) && ((forks[(myNumber + 1) % amount]) == true)) {
            forks[myNumber] = false;
            forks[(myNumber + 1) % amount] = false;
            return true;
        }
        return false;
    }

    public void eating(boolean[] forks) {
        this.isEating = true;
        try {
            System.out.println("Phil: " + myNumber + " is eating");
            TimeUnit.SECONDS.sleep((int) (random() * (10 - 5) + 5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isHungry = false;
    }

    public synchronized void releaseForks(boolean[] forks) {
        forks[myNumber] = true;
        forks[(myNumber + 1) % amount] = true;
    }

    public void sleeping() {
        try {
            System.out.println("Phil: " + myNumber + " is sleeping");
            TimeUnit.SECONDS.sleep((int) (random() * (2 - 1) + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void life1(boolean[] forks) {
        while (1 > 0) {
            try {
                TimeUnit.SECONDS.sleep((int) (random() * (10 - 1) + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getLeftFork(forks)) {
                if (getRightFork(forks)) {
                    eating(forks);
                    releaseForks(forks);
                    sleeping();
                }
            }
        }
    }

    public void life2(boolean[] forks) {
        while (1 > 0) {
            if (getBothForks(forks)) {
                eating(forks);
                releaseForks(forks);
                sleeping();

            }
        }
    }

    public void life3(boolean[] forks) {
        while (1 > 0) {
            if ((myNumber % 2) == 0) {
                if (getLeftFork(forks)) {
                    if (getRightFork(forks)) {
                        eating(forks);
                        releaseForks(forks);
                        sleeping();
                    }
                    releaseLeftFork(forks);
                }
            } else {
                if (getRightFork(forks)) {
                    if (getLeftFork(forks)) {
                        eating(forks);
                        releaseForks(forks);
                        sleeping();
                    }
                    releaseRigthFork(forks);
                }
            }
        }
    }

}
