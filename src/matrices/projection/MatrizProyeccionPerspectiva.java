package matrices.projection;

import matrices.MatrizIdentidad;
import matrices.MatrizTransformacion;
import matrices.plano.Punto2D;

public class MatrizProyeccionPerspectiva extends MatrizIdentidad implements MatrizTransformacion {

    public MatrizProyeccionPerspectiva(Punto2D topLeft, Punto2D bottomRight, double near, double far) {
        super(4);

        double right = bottomRight.getX(), left = topLeft.getX();
        double bottom = bottomRight.getY(), top = topLeft.getY();

        double f = 1/Math.tan(0.5 * 1 * Math.PI/180);
        set(0, 0, f);
        set(1, 1, f);
        set(2, 2, (far + near)/(far - near));
        set(3, 2, 2*far*near/(far - near));
        set(2, 3, 1);
        set(3, 3, 0);

        /*
        set(0, 0, 2 * near * (right - left));
        set(1, 1, 2 * near * (bottom - top));
        set(2, 2, -(far+near)/(far-near));
        set(2, 3, 1);
        set(3, 0, -near*(right+left)/(right-left));
        set(3, 1, -near*(bottom+top)/(bottom-top));
        set(3,2, 2*far*near/(near-far));
        set(3, 3, 0);
*/

        //set(2, 3, 1);
        //set(3, 3, 0);
    }
}
