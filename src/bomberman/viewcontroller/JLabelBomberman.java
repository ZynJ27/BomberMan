package bomberman.viewcontroller;

import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bomberman.model.GestorPantallaInicio;

public class JLabelBomberman extends JLabel implements Observer {
	private String nombreBomberman;
	private ImageIcon originalIcon;
	private ImageIcon grayIcon;

	public JLabelBomberman(String pNombreBomberman, String imagePath) {
		super();
		this.nombreBomberman = pNombreBomberman;
		this.originalIcon = new ImageIcon(getClass().getResource(imagePath));
		this.grayIcon = toGrayScale(originalIcon);
		this.setIcon(grayIcon);
		GestorPantallaInicio.getGestorPantallaInicio().addObserver(this); 
		
	}
	
	 public String getNombreBomberman() {
		 return nombreBomberman;
		 }

	private static ImageIcon toGrayScale(ImageIcon icon) {
		BufferedImage img = new BufferedImage(
				icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.drawImage(icon.getImage(), 0, 0, null);
		g.dispose();

		BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		img = op.filter(img, null);

		return new ImageIcon(img);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GestorPantallaInicio) {
			Object [] res = (Object[]) arg;
			String bombermanActivo = (String) res[0];
			 
			if (bombermanActivo.equals(nombreBomberman)) {
				this.setIcon(originalIcon);
			}
			else {
				this.setIcon(grayIcon);
			}
		}
	}

}