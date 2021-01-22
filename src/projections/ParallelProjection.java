package projections;

import figuras.Figura;
import matrices.projection.MatrizProyeccionParalela;
import matrices.plano.Arista2D;
import matrices.plano.Arista3D;
import matrices.plano.Punto2D;
import matrices.plano.Punto3D;

import java.util.ArrayList;
import java.util.List;

public class ParallelProjection implements Proyectador {

    protected double alpha, beta;

    public ParallelProjection(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public List<Arista2D> proyectar(Figura figura) {

        for(Punto3D p : figura.getVertices()) {
            p.transform(new MatrizProyeccionParalela(alpha, beta));
        }

        List<Arista2D> proyectadas = new ArrayList<>();
        for (Arista3D arista : figura.getAristas()) {
            Punto3D a = arista.getA();
            Punto3D b = arista.getB();
            proyectadas.add(new Arista2D(
                    new Punto2D(a.getX(), a.getY()),
                    new Punto2D(b.getX(), b.getY())
            ));
        }
        return proyectadas;
    }
}
