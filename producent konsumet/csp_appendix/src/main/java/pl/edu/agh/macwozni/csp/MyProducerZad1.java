package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.CSProcess;


public class MyProducerZad1 implements CSProcess {

  private One2OneChannelInt out[], in[];
  private int howMany;

  public MyProducerZad1(One2OneChannelInt[] out, One2OneChannelInt[] in, int howMany) {
    this.out = out;
    this.in = in;
    this.howMany = howMany;
  }

  // first implementation -> without order
  public void run() {
    final Guard[] guards = new Guard[in.length];
    for (int i = 0; i < out.length; i++) {
      guards[i] = in[i].in();
    }
    final Alternative alternative = new Alternative(guards);

    for (int i = 0; i < howMany; i++) {
      int index = alternative.select();
      in[index].in().read();
      int toWrite = i;
      System.out.println("Producer writing: "+toWrite);
      out[index].out().write(toWrite);
    }
  }



} // class Producer