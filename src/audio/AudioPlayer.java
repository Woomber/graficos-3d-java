package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    // https://archive.org/details/TetrisThemeMusic
    public static final String TETRIS_WAV = "tetris.wav";
    public static final String GAMEOVER_WAV = "gameover.wav";

    protected static AudioPlayer instance;

    protected final AudioInputStream inputStream;
    protected final Clip clip;

    public AudioPlayer(String path, boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path).getAbsoluteFile();
        inputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(inputStream);
        if(loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
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

    public static void initAndPlay(String path, boolean loop) {
        try {
            instance = new AudioPlayer(path, loop);
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
