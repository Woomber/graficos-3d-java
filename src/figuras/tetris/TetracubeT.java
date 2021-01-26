package figuras.tetris;

import matrices.plano.Punto3D;

/*

00--01
|    |
|    02--03
|         |
|    05--04
|    |
07--06

 */

public class TetracubeT extends Tetracube {

    public TetracubeT() {
        setupShape();
    }

    public TetracubeT(int scale) {
        super(scale);
        setupShape();
    }

    @Override
    public void setupShape() {
        addScaledPoints(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(0, 0, 1),
                new Punto3D(1, 0, 1),
                new Punto3D(1, 1, 1),
                new Punto3D(2, 1, 1),
                // 4 5 6 7
                new Punto3D(2, 2, 1),
                new Punto3D(1, 2, 1),
                new Punto3D(1, 3, 1),
                new Punto3D(0, 3, 1),
        });

        duplicateAndConnect();
    }

    @Override
    public void setupMesh() {
        for(double x = 0; x < 2; x+=meshSize) {
            if(x >= 1) {
                addArista(addScaledPoint(x, 1, 1), addScaledPoint(x, 2, 1));
            } else {
                addArista(addScaledPoint(x, 0, 1), addScaledPoint(x, 3, 1));
            }
        }

        for(double y = 0; y < 3; y+=meshSize) {
            if(y >= 1 && y <= 2) {
                addArista(addScaledPoint(0, y, 1), addScaledPoint(2, y, 1));
            } else {
                addArista(addScaledPoint(0, y, 1), addScaledPoint(1, y, 1));
            }
        }
    }
}
