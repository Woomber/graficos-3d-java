package matrices.projection;

import matrices.MatrizIdentidad;
import matrices.MatrizTransformacion;

public class MatrizProyeccionParalela extends MatrizIdentidad implements MatrizTransformacion {
    public MatrizProyeccionParalela(double a, double b) {
        super(4);
        set(2, 0, a);
        set(2, 1, b);
        set(2, 2, 0);
    }
}
