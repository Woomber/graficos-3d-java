package factories;

import figuras.Curva3D;
import functions.Functions;
import matrices.MatrizTraslacion;

import java.util.ArrayList;
import java.util.List;

public class LetterCurveFactory {

    public static final double OFFSET_INCREMENT = 2.5;
    public static final double T_FACTOR = 0.3;

    public static List<Curva3D> buildLetter(char letter) {
        List<Curva3D> curvas = new ArrayList<>();

        switch (letter) {
            case 'A':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 4*t + 2, t -> T_FACTOR * t,
                        -1, 0, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 1 - t, t -> 4*t - 2, t -> T_FACTOR * t,
                        0, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0, t-> T_FACTOR * t,
                        -0.5, 0.5, 2
                )));
                break;
            case 'M':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                    t -> -1, t -> t, t -> T_FACTOR * t,
                    -2, 2, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                    t -> 1, t -> t, t -> T_FACTOR * t,
                    -2, 2, 2
                )));
            case 'V':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> - 4*t - 2, t -> T_FACTOR * t,
                        -1, 0, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 1 - t, t -> - 4*t + 2, t -> T_FACTOR * t,
                        0, 1, 2
                )));
                break;
            case 'E':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> -1, t -> t, t -> T_FACTOR * t,
                        -2, 2, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 2, t -> T_FACTOR * t,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> -2, t -> T_FACTOR * t,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0, t -> T_FACTOR * t,
                        -1, 0, 2
                )));
                break;
            case 'O':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> 2 * Math.sin(t), t -> T_FACTOR * t,
                        0, 2.01 * Math.PI, 200
                )));
                break;
            case 'G':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> Math.cos(t), t -> 2 * Math.sin(t), t -> T_FACTOR * t,
                        Math.PI/4.0, 2.01 * Math.PI, 200
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> 0, t -> T_FACTOR * t,
                        0, 1, 2
                )));
                break;
            case 'R':
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> -1, t -> t, t -> T_FACTOR * t,
                        -2, 2, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> t, t -> -t - 1, t -> T_FACTOR * t,
                        -1, 1, 2
                )));
                curvas.add(new Curva3D(Functions.create3DCurveParametric(
                        t -> 2 * Math.cos(t) - 1, t -> Math.sin(t) + 1, t -> T_FACTOR * t,
                        - Math.PI/2.0, 1.01 * Math.PI / 2.0, 200
                )));
                break;
            case ' ':
                break;
            default:
                throw new UnsupportedOperationException("Letra no almacenada");
        }

        return curvas;
    }

    public static List<Curva3D> buildString(String str) {
        List<Curva3D> curvas = new ArrayList<>();

        double offset = 0;

        for(char ch : str.toUpperCase().toCharArray()) {
            List<Curva3D> letter = buildLetter(ch);
            for(Curva3D c : letter) {
                c.transform(new MatrizTraslacion(offset, 0, 0));
            }
            offset += OFFSET_INCREMENT;
            curvas.addAll(letter);
        }

        return curvas;
    }
}
