package meshes;

import matrices.plano.Punto2D;

public class MeshRectangular extends Mesh2D {

    public MeshRectangular(int pointsX, int pointsY, double separation) {
         for(int x = 0; x < pointsX; x++) {
             for(int y = 0; y < pointsY; y++) {
                 Punto2D p1 = new Punto2D(x*separation, y*separation);
                 if(x != pointsX - 1) {
                    Punto2D p2 = new Punto2D((x + 1)*separation, (y)*separation);
                    addArista(p1, p2);
                 }
                 if(y != pointsY - 1) {
                    Punto2D p3 = new Punto2D((x)*separation, (y + 1)*separation);
                    addArista(p1, p3);
                 }
             }
         }
    }

}
