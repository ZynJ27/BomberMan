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
		int xActual = this.bomberMan.getX();
		int yActual = this.bomberMan.getY();
		if(xActual+pX<0||xActual+pX>=getRows()||yActual+pY<0||yActual+pY>=getCols()||getCasillas()[xActual+pX][yActual+pY].tieneBloque()) {
		}else {
			getCasillas()[xActual][yActual].setBomberMan(null);
			this.bomberMan.mover(pX, pY);
			getCasillas()[xActual+pX][yActual+pY].setBomberMan(this.bomberMan);
		}
	}

	public void ponerBomba() {
		// TODO Auto-generated method stub
		if (this.bomberMan.puedePlantarBomba()) {
			int x = this.bomberMan.getX();
			int y = this.bomberMan.getY();
			if (this.bomberMan instanceof BombermanBlanco) {
				getCasillas()[x][y].setBomba("super");
			} else {
				getCasillas()[x][y].setBomba("ultra");
			}
			
		}	
	}
	
	public void explotarBomba(int pX, int pY, int pRadio) {
		
		getCasillas()[pX][pY].setBomba("");
		this.bomberMan.bombaExplotada();
		getCasillas()[pX][pY].setExplosion("explosion");
		for (int i=1;i<=pRadio;i++) {
			if (pX-i>=0 && !getCasillas()[pX-i][pY].tieneBloqueDuro()) {
				getCasillas()[pX-i][pY].setExplosion("explosion");
				getCasillas()[pX-i][pY].setBloque("");
			}
			if (pX+i<getRows() && !getCasillas()[pX+i][pY].tieneBloqueDuro()) {
				getCasillas()[pX+i][pY].setExplosion("explosion");
				getCasillas()[pX+i][pY].setBloque("");
			}
			if (pY-i>=0 && !getCasillas()[pX][pY-i].tieneBloqueDuro()) {
				getCasillas()[pX][pY-i].setExplosion("explosion");
				getCasillas()[pX][pY-i].setBloque("");
			}
			if (pY+i<getCols() && !getCasillas()[pX][pY+i].tieneBloqueDuro()) {
				getCasillas()[pX][pY+i].setExplosion("explosion");
				getCasillas()[pX][pY+i].setBloque("");
			}
			
		}
	}

	public void quitarExplosion(int pX, int pY) {
		getCasillas()[pX][pY].setExplosion("");
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
