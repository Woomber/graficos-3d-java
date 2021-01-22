package matrices;

import matrices.plano.Punto3D;

public class MatrizTraslacion extends MatrizIdentidad implements MatrizTransformacion {

    public MatrizTraslacion(double dx, double dy, double dz) {
        super(4);
        set(3, 0, dx);
        set(3, 1, dy);
        set(3, 2, dz);
    }

    public MatrizTraslacion(Punto3D inicio, Punto3D fin) {
        this(fin.getX() - inicio.getX(), fin.getY() - inicio.getY(), fin.getZ() - inicio.getZ());
    }
}
