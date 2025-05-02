package bomberman.model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GestorSonidos {
	private Clip clip;
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
		if (pTipo.equals("musicaInicio")) {
			archivo = new File("src/bomberman/viewcontroller/musicaInicio.wav");
			cancionQ = true;
		}
		else if (pTipo.equals("musicaPartida")) {
			archivo = new File("src/bomberman/viewcontroller/musicaPartida.wav"); //
			cancionQ = true;
		}
		else if (pTipo.equals("seleccionar")) archivo = new File("src/bomberman/viewcontroller/elegirBomberman.wav");
		else if (pTipo.equals("elegido")) archivo = new File("src/bomberman/viewcontroller/elegidoBomberman.wav");
		else if (pTipo.equals("andarBomberman")) archivo = new File ("src/bomberman/viewcontroller/andarBomberman.wav"); //
		else if (pTipo.equals("bombaPuesta")) archivo = new File ("src/bomberman/viewcontroller/bombaPuesta.wav"); 
		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivo);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (clip != null) {
			if (!cancionQ) {
				this.detener(clip);
				this.reproducir(clip);
			}
			else if (pTipo.equals("musicaInicio")) {
				clipMusicaInicial = clip;
				this.reproducir(clipMusicaInicial);
			}
			else if (pTipo.equals("musicaPartida")) {
				clipMusicaPartida = clip;
				this.reproducir(clipMusicaPartida);
			}
		}
		
		
	}

	private void detener(Clip pC) {
		if (pC.isRunning()) {
			pC.stop();
		}
	}

	private void reproducir(Clip pC) {
		if (pC != null) {
			pC.setFramePosition(0);
			pC.start();
		}
		//Para reiniciar el sonido
		if (pC.equals(clip)) clip = null;
	}

	public void detenerMusica(String pTipo) {
		if (pTipo.equals("inicial")) this.detener(clipMusicaInicial);
		else if (pTipo.equals("partida")) this.detener(clipMusicaPartida);
	}
}
