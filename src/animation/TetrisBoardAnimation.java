package animation;

import audio.AudioPlayer;
import figuras.tetris.*;
import matrices.*;
import projections.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TetrisBoardAnimation extends Animation {

    public final static double SCALE = 50;
    public final static int FALL_TIMES = 27;
    public static final int CHANGE_AT = 120;

    protected Proyectador proyectadorA, proyectadorB, proyectadorC, proyectadorFinal;
    protected int frameCount = 0, colorCount = 0, perspectiveCount = 0;
    protected int perspectiveFinalFrame = 0;
    protected boolean hasEnded = false;
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

        proyectadorA = new ParallelProjection(-0.5 * Math.cos(Math.atan(-1.5)),  0.75 * Math.sin(Math.atan(-1.5)));
        proyectadorB = new PerspectiveProjection(280, 0.35);
        proyectadorC = new ParallelOrthogonalProjection();
        proyectadorFinal = new PerspectiveProjection(560, 0.18);

        colors = new Color[] {
                new Color(255, 0, 0),
                new Color(0, 120, 0),
                new Color(0, 0, 200),
                new Color(200, 140, 0),
                new Color(200, 0, 200),
                new Color(200, 100, 0),
                new Color(0, 120, 200),
        };


        this.setFrameDelay(80);

        TetrisContainer container = new TetrisContainer();
        container.setLineColor(new Color(230, 230, 230));
        container.setupMesh();
        AnimationElement containerElement = new AnimationElement(container, proyectadorA);
        addElement(containerElement);

        addTetracube(new TetracubeT(), 90, 0, 0, 0);
        addTetracube(new TetracubeT(), 180, 4, 0, 0);
        addTetracube(new TetracubeL(), 0, 6, 3, 0);
        addTetracube(new TetracubeO(), 0, 4, 1, 1);
        addTetracube(new TetracubeS(), 0, 7, 3, 0);
        addTetracube(new TetracubeS(), -90, 4, 3, 1);
        addTetracube(new TetracubeJ(), 180, 2, 0, 1);
        addTetracube(new TetracubeI(), 0, 5, 3, 3);
        addTetracube(new TetracubeL(), 180, 5, 0, 2);
        addTetracube(new TetracubeT(), 90, 0, 3, 1);
        addTetracube(new TetracubeZ(), -90, 4, 1, 6);
        addTetracube(new TetracubeJ(), 180, 2, 0, 5);
        addTetracube(new TetracubeT(), 180, 9, 0, 2);
        addTetracube(new TetracubeZ(), 0, 6, 3, 3);
        addTetracube(new TetracubeL(), 0, 0, 3, 8);
        addTetracube(new TetracubeS(), 0, 3, 3, 5);
        addTetracube(new TetracubeT(), -90, 8, 1, 6);
        addTetracube(new TetracubeJ(), -90, 7, 1, 8);
        addTetracube(new TetracubeZ(), 0, 2, 3, 7);
        addTetracube(new TetracubeO(), 0, 1, 2, 9);
        addTetracube(new TetracubeL(), 180, 9, 0, 5);
        addTetracube(new TetracubeI(), 90, 5, 0, 8);
        addTetracube(new TetracubeJ(), -90, 3, 2, 11);
        addTetracube(new TetracubeS(), 0, 3,3, 9);
        addTetracube(new TetracubeL(), 90, 7, 0, 9);
        addTetracube(new TetracubeT(), 0, 6, 3, 9);
        addTetracube(new TetracubeT(), 180, 6, 0, 10);
        addTetracube(new TetracubeZ(), -90, 9, 2, 11);
        addTetracube(new TetracubeL(), 90, 2, 0, 12);
        addTetracube(new TetracubeO(), 0, 5, 2, 13);
        addTetracube(new TetracubeS(), 0, 7, 3, 12);
        addTetracube(new TetracubeJ(), 0, 2, 3, 13);
        addTetracube(new TetracubeZ(), 0, 4, 3, 14);
        addTetracube(new TetracubeO(), 0, 8, 2, 14);
        addTetracube(new TetracubeI(), 0, 1, 4, 12);
        addTetracube(new TetracubeL(), 0, 6, 3, 15);
        addTetracube(new TetracubeJ(), -90, 10, 2, 16);
        addTetracube(new TetracubeS(), -90, 6, 2, 16);
        addTetracube(new TetracubeT(), 0, 2,  2,17);
        addTetracube(new TetracubeO(), 0, 3, 2, 18);

        padElements();

        addGeneralAction(new MatrizEscalado(0.5));
    }

    protected void addTetracube(Tetracube tetracube, double rotate, int boardX, int moveUp, int offset) {
        AnimationElement element = new AnimationElement(tetracube
                .transform(new MatrizRotacion(Eje.Z, rotate, true))
                .transform(moveUp(moveUp))
                .transform(new MatrizTraslacion(this.boardX(boardX), -850, -100))
        , proyectadorA);
        for( int i = 0; i < frameCount; i++) element.addAction(new MatrizIdentidad3D());
        for(int i = 0; i < FALL_TIMES - offset; i++) {
            element.addAction(new MatrizTraslacion(0, 50, 0));
            frameCount++;
        }
        element.getFigura().setLineColor(getColor(colorCount++));
        addElement(element);
    }

    protected void padElements() {
        perspectiveFinalFrame = frameCount;
        for(AnimationElement e : elements) {
            if(e.getFigura() instanceof TetrisContainer) continue;

            int stepNumber = e.getNumberOfActions();
            for(int i = stepNumber; i < frameCount + 10; i++) {
                e.addAction(new MatrizIdentidad3D());
            }

            for(int i = 0; i < 200; i++) {
                e.addAction(new MatrizTraslacion(
                        3*(Math.random() - 0.5),
                        3*(Math.random() - 0.5),
                        -2 * Math.random()
                ));
            }
        }
    }

    @Override
    protected int getDelay() {
        double delayFactor = 1 + 0.2 * (getCurrentFrame() / 100.0);
        return (int) (super.getDelay() / delayFactor);
    }

    @Override
    protected void postFrameOperations() {
        super.postFrameOperations();

        if(hasEnded) return;
        if(currentFrame.get() > perspectiveFinalFrame) {
            setProyectador(proyectadorFinal);
            hasEnded = true;
            AudioPlayer.stopInstance();
            AudioPlayer.initAndPlay(AudioPlayer.GAMEOVER_WAV, false);
            setFrameDelay(10);
            return;
        }
        if(currentFrame.get() % CHANGE_AT == 0) {
            switch (perspectiveCount++ % 3) {
                case 0:
                    setProyectador(proyectadorB);
                    break;
                case 1:
                    setProyectador(proyectadorC);
                    break;
                case 2:
                    setProyectador(proyectadorA);
                    break;
            }
        }
    }
}
