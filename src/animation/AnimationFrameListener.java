package animation;

import java.awt.image.BufferedImage;

public interface AnimationFrameListener {
    void drawFrame(BufferedImage frame, int frameNumber);
    void animationFinished(Object sender, BufferedImage lastFrame);
}
