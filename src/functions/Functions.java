package functions;

import matrices.plano.Punto2D;
import matrices.plano.Punto3D;

import java.util.ArrayList;
import java.util.List;

public class Functions {

    public enum MainVariable {
        X,
        Y
    }

    protected static double getIncrement(double start, double end, int points) {
        return (end - start) / (double) points;
    }

    public static List<Punto2D> create2DCurveExplicit(ExplicitFunction function, double start, double end, int points, MainVariable main) {
        List<Punto2D> result = new ArrayList<>();
        double inc = getIncrement(start, end, points);
        double currVal = start;

        while(currVal <= end) {
            if(main == MainVariable.X) {
                result.add(new Punto2D(currVal, -function.call(currVal)));
            } else {
                result.add(new Punto2D(function.call(currVal), -currVal));
            }
            currVal += inc;
        }

        return result;
    }

    public static List<Punto2D> create2DCurveParametric(ExplicitFunction x, ExplicitFunction y, double start, double end, int points) {
        List<Punto2D> result = new ArrayList<>();
        double inc = getIncrement(start, end, points);
        double currVal = start;

        while(currVal <= end) {
            result.add(new Punto2D(x.call(currVal), -y.call(currVal)));
            currVal += inc;
        }

        return result;
    }

    public static List<Punto3D> create3DCurveParametric(ExplicitFunction x, ExplicitFunction y, ExplicitFunction z, double start, double end, int points) {
        List<Punto3D> result = new ArrayList<>();
        double inc = getIncrement(start, end, points);
        double currVal = start;

        while(currVal <= end) {
            result.add(new Punto3D(x.call(currVal), -y.call(currVal), z.call(currVal)));
            currVal += inc;
        }

        return result;
    }


}
