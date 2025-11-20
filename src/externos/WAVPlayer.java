package externos;

import javax.sound.sampled.*;
import java.io.File;

public class WAVPlayer {

    private final String ruta;

    public WAVPlayer(String ruta) {

        this.ruta = ruta;
    }

    public void playWAV() {
        
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(ruta))) {

            AudioInputStream decodedStream = AudioSystem.getAudioInputStream(
                    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            ais.getFormat().getSampleRate(), 16,
                            ais.getFormat().getChannels(),
                            ais.getFormat().getChannels() * 2,
                            ais.getFormat().getSampleRate(), false),
                    ais);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, decodedStream.getFormat()));

            line.open(decodedStream.getFormat());
            line.start();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = decodedStream.read(buffer, 0, buffer.length)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.close();

        } catch (Exception e) {
        }
    }
}
