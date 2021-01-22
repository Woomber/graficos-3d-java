package matrices;

public class MatrizEscalado extends MatrizIdentidad implements MatrizTransformacion {

    public MatrizEscalado(double factor) {
        this(factor, factor, factor);
    }

    public MatrizEscalado(double factorX, double factorY, double factorZ) {
        super(4);
        set(0, 0, factorX);
        set(1, 1, factorY);
        set(2, 2, factorZ);
    }
}
