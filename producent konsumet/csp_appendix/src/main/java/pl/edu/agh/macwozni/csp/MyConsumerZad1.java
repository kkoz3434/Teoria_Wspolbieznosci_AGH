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

public class MyConsumerZad1 implements CSProcess {
  private One2OneChannelInt[] in;
  private int howMany;


  public MyConsumerZad1(One2OneChannelInt[] in, int howMany) {
    this.in = in;
    this.howMany = howMany;
  }


  public void run() {


    final Guard[] guards = new Guard[in.length];
    for (int i = 0; i < in.length; i++) {
      guards[i] = in[i].in();
    }

    final Alternative alternative = new Alternative(guards);
    for (int i = 0; i < howMany; i++) {
      int index = alternative.select();
      int item = in[index].in().read();
      System.out.println("Consumer got: " + item);
    }
  } // run

} // class Consumer
