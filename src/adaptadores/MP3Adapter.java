package adaptadores;

import interfaz.Reproductible;
import externos.MP3PlayerJL;

public class MP3Adapter implements Reproductible {

    private MP3PlayerJL mp3;

    public MP3Adapter(String ruta) {

        this.mp3 = new MP3PlayerJL(ruta);
    }

    @Override
    public void play() {

        mp3.playMP3();
    }
}