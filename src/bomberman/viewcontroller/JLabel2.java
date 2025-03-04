package bomberman.viewcontroller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bomberman.model.Bloque;
import bomberman.model.Bomba;
import bomberman.model.Bomberman;
import bomberman.model.Casilla;
import bomberman.model.Enemigo;
import bomberman.model.Explosion;
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
		TableroClassic.getTablero().setObserver(this, x, y);
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
			Bomberman bomberman = (Bomberman) res[0];
			Bomba bomba = (Bomba) res[1];
			Bloque bloque = (Bloque) res[2];
			Enemigo enemigo = (Enemigo) res[3];
			Explosion explosion = (Explosion) res[4];
			
			
			if(bomba!=null && bomberman!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("whitewithbomb1.png")));
			}else if(bomberman!=null && enemigo!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(bomba!=null && enemigo!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(enemigo!=null && explosion!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(explosion!=null && bomberman!=null) {	
				this.setIcon(new ImageIcon(getClass().getResource("")));
			}else if(bomba!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("bomb1.png")));
			}else if(bomberman!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("whitehappy1.png")));
			}else if(bloque!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("soft4.png")));
			}else if(explosion!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("kaBomb0.png")));
			}else if(enemigo!=null) {
				this.setIcon(new ImageIcon(getClass().getResource("baloon1.png")));
			}else {
				this.setIcon(null);
			}
			
		}
		
		
	}
	
}
