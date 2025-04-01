package bomberman.viewcontroller;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bomberman.model.Bloque;
import bomberman.model.BloqueDuro;
import bomberman.model.Bomba;
import bomberman.model.Bomberman;
import bomberman.model.Casilla;
import bomberman.model.Enemigo;
import bomberman.model.Explosion;
import bomberman.model.GestorTablero;
import bomberman.model.Tablero;
import bomberman.model.TableroClassic;

public class JLabel2 extends JLabel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public JLabel2 (int pX, int pY) {
		x = pX;
		y = pY;
		GestorTablero.getGestor().getTablero().getCasilla(x, y).addObserver(this);
		
	}
	
	public int getCoordX() {
		return x;
	}
	
	public int getCoordY() {
		return y;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof Casilla) {
			Object [] res = (Object[]) arg;
			String bomberman = (String) res[0];
			String bomba = (String) res[1];
			String bloque = (String) res[2];
			String enemigo = (String) res[3];
			String explosion = (String) res[4];
			
			
			if(!bomba.equals("") && !bomberman.equals("")) {
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("whitewithbomb1.png")));
 				}else {
 					this.setIcon(new ImageIcon(getClass().getResource("blackwithbomb1.png")));
 				}
			}else if(!bomberman.equals("") && !enemigo.equals("")) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(!bomba.equals("") && !enemigo.equals("")) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(!enemigo.equals("") && !explosion.equals("")) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(!explosion.equals("") && !bomberman.equals("")) {	
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire2.png")));
 				}else {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire4.png")));
 				}
				JOptionPane.showMessageDialog(this, "GAME OVER");
				System.exit(0);
			}else if(!bomba.equals("")) {
				this.setIcon(new ImageIcon(getClass().getResource("bomb1.png")));
			}else if(!bomberman.equals("")) {
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("whitehappy1.png")));
 				}else {
 					this.setIcon(new ImageIcon(getClass().getResource("blackhappy1.png")));
 				}
			}else if(!bloque.equals("")) {
					if (bloque.equals("duro")) {
						this.setIcon(new ImageIcon(getClass().getResource("hard5.png")));
					} else {
						this.setIcon(new ImageIcon(getClass().getResource("soft4.png")));
					}
			}else if(!explosion.equals("")) {
				ImageIcon gif = new ImageIcon(getClass().getResource("miniBlast1.gif"));
				this.setIcon(gif);
				this.setHorizontalAlignment(JLabel.CENTER);
				this.setVerticalAlignment(JLabel.CENTER);
			}else if(!enemigo.equals("")) {
				this.setIcon(new ImageIcon(getClass().getResource("baloon1.png")));
			}else {
				this.setIcon(null);
			}
			this.revalidate();
			this.repaint();
		}
		
		
	}
	
}