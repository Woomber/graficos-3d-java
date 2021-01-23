package figuras;

import matrices.MatrizTraslacion;
import matrices.plano.Punto3D;

/*

00--01
|    |
03--02

(04 - 07 igual que 00 - 03 pero en z = 2*size)

 */

public class FiguraCubo extends Figura {

    public FiguraCubo() {
        this(50);
    }

    public FiguraCubo(int size) {
        addVertices(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(0, 0, size),
                new Punto3D(size, 0, size),
                new Punto3D(size, size, size),
                new Punto3D(0, size, size),
                // 4 5 6 7
                new Punto3D(0, 0, 2*size),
                new Punto3D(size, 0, 2*size),
                new Punto3D(size, size, 2*size),
                new Punto3D(0, size, 2*size),
        });

        // Mover al origen
        for(Punto3D p : getVertices()) {
            p.transform(new MatrizTraslacion( -25, -25, -75));
        }

        for(int idx = 0; idx < 4; idx++) {
            for(int z = 0; z < 2; z++) {
                // Conectar plano frontal y trasero
                addArista(vertices.get(4 * z + idx), vertices.get(4 * z + (idx + 1)%4));
            }
            // Conectar planos
            addArista(vertices.get(idx), vertices.get(idx + 4));
        }
    }
}
