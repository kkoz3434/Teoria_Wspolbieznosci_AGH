package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.CSProcess;

/**
 * Consumer class:
 * reads one int from input channel, displays it,
 * then terminates.
 */

public class MyConsumerZad2 implements CSProcess {
    private One2OneChannelInt in;
    private int howMany;

    public MyConsumerZad2(One2OneChannelInt in, int howMany) {
        this.in = in;
        this.howMany = howMany;
    }

    public void run() {
        for (int i = 0; i < howMany; i++) {
            int item = in.in().read();
            System.out.println("Consumer got: " + item);
        }
    }

}
