package projections;

import figuras.Figura;
import matrices.plano.Arista2D;

import java.util.List;

public interface Proyectador {
    List<Arista2D> proyectar(Figura figura);
}
