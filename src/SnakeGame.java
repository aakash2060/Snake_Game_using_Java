import javax.sound.sampled.*; // for Clip interface
import java.io.File; // for File Oject
import java.io.IOException; //for stoping IoException to crash the program
import java.util.Scanner; // for scanner

public class SnakeGame {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        new GameFrame ();

        // For Audio Input
        File file = new File("C:\\Users\\WINDOWS 10\\SnakeGame\\Music.wav");// music must be in .wav format
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); // audioStream cannot execute the file if its in other format other than wav
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream); // opens audio and load it into the Clip object.
        clip.start(); //plays the audio

        String response = scanner.next(); // helps us to not stop the program as it asks for an input which won't be given


    }
}
