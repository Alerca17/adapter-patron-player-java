package externos;

import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class MP3PlayerJL {

    private String ruta;

    public MP3PlayerJL(String ruta) {

        this.ruta = ruta;
    }

    public void playMP3() {

        try (FileInputStream fis = new FileInputStream(ruta);

             BufferedInputStream bis = new BufferedInputStream(fis)) {

                Player player = new Player(bis);
                System.out.println("Reproduciendo MP3: " + ruta);

                player.play();
                System.out.println("Fin reproducción MP3.");

            }

        catch (Exception e) {

            System.out.println("Error reproduciendo MP3: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
