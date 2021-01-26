import animation.*;
import dibujante.Dibujante3D;
import factories.NumberCurveFactory;
import figuras.*;
import functions.*;
import matrices.*;
import matrices.plano.*;
import meshes.*;
import projections.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainWindow extends JFrame {

    protected static final double PROJECTION_ANGLE = Math.atan(-2);

    protected Dibujante3D dibujante3D;
    protected Dibujante3D background2D, background3D;
    protected boolean drawn = false;

    public MainWindow() {
        this.setSize(800, 600);
        this.setTitle("Proyecciones");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        if(!drawn) {
            drawn = true;
            dibujante3D = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB), this);
            dibujante3D.setOrigin(new Punto2D(getWidth()/2, getHeight()/2));

            background2D = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB), this);
            background2D.setColor(Color.WHITE);
            background2D.clear();
            background2D.setOrigin(new Punto2D(getWidth()/2, getHeight()/2));
            background2D.setColor(new Color(200, 200, 200));
            background2D.drawLine(new Punto2D(0, -getHeight()/2), new Punto2D(0, getHeight()/2));
            background2D.drawLine(new Punto2D(-getWidth()/2, 0), new Punto2D(getWidth()/2, 0));
            getGraphics().drawImage(background2D.getImage(), 0, 0, this);


            dibujarCurva3D();
        }
    }

    protected void dibujarCurva3D() {
        Proyectador proy = new ParallelOrthogonalProjection();


        final double scale = 7.5;
        for(int i = 0; i < 10; i++) {
            // Dibujar curva
            List<Curva3D> curvas = NumberCurveFactory.buildDigit(i);
            for (Curva3D c: curvas) {
                dibujante3D.drawCurve(proy.proyectar(c
                        .transform(new MatrizEscalado(scale))
                        .transform(new MatrizTraslacion(380 - 2.5 * scale * i, -220 - scale*3, 0))
                ));
            }
        }

        getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
