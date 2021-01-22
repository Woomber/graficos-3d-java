package matrices.plano;

public class Arista2D {
    protected Punto2D a, b;

    public Arista2D(Punto2D a, Punto2D b) {
        this.a = a;
        this.b = b;
    }

    public Punto2D getA() {
        return a;
    }

    public void setA(Punto2D a) {
        this.a = a;
    }

    public Punto2D getB() {
        return b;
    }

    public void setB(Punto2D b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Arista3D{" + "a=" + a + ", b=" + b + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arista2D arista = (Arista2D) o;
        return (a.equals(arista.a) && b.equals(arista.b)) || (a.equals(arista.b) && b.equals(arista.a));
    }
}
