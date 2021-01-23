package matrices.projection;

import matrices.MatrizIdentidad;
import matrices.MatrizTransformacion;
import matrices.plano.Punto2D;

public class MatrizProyeccionPerspectiva extends MatrizIdentidad implements MatrizTransformacion {

    public MatrizProyeccionPerspectiva(double near, double far) {
        this(near, far, 1);
    }

    public MatrizProyeccionPerspectiva(double near, double far, double fov) {
        super(4);

        double f = 1/Math.tan(0.5 * fov * Math.PI/180);
        set(0, 0, f);
        set(1, 1, f);
        set(2, 2, (far + near)/(far - near));
        set(3, 2, 2*far*near/(far - near));
        set(2, 3, 1);
        set(3, 3, 0);
    }
}
