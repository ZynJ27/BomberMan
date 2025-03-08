package bomberman.viewcontroller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import bomberman.model.TableroClassic;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Partida extends JFrame{

	private JPanel contentPane;
	private JLabel2[][] jLabels;
	private static int filas = 11;
	private static int columnas = 17;
	private Controlador controlador = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partida frame = new Partida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Partida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("stageBack1.png"));
        Image backgroundImage = backgroundIcon.getImage();

        contentPane = new Fondo(backgroundImage);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(filas, columnas, 0, 0));

        setContentPane(contentPane);

        jLabels = new JLabel2[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                contentPane.add(getLblNewLabel(i, j));
            }
        }
        setVisible(true);
        this.addKeyListener(getControlador());
	}

	private JLabel getLblNewLabel(int fila, int columna) {
		JLabel2 lblNewLabel = new JLabel2(fila,columna);
		lblNewLabel.setOpaque(false);
		jLabels[fila][columna]=lblNewLabel;
		return lblNewLabel;
	}
	
	private class Controlador implements KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getKeyCode()==KeyEvent.VK_W) {
				TableroClassic.getTablero().moverBomberman(-1,0);
			}else if(e.getKeyCode()==KeyEvent.VK_A){
				TableroClassic.getTablero().moverBomberman(0,-1);
			}else if(e.getKeyCode()==KeyEvent.VK_S){
				TableroClassic.getTablero().moverBomberman(1,0);
			}else if(e.getKeyCode()==KeyEvent.VK_D){
				TableroClassic.getTablero().moverBomberman(0,1);
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				TableroClassic.getTablero().ponerBomba();
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
