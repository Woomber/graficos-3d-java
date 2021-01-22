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

    protected Dibujante3D dibujante3D;
    protected Proyectador proyectador;
    protected Figura figura;
    protected boolean drawn = false;

    public MainWindow() {
        this.setSize(800, 600);
        this.setTitle("Proyecciones");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===========================

        // Proyección Paralela Ortogonal
        // figura = new FiguraTetris();
        //proyectador = new ParallelOrthogonalProjection();

        // ===========================

        // Proyecciones Paralelas Oblicuas
        double projectionAngle = Math.atan(-2);

        // Proyección Cabinet
        //figura = new FiguraCubo();
        //proyectador = new ParallelProjection(0.5 * Math.cos(projectionAngle), 0.5 * Math.sin(projectionAngle));

        // Proyección Cavalier
        //figura = new FiguraCubo();
        //proyectador = new ParallelProjection(Math.cos(projectionAngle), Math.sin(projectionAngle));

        // Proyección Isométrica
        //figura = new FiguraCubo();
        //proyectador = new ParallelProjection(Math.PI/4, Math.PI/4);

        // ===========================

        figura = new FiguraCubo();
        proyectador = new PerspectiveProjection(2);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        if(!drawn) {
            drawn = true;
            dibujante3D = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB), this);
            dibujante3D.setOrigin(new Punto2D(getWidth()/2, getHeight()/2));

            dibujante3D.drawAristas(proyectador.proyectar(
                    figura
                        //.transform(new MatrizTraslacion(20, 20, -75))
                        .transform(new MatrizTraslacion(-25, -25, -75))
                        .transform(new MatrizRotacion(Eje.Y, 30, true))
                        .transform(new MatrizRotacion(Eje.X, -30, true))
                        .transform(new MatrizTraslacion(0, 0, 75))
            ));

            getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);

        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
