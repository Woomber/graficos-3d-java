package matrices.plano;

import matrices.Matriz;
import matrices.MatrizTransformacion;

public class Punto3D extends Matriz {

    public Punto3D() {
        this(0, 0, 0);
    }

    public Punto3D(double x, double y, double z) {
        super(1, 4);
        setX(x);
        setY(y);
        setZ(z);
        set(0, 3, 1);
    }

    public Punto3D(Punto3D copia) {
        this(copia.getX(), copia.getY(), copia.getZ());
        this.setW(copia.getW());
    }

    public Punto3D(Matriz matriz) {
        this();
        if(matriz.getWidth() != 1 || matriz.getHeight() != 4) {
            throw new ArithmeticException();
        }
        setX(matriz.get(0, 0));
        setY(matriz.get(0, 1));
        setZ(matriz.get(0, 2));
        setW(matriz.get(0, 3));
    }

    public Punto3D transform(MatrizTransformacion m2) {
        Punto3D transformed = new Punto3D(product((Matriz) m2));
        setX(transformed.getX());
        setY(transformed.getY());
        setZ(transformed.getZ());
        setW(transformed.getW());
        return this;
    }

    public double getX() {
        return get(0, 0);
    }

    public double getY() {
        return  get(0, 1);
    }

    public double getZ() {
        return  get(0, 2);
    }

    public double getW() {
        return get(0, 3);
    }

    public int getIntX() {
        return (int) Math.round(getX());
    }

    public int getIntY() {
        return (int) Math.round(getY());
    }

    public int getIntZ() {
        return (int) Math.round(getZ());
    }

    public int getIntW() {
        return (int) Math.round(getW());
    }

    public void setX(double val) {
        set(0, 0, val);
    }

    public void setY(double val) {
        set(0, 1, val);
    }

    public void setZ(double val) {
        set(0, 2, val);
    }

    public void setW(double val) {
        set(0, 3, val);
    }

    @Override
    public String toString() {
        return String.format("Punto3D{x=%f, y=%f, z=%f}", getX(), getY(), getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto3D punto = (Punto3D) o;
        return getX() == punto.getX() && getY() == punto.getY() && getZ() == punto.getZ();
    }

}
