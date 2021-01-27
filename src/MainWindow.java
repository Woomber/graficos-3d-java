import animation.*;
import audio.AudioPlayer;
import dibujante.Dibujante3D;
import factories.NumberCurveFactory;
import matrices.*;
import matrices.plano.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class MainWindow extends JFrame {

    protected Dibujante3D background;
    protected boolean drawn = false;

    protected final Punto2D origin;

    public MainWindow() {
        this.setSize(600, 800);
        this.setTitle("Proyecto Final - Yael Chavoya");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.origin = new Punto2D(getWidth()/2.0, getHeight()/2.0);

        //AudioPlayer.initAndPlay();

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

            setupBackground();
            setupAnimation();
        }
    }

    protected void setupBackground() {
        background = new Dibujante3D(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB), this);
        background.setColor(Color.WHITE);
        background.clear();
        background.setOrigin(origin);
        getGraphics().drawImage(background.getImage(), 0, 0, this);
    }

    protected void setupAnimation() {
        double scoreScale = 7.5;
        int scoreWidth = (int) Math.ceil(NumberCurveFactory.OFFSET_INCREMENT * scoreScale * 8.5) + 30;
        int scoreHeight = 50;

        Animation scoreAnimation = new ScoreAnimation(scoreWidth, scoreHeight, this, 0, 117500, 100);
        scoreAnimation.setDrawOrigin(new Punto2D(scoreScale, scoreHeight/2.0));
        scoreAnimation.setScreenPosition(new Punto2D(getWidth() - scoreWidth, 150));
        scoreAnimation.addGeneralAction(new MatrizEscalado(scoreScale));
        scoreAnimation.setInitialDelay(1000);
        AnimationQueue.add(scoreAnimation);

        Animation boardAnimation = new TetrisBoardAnimation(getWidth() - scoreWidth, getHeight(), this);
        boardAnimation.setDrawOriginCenter();
        boardAnimation.setInitialDelay(1000);
        AnimationQueue.add(boardAnimation);

        Animation shapeAnimation = new TetrisShapeAnimation(scoreWidth, getHeight() - scoreHeight - 150, this);
        shapeAnimation.setInitialDelay(1000);
        shapeAnimation.setScreenPosition(new Punto2D(getWidth() - scoreWidth - 20, 150 + scoreHeight));
        AnimationQueue.add(shapeAnimation);

        AnimationQueue.playParallel(getGraphics());
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
