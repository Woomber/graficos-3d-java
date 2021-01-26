package figuras.tetris;

import matrices.plano.Punto3D;

/*

00--01
|    |
|    |
|    |
|    |
|    |
03--02

 */
public class TetracubeI extends Tetracube {

    public TetracubeI() {
        setupShape();
    }

    public TetracubeI(int scale) {
        super(scale);
        setupShape();
    }

    @Override
    public void setupShape() {
        addScaledPoints(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(0, 0, 1),
                new Punto3D(1, 0, 1),
                new Punto3D(1, 4, 1),
                new Punto3D(0, 4, 1),
        });

        duplicateAndConnect();
    }

    @Override
    public void setupMesh() {

    }
}
