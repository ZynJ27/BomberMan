package bomberman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero {
	
	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] casillas;
	private List<Enemigo> enemigos;
	
	protected Tablero() {
		this.setCasillas(new Casilla[getRows()][getCols()]);
		this.enemigos = new ArrayList<>();
		inicializarTimer();
	}
	
	private void inicializarTimer() {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				moverEnermigosAleatoriamente();
			}
		}, 0, 500);
	}
	
	public void moverEnermigosAleatoriamente() {
		Random r = new Random();
		for (Enemigo enemigo : enemigos) {
			int direccion = r.nextInt(4);
			int px = 0;
			int py = 0;
			
			switch(direccion) {
			case 0:
				px = -1;
				break;
			case 1:
				px = 1;
				break;
			case 2:
				py = - 1;
				break;
			case 3:
				py = 1;
				break;
			}
			int xActual = enemigo.getX();		
			int yActual = enemigo.getY();
			
			  if (xActual + px >= 0 && xActual + px < getRows() &&
			            yActual + py >= 0 && yActual + py < getCols() &&
			            !getCasillas()[xActual + px][yActual + py].tieneBloque()) {
			            
			            getCasillas()[xActual][yActual].setEnemigo(null);
			            enemigo.mover(px, py);
			            getCasillas()[xActual + px][yActual + py].setEnemigo(enemigo);
			        }
		}	
	}
	
	public boolean esPosicionValida(int nuevoX, int nuevoY) {
		
		if (nuevoX < 0 || nuevoY < 0 || nuevoX >= getRows() || nuevoY >= getCols()) {
			return false;
		}
		return !getCasilla(nuevoX, nuevoY).tieneBloque();
	}
	
	public List<Enemigo> getEnemigos(){
		return enemigos;
	}


	protected boolean esPosicionInicial(int i, int j) {
		return (i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0);
	}
	
	public Casilla getCasilla(int x, int y) {
		return casillas[x][y];
	}
	
	public void moverBomberman(int pX, int pY) {
		int i,j;
		boolean movido = false;
		i=0;
		j=0;
		while (i<getRows() && !movido) {
			while (j<getCols() && !movido) {
				if(casillas[i][j].tieneBomberman() && !(i+pX<0||i+pX>=getRows()||j+pY<0||j+pY>=getCols()||getCasillas()[i+pX][j+pY].tieneBloque())) {
					casillas[i][j].moverBomberman(pX,pY);
					movido = true;
				}
				j++;
			}
			j=0;
			i++;
		}
	}

	public void ponerBomba() {
		int i,j;
		boolean plantado = false;
		i=0;
		j=0;
		while (i<getRows() && !plantado) {
			while (j<getCols() && !plantado) {
				if(casillas[i][j].tieneBomberman()) {
					casillas[i][j].ponerBomba();
					plantado = true;
				}
				j++;
			}
			j=0;
			i++;
		}
		
	}
	
	public void explotarBomba(int pX, int pY, int pRadio) {
		boolean arriba=false;
		boolean abajo=false;
		boolean izquierda=false;
		boolean derecha=false;
		int i,j;
		boolean explotado = false;
		i=0;
		j=0;
		getCasillas()[pX][pY].setBomba("");
		
		while (i<getRows() && !explotado) {
			while (j<getCols() && !explotado) {
				if(casillas[i][j].tieneBomberman()) {
					casillas[i][j].bombaExplotada();
					explotado = true;
				}
				j++;
			}
			j=0;
			i++;
		}
		
		getCasillas()[pX][pY].setExplosion("explosion");
		for (i=1;i<=pRadio;i++) {
			if(!arriba && pX-i>=0) {
				if (!getCasillas()[pX-i][pY].tieneBloqueDuro()) {
					getCasillas()[pX-i][pY].setExplosion("explosion");
					getCasillas()[pX-i][pY].setBloque("");
				} else {
					arriba = true;
				}
			}
			if (!abajo && pX+i<getRows()) {
				if (!getCasillas()[pX+i][pY].tieneBloqueDuro()) {
					getCasillas()[pX+i][pY].setExplosion("explosion");
					getCasillas()[pX+i][pY].setBloque("");
				} else { 
					abajo = true;
				}
			}
			if(!izquierda && pY-i>=0) {
				if (!getCasillas()[pX][pY-i].tieneBloqueDuro()) {
					getCasillas()[pX][pY-i].setExplosion("explosion");
					getCasillas()[pX][pY-i].setBloque("");
				} else {
					izquierda = true;
				}
			}
			if (!derecha && pY+i<getCols()) {
				if (!getCasillas()[pX][pY+i].tieneBloqueDuro()) {
					getCasillas()[pX][pY+i].setExplosion("explosion");
					getCasillas()[pX][pY+i].setBloque("");
				} else {
					derecha = true;
				}
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

	public void actualizarCasillas() {
		for(int i=0;i<getRows();i++) {
			for(int j=0;j<getCols();j++) {
				casillas[i][j].actualizar();
			}
		}
	}
	
	protected void crearBomberMan(String bomberMan) {
		casillas[0][0].crearBomberMan(bomberMan);
	}

	public abstract void inicializarTablero();
	
	public abstract String getTipoTablero();

	
}
