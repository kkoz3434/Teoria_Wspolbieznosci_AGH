package pl.edu.agh.macwozni.dmeshparallel.myProductions;

import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;

public class PJ {
        public synchronized void apply(Vertex t2) {
            Vertex leftToJoin = t2.getLeft().getmLower();
            Vertex rightToJoin = t2.getmLower();
            if(leftToJoin != null && rightToJoin != null){
                leftToJoin.setRight(rightToJoin);
                rightToJoin.setLeft(leftToJoin);
            }
            //return t2;
        }
    }


