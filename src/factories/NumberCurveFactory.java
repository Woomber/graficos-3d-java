package factories;

import figuras.Curva3D;
import functions.Functions;
import matrices.MatrizTraslacion;

import java.util.ArrayList;
import java.util.List;

public class NumberCurveFactory extends Curva3D {

    public static final double OFFSET_INCREMENT = 2.5;

    public static List<Curva3D> buildDigit(int digit) throws ArrayIndexOutOfBoundsException {
        List<Curva3D> curvas = new ArrayList<>();
        switch (digit) {
            case 0:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> 2 * Math.sin(t), t -> 0,
                        0, 2.1 * Math.PI, 100
                )));
                break;
            case 1:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 0, t -> t, t -> 0,
                        -2, 2, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> -2, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t - 2, t -> t, t -> 0,
                        1, 2, 2
                )));
                break;
            case 2:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) + 1, t -> 0,
                        0, Math.PI, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> (3*t - 1)/2, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> -2, t -> 0,
                        -1, 1, 2
                )));
                break;
            case 3:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) + 1, t -> 0,
                        - Math.PI/2.0, 3.0 / 4.0 * Math.PI, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) - 1, t -> 0,
                        - 3.0 / 4.0 * Math.PI, Math.PI/2.0, 100
                )));
                break;
            case 4:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 1, t -> 2*t, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 2 * t + 2, t -> 0,
                        -1, 0, 2
                )));
                break;
            case 5:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> (5 * Math.sin(t) - 3)/4.0, t -> 0,
                        -7.0/8.0 * Math.PI, 1.1 * Math.PI/2.0, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0.5, t -> 0,
                        -1, 0, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 2, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> -1, t -> t, t -> 0,
                        0.5, 2, 2
                )));
                break;
            case 6:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) - 1, t -> 0,
                        0, 2.1 * Math.PI, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 2 * Math.cos(t) + 1, t -> 3 * Math.sin(t) - 1, t -> 0,
                        0.5 * Math.PI, 1.05 * Math.PI, 100
                )));
                break;
            case 7:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0, t -> 0,
                        -0.5, 0.5, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 2, t -> 0,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 2 * t, t -> 0,
                        -1, 1, 2
                )));
                break;
            case 8:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) + 1, t -> 0,
                        0,  2.05 * Math.PI, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) - 1, t -> 0,
                        0 * Math.PI, 2.05 * Math.PI, 100
                )));
                break;
            case 9:
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> Math.sin(t) + 1, t -> 0,
                        0, 2.1 * Math.PI, 100
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 2 * Math.cos(t) - 1, t -> 3 * Math.sin(t) + 1, t -> 0,
                        -0.5 * Math.PI, 0.05 * Math.PI, 100
                )));
                break;
            default:
                throw new ArrayIndexOutOfBoundsException("Dígito debe ser entre 0 y 9");
        }
        return curvas;
    }

    public static List<Curva3D> buildNumber(int number, int padding) {
        List<Curva3D> curvas = new ArrayList<>();

        if(padding == 0) padding = 1;

        String numberFormat = String.format("%0" + padding + "d", number);
        double offset = 0;

        for(char digit : numberFormat.toCharArray()) {
            List<Curva3D> numberCurves = buildDigit(digit - '0');
            for (Curva3D curve : numberCurves) {
                curve.transform(new MatrizTraslacion(offset, 0, 0));
            }
            offset += OFFSET_INCREMENT;
            curvas.addAll(numberCurves);
        }

        return curvas;
    }
}
