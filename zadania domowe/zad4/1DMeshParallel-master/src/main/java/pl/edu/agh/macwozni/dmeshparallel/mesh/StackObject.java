package pl.edu.agh.macwozni.dmeshparallel.mesh;

public class StackObject {
    int index;
    Vertex vertex;

    public StackObject(int index, Vertex vertex) {
        this.index = index;
        this.vertex = vertex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
}
