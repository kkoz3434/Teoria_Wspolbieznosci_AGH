import java.util.concurrent.Semaphore;

public class CountingSemafor implements ISemafor{
    public int state;
    public int maxState;
    public Semafor available = new Semafor(true);
    public Semafor toDecrement = new Semafor(true);
    public Semafor toIncrement = new Semafor(false);

    public CountingSemafor(int maxState) {
        this.maxState = maxState;
        this.state = maxState;
    }

    public void P() {
        toDecrement.P();
        available.P();
        state--;
        if(state>=1){
            toDecrement.V();
        }if(state<maxState){
            toIncrement.V();
        }
        available.V();
    }

    public void V() {
        toIncrement.P();
        available.P();
        state++;
        if(state<maxState){
            toIncrement.V();
        }
        if(state >= 1){
            toDecrement.V();
        }
        available.V();
    }

    public int getState() {
        return state;
    }
}
