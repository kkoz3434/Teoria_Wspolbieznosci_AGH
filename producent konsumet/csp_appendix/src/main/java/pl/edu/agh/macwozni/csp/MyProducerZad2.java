package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.CSProcess;


public class MyProducerZad2 implements CSProcess {

    private One2OneChannelInt out;
    private int howMany;

    public MyProducerZad2(One2OneChannelInt out, int howMany) {
        this.out = out;
        this.howMany = howMany;
    }

    // first implementation -> without order
    public void run() {
        for (int i = 0; i < howMany; i++) {
            int toWrite = i;
            System.out.println("Producer writing: "+toWrite);
            out.out().write(toWrite);
        }
        System.exit(0);
    }



} // class Producer