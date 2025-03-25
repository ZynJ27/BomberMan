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
	
	private GestorPantallaInicio() {
		bombermanActivo = "";
	}
	
	public static GestorPantallaInicio getGestorPantallaInicio() {
		if (miGestor == null) miGestor = new GestorPantallaInicio();
		return miGestor;
	}
	
	public void setBombermanActivo(String nombreBomberman) { 
	        bombermanActivo = nombreBomberman;
	        notificar();
	}
	
	private void notificar() {
		setChanged();
		Object[] array = new Object[1];
		array[0] = (Object) bombermanActivo;
		notifyObservers(array);
	}
}
