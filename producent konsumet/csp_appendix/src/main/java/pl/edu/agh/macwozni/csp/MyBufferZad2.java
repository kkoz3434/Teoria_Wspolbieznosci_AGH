package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class MyBufferZad2 implements CSProcess {
    private One2OneChannelInt in;
    private One2OneChannelInt out;


    public MyBufferZad2(One2OneChannelInt in, One2OneChannelInt out ){
        this.in = in;
        this.out = out;

    }

    @Override
    public void run() {
        while(true){
            out.out().write(in.in().read());
        }
    }
}
