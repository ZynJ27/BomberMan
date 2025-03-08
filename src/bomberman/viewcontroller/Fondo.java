package bomberman.viewcontroller;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Fondo extends JPanel {
	private Image backgroundImage;

    public Fondo(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
