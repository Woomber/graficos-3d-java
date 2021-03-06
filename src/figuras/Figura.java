package figuras;

import matrices.plano.Arista3D;
import matrices.MatrizTransformacion;
import matrices.plano.Punto3D;

import java.util.ArrayList;
import java.util.List;

public class Figura {

    protected List<Punto3D> vertices;
    protected List<Arista3D> aristas;

    public Figura() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public Figura(Figura f) {
        this();
        for(Punto3D vertice : f.getVertices()) {
            vertices.add(new Punto3D(vertice));
        }

        for(Arista3D arista: f.getAristas()) {
            Punto3D a = null, b = null;
            for(Punto3D p : vertices) {
                if (p.equals(arista.getA())) {
                    a = p;
                }
                if(p.equals(arista.getB())) {
                    b = p;
                }
            }
            this.addArista(a, b);
        }
    }

    public List<Punto3D> getVertices() {
        return vertices;
    }

    public Punto3D getVertice(int index) {
        return vertices.get(index);
    }

    public List<Arista3D> getAristas() {
        return aristas;
    }

    public Arista3D getArista(int index) {
        return aristas.get(index);
    }

    public void addVertice(Punto3D p) {
        if(!vertices.contains(p)) {
            vertices.add(p);
        }
    }

    public void addVertices(Punto3D[] p) {
        for (Punto3D punto3D : p) {
            addVertice(punto3D);
        }
    }

    public void addArista(Punto3D a, Punto3D b) {
        addVertice(a);
        addVertice(b);
        for (Arista3D arista: aristas) {
            if(arista.equals(new Arista3D(a, b))) {
                return;
            }
        }
        aristas.add(new Arista3D(a, b));
    }

    public List<Punto3D> connectedTo(Punto3D a) {
        List<Punto3D> connected = new ArrayList<>();
        for(Arista3D arista: aristas) {
            if(arista.getA().equals(a)) {
                connected.add(arista.getB());
            }
        }
        return connected;
    }

    public Figura transform(MatrizTransformacion t) {
        for(Punto3D punto : vertices) {
            punto.transform(t);
        }
        return this;
    }

}
