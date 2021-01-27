package animation;

import figuras.Figura;
import figuras.tetris.*;
import matrices.*;
import matrices.plano.Punto2D;
import projections.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TetrisShapeAnimation extends Animation {

    protected double changeX = 0, changeY = 0, changeZ = 0, changeScale = 0;
    protected double angle = 0;
    protected int currentShape = 0;

    protected final Color[] colors = new Color[] {
            new Color(255, 0, 0),
            new Color(0, 120, 0),
            new Color(0, 0, 200),
            new Color(200, 140, 0),
            new Color(200, 0, 200),
            new Color(240, 100, 0),
            new Color(0, 160, 250),
    };

    public TetrisShapeAnimation(int width, int height, ImageObserver observer) {
        super(width, height, observer);
        setDrawOriginCenter();
        setDrawOrigin(new Punto2D(origin.getX() - 20, origin.getY()));
        setMaxFrames(Integer.MAX_VALUE);

        Proyectador p = new PerspectiveProjection(380, 0.5);

        addElement(new AnimationElement(new TetracubeT().transform(new MatrizTraslacion(-75, -75, -75)), p));
        addElement(new AnimationElement(new TetracubeL().transform(new MatrizTraslacion(-50, -75, -75)), p));
        addElement(new AnimationElement(new TetracubeI().transform(new MatrizTraslacion(-25, -100, -75)), p));
        addElement(new AnimationElement(new TetracubeZ().transform(new MatrizTraslacion(-50, -75, -75)), p));
        addElement(new AnimationElement(new TetracubeO().transform(new MatrizTraslacion(-50, -50, -75)), p));
        addElement(new AnimationElement(new TetracubeJ().transform(new MatrizTraslacion(-50, -75, -75)), p));
        addElement(new AnimationElement(new TetracubeS().transform(new MatrizTraslacion(-50, -75, -75)), p));

        int i = 0;
        for(AnimationElement e : elements) {
            e.getFigura().setLineColor(colors[i++ % colors.length]);
        }
    }

    protected double random() {
        return Math.random() * 2 - 1;
    }

    protected void randomizeIncrements() {
        angle += Math.PI/32;
        changeX += 0.05 * (Math.cos(angle));
        changeY += 0.15 * (Math.sin(angle));
        changeZ = 0;
        changeScale = 1 + 0.01 * (Math.sin(angle));
    }

    @Override
    protected void frameOperations() {
        randomizeIncrements();
        for(AnimationElement element : elements) {
            element.getFigura()
                    .transform(new MatrizRotacion(Eje.X, changeX, true))
                    .transform(new MatrizRotacion(Eje.Y, changeY, true))
                    .transform(new MatrizRotacion(Eje.Z, changeZ, true))
                    .transform(new MatrizEscalado(changeScale));
        }
        AnimationElement element = elements.get(currentShape % elements.size());
        Figura projected = new Figura(element.getFigura());
        for(MatrizTransformacion t : generalActions) {
            projected.transform(t);
        }
        foreground.setColor(element.getFigura().getLineColor());
        foreground.drawAristas(element.getProyectador().proyectar(projected));

        if(currentFrame.get() % 50 == 0) {
            currentShape++;
        }
    }
}
