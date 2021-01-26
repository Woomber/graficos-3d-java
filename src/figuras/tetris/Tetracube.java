package figuras.tetris;

import figuras.Figura;
import matrices.MatrizTransformacion;
import matrices.plano.Punto3D;

public abstract class Tetracube extends Figura {

    protected final double scale;
    protected final double meshSize = 0.1;

    public Tetracube() {
        this.scale = 50;
    }

    public Tetracube(double scale) {
        this.scale = scale;
    }

    protected Punto3D addScaledPoint(double x, double y, double z) {
        Punto3D p = new Punto3D(x * scale, y * scale, z * scale);
        vertices.add(p);
        return p;
    }

    protected Punto3D addScaledPoint(Punto3D p) {
        return addScaledPoint(p.getX(), p.getY(), p.getZ());
    }

    protected void addScaledPoints(Punto3D[] points) {
        for(Punto3D p : points) {
            addScaledPoint(p);
        }
    }

    protected void connectPoints() {
        int pointN = vertices.size()/2;
        for(int idx = 0; idx < pointN; idx++) {
            for(int z = 0; z < 2; z++) {
                // Conectar plano frontal y trasero
                addArista(vertices.get(pointN * z + idx), vertices.get(pointN * z + (idx + 1)%pointN));
            }
            // Conectar planos
            addArista(vertices.get(idx), vertices.get(idx + pointN));
        }
    }

    protected void duplicate(double distanceZ) {
        final int points = vertices.size();
        for(int i = 0; i < points; i++) {
            Punto3D v = vertices.get(i);
            addVertice(new Punto3D(v.getX(), v.getY(), v.getZ() + distanceZ));
        }
    }

    protected void duplicateAndConnect(double distanceZ) {
        duplicate(distanceZ);
        connectPoints();
    }

    protected void duplicateAndConnect() {
        duplicateAndConnect(scale);
    }

    public Tetracube transform(MatrizTransformacion t) {
        super.transform(t);
        return this;
    }

    public abstract void setupShape();

    public abstract void setupMesh();

}
