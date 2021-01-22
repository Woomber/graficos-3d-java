package figuras;

import matrices.plano.Punto3D;

/*

00--01
|    |
|    02--03
|         |
|    05--04
|    |
07--06

(08 - 15 igual que 00 - 07 pero en z=2*size)

 */

public class FiguraTetris extends Figura {

    public FiguraTetris() {
        this(50);
    }

    public FiguraTetris(int size) {
        addVertices(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(0, 0, size),
                new Punto3D(size, 0, size),
                new Punto3D(size, size, size),
                new Punto3D(2*size, size, size),
                // 4 5 6 7
                new Punto3D(2*size, 2*size, size),
                new Punto3D(size, 2*size, size),
                new Punto3D(size, 3*size, size),
                new Punto3D(0, 3*size, size),
                // 8 9 10 11
                new Punto3D(0, 0, 2*size),
                new Punto3D(size, 0, 2*size),
                new Punto3D(size, size, 2*size),
                new Punto3D(2*size, size, 2*size),
                // 12 13 14 15
                new Punto3D(2*size, 2*size, 2*size),
                new Punto3D(size, 2*size, 2*size),
                new Punto3D(size, 3*size, 2*size),
                new Punto3D(0, 3*size, 2*size),
        });


        for(int idx = 0; idx < 8; idx++) {
            for(int z = 0; z < 2; z++) {
                // Conectar plano frontal y trasero
                addArista(vertices.get(8 * z + idx), vertices.get(8 * z + (idx + 1)%8));
            }
            // Conectar planos
            addArista(vertices.get(idx), vertices.get(idx + 8));
        }
    }
}
