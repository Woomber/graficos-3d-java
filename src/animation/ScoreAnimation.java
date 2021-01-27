package animation;

import factories.HeartFactory;
import factories.NumberCurveFactory;
import figuras.Curva3D;
import matrices.*;
import matrices.plano.Punto2D;
import projections.*;

import java.awt.*;
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

        setFrameDelay(250);
        setMaxFrames(Math.abs(from - to)/increment + 1);
        number = from;
        this.increment = increment;

        setupHearts();
    }

    protected void setupHearts() {
        final double heartScale = 1.1;
        final int width = frame.getWidth();

        List<Punto2D> heartPolygon1 = HeartFactory.buildHeart();
        for(Punto2D p : heartPolygon1) {
            p.setX(heartScale * p.getX() + width - 60);
            p.setY(heartScale * p.getY() + 130);
        }
        background.drawPolygon(heartPolygon1);
        List<Punto2D> heartPolygon2 = HeartFactory.buildHeart();
        for(Punto2D p : heartPolygon2) {
            p.setX(heartScale * p.getX() + width - 100);
            p.setY(heartScale * p.getY() + 130);
        }
        background.drawPolygon(heartPolygon2);
        List<Punto2D> heartPolygon3 = HeartFactory.buildHeart();
        for(Punto2D p : heartPolygon3) {
            p.setX(heartScale * p.getX() + width - 140);
            p.setY(heartScale * p.getY() + 130);
        }
        background.drawAndFillPolygon(heartPolygon3, new Color(250, 90, 90));
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
}
