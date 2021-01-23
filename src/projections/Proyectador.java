package projections;

import figuras.Curva3D;
import figuras.Figura;
import matrices.plano.Arista2D;
import matrices.plano.Punto2D;
import matrices.plano.Punto3D;

import java.util.List;

public interface Proyectador {
    List<Arista2D> proyectar(Figura figura);
    List<Punto2D> proyectar(Curva3D curva);
}
