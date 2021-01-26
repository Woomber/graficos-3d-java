package figuras.tetris;

import matrices.plano.Punto3D;

/*

00--01
|    |
|   02--03
|        |
07--06   |
    |    |
    05--04

 */
public class TetracubeS extends Tetracube {

    public TetracubeS() {
        setupShape();
    }

    public TetracubeS(int scale) {
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
                new Punto3D(2, 3, 1),
                new Punto3D(1, 3, 1),
                new Punto3D(1, 2, 1),
                new Punto3D(0, 2, 1),
        });
        duplicateAndConnect();
    }
}
