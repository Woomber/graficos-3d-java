package animation;

import java.awt.image.BufferedImage;

public interface AnimationFrameListener {
    void drawFrame(BufferedImage frame, Animation sender, int frameNumber);
    void animationFinished(Animation sender, BufferedImage lastFrame);
}
