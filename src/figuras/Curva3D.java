package figuras;

import matrices.MatrizTransformacion;
import matrices.plano.Punto3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Curva3D {

    protected List<Punto3D> puntos;

    public Curva3D() {
        puntos = new ArrayList<>();
    }

    public Curva3D(List<Punto3D> puntos) {
        this.puntos = puntos;
    }

    public Curva3D(Curva3D copia) {
        this();
        for (Punto3D punto : copia.getPuntos()) {
            this.addPunto(new Punto3D(punto));
        }
    }

    public void addPunto(Punto3D p) {
        puntos.add(p);
    }

    public void addPuntos(Punto3D[] puntos) {
        this.puntos.addAll(Arrays.asList(puntos));
    }

    public Curva3D transform(MatrizTransformacion t) {
        for(Punto3D punto : puntos) {
            punto.transform(t);
        }
        return this;
    }

    public List<Punto3D> getPuntos() {
        return puntos;
    }
}
