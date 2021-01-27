package factories;

import functions.Functions;
import matrices.plano.Punto2D;

import java.util.ArrayList;
import java.util.List;

public class HeartFactory {

    public static List<Punto2D> buildHeart() {
        List<Punto2D> curva = new ArrayList<>();

        curva.addAll(Functions.create2DCurveParametric(
                t -> 16 * Math.sin(t) * Math.sin(t) * Math.sin(t),
                t -> 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t),
                0, 2.01 * Math.PI, 200
        ));

        return curva;
    }

}
