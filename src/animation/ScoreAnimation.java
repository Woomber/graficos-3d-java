package animation;

import factories.NumberCurveFactory;
import figuras.Curva3D;
import matrices.*;
import projections.*;

import java.awt.image.ImageObserver;
import java.util.List;

public class ScoreAnimation extends Animation {

    protected Proyectador proyectador;

    private final int increment;
    private int number;

    public ScoreAnimation(int width, int height, ImageObserver observer, int from, int to, int increment) {
        super(width, height, observer);
        proyectador = new ParallelOrthogonalProjection();

        if(increment == 0) throw new IllegalArgumentException("increment no puede ser 0");
        if(from > to && increment > 0) throw new IllegalArgumentException("from debe ser menor que to");
        if(from < to && increment < 0) throw new IllegalArgumentException("from debe ser mayor que to");

        setMaxFrames(Math.abs(from - to)/increment + 1);
        number = from;
        this.increment = increment;
    }

    @Override
    protected void frameOperations() {
        List<Curva3D> curvas = NumberCurveFactory.buildNumber(number, 8);
        for (Curva3D c : curvas) {
            for(MatrizTransformacion t : generalActions) {
                c.transform(t);
            }
            foreground.drawCurve(proyectador.proyectar(c));
        }
        number += increment;
    }

    @Override
    protected int getDelay() {
        return (int) Math.floor(Math.random() * (1000 - 50 + 1)) + 50;
    }
}
