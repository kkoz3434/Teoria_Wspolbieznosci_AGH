package pl.edu.agh.macwozni.dmeshparallel.production;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.parallelism.MyLock;

public abstract class AbstractProduction<P> implements IProduction<P> {

    private MyLock lock;
    private final PThread thread = new PThread();
    private P obj;
    private P result;
    private PDrawer<P> drawer;
    private int N;

    public AbstractProduction(P _obj, PDrawer<P> _drawer) {
        this.obj = _obj;
        this.drawer = _drawer;
        this.N = N;
    }

    public AbstractProduction(Vertex obj) {
    }

    @Override
    public P getObj() {
        return this.result;
    }

    //run the thread
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void join() throws InterruptedException {
        thread.join();
    }

    @Override
    public void injectRefs(MyLock _lock) {
        this.lock = _lock;
    }

    private class PThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            result = apply(obj);
            drawer.draw(result, N);
        }
    }
}
