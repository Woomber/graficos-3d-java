package matrices;

public class MatrizRotacion extends MatrizIdentidad implements MatrizTransformacion {

    public MatrizRotacion(Eje eje, double grados) {
        this(eje, grados, false);
    }

    public MatrizRotacion(Eje eje, double grados, boolean useDeg) {
        super(4);
        double theta = grados;
        if(useDeg) {
            theta *= Math.PI / 180.0;
        }

        switch (eje) {
            case X:
                set(1, 1, Math.cos(theta));
                set(1, 2, -Math.sin(theta));
                set(2, 1, Math.sin(theta));
                set(2, 2, Math.cos(theta));
                break;
            case Y:
                set(0, 0, Math.cos(theta));
                set(0, 2, Math.sin(theta));
                set(2, 0, -Math.sin(theta));
                set(2, 2, Math.cos(theta));
                break;
            case Z:
                set(0, 0, Math.cos(theta));
                set(0, 1, -Math.sin(theta));
                set(1, 0, Math.sin(theta));
                set(1, 1, Math.cos(theta));
                break;
        }
    }
}

