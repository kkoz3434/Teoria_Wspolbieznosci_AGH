package pl.edu.agh.macwozni.dmeshparallel.myProductions;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;

public class PN {
    public Vertex apply(Vertex t1) {
        Vertex t2 = new Vertex(null, null, null, t1, "M");
        t1.setmLower(t2);
        return t2;
    }
}
