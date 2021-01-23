import dibujante.Dibujante3D;
import figuras.Figura;
import figuras.FiguraCubo;
import figuras.FiguraTetris;
import matrices.Eje;
import matrices.MatrizEscalado;
import matrices.MatrizRotacion;
import matrices.MatrizTraslacion;
import matrices.plano.Punto2D;
import projections.ParallelOrthogonalProjection;
import projections.ParallelProjection;
import projections.PerspectiveProjection;
import projections.Proyectador;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {

    protected static final double PROJECTION_ANGLE = Math.atan(-2);

    protected Dibujante3D dibujante3D;
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

            // Proyección Paralela Ortogonal
            //proyectar(new FiguraTetris(), new ParallelOrthogonalProjection());

            // Proyecciones Paralelas Oblicuas
            // Proyección Cabinet
            // proyectar(new FiguraCubo(), new ParallelProjection(0.5 * Math.cos(PROJECTION_ANGLE), 0.5 * Math.sin(PROJECTION_ANGLE)));
            // Proyección Cavalier
            // proyectar(new FiguraCubo(), new ParallelProjection(Math.cos(PROJECTION_ANGLE), Math.sin(PROJECTION_ANGLE)));
            // Proyección Isométrica
            // proyectar(new FiguraCubo(), new ParallelProjection(Math.PI/4, Math.PI/4));

            // Proyección Perspectiva
            proyectar(new FiguraCubo(), new PerspectiveProjection(2));

        }
    }

    protected void proyectar(Figura figura, Proyectador proyectador) {

        dibujante3D.drawAristas(proyectador.proyectar(
                figura
                        .transform(new MatrizEscalado(1.1))
                        .transform(new MatrizRotacion(Eje.Y, 30, true))
                        .transform(new MatrizRotacion(Eje.X, -30, true))
                        .transform(new MatrizTraslacion(40, 40, 100))
        ));

        getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
