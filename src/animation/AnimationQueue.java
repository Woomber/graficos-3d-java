package animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Queue;

public class AnimationQueue {

    protected final static Queue<Animation> queue = new LinkedList<>();

    protected static Graphics graphics;
    protected static boolean repeat = false;

    protected static AnimationFrameListener listener = new AnimationFrameListener() {
        @Override
        public void drawFrame(BufferedImage frame, Animation sender, int frameNumber) {
            if(graphics != null) {
                graphics.drawImage(frame, 0, 0, sender.observer);
            }
        }

        @Override
        public void animationFinished(Animation sender, BufferedImage lastFrame) {
            startNext();
            System.out.println(sender + " finished");
        }
    };

    public static void add(Animation animation) {
        queue.add(animation);
    }

    public static void play(Graphics g) {
        start(g, false);
    }

    public static void playOnRepeat(Graphics g) {
        start(g, true);
    }

    protected static void start(Graphics g, boolean onRepeat) {
        graphics = g;
        repeat = onRepeat;

        startNext();
    }

    protected static void startNext() {
        try {
            if(!queue.isEmpty()) {
                Animation a = queue.remove();
                if(repeat) queue.add(new Animation(a));
                a.addListener(listener);
                a.start();
            }
        } catch (IllegalThreadStateException ignored) {}
    }

}
