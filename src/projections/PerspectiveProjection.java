package projections;

import figuras.Figura;
import matrices.plano.*;
import matrices.projection.*;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveProjection implements Proyectador {

    protected double focalLength;

    public PerspectiveProjection(double focalLength) {
        this.focalLength = focalLength;
    }

    @Override
    public List<Arista2D> proyectar(Figura figura) {

        for(Punto3D p : figura.getVertices()) {
            p.transform(new MatrizProyeccionPerspectiva(20,120));
            double factorZ = p.get(0, 3);
            p.setX(p.getX() / factorZ);
            p.setY(p.getY() / factorZ);
            p.setZ(p.getZ() / factorZ);
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
