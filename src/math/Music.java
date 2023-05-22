package math;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {

    public static void rightMusic() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new URL("file", "localhost","sound/cleanrow.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void wrongMusic() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new URL("file", "localhost","sound/keyboard.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void winMusic() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new URL("file", "localhost","sound/win.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
