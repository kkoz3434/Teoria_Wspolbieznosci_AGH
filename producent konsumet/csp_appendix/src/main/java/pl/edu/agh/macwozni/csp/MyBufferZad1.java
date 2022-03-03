package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class MyBufferZad1 implements CSProcess {
    private One2OneChannelInt in;
    private One2OneChannelInt out;
    private One2OneChannelInt rest;

    public MyBufferZad1(One2OneChannelInt in, One2OneChannelInt out, One2OneChannelInt rest) {
        this.in = in;
        this.out = out;
        this.rest = rest;
    }

    @Override
    public void run() {
        while(true){
            rest.out().write(0);
            out.out().write(in.in().read());
        }
    }
}
