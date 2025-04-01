package bomberman.viewcontroller;

import javax.swing.*;

import bomberman.model.GestorPantallaInicio;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


public class PantallaEleccion extends JFrame implements Observer {

	private JPanel mainPanel;
	private JLabel lblTitle;
	private JLabel lblBoss2, lblBoss3;
	private JLabelBomberman lblBomberman1, lblBomberman2, lblBomberman3, lblBomberman4;
	private ArrayList<JLabelBomberman> bombermans;
	private JLabel lblMonster1, lblMonster2;
	private JLabel lblExplosion;
	private ControladorMouse controladorMouse = null;
	private JComboBox<String> comboBoxTableros;
	
	public PantallaEleccion() {
		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(390, 180, 700, 450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

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
		lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setVerticalAlignment(JLabel.CENTER);
		lblTitle.setBounds(0, 11, getWidth(), 100);
		lblTitle.setIcon(new ImageIcon(getClass().getResource("title.png")));
		mainPanel.add(lblTitle);
		
		
		// Imagen del boss
		lblBoss2 = new JLabel();
		lblBoss2.setHorizontalAlignment(JLabel.CENTER);
		lblBoss2.setVerticalAlignment(JLabel.CENTER);
		lblBoss2.setBounds(205, 95, 250, 250);
		lblBoss2.setIcon(new ImageIcon(getClass().getResource("boss2.png")));
		mainPanel.add(lblBoss2);
		
		lblBoss3 = new JLabel();
		lblBoss3.setHorizontalAlignment(JLabel.CENTER);
		lblBoss3.setVerticalAlignment(JLabel.CENTER);
		lblBoss3.setBounds(560, 85, 250, 250);
		lblBoss3.setIcon(new ImageIcon(getClass().getResource("boss3.png")));
		mainPanel.add(lblBoss3);

		// Imágenes de los Bomberman
		lblBomberman1 = new JLabelBomberman("Bomberman1", "bomber1.png");
		lblBomberman1.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman1.setVerticalAlignment(JLabel.CENTER);
		lblBomberman1.setBounds(109, 272, 100, 120);
		
		//lblBomberman1.addMouseListener(getControladorMouse());
		//lblBomberman1.addMouseMotionListener(getControladorMouse());
		mainPanel.add(lblBomberman1);

		lblBomberman2 = new JLabelBomberman("Bomberman2", "bomber2.png");
		lblBomberman2.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman2.setVerticalAlignment(JLabel.CENTER);
		lblBomberman2.setBounds(71, 141, 100, 120);
		
		//lblBomberman2.addMouseListener(getControladorMouse());
		//lblBomberman2.addMouseMotionListener(getControladorMouse());
		mainPanel.add(lblBomberman2);

		lblBomberman3 = new JLabelBomberman("Bomberman3", "bomber3.png");
		lblBomberman3.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman3.setVerticalAlignment(JLabel.CENTER);
		lblBomberman3.setBounds(495, 141, 100, 120);
		
		//lblBomberman3.addMouseListener(getControladorMouse());
		//lblBomberman3.addMouseMotionListener(getControladorMouse());
		mainPanel.add(lblBomberman3);

		lblBomberman4 = new JLabelBomberman("Bomberman4", "bomber4.png");
		lblBomberman4.setHorizontalAlignment(JLabel.CENTER);
		lblBomberman4.setVerticalAlignment(JLabel.CENTER);
		lblBomberman4.setBounds(450, 272, 100, 120);
		
		//lblBomberman4.addMouseListener(getControladorMouse());
		//lblBomberman4.addMouseMotionListener(getControladorMouse());
		mainPanel.add(lblBomberman4);
		bombermans = new ArrayList<JLabelBomberman>();
		

		// Monstruos
		lblMonster1 = new JLabel();
		lblMonster1.setHorizontalAlignment(JLabel.CENTER);
		lblMonster1.setVerticalAlignment(JLabel.CENTER);
		lblMonster1.setBounds(26, 327, 50, 50);
		lblMonster1.setIcon(new ImageIcon(getClass().getResource("pass1.png")));
		mainPanel.add(lblMonster1);

		lblMonster2 = new JLabel();
		lblMonster2.setHorizontalAlignment(JLabel.CENTER);
		lblMonster2.setVerticalAlignment(JLabel.CENTER);
		lblMonster2.setIcon(new ImageIcon(getClass().getResource("doria2.png")));
		lblMonster2.setBounds(628, 346, 50, 50);
		mainPanel.add(lblMonster2);
		
		JLabel lblTipoTablero = new JLabel("Seleccione una tablero");
		lblTipoTablero.setForeground(Color.BLACK);
		lblTipoTablero.setBounds(265,335,200,20);
		mainPanel.add(lblTipoTablero);
		
		comboBoxTableros = new JComboBox<>();
		comboBoxTableros.setBounds(265, 356, 150, 30);
		comboBoxTableros.setBackground(Color.WHITE);
		comboBoxTableros.setForeground(Color.BLACK);
        mainPanel.add(comboBoxTableros);
        mainPanel.setComponentZOrder(comboBoxTableros, 0); 
        
        // Agregar elementos al JComboBox
        comboBoxTableros.addItem("classic");
        comboBoxTableros.addItem("soft");
        comboBoxTableros.addItem("empty");;
        
        
		mainPanel.revalidate();
		mainPanel.repaint();
		mainPanel.addMouseMotionListener(this.getControladorMouse());
		mainPanel.addMouseListener(this.getControladorMouse());
		GestorPantallaInicio.getGestorPantallaInicio().addObserver(this); 
	

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
		private ControladorMouse() {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Component comp = ((JPanel) e.getSource()).getComponentAt(e.getPoint());

			if (comp instanceof JLabelBomberman) {
				JLabelBomberman lbl = (JLabelBomberman) comp;
				GestorPantallaInicio.getGestorPantallaInicio().setBombermanActivo(lbl.getNombreBomberman());
			} else {
				GestorPantallaInicio.getGestorPantallaInicio().setBombermanActivo("");
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) { //ARREGLAR
			//Component comp = e.getComponent();
			Component comp = ((JPanel) e.getSource()).getComponentAt(e.getPoint());
			if (comp instanceof JLabelBomberman) {
				JLabelBomberman lbl = (JLabelBomberman) comp;
				
				// Obtener el tipo de tablero seleccionado
				String tipoTablero = (String) comboBoxTableros.getSelectedItem();
				
				// Notificar al gestor para crear el tablero adecuado
				GestorPantallaInicio.getGestorPantallaInicio().setPartida(lbl.getNombreBomberman(),tipoTablero);
			}


		}
	}
	private Iterator<JLabelBomberman> getIterator() {
		return this.bombermans.iterator();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GestorPantallaInicio) {
			Object [] res = (Object[]) arg;
			String bombermanActivo = (String) res[0];
			boolean partidaIniciada = (boolean) res[1];

			boolean estaQ = false;
			Iterator<JLabelBomberman> itr = this.getIterator();
			JLabelBomberman jlb = null;

			while (itr.hasNext() && !estaQ) {
				jlb = itr.next();
				estaQ = jlb.setCambio(bombermanActivo);
			}
			
			if (partidaIniciada) {
				PantallaEleccion.this.dispose();
				Partida p = new Partida();
				p.setVisible(true);
			}
			
		}
	}
}