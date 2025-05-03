package bomberman.model;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.Observable;
import javax.swing.ImageIcon;

public class GestorPantallaInicio extends Observable {
	private static GestorPantallaInicio miGestor = null;
	private String bombermanActivo;
	private boolean partidaIniciada;

	private GestorPantallaInicio() {
		bombermanActivo = "";
	}

	public static GestorPantallaInicio getGestorPantallaInicio() {
		if (miGestor == null) miGestor = new GestorPantallaInicio();
		return miGestor;
	}

	public void setBombermanActivo(String nombreBomberman) { 
		GestorSonidos.getGestorSonidos().sonidoClickBomberman("seleccionar", bombermanActivo);
		bombermanActivo = nombreBomberman;
		notificar("");
	}

	public void setPartida(String nombreBomberman, String tipoTablero)
	{
		bombermanActivo = nombreBomberman;
		GestorSonidos.getGestorSonidos().sonidoClickBomberman("elegido", bombermanActivo);
		GestorTablero.getGestor().inicializarTablero(nombreBomberman, tipoTablero);
		this.partidaIniciada=true;
		notificar(tipoTablero);
		this.partidaIniciada=false;
	}



	private void notificar(String tipoTablero) {
		setChanged();
		Object[] array = new Object[3];
		array[0] = (Object) bombermanActivo;
		array[1]= (Object) partidaIniciada;
		array[2]= (Object) tipoTablero;

		notifyObservers(array);

	}
}