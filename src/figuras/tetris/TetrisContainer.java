package figuras.tetris;

import figuras.Figura;
import matrices.plano.Punto3D;

public class TetrisContainer extends Figura {

    protected final int cubeSize, height;

    public TetrisContainer() {
        this(50, 20);
    }

    public TetrisContainer(int cubeSize, int height) {
        this.cubeSize = cubeSize;
        this.height = height;

        addVertices(new Punto3D[]{
                new Punto3D(-5*cubeSize, - height * cubeSize / 2.0, 0),
                new Punto3D(5 * cubeSize, -height * cubeSize / 2.0, 0),
                new Punto3D(5*cubeSize, height * cubeSize / 2.0, 0),
                new Punto3D(-5 * cubeSize, height * cubeSize / 2.0, 0),

                new Punto3D(-5*cubeSize, - height * cubeSize / 2.0, -cubeSize),
                new Punto3D(-5 * cubeSize, height * cubeSize / 2.0, -cubeSize),

                new Punto3D(5 * cubeSize, -height * cubeSize / 2.0, -cubeSize),
                new Punto3D(5*cubeSize, height * cubeSize / 2.0, -cubeSize),
        });

        addArista(getVertice(0), getVertice(1));
        addArista(getVertice(1), getVertice(2));
        addArista(getVertice(2), getVertice(3));
        addArista(getVertice(3), getVertice(0));

        addArista(getVertice(0), getVertice(4));
        addArista(getVertice(3), getVertice(5));
        addArista(getVertice(4), getVertice(5));

        addArista(getVertice(1), getVertice(6));
        addArista(getVertice(2), getVertice(7));
        addArista(getVertice(6), getVertice(7));

        addArista(getVertice(5), getVertice(7));
    }

    public void setupMesh() {
        double meshSize = 50;
        final double top = -height * cubeSize/2.0, bottom = -top;
        final double left = -5 * cubeSize, right = -left;
        for(double x = left; x < right; x+=meshSize) {
            addArista(new Punto3D(x, top, 0), new Punto3D(x, bottom, 0));
            addArista(new Punto3D(x, bottom, 0), new Punto3D(x, bottom, -cubeSize));
        }

        for(double y = top; y < bottom; y+=meshSize) {
            addArista(new Punto3D(left, y, 0), new Punto3D(right, y, 0));
            addArista(new Punto3D(left, y, 0), new Punto3D(left, y, -cubeSize));
            addArista(new Punto3D(right, y, 0), new Punto3D(right, y, -cubeSize));
        }
    }
}
