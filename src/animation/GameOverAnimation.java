package animation;

import factories.LetterCurveFactory;
import factories.NumberCurveFactory;
import figuras.Curva3D;
import matrices.Eje;
import matrices.MatrizRotacion;
import matrices.MatrizTransformacion;
import projections.ParallelOrthogonalProjection;
import projections.PerspectiveProjection;
import projections.Proyectador;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public class GameOverAnimation extends Animation {

    protected Proyectador proyectador;
    protected  List<Curva3D> curvas;


    public GameOverAnimation(int width, int height, ImageObserver observer) {
        super(width, height, observer);
        proyectador = new PerspectiveProjection(300, 0.3);

        curvas = LetterCurveFactory.buildString("GAME OVER");

        setFrameDelay(250);
        setMaxFrames(1);
    }

    @Override
    protected void frameOperations() {

        try {
            for (Curva3D c : curvas) {

                for(MatrizTransformacion t : generalActions) {
                    c.transform(t);
                }
                foreground.setColor(Color.WHITE);
                foreground.drawCurve(proyectador.proyectar(c));

                sleep(10);
                drawFrame();
                sendFrame();
            }
            sleep(150);
            for(int i = 0; i < 180; i++) {
                foreground.resetBuffer();
                for (Curva3D c : curvas) {
                    c.transform(new MatrizRotacion(Eje.Y, 2, true));
                    foreground.drawCurve(proyectador.proyectar(c));
                }
                sleep(30);
                drawFrame();
                sendFrame();
            }
            sleep(1000);
        } catch (InterruptedException ignored) {
        } finally {
            currentFrame.set(1);
        }
    }

    @Override
    protected void postFrameOperations() {

    }
}
