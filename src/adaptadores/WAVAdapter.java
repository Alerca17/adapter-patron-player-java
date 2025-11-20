package adaptadores;


import interfaz.Reproductible;
import externos.WAVPlayer;

public class WAVAdapter implements Reproductible {

    private WAVPlayer wav;

    public WAVAdapter(String ruta) {

        this.wav = new WAVPlayer(ruta);
    }

    @Override
    public void play() {

        wav.playWAV();
    }
}
