package pl.edu.agh.macwozni.dmeshparallel.myProductions;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.production.AbstractProduction;

public class PW  extends AbstractProduction<Vertex> {
    public PW(Vertex _obj) {
        super(_obj);
    }

    @Override
    public Vertex apply(Vertex t1) {
        Vertex t2 = new Vertex(null, t1, null, null, "M");
        t1.setLeft(t2);
        return t2;
    }
}
