public class Semafor implements ISemafor {
    //dostepnosc true-dostępny
    private boolean _stan = true;
    private int _czeka = 0;

    public Semafor(boolean stan){
        this._stan = stan;
    };

    // dekrementacja semafora
    public synchronized void P(){
        while(!_stan){
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

    public boolean is_stan() {
        return _stan;
    }

    public int get_czeka() {
        return _czeka;
    }

    public int getState(){
        return -1;
    }
}
