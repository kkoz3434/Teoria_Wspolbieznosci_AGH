package pl.edu.agh.macwozni.dmeshparallel.myProductions;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;
import pl.edu.agh.macwozni.dmeshparallel.production.AbstractProduction;
import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;

public class PI extends AbstractProduction<Vertex> {

    public PI(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    public PI(Vertex obj) {
        super(obj);
    }

    @Override
    public Vertex apply(Vertex s) {
        return new Vertex(null, null, null, null, "M");
    }
}

