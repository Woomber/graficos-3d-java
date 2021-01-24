package projections;

import figuras.Curva3D;
import figuras.Figura;
import matrices.MatrizTraslacion;
import matrices.plano.*;
import matrices.projection.*;

import java.util.ArrayList;
import java.util.List;

public class PerspectiveProjection implements Proyectador {

    protected double focalLength, fov;

    public static final double NEAR = 20, FAR = 120;

    public PerspectiveProjection(double focalLength) {
        this(focalLength, 1);
    }

    public PerspectiveProjection(double focalLength, double fov) {
        this.focalLength = focalLength;
        this.fov = fov;
    }

    @Override
    public List<Arista2D> proyectar(Figura figura) {

        Figura copia = new Figura(figura).transform(new MatrizTraslacion(0, 0, focalLength));

        for(Punto3D p : copia.getVertices()) {
            p.transform(new MatrizProyeccionPerspectiva(NEAR, FAR, fov));
            double factorZ = p.getW();
            p.setX(p.getX() / factorZ);
            p.setY(p.getY() / factorZ);
            p.setZ(p.getZ() / factorZ);
            p.setW(p.getW() / factorZ);
        }

        List<Arista2D> proyectadas = new ArrayList<>();
        for (Arista3D arista : copia.getAristas()) {
            Punto3D a = arista.getA();
            Punto3D b = arista.getB();
            proyectadas.add(new Arista2D(
                    new Punto2D(a.getX(), a.getY()),
                    new Punto2D(b.getX(), b.getY())
            ));
        }
        return proyectadas;
    }

    @Override
    public List<Punto2D> proyectar(Curva3D curva) {

        Curva3D copia = new Curva3D(curva).transform(new MatrizTraslacion(0, 0, focalLength));

        List<Punto2D> proyectados = new ArrayList<>();
        for(Punto3D p : copia.getPuntos()) {
            p.transform(new MatrizProyeccionPerspectiva(NEAR, FAR, fov));
            double factorZ = p.getW();
            proyectados.add(new Punto2D(p.getX() / factorZ, p.getY() / factorZ));
        }

        return proyectados;
    }
}
