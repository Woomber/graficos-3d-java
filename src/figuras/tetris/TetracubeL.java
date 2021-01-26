package figuras.tetris;

import matrices.plano.Punto3D;

/*

00--01
|    |
|    |
|    |
|   02--03
|        |
05------04

 */
public class TetracubeL extends Tetracube {

    public TetracubeL() {
        setupShape();
    }

    public TetracubeL(int scale) {
        super(scale);
        setupShape();
    }

    @Override
    public void setupShape() {
        addScaledPoints(new Punto3D[]{
                // 0 1 2
                new Punto3D(0, 0, 1),
                new Punto3D(1, 0, 1),
                new Punto3D(1, 2, 1),
                // 3 4 5
                new Punto3D(2, 2, 1),
                new Punto3D(2, 3, 1),
                new Punto3D(0, 3, 1),
        });

        duplicateAndConnect();
    }
}
