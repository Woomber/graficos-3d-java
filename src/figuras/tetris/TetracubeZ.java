package figuras.tetris;

import matrices.plano.Punto3D;

/*

    00--01
    |    |
06--07   |
|        |
|   03--02
|    |
05--04

 */
public class TetracubeZ extends Tetracube {

    public TetracubeZ() {
        setupShape();
    }

    public TetracubeZ(int scale) {
        super(scale);
        setupShape();
    }

    @Override
    public void setupShape() {
        addScaledPoints(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(1, 0, 1),
                new Punto3D(2, 0, 1),
                new Punto3D(2, 2, 1),
                new Punto3D(1, 2, 1),
                // 4 5 6 7
                new Punto3D(1, 3, 1),
                new Punto3D(0, 3, 1),
                new Punto3D(0, 1, 1),
                new Punto3D(1, 1, 1),
        });
        duplicateAndConnect();
    }
}
