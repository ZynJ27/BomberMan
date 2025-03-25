package bomberman.viewcontroller;

import javax.swing.*;

import bomberman.model.GestorPantallaInicio;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

public class PantallaEleccion extends JFrame {

	private JPanel mainPanel;
	private JLabel2 lblTitle;
	private JLabel2 lblBoss;
	private JLabelBomberman lblBomberman1, lblBomberman2, lblBomberman3, lblBomberman4;
	private JLabel2 lblMonster1, lblMonster2;
	private ControladorMouse controladorMouse = null;

	public PantallaEleccion() {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		this.setResizable(false);

		mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon(getClass().getResource("back.png"));
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		setVisible(true);

		// Título
		lblTitle = new JLabel2();
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setVerticalAlignment(JLabel.CENTER);
		lblTitle.setBounds(0, 10, getWidth(), 100);
		lblTitle.setIcon(new ImageIcon(getClass().getResource("title.png")));
		mainPanel.add(lblTitle);

		// Imagen del boss
		lblBoss = new JLabel2();
		lblBoss.setHorizontalAlignment(JLabel.CENTER);
		lblBoss.setVerticalAlignment(JLabel.CENTER);
		lblBoss.setBounds(248, 152, 250, 250);
		lblBoss.setIcon(new ImageIcon(getClass().getResource("boss2.png")));
		mainPanel.add(lblBoss);

		// Imágenes de los Bomberman
		lblBomberman1 = new JLabelBomberman("Bomberman1","bomber1.png");
		lblBomberman1.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman1.setVerticalAlignment(JLabel.CENTER);
		lblBomberman1.setBounds(145, 346, 100, 120);
		mainPanel.add(lblBomberman1);

		lblBomberman2 = new JLabelBomberman("Bomberman2","bomber2.png");
		lblBomberman2.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman2.setVerticalAlignment(JLabel.CENTER);
		lblBomberman2.setBounds(95, 200, 100, 120);
		mainPanel.add(lblBomberman2);

		lblBomberman3 = new JLabelBomberman("Bomberman3","bomber3.png");
		lblBomberman3.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman3.setVerticalAlignment(JLabel.CENTER);
		lblBomberman3.setBounds(574, 200, 100, 120);
		mainPanel.add(lblBomberman3);

		lblBomberman4 = new JLabelBomberman("Bomberman4","bomber4.png");
		lblBomberman4.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman4.setVerticalAlignment(JLabel.CENTER);
		lblBomberman4.setBounds(508, 346, 100, 120);
		mainPanel.add(lblBomberman4);


		// Monstruos
		lblMonster1 = new JLabel2();
		lblMonster1.setHorizontalAlignment(JLabel.CENTER);
		lblMonster1.setVerticalAlignment(JLabel.CENTER);
		lblMonster1.setBounds(10, 327, 50, 50);
		lblMonster1.setIcon(new ImageIcon(getClass().getResource("pass1.png")));
		mainPanel.add(lblMonster1);

		lblMonster2 = new JLabel2();
		lblMonster2.setHorizontalAlignment(JLabel.CENTER);
		lblMonster2.setVerticalAlignment(JLabel.CENTER);
		lblMonster2.setIcon(new ImageIcon(getClass().getResource("doria2.png")));
		lblMonster2.setBounds(736, 327, 50, 50);
		mainPanel.add(lblMonster2);
		
		
		mainPanel.revalidate();
		mainPanel.repaint();
		mainPanel.addMouseMotionListener(this.getControladorMouse());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				PantallaEleccion frame = new PantallaEleccion();
				frame.setVisible(true);
			}
		});
	}
	
	private ControladorMouse getControladorMouse() {
		if (controladorMouse == null) {
			controladorMouse = new ControladorMouse();
		}
		return controladorMouse;
	}
	
	private class ControladorMouse extends MouseAdapter {
		private ControladorMouse() {}
		@Override
		public void mouseMoved(MouseEvent e) {
		    Component comp = ((JPanel) e.getSource()).getComponentAt(e.getPoint());

		    if (comp instanceof JLabelBomberman) {
		        JLabelBomberman lbl = (JLabelBomberman) comp;
		        GestorPantallaInicio.getGestorPantallaInicio().setBombermanActivo(lbl.getNombreBomberman());
		    }
		    else {
		    	GestorPantallaInicio.getGestorPantallaInicio().setBombermanActivo("");
		    }
		}
	}
}
