package dibujante;

import matrices.plano.Arista2D;
import matrices.plano.Punto2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.List;

public class Dibujante3D {

    protected static final boolean DEBUG_COORDS = false;

    protected final BufferedImage pixel;

    protected BufferedImage image;

    protected final ImageObserver observer;

    protected Color color = Color.BLACK;
    protected Punto2D origin;
    protected Punto2D boundLow, boundHigh;

    public Dibujante3D(BufferedImage image, ImageObserver observer) {
        assert image != null;

        this.image = image;
        this.observer = observer;

        this.boundLow = new Punto2D(0, 0);
        this.boundHigh = new Punto2D(image.getWidth(), image.getHeight());

        this.origin = new Punto2D(0, 0);

        pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        pixel.createGraphics();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPixel(int x, int y) {
        setPixel(new Punto2D(x, y));
    }

    public void setPixel(Punto2D p) {
        pixel.setRGB(0,0, color.getRGB());
        if (DEBUG_COORDS) System.out.format("x: %d, y: %d\n", p.getIntX(), p.getIntY());
        try {
            image.getGraphics().drawImage(pixel, p.getIntX() + origin.getIntX(), p.getIntY() + origin.getIntY(), observer);
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage resetBuffer() {
        image = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        image.createGraphics();

        return image;
    }

    public void setOrigin(Punto2D p) {
        origin = new Punto2D(p.getX(), p.getY());
        boundLow = new Punto2D(boundLow.getX() - p.getX(), boundLow.getY() - p.getY());
        boundHigh = new Punto2D(boundHigh.getX() - p.getX(), boundHigh.getY() - p.getY());
    }

    public Color getPixelColor(int x, int y) {
        return getPixelColor(new Punto2D(x, y));
    }

    public Color getPixelColor(Punto2D p) {
        try {
            return new Color(image.getRGB(p.getIntX() + origin.getIntX(), p.getIntY() + origin.getIntY()), true);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null;
        }
    }

    public void clear() {
        clear(Color.WHITE);
    }

    public void clear(Color c) {
        Color oldColor = new Color(color.getRGB(), true);
        setColor(c);
        for(int x = boundLow.getIntX(); x < boundHigh.getIntX(); x++) {
            for(int y = boundLow.getIntY(); y < boundHigh.getIntY(); y++) {
                setPixel(x, y);
            }
        }
        setColor(oldColor);
    }

    protected double degToRad(double deg) {
        return deg * Math.PI / 180.0;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {

        int dy = y2 - y1;
        int dx = x2 - x1;

        if(dy == 0 && dx == 0) {
            setPixel(x1, y1);
            return;
        }

        boolean yMain = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        int dMain, dDep, currMain, currDep, goal;
        int step = 1;

        if(yMain) {
            dMain = dy;
            dDep = dx;
            currMain = y1;
            currDep = x1;
            goal = y2;
        } else {
            dMain = dx;
            dDep = dy;
            currMain = x1;
            currDep = y1;
            goal = x2;
        }

        if(dMain < 0) step = -1;

        float m = (float) dDep / (float) dMain;
        float b = currDep - m*currMain;
        do {
            if(yMain) {
                setPixel(currDep, currMain);
            }
            else {
                setPixel(currMain, currDep);
            }
            currMain += step;
            currDep = Math.round(m * currMain + b);
        }  while(currMain != goal);

        setPixel(x2, y2);
    }

    public void drawLine(Punto2D p1, Punto2D p2) {
        drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
    }

    public void drawRectangle(int x1, int y1, int width, int height) {
        drawLine(x1, y1, x1 + width, y1);
        drawLine(x1 + width, y1, x1 + width, y1 + height);
        drawLine(x1, y1, x1, y1 + height);
        drawLine(x1, y1 + height, x1 + width, y1 + height);
    }

    public void drawPolygon(List<Punto2D> puntos) {
        drawCurve(puntos);
        int lastIdx = puntos.size() - 1;
        drawLine(puntos.get(lastIdx), puntos.get(0));
    }

    public void drawCurve(List<Punto2D> puntos) {
        for(int i = 0; i < puntos.size() - 1; i++) {
            drawLine(puntos.get(i), puntos.get(i + 1));
        }
    }

    public void drawArista(Arista2D arista2D) {
        drawLine(arista2D.getA(), arista2D.getB());
    }

    public void drawAristas(List<Arista2D> aristas) {
        for(Arista2D a : aristas) {
           drawArista(a);
        }
    }

    public void setBounds(Punto2D boundLow, Punto2D boundHigh) {
        this.boundLow = boundLow;
        this.boundHigh = boundHigh;
    }

}
