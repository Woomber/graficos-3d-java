import animation.*;
import audio.AudioPlayer;
import dibujante.Dibujante3D;
import matrices.*;
import matrices.plano.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class MainWindow extends JFrame {

    protected Dibujante3D dibujante3D;
    protected Dibujante3D background;
    protected boolean drawn = false;

    protected final Punto2D origin;

    public MainWindow() {
        this.setSize(800, 600);
        this.setTitle("Proyecto Final - Yael Chavoya");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.origin = new Punto2D(getWidth()/2.0, getHeight()/2.0);

        AudioPlayer.initAndPlay();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                AudioPlayer.stopInstance();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if(!drawn) {
            drawn = true;
            dibujante3D = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB), this);
            dibujante3D.setOrigin(origin);

            setupBackground();
            setupAnimation();
        }
    }

    protected void setupBackground() {
        background = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB), this);
        background.setColor(Color.WHITE);
        background.clear();
        background.setOrigin(origin);
        background.setColor(new Color(200, 200, 200));
        background.drawLine(new Punto2D(0, origin.getY()), new Punto2D(0, origin.getY()));
        background.drawLine(new Punto2D(origin.getX(), 0), new Punto2D(origin.getX(), 0));
    }

    protected void setupAnimation() {
        ScoreAnimation animation = new ScoreAnimation(getWidth(), getHeight(), this, 0, 117500, 100);
        animation.setOrigin(origin);
        animation.addGeneralAction(new MatrizEscalado(7.5));
        animation.addGeneralAction(new MatrizTraslacion(250, -250, 0));
        animation.setFrameDelay(100);

        AnimationQueue.add(animation);
        AnimationQueue.play(getGraphics());
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
