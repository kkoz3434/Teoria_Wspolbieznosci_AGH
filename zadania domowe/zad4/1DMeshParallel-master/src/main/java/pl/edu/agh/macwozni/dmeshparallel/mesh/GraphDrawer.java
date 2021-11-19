package pl.edu.agh.macwozni.dmeshparallel.mesh;

import pl.edu.agh.macwozni.dmeshparallel.production.PDrawer;
import pl.edu.agh.macwozni.dmeshparallel.mesh.Vertex;

import java.sql.Array;

public class GraphDrawer implements PDrawer<Vertex> {

    public void makeArray(Vertex v, String[][] arr, int row, int column) {
        arr[row][column] = v.toString();
        if (v.getmUpper() != null) {
            arr[row - 1][column] = " | ";
            makeArray(v.getmUpper(), arr, row - 2, column);
        }
        if (v.getLeft() != null) {
            makeArray(v.getLeft(), arr, row, column - 1);
        }
    }

    @Override
    public void draw(Vertex v, int N) {
        String[][] arr = new String[2 * N - 1][N];
        int row = 2 * N - 1;
        int column = N;
        makeArray(v, arr, row, column);
        for (int i = 0; i < (2 * N - 1); i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }
}
