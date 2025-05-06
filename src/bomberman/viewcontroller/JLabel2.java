package bomberman.viewcontroller;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

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
	//private Timer resetTimer;
	
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
			boolean win = (boolean) res[5];
			String estadoBomberman = (String) res[6];
			String dir = (String) res[7];
			
			//if(win) {
				//JOptionPane.showMessageDialog(this, "WIN", "Victory", JOptionPane.INFORMATION_MESSAGE);
				//GestorTablero.getGestor().getTablero().volverAlMenu();
			//}
			//if (estadoBomberman.equals("muerto")){
				//JOptionPane.showMessageDialog(this, "Loss", "Game Over", JOptionPane.ERROR_MESSAGE);
				//System.exit(0);
			//}
			if(estadoBomberman.equals("muerto")){
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire1.png")));
				} else if (bomberman.equals("negro")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire4.png")));
 				} else if (bomberman.equals("rojo")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire2.png")));
				} else if (bomberman.equals("azul")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire3.png")));
				}
			}else if (!bomba.equals("") && !bomberman.equals("")) {
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("whitewithbomb1.png")));
				} else if (bomberman.equals("negro")) {
					this.setIcon(new ImageIcon(getClass().getResource("blackwithbomb2.png")));
				} else if (bomberman.equals("rojo")) {
					this.setIcon(new ImageIcon(getClass().getResource("redwithbomb3.png")));
				} else if (bomberman.equals("azul")) {
					this.setIcon(new ImageIcon(getClass().getResource("bluewithbomb.png")));
				}
			}else if(!bomberman.equals("") && !enemigo.equals("")) {
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire1.png")));
				} else if (bomberman.equals("negro")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire4.png")));
 				} else if (bomberman.equals("rojo")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire2.png")));
				} else if (bomberman.equals("azul")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire3.png")));
				}
			}else if(!bomba.equals("") && !enemigo.equals("")) {
				if (enemigo.equals("baloon")) {
					this.setIcon(new ImageIcon(getClass().getResource("baloon1.png")));
				} else if (enemigo.equals("doria")) {
					this.setIcon(new ImageIcon(getClass().getResource("doria1.png")));
				} else {
					this.setIcon(new ImageIcon(getClass().getResource("pass1.png")));
				}
			}else if(!enemigo.equals("") && !explosion.equals("")) {
				ImageIcon gif = new ImageIcon(getClass().getResource("miniBlast1.gif"));
				this.setIcon(gif);
				this.setHorizontalAlignment(JLabel.CENTER);
				this.setVerticalAlignment(JLabel.CENTER);
			}else if(!explosion.equals("") && !bomberman.equals("")) {	
				if (bomberman.equals("blanco")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire1.png")));
				} else if (bomberman.equals("negro")) {
 					this.setIcon(new ImageIcon(getClass().getResource("onFire4.png")));
 				} else if (bomberman.equals("rojo")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire2.png")));
				} else if (bomberman.equals("azul")) {
					this.setIcon(new ImageIcon(getClass().getResource("onFire3.png")));
				}
			}else if(!bomba.equals("")) {
				if (bomba.equals("super")) {
					this.setIcon(new ImageIcon(getClass().getResource("bomb1.png")));
				} else if (bomba.equals("ultra")) {
					this.setIcon(new ImageIcon(getClass().getResource("bomb2.png")));
				} else if (bomba.equals("diagonal")) {
					this.setIcon(new ImageIcon(getClass().getResource("bomb3.png")));
				}else if (bomba.equals("x")){
					this.setIcon(new ImageIcon(getClass().getResource("bomb4.png")));
				}
			}else if(!bomberman.equals("")) {
				if (bomberman.equals("blanco")) {
					if(dir.equals("w")){
						this.setIcon(new ImageIcon(getClass().getResource("whiteup2.png")));
					} else if(dir.equals("a")){
						this.setIcon(new ImageIcon(getClass().getResource("whiteleft2.png")));
					} else if(dir.equals("s")){
						this.setIcon(new ImageIcon(getClass().getResource("whitedown2.png")));
					} else if(dir.equals("d")){
						this.setIcon(new ImageIcon(getClass().getResource("whiteright2.png")));
					} else {
						this.setIcon(new ImageIcon(getClass().getResource("whitehappy2.png")));
					}

					//volver a imagen default.
					// resetTimer = new Timer(60, e -> {
                    //     this.setIcon(new ImageIcon(getClass().getResource("whitehappy1.png")));
                    // });
                    // resetTimer.setRepeats(false);
                    // resetTimer.start();
	
 				}else if (bomberman.equals("negro")) {
					if(dir.equals("w")){
						this.setIcon(new ImageIcon(getClass().getResource("blackup2.png")));
					} else if(dir.equals("a")){
						this.setIcon(new ImageIcon(getClass().getResource("blackleft2.png")));
					} else if(dir.equals("s")){
						this.setIcon(new ImageIcon(getClass().getResource("blackdown2.png")));
					} else if(dir.equals("d")){
						this.setIcon(new ImageIcon(getClass().getResource("blackright2.png")));
					} else {
						this.setIcon(new ImageIcon(getClass().getResource("blackhappy1.png")));
					}
 				}else if (bomberman.equals("rojo")) {
					if (dir.equals("w")) {
						this.setIcon(new ImageIcon(getClass().getResource("redup2.png")));
					} else if (dir.equals("a")) {
						this.setIcon(new ImageIcon(getClass().getResource("redleft2.png")));
					} else if (dir.equals("s")) {
						this.setIcon(new ImageIcon(getClass().getResource("reddown2.png")));
					} else if (dir.equals("d")) {
						this.setIcon(new ImageIcon(getClass().getResource("redright2.png")));
					} else {
						this.setIcon(new ImageIcon(getClass().getResource("redhappy2.png")));
					}
 				} else if (bomberman.equals("azul")) {
					if (dir.equals("w")) {
						this.setIcon(new ImageIcon(getClass().getResource("blueup.png")));
					} else if (dir.equals("a")) {
						this.setIcon(new ImageIcon(getClass().getResource("blueleft.png")));
					} else if (dir.equals("s")) {
						this.setIcon(new ImageIcon(getClass().getResource("bluedown.png")));
					} else if (dir.equals("d")) {
						this.setIcon(new ImageIcon(getClass().getResource("blueright.png")));
					} else {
						this.setIcon(new ImageIcon(getClass().getResource("bluehappy.png")));
					}
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
				if (enemigo.equals("baloon")) {
					this.setIcon(new ImageIcon(getClass().getResource("baloon1.png")));
				} else if (enemigo.equals("doria")) {
					this.setIcon(new ImageIcon(getClass().getResource("doria1.png")));
				} else {
					this.setIcon(new ImageIcon(getClass().getResource("pass1.png")));
				}
			}else {
				this.setIcon(null);
			}
			this.revalidate();
			this.repaint();
		}
		
		
	}
	
}