package pl.edu.agh.macwozni.csp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.Channel;

/**
 * Main program class for Producer/Consumer example.
 * Sets up channel, creates one of each process
 * then executes them in parallel, using JCSP.
 */

public final class PCMain2 {

    public static void main(String[] args) {
        new PCMain2();
    } // main

    public PCMain2(){
        int howMany = 100000;
        int channelsAmount = 10;
        final One2OneChannelInt[] channels = channelFactory(channelsAmount+2);
        CSProcess[] procList = new CSProcess[channelsAmount + 2];
        procList[0] = new MyProducerZad2(channels[0], howMany);
        procList[1] = new MyConsumerZad2(channels[channelsAmount], howMany);
        for (int i = 0; i < channelsAmount; i++) {
            procList[i+2] = new MyBufferZad2(channels[i], channels[i+1]);
        }

        Parallel par = new Parallel(procList);
        par.run();
    }

/*
    public PCMain2() {
        int howMany = 1000;
        int channelsAmount = 10;
        final One2OneChannelInt[] prod = channelFactory(channelsAmount);
        final One2OneChannelInt[] cons = channelFactory(channelsAmount);
        final One2OneChannelInt[] rest = channelFactory(channelsAmount);


        CSProcess[] procList = new CSProcess[channelsAmount + 2];
        procList[0] = new MyProducerZad1(prod, rest, howMany);
        procList[1] = new MyConsumerZad1(cons, howMany);
        for (int i = 0; i < channelsAmount; i++) {
            procList[i+2] = new MyBufferZad1(prod[i], cons[i], rest[i]);
        }

        Parallel par = new Parallel(procList);
        par.run();
    }
*/
    public One2OneChannelInt[] channelFactory(int N) {
        One2OneChannelInt[] result = new One2OneChannelInt[N];
        for (int i = 0; i < N; i++) {
            result[i] = Channel.one2oneInt();
        }
        return result;
    }


    /*
      public PCMain2() { // Create channel object
        final One2OneChannelInt[] prodChan = {
            (One2OneChannelInt) Channel.one2oneInt(),
            (One2OneChannelInt) Channel.one2oneInt()
        };

        final One2OneChannelInt[] consReq = {
            (One2OneChannelInt) Channel.one2oneInt(),
            (One2OneChannelInt) Channel.one2oneInt()
        };

        final One2OneChannelInt[] consChan = {
            (One2OneChannelInt) Channel.one2oneInt(),
            (One2OneChannelInt) Channel.one2oneInt()
        };

        // Create parallel construct
        CSProcess[] procList = { new Producer2(prodChan[0], 0),
            new Producer2(prodChan[1], 100),
            new Buffer(prodChan, consReq, consChan),
            new Consumer2(consReq[0], consChan[0]),
            new Consumer2(consReq[1], consChan[1])
        }; // Processes
        Parallel par = new Parallel(procList); // PAR construct
        par.run(); // Execute processes in parallel
      } // PCMain constructor
    */


} // class PCMain
