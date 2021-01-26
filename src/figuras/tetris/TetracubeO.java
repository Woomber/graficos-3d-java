package figuras.tetris;

import matrices.plano.Punto3D;

/*

00------01
|        |
|        |
|        |
03------02

 */
public class TetracubeO extends Tetracube {

    public TetracubeO() {
        setupShape();
    }

    public TetracubeO(int scale) {
        super(scale);
        setupShape();
    }

    @Override
    public void setupShape() {
        addScaledPoints(new Punto3D[]{
                // 0 1 2 3
                new Punto3D(0, 0, 1),
                new Punto3D(2, 0, 1),
                new Punto3D(2, 2, 1),
                new Punto3D(0, 2, 1),
        });

        duplicateAndConnect();
    }
}
