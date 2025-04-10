package bomberman.viewcontroller;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bomberman.model.GestorTablero;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;

public class Partida extends JFrame{

	private JPanel contentPane;
	private JLabel2[][] jLabels;
	private static int filas = 11;
	private static int columnas = 17;
	private Controlador controlador = null;
	private Controlador2 controlador2 = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Partida frame = new Partida();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Partida(String tipoTablero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(390, 180, 700, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		 // Cargar la imagen de fondo
		String tipo = tipoTablero;
		ImageIcon backgroundIcon;
		if (tipo.equals("classic")) {
			backgroundIcon = new ImageIcon(getClass().getResource("stageBack1.png"));
		} else if (tipo.equals("soft")) {
			backgroundIcon = new ImageIcon(getClass().getResource("stageBack3.png"));
		} else {
			backgroundIcon = new ImageIcon(getClass().getResource("stageBack2.png"));
		}
        Image backgroundImage = backgroundIcon.getImage();

        // Crear el panel de fondo con la imagen
        contentPane = new JPanel() { 
            @Override 
            protected void paintComponent(Graphics g) { 
                super.paintComponent(g); 
                // Dibuja la imagen de fondo, escalandola para que se ajuste al tama�o del panel 
                if (backgroundImage != null) { 
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
                } 
            } 
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(filas, columnas, 0, 0));
		setContentPane(contentPane);
	    
		jLabels = new JLabel2[filas][columnas];
		for (int i=0; i<filas;i++) {
			for (int j=0;j<columnas;j++) {
				contentPane.add(getLblNewLabel(i,j));
			}
		}
		setVisible(true);
		this.addKeyListener(getControlador());
		this.addWindowListener(getControlador2());
	}

	private JLabel getLblNewLabel(int fila, int columna) {
		JLabel2 lblNewLabel = new JLabel2(fila,columna);
		lblNewLabel.setOpaque(false);
		jLabels[fila][columna]=lblNewLabel;
		return lblNewLabel;
	}
	
	private class Controlador2 implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			GestorTablero.getGestor().getTablero().actualizarCasillas();
			//cambiar por una llamada a otra mae
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private Controlador2 getControlador2() {
		if (controlador2 == null) {
			controlador2 = new Controlador2();
		}
		return controlador2;
	}
	
	private class Controlador implements KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getKeyCode()==KeyEvent.VK_W ||e.getKeyCode()==KeyEvent.VK_UP ) {
				GestorTablero.getGestor().getTablero().moverBomberman(-1,0);
			}else if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT){
				GestorTablero.getGestor().getTablero().moverBomberman(0,-1);
			}else if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){
				GestorTablero.getGestor().getTablero().moverBomberman(1,0);
			}else if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT){
				GestorTablero.getGestor().getTablero().moverBomberman(0,1);
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				GestorTablero.getGestor().getTablero().ponerBomba();
			}
				
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
	return controlador;
	}
	
	
}