public class NotOkSemafor implements ISemafor {
    //dostepnosc true-dostępny
    private boolean _stan = true;
    private int _czeka = 0;

    public NotOkSemafor(boolean stan){
        _stan = stan;
    };

    // dekrementacja semafora
    public synchronized void P(){
        if(!_stan){
            try{
                this.wait();
            }catch(InterruptedException e){
                System.exit(-1);
            }
        }
        _stan = false;
    }

    // inkrementacja semafora
    public synchronized void V(){
        _stan = true;
        this.notifyAll();
    }

    public int getState(){
        return -1;
    }
}
