package animation;

import dibujante.Dibujante3D;
import figuras.Figura;
import matrices.plano.Punto2D;
import projections.Proyectador;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("BusyWait")
public class Animation extends Thread {

    protected final ImageObserver observer;
    protected final List<AnimationFrameListener> frameListeners;

    protected Dibujante3D foreground, background;
    protected BufferedImage frame;

    protected final List<AnimationElement> elements;

    protected AtomicInteger currentFrame;

    protected int frameDelay = 100;
    protected int initialDelay = 0;


    public Animation(int width, int height, ImageObserver observer) {
        this.observer = observer;
        this.frameListeners = new ArrayList<>();
        this.elements = new ArrayList<>();

        background = new Dibujante3D(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB), observer);
        foreground = new Dibujante3D(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB), observer);

        frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        frame.createGraphics();

        background.clear();
    }

    public void setOrigin(Punto2D origin) {
        background.setOrigin(origin);
        foreground.setOrigin(origin);
    }

    public void setProyectador(Proyectador proyectador) {
        for(AnimationElement e : elements) {
            e.setProyectador(proyectador);
        }
    }

    public void addListener(AnimationFrameListener l) {
        frameListeners.add(l);
    }

    public void addElement(AnimationElement element) {
        elements.add(element);
    }

    @Override
    public void run() {
        try {
            currentFrame.set(0);

            int maxFrame = 0;
            for(AnimationElement e : elements) {
                if(e.getNumberOfActions() > maxFrame) {
                    maxFrame = e.getNumberOfActions();
                }
            }

            sleep(initialDelay);

            for(AnimationElement element : elements) {
                Figura projected = new Figura(element.getFigura());
                foreground.drawAristas(element.getProyectador().proyectar(projected));
            }

            for(; currentFrame.get() < maxFrame; currentFrame.getAndIncrement()) {

                // Dibujar polígonos
                for(AnimationElement element : elements) {
                    if(element.getNumberOfActions() > currentFrame.get()) {
                        element.getFigura().transform(element.getAction(currentFrame.get()));
                    }
                    Figura projected = new Figura(element.getFigura());
                    foreground.drawAristas(element.getProyectador().proyectar(projected));
                }

                // Dibujar frames
                sleep(frameDelay);
                drawFrame();
                sendFrame();
                postFrameOperations();

            }
        } catch (InterruptedException ignored) {

        } finally {
            finish();
        }
    }

    protected void drawFrame() {
        frame.getGraphics().drawImage(background.getImage(), 0, 0, observer);
        frame.getGraphics().drawImage(foreground.getImage(), 0, 0, observer);
    }

    protected void sendFrame() {
        for(AnimationFrameListener l : frameListeners) {
            l.drawFrame(frame, currentFrame.get());
        }
    }

    protected void postFrameOperations() {}

    protected void finish() {
        for(AnimationFrameListener l : frameListeners) {
            l.animationFinished(this, frame);
        }
    }

    public Dibujante3D getForeground() {
        return foreground;
    }

    public Dibujante3D getBackground() {
        return background;
    }

    public void setFrameAsBackground(BufferedImage image) {
        this.background.getImage().getGraphics().drawImage(image, 0, 0, observer);
    }

    public AnimationElement getElement(int index) {
        return elements.get(index);
    }

    public int getCurrentFrame() {
        return currentFrame.get();
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public void setInitialDelay(int initialDelay) {
        this.initialDelay = initialDelay;
    }
}
