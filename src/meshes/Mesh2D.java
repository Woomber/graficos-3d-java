package meshes;

import figuras.Figura;
import matrices.MatrizTransformacion;
import matrices.plano.*;

import java.util.ArrayList;
import java.util.List;

public class Mesh2D {
    protected List<Punto2D> vertices;
    protected List<Arista2D> aristas;

    public Mesh2D() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public List<Punto2D> getVertices() {
        return vertices;
    }

    public Punto2D getVertice(int index) {
        return vertices.get(index);
    }

    public List<Arista2D> getAristas() {
        return aristas;
    }

    public Arista2D getArista(int index) {
        return aristas.get(index);
    }

    public void addVertice(Punto2D p) {
        if(!vertices.contains(p)) {
            vertices.add(p);
        }
    }

    public void addVertices(Punto2D[] puntos) {
        for (Punto2D p : puntos) {
            addVertice(p);
        }
    }

    public void addArista(Punto2D a, Punto2D b) {
        addVertice(a);
        addVertice(b);
        for (Arista2D arista: aristas) {
            if(arista.equals(new Arista2D(a, b))) {
                return;
            }
        }
        aristas.add(new Arista2D(a, b));
    }

    public List<Arista2D> connectedTo(Punto2D a) {
        List<Arista2D> connected = new ArrayList<>();
        for(Arista2D arista: aristas) {
            if(arista.getA().equals(a) || arista.getB().equals(a)) {
                connected.add(arista);
            }
        }
        return connected;
    }

}
