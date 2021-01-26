package animation;

import figuras.tetris.*;
import matrices.*;
import projections.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TetrisBoardAnimation extends Animation {

    public final static boolean DEBUG_FLAT = false;

    public final static double SCALE = 50;
    public final static int FALL_TIMES = 27;

    protected Proyectador proyectador;
    protected int frameCount = 0, colorCount = 0;
    protected Color[] colors;

    protected double boardX(int posX) {
        return SCALE * posX - 250;
    }

    protected Color getColor(int count) {
        return colors[count % colors.length];
    }

    protected MatrizTransformacion moveUp(int posY) {
        return new MatrizTraslacion(0, - posY * SCALE, 0);
    }

    public TetrisBoardAnimation(int width, int height, ImageObserver observer) {
        super(width, height, observer);

        proyectador = new ParallelProjection(0.5 * Math.cos(Math.atan(-1.5)),  0.75 * Math.sin(Math.atan(-1.5)));
        if(DEBUG_FLAT) proyectador = new ParallelOrthogonalProjection();

        colors = new Color[] {
                new Color(255, 0, 0),
                new Color(0, 120, 0),
                new Color(0, 0, 200),
                new Color(190, 120, 0)
        };


        this.setFrameDelay(50);

        AnimationElement containerElement = new AnimationElement(new TetrisContainer(), proyectador);
        addElement(containerElement);

        addTetracube(new TetracubeT(), 90, 0, 0, 0);
        addTetracube(new TetracubeT(), 180, 4, 0, 0);
        addTetracube(new TetracubeL(), 0, 6, 3, 0);
        addTetracube(new TetracubeO(), 0, 4, 1, 1);
        addTetracube(new TetracubeS(), 0, 7, 3, 0);
        addTetracube(new TetracubeS(), -90, 4, 3, 1);

        addGeneralAction(new MatrizEscalado(0.5));
    }

    protected void addTetracube(Tetracube tetracube, double rotate, int boardX, int moveUp, int offset) {
        AnimationElement element = new AnimationElement(tetracube
                .transform(new MatrizRotacion(Eje.Z, rotate, true))
                .transform(moveUp(moveUp))
                .transform(new MatrizTraslacion(this.boardX(boardX), -850, -100))
        , proyectador);
        for( int i = 0; i < frameCount; i++) element.addAction(new MatrizIdentidad3D());
        for(int i = 0; i < FALL_TIMES - offset; i++) {
            element.addAction(new MatrizTraslacion(0, 50, 0));
            frameCount++;
        }
        element.getFigura().setLineColor(getColor(colorCount++));
        addElement(element);
    }
}
