package pl.edu.agh.macwozni.dmeshparallel;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.mesh.GraphDrawer;
import pl.edu.agh.macwozni.dmeshparallel.myProductions.PI;
import pl.edu.agh.macwozni.dmeshparallel.parallelism.BlockRunner;
import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;

public class Executor extends Thread {

    private final BlockRunner runner;
    private int N;

    public Executor(BlockRunner _runner, int N) {
        this.runner = _runner;
        this.N = N;
    }

    @Override
    public void run() {
        // create all threads and run them to properly work
        PDrawer drawer = new GraphDrawer();


        //prepare first proccess to do the job
        Vertex start = new Vertex(null, null, null, null, "B");
        PI piStart = new PI(start);
        this.runner.addThread(piStart);
        this.runner.startAll();

        Vertex begin = piStart.getObj(); //getting the result
        Vertex curr;
        Vertex join;

        for (int i = 0; i < (2*N-2); i++) {
            curr = start;
            join = start.getRight();
        }







    }
}
