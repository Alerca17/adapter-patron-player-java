package menu;

import adaptadores.MP3Adapter;
import adaptadores.WAVAdapter;
import interfaz.Reproductible;
import player.AudioPlayer;
import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFileChooser fc = new JFileChooser();

            fc.setDialogTitle("Selecciona archivo MP3 o WAV");
            int sel = fc.showOpenDialog(null);

            if (sel != JFileChooser.APPROVE_OPTION) {

                System.out.println("No se seleccionó archivo. Saliendo.");
                return;
            }

            File f = fc.getSelectedFile();
            String ruta = f.getAbsolutePath();

            System.out.println("Archivo seleccionado: " + ruta);

            Reproductible adaptado = crearAdaptadorSegunExtension(ruta);

            if (adaptado == null) {

                JOptionPane.showMessageDialog(null,
                        "Formato no soportado. Selecciona .mp3 o .wav",
                        "Formato invalido",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            AudioPlayer player = new AudioPlayer();
            player.reproducir(adaptado);
        });
    }

    private static Reproductible crearAdaptadorSegunExtension(String ruta) {

        String ext = obtenerExtension(ruta).toLowerCase();

        switch (ext) {
            case "mp3":
                return new MP3Adapter(ruta);
            case "wav":
                return new WAVAdapter(ruta);
            default:
                return null;
        }
    }

    private static String obtenerExtension(String ruta) {

        int i = ruta.lastIndexOf('.');

        if (i > 0 && i < ruta.length() - 1) {

            return ruta.substring(i + 1);
        }
        return "";
    }
}
