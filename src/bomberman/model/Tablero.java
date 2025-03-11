package bomberman.model;

import java.util.Random;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero {
	
	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] casillas;
	private Bomberman bomberMan;
	
	protected Tablero() {
		this.setCasillas(new Casilla[getRows()][getCols()]);
	}

	protected boolean esPosicionInicial(int i, int j) {
		return (i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0);
	}
	
	public Casilla getCasilla(int x, int y) {
		return casillas[x][y];
	}
	
	public void moverBomberman(int pX, int pY) {
		int xActual = this.getBomberMan().getX();
		int yActual = this.getBomberMan().getY();
		if(xActual+pX<0||xActual+pX>=getRows()||yActual+pY<0||yActual+pY>=getCols()||getCasillas()[xActual+pX][yActual+pY].tieneBloque()) {
		}else {
			getCasillas()[xActual][yActual].setBomberMan(null);
			this.getBomberMan().mover(pX, pY);
			getCasillas()[xActual+pX][yActual+pY].setBomberMan(this.getBomberMan());
		}
	}

	public void ponerBomba() {
		// TODO Auto-generated method stub
		if (this.getBomberMan().puedePlantarBomba()) {
			int x = this.getBomberMan().getX();
			int y = this.getBomberMan().getY();
			if (this.getBomberMan() instanceof BombermanBlanco) {
				getCasillas()[x][y].setBomba(x,y,"super");
			} else {
				getCasillas()[x][y].setBomba(x,y,"ultra");
			}
			
		}	
	}
	
	public void explotarBomba(int pX, int pY, int pRadio) {
		
		getCasillas()[pX][pY].setBomba(pX,pY,"");
		this.getBomberMan().bombaExplotada();
		getCasillas()[pX][pY].setExplosion(new Explosion(pX,pY));
		for (int i=1;i<=pRadio;i++) {
			if (pX-i>=0 && !getCasillas()[pX-i][pY].tieneBloqueDuro()) {
				getCasillas()[pX-i][pY].setExplosion(new Explosion(pX-i,pY));
				getCasillas()[pX-i][pY].setBloque(null);
			}
			if (pX+i<getRows() && !getCasillas()[pX+i][pY].tieneBloqueDuro()) {
				getCasillas()[pX+i][pY].setExplosion(new Explosion(pX+i,pY));
				getCasillas()[pX+i][pY].setBloque(null);
			}
			if (pY-i>=0 && !getCasillas()[pX][pY-i].tieneBloqueDuro()) {
				getCasillas()[pX][pY-i].setExplosion(new Explosion(pX,pY-i));
				getCasillas()[pX][pY-i].setBloque(null);
			}
			if (pY+i<getCols() && !getCasillas()[pX][pY+i].tieneBloqueDuro()) {
				getCasillas()[pX][pY+i].setExplosion(new Explosion(pX,pY+i));
				getCasillas()[pX][pY+i].setBloque(null);
			}
			
		}
	}

	public void quitarExplosion(int pX, int pY) {
		getCasillas()[pX][pY].setExplosion(null);
	}

	protected static int getRows() {
		return ROWS;
	}

	protected static int getCols() {
		return COLS;
	}

	protected Casilla[][] getCasillas() {
		return casillas;
	}

	protected void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	protected Bomberman getBomberMan() {
		return bomberMan;
	}

	protected void setBomberMan(Bomberman bomberMan) {
		this.bomberMan = bomberMan;
	}

	public void actualizarCasillas() {
		// TODO Auto-generated method stub
		for(int i=0;i<getRows();i++) {
			for(int j=0;j<getCols();j++) {
				casillas[i][j].actualizar();
			}
		}
	}

}
