package animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class AnimationQueue {

    protected final static Queue<Animation> queue = new LinkedList<>();
    protected final static List<Animation> playing = new ArrayList<>();
    protected final static List<QueueFinishedListener> listeners = new ArrayList<>();

    protected static Graphics graphics;
    protected static boolean repeat = false;

    protected static AnimationFrameListener listener = new AnimationFrameListener() {
        @Override
        public void drawFrame(BufferedImage frame, Animation sender, int frameNumber) {
            if(graphics != null) {
                graphics.drawImage(frame, sender.getScreenPosition().getIntX(), sender.getScreenPosition().getIntY(), sender.observer);
            }
        }

        @Override
        public void animationFinished(Animation sender, BufferedImage lastFrame) {
            startNext();
            System.out.println(sender + " finished");

            synchronized (AnimationQueue.class) {
                if(sender instanceof TetrisBoardAnimation) {
                    for(Animation a : playing) {
                        if(a.equals(sender)) continue;
                        a.interrupt();
                    }
                }

                playing.remove(sender);

                if(playing.size() == 0) {
                    QueueFinishedListener[] arr = new QueueFinishedListener[listeners.size()];
                    for(QueueFinishedListener l : listeners.toArray(arr)) l.queueFinished(sender);
                }
            }


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

    public static void playParallel(Graphics g) {
        graphics = g;
        while(!queue.isEmpty()) {
            Animation a = queue.remove();
            a.addListener(listener);
            a.start();
            playing.add(a);
        }
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

    public static void addListener(QueueFinishedListener listener) {
        listeners.add(listener);
    }

    public static boolean isListener(QueueFinishedListener listener) {
        return listeners.contains(listener);
    }

}
