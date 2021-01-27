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
        setLayout(new BorderLayout());

        this.origin = new Punto2D(getWidth()/2.0, getHeight()/2.0);

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
        getGraphics().drawImage(background.getImage(), 0, 0, this);
    }

    protected QueueFinishedListener onFinished = lastAnimation -> {
        if(lastAnimation instanceof GameOverAnimation) {
            setupBackground();
            setupAnimation();
            return;
        }
        Animation gameOver = new GameOverAnimation(getWidth(), getHeight(), MainWindow.this);
        gameOver.getBackground().clear(new Color(0, 0, 0, 0));
        gameOver.setDrawOriginCenter();
        gameOver.addGeneralAction(new MatrizTraslacion(-10.25, 0, 0));
        gameOver.addGeneralAction(new MatrizEscalado(10));
        AnimationQueue.add(gameOver);
        AnimationQueue.play(getGraphics());
    };

    protected void setupAnimation() {
        double scoreScale = 7.5;
        int scoreWidth = (int) Math.ceil(NumberCurveFactory.OFFSET_INCREMENT * scoreScale * 8.5) + 30;
        int scoreHeight = 200;

        Animation scoreAnimation = new ScoreAnimation(scoreWidth, scoreHeight, this, 0, 117500, 100);
        scoreAnimation.setDrawOrigin(new Punto2D(scoreScale, 175));
        scoreAnimation.setScreenPosition(new Punto2D(getWidth() - scoreWidth, 0));
        scoreAnimation.addGeneralAction(new MatrizEscalado(scoreScale));
        scoreAnimation.setInitialDelay(1000);
        AnimationQueue.add(scoreAnimation);

        Animation boardAnimation = new TetrisBoardAnimation(getWidth() - scoreWidth, getHeight(), this);
        boardAnimation.setDrawOriginCenter();
        boardAnimation.setInitialDelay(1000);
        AnimationQueue.add(boardAnimation);

        Animation shapeAnimation = new TetrisShapeAnimation(scoreWidth, getHeight() - scoreHeight, this);
        shapeAnimation.setInitialDelay(1000);
        shapeAnimation.setScreenPosition(new Punto2D(getWidth() - scoreWidth, scoreHeight));
        AnimationQueue.add(shapeAnimation);

        if(!AnimationQueue.isListener(onFinished)) {
            AnimationQueue.addListener(onFinished);
        }

        AnimationQueue.playParallel(getGraphics());
        AudioPlayer.initAndPlay(AudioPlayer.TETRIS_WAV, true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
