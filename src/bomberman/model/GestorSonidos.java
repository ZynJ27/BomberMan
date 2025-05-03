package bomberman.model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorSonidos {
    private Clip clipMusicaInicial;
    private Clip clipMusicaPartida;
    private String nombreBomberman = "";
    private static GestorSonidos miGestorSonidos = null;

    private GestorSonidos() {}

    public static GestorSonidos getGestorSonidos() {
        if (miGestorSonidos == null) miGestorSonidos = new GestorSonidos();
        return miGestorSonidos;
    }

    public void sonidoClickBomberman(String pTipo, String pNombreBomberman) {
        if (nombreBomberman.equals("") && !pNombreBomberman.equals("")){
            nombreBomberman = pNombreBomberman;
            this.ejecutarSonido(pTipo);
        }
        else if (!nombreBomberman.equals("") && pNombreBomberman.equals("")) {
            nombreBomberman = pNombreBomberman;
        }
    }

    public void sonido(String pTipo) {
        this.ejecutarSonido(pTipo);
    }

    private void ejecutarSonido(String pTipo) {
        boolean cancionQ = false;
        File archivo = null;

        // Determinamos qué archivo de sonido se debe usar
        if (pTipo.equals("musicaInicio")) {
            archivo = new File("src/bomberman/viewcontroller/musicaInicio.wav");
            cancionQ = true;
        }
        else if (pTipo.equals("musicaPartida")) {
            archivo = new File("src/bomberman/viewcontroller/musicaPartida.wav");
            cancionQ = true;
        }
        else if (pTipo.equals("seleccionar")) archivo = new File("src/bomberman/viewcontroller/elegirBomberman.wav");
        else if (pTipo.equals("elegido")) archivo = new File("src/bomberman/viewcontroller/elegidoBomberman.wav");
        else if (pTipo.equals("andarBomberman")) archivo = new File ("src/bomberman/viewcontroller/andarBomberman.wav");
        else if (pTipo.equals("bombaPuesta")) archivo = new File ("src/bomberman/viewcontroller/bombaPuesta.wav");
        else if (pTipo.equals("explosion")) archivo = new File ("src/bomberman/viewcontroller/explosion.wav");

        try {
            // Cargar el archivo de sonido
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivo);
            Clip clipNuevo = AudioSystem.getClip();
            clipNuevo.open(audioInputStream);

            // Si no es música, reproducir el sonido
            if (!cancionQ) {
                this.detener(clipNuevo); // Detener cualquier sonido anterior
                this.reproducir(clipNuevo); // Reproducir el sonido actual
            }
            // Si es música de fondo
            else if (pTipo.equals("musicaInicio")) {
                clipMusicaInicial = clipNuevo;
                this.reproducir(clipMusicaInicial);
            }
            else if (pTipo.equals("musicaPartida")) {
                clipMusicaPartida = clipNuevo;
                this.reproducir(clipMusicaPartida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void detener(Clip pC) {
        if (pC.isRunning()) {
            pC.stop();
        }
    }

    private void reproducir(Clip pC) {
        if (pC != null) {
            pC.setFramePosition(0); // Reiniciar la posición del clip a 0
            if (pC.equals(clipMusicaPartida)) {
                pC.loop(Clip.LOOP_CONTINUOUSLY); // Música de fondo
            } else {
                pC.start(); // Reproducir sonido una vez
            }
        }
    }

    public void detenerMusica(String pTipo) {
        if (pTipo.equals("inicial")) this.detener(clipMusicaInicial);
        else if (pTipo.equals("partida")) this.detener(clipMusicaPartida);
    }
}
