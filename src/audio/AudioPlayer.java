package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    protected static final String PATH = "tetris.wav";
    protected static AudioPlayer instance;

    protected final AudioInputStream inputStream;
    protected final Clip clip;

    public AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(PATH).getAbsoluteFile();
        inputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        if(clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if(clip != null) {
            clip.stop();
        }
    }

    public static void initAndPlay() {
        try {
            instance = new AudioPlayer();
            instance.play();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public static void stopInstance() {
        if(instance != null) {
            instance.stop();
        }
    }
}
