package matrices.plano;

public class Arista3D {

    protected Punto3D a, b;

    public Arista3D(Punto3D a, Punto3D b) {
        this.a = a;
        this.b = b;
    }

    public Punto3D getA() {
        return a;
    }

    public void setA(Punto3D a) {
        this.a = a;
    }

    public Punto3D getB() {
        return b;
    }

    public void setB(Punto3D b) {
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
        Arista3D arista = (Arista3D) o;
        return (a.equals(arista.a) && b.equals(arista.b)) || (a.equals(arista.b) && b.equals(arista.a));
    }
}
