package bomberman.model;

import java.util.Random;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero {
	protected static final int ROWS=11;
	protected static final int COLS=17;
	protected Casilla[][] casillas;
	protected Bomberman bomberMan;
	
	protected Tablero() {
		this.casillas=new Casilla[ROWS][COLS];	
	}

	protected boolean esPosicionInicial(int i, int j) {
		return (i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0);
	}
	
	public void setObserver(JLabel2 label,int pX, int pY) {
		casillas[pX][pY].addObserver(label);
		casillas[pX][pY].notificar();
	}
	
	public void moverBomberman(int pX, int pY) {
		int xActual = this.bomberMan.getX();
		int yActual = this.bomberMan.getY();
		if(xActual+pX<0||xActual+pX>=ROWS||yActual+pY<0||yActual+pY>=COLS||casillas[xActual+pX][yActual+pY].tieneBloque()) {
		}else {
			casillas[xActual][yActual].quitarBomberman();
			this.bomberMan.setX(xActual+pX);
			this.bomberMan.setY(yActual+pY);
			casillas[xActual+pX][yActual+pY].setBomberMan(this.bomberMan);
		}
	}

	public void ponerBomba() {
		// TODO Auto-generated method stub
		int x = this.bomberMan.getX();
		int y = this.bomberMan.getY();
		casillas[x][y].setBomba(new BombaSuper(x,y));
	}

}
