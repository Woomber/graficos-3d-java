import dibujante.Dibujante3D;
import figuras.Curva3D;
import figuras.Figura;
import figuras.FiguraCubo;
import figuras.FiguraTetris;
import functions.Functions;
import matrices.*;
import matrices.plano.Punto2D;
import matrices.plano.Punto3D;
import meshes.Mesh2D;
import meshes.MeshRectangular;
import projections.ParallelOrthogonalProjection;
import projections.ParallelProjection;
import projections.PerspectiveProjection;
import projections.Proyectador;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MainWindow extends JFrame {

    protected static final double PROJECTION_ANGLE = Math.atan(-2);

    protected Dibujante3D dibujante3D;
    protected Dibujante3D background;
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

            background = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB), this);
            background.setColor(Color.WHITE);
            background.clear();
            background.setOrigin(new Punto2D(getWidth()/2, getHeight()/2));
            background.setColor(new Color(200, 200, 200));

            // Dibujar una de las proyecciones (0 al 3)
            // dibujarProyecciones(3);

            // Dibujar una de las curvas (0 al 7)
            // dibujarCurvas(6);

            // Dibujar la malla
            dibujarMalla();
        }
    }

    protected void dibujarMalla() {
        Mesh2D mesh = new MeshRectangular(25, 25, 8);
        dibujante3D.drawAristas(mesh.getAristas());

        getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);
    }

    protected void dibujarCurvas(int numCurva) {

        String out = "Dibujando\n\t";

        switch (numCurva) {
            // Seno de X (5)
            case 0:
                System.out.println(out + "y = sin(x)");
                dibujarCurva(Functions.create2DCurveExplicit(
                        (x) -> 200 * Math.sin(x/60),
                        0,
                        60.1 * Math.PI,
                        100,
                        Functions.MainVariable.X
                ));
                break;

            // Coseno de Y (6)
            case 1:
                System.out.println(out + "x = y cos(4y)");
                dibujarCurva(Functions.create2DCurveExplicit(
                        (y) ->  y * Math.cos(4*y / 15),
                        0,
                        30.1 * Math.PI,
                        100,
                        Functions.MainVariable.Y
                ));
                break;

            // Paramétricas (7)
            case 2:
                System.out.println(out + "x = t - 3sin(t)\n\ty = 4 - 3cos(t)");
                dibujarCurva(Functions.create2DCurveParametric(
                        (t) -> 10*(t - 3*Math.sin(t)),
                        (t) -> 10*(4 - 3 * Math.cos(t)),
                        0,
                        10,
                        100
                ));
                break;

            // Paramétricas infinito (8)
            case 3:
                System.out.println(out + "x = sin(t)/(1 + cos^2(t))\n\ty = sin(t)cos(t)/(1 + cos^2(t))");
                dibujarCurva(Functions.create2DCurveParametric(
                        (t) -> 100 * Math.sin(t)/(1 + Math.cos(t) * Math.cos(t)),
                        (t) -> 100 * Math.sin(t) * Math.cos(t)/(1 + Math.cos(t) * Math.cos(t)),
                        0,
                        2.1 * Math.PI,
                        100
                ));
                break;

            // Paramétrica patrón (9)
            case 4:
                System.out.println(out + "x = cos(t) + cos(7t)/2 + sin(17t)/3\n\ty = = sin(t) + sin(7t)/2 + cos(17t)/3");
                dibujarCurva(Functions.create2DCurveParametric(
                        (t) -> 100 * (Math.cos(t) + 0.5 * Math.cos(7 * t) + Math.sin(17 * t)/3.0),
                        (t) -> 100 * (Math.sin(t) + 0.5 * Math.sin(7 * t) + Math.cos(17 * t)/3.0),
                        0,
                        2.01 * Math.PI,
                        500
                ));
                break;

            // Paramétrica estrella (9)
            case 5:
                System.out.println(out + "x = 17cos(t) + 7cos(17t/7)\n\ty = 17sin(t) + 7sin(17t/7)");
                dibujarCurva(Functions.create2DCurveParametric(
                        (t) -> 10 * (17 * Math.cos(t) + 7 * Math.cos(17 * t / 7.0)),
                        (t) -> 10 * (17 * Math.sin(t) - 7 * Math.sin(17 * t / 7.0)),
                        0,
                        14.01 * Math.PI,
                        500
                ));
                break;

            // Paramétrica 3D resorte
            case 6:
                System.out.println(out + "x = cos(t)\n\ty = sin(t)\n\tz = t");
                dibujarCurva3D(Functions.create3DCurveParametric(
                        (t) -> 100 * Math.cos(t),
                        (t) -> 100 * Math.sin(t),
                        (t) -> 8 * t,
                        0,
                        8.01 * Math.PI,
                        500
                ));
                break;

            // Paramétrica 3D curva
            case 7:
                System.out.println(out + "x = cos(3t)\n\ty = 2cos^2(t)\n\tz = sin(2t)");
                dibujarCurva3D(Functions.create3DCurveParametric(
                        (t) -> 100 * Math.cos(3 * t),
                        (t) -> 100 * 2 * Math.cos(t) * Math.cos(t),
                        (t) -> 100 * Math.sin(2 * t),
                        - 1.01 * Math.PI,
                        1.01 * Math.PI,
                        500
                ));
                break;

            default:
                System.out.println("Número de curva inválido");
        }

    }

    protected void dibujarCurva(List<Punto2D> puntos) {
        // Dibujar ejes
        background.drawLine(new Punto2D(0, -getHeight()/2), new Punto2D(0, getHeight()/2));
        background.drawLine(new Punto2D(-getWidth()/2, 0), new Punto2D(getWidth()/2, 0));
        getGraphics().drawImage(background.getImage(), 0, 0, this);

        // Dibujar curva
        dibujante3D.drawCurve(puntos);
        getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);
    }

    protected void dibujarCurva3D(List<Punto3D> puntos) {
        Proyectador proy = new ParallelOrthogonalProjection();

        MatrizTransformacion tz = new MatrizRotacion(Eje.Z, -45, true);
        MatrizTransformacion tx = new MatrizRotacion(Eje.X, -60, true);

        // Dibujar ejes

        Curva3D ejeX = new Curva3D(), ejeY = new Curva3D(), ejeZ = new Curva3D();

        ejeX.addPuntos(new Punto3D[]{ new Punto3D(0, 0, 0), new Punto3D(500, 0, 0) });
        ejeY.addPuntos(new Punto3D[]{ new Punto3D(0, 0, 0), new Punto3D(0, 500, 0) });
        ejeZ.addPuntos(new Punto3D[]{ new Punto3D(0, 0, 0), new Punto3D(0, 0, 500) });

        background.drawCurve(proy.proyectar(ejeX.transform(tz).transform(tx)));
        background.drawCurve(proy.proyectar(ejeY.transform(tz).transform(tx)));
        background.drawCurve(proy.proyectar(ejeZ.transform(tz).transform(tx)));
        getGraphics().drawImage(background.getImage(), 0, 0, this);

        // Dibujar curva

        dibujante3D.drawCurve(proy.proyectar(new Curva3D(puntos).transform(tz).transform(tx)));
        getGraphics().drawImage(dibujante3D.getImage(), 0, 0, this);
    }

    protected void dibujarProyecciones(int numProy) {
        String out = "Usando proyección ";

        switch (numProy) {

            // Proyección Paralela Ortogonal
            case 0:
                System.out.println(out + "paralela ortogonal");
                proyectar(new FiguraTetris(), new ParallelOrthogonalProjection());
                break;

            // Proyecciones Paralelas Oblicuas (1 al 3)
            // Proyección Cabinet
            case 1:
                System.out.println(out + "paralela oblicua Cabinet");
                proyectar(new FiguraCubo(), new ParallelProjection(0.5 * Math.cos(PROJECTION_ANGLE), 0.5 * Math.sin(PROJECTION_ANGLE)));
                break;

            // Proyección Cavalier
            case 2:
                System.out.println(out + "paralela oblicua Cavalier");
                proyectar(new FiguraCubo(), new ParallelProjection(Math.cos(PROJECTION_ANGLE), Math.sin(PROJECTION_ANGLE)));
                break;

            // Proyección Perspectiva
            case 3:
                System.out.println(out + "perspectiva");
                proyectar(new FiguraCubo(), new PerspectiveProjection(2));
                break;

            default:
                System.out.println("Número de proyección inválido");
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
