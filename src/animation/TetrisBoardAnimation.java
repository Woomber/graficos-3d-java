package animation;

import figuras.Figura;
import figuras.TetracubeT;
import figuras.TetrisContainer;
import matrices.*;
import projections.ParallelOrthogonalProjection;
import projections.ParallelProjection;
import projections.Proyectador;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TetrisBoardAnimation extends Animation {

    public final static double SCALE = 50;

    protected Proyectador proyectador;
    protected int frameCount = 0;
    protected Color[] colors;

    protected double boardX(int posX) {
        return SCALE * posX - 250;
    }

    protected Color getColor(int count) {
        return colors[count % colors.length];
    }

    public TetrisBoardAnimation(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        proyectador = new ParallelProjection(0.5 * Math.cos(Math.atan(-1.5)),  0.75 * Math.sin(Math.atan(-1.5)));

        colors = new Color[] {
                new Color(255, 0, 0),
                new Color(0, 120, 0),
                new Color(0, 0, 200),
                new Color(190, 120, 0)
        };


        this.setFrameDelay(200);

        AnimationElement containerElement = new AnimationElement(new TetrisContainer(), proyectador);
        addElement(containerElement);

        AnimationElement t1Element = new AnimationElement(new TetracubeT()
                .transform(new MatrizRotacion(Eje.Z, 90, true))
                .transform(new MatrizTraslacion(boardX(0), -850, -100))
                ,proyectador);
        for(int i = 0; i < 27; i++) {
            t1Element.addAction(new MatrizTraslacion(0, 50, 0));
            frameCount++;
        }
        t1Element.getFigura().setLineColor(getColor(0));
        addElement(t1Element);

        AnimationElement t2Element = new AnimationElement(new TetracubeT()
                .transform(new MatrizRotacion(Eje.Z, 180, true))
                .transform(new MatrizTraslacion(boardX(4), -850, -100))
                ,proyectador);
        for(int i = 0; i < frameCount; i++) t2Element.addAction(new MatrizIdentidad3D());
        for(int i = 0; i < 27; i++) {
            t2Element.addAction(new MatrizTraslacion(0, 50, 0));
        }
        t2Element.getFigura().setLineColor(getColor(1));
        addElement(t2Element);

        addGeneralAction(new MatrizEscalado(0.5));
        //addGeneralAction(new MatrizRotacion(Eje.Y, 15, true));

    }
}
