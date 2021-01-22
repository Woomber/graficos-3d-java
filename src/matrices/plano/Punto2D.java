package matrices.plano;

import matrices.Matriz;

public class Punto2D extends Matriz {

    public Punto2D() {
        this(0, 0);
    }

    public Punto2D(double x, double y) {
        super(1, 3);
        setX(x);
        setY(y);
        set(0, 2, 1);
    }

    public Punto2D(Punto2D copia) {
        this(copia.getX(), copia.getY());
    }

    public Punto2D(Matriz matriz) {
        this();
        if(matriz.getWidth() != 1 || matriz.getHeight() != 3) {
            throw new ArithmeticException();
        }
        setX(matriz.get(0, 0));
        setY(matriz.get(0, 1));
    }

    public double getX() {
        return get(0, 0);
    }

    public double getY() {
        return  get(0, 1);
    }

    public int getIntX() {
        return (int) Math.round(getX());
    }

    public int getIntY() {
        return (int) Math.round(getY());
    }

    public void setX(double val) {
        set(0, 0, val);
    }

    public void setY(double val) {
        set(0, 1, val);
    }

    @Override
    public String toString() {
        return String.format("Punto3D{x=%f, y=%f}", getX(), getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto2D punto = (Punto2D) o;
        return getX() == punto.getX() && getY() == punto.getY();
    }
}
