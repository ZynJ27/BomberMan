package bomberman.model;

import java.util.ArrayList;
import java.util.Random;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero {

	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] casillas;

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
		int i,j;
		boolean movido = false;
		i=0;
		j=0;
		while (i<getRows() && !movido) {
			while (j<getCols() && !movido) {
				if(casillas[i][j].tieneBomberman() && casillas[i][j].getEstadoBomberman().equals("vivo") && !(i+pX<0||i+pX>=getRows()||j+pY<0||j+pY>=getCols()||getCasillas()[i+pX][j+pY].tieneBloque())) {
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
				if(casillas[i][j].tieneBomberman() && casillas[i][j].getEstadoBomberman().equals("vivo")) {
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
		// TODO Auto-generated method stub
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

	public boolean crearEnemigo(String nombre, int x, int y,int id) {

		return casillas[x][y].crearEnemigo(nombre,x,y,id);
	}

	public void moverEnemigo(int x, int y, int id) {
		// TODO Auto-generated method stub 
		ArrayList<String> movimientos = new ArrayList<String>();
		if (x-1>=0&&!casillas[x-1][y].tieneBloque()&&!casillas[x-1][y].tieneEnemigo()&&!casillas[x-1][y].tieneExplosion()&&!(x-2>=0&&casillas[x-2][y].tieneEnemigo())&&!(y-1>=0&&casillas[x-1][y-1].tieneEnemigo())&&!(y+1<getCols()&&casillas[x-1][y+1].tieneEnemigo())) {//arriba
			movimientos.add("Arriba");
		}
		if(x+1<getRows()&&!casillas[x+1][y].tieneBloque()&&!casillas[x+1][y].tieneEnemigo()&&!casillas[x+1][y].tieneExplosion()&&!(x+2<getRows()&&casillas[x+2][y].tieneEnemigo())&&!(y-1>=0&&casillas[x+1][y-1].tieneEnemigo())&&!(y+1<getCols()&&casillas[x+1][y+1].tieneEnemigo())) {//abajo
			movimientos.add("Abajo");
		}
		if(y-1>=0&&!casillas[x][y-1].tieneBloque()&&!casillas[x][y-1].tieneEnemigo()&&!casillas[x][y-1].tieneExplosion()&&!(y-2>=0&&casillas[x][y-2].tieneEnemigo())&&!(x-1>=0&&casillas[x-1][y-1].tieneEnemigo())&&!(x+1<getRows()&&casillas[x+1][y-1].tieneEnemigo())) {//izq
			movimientos.add("Izquierda");
		}
		if(y+1<getCols()&&!casillas[x][y+1].tieneBloque()&&!casillas[x][y+1].tieneEnemigo()&&!casillas[x][y+1].tieneExplosion()&&!(y+2<getCols()&&casillas[x][y+2].tieneEnemigo())&&!(x-1>=0&&casillas[x-1][y+1].tieneEnemigo())&&!(x+1<getRows()&&casillas[x+1][y+1].tieneEnemigo())) {//der
			movimientos.add("Derecha");
		}

		Random r = new Random();
		if(movimientos.size()>0) {
			String mov = movimientos.get(r.nextInt(movimientos.size()));

			if(mov.equals("Arriba")) {
				casillas[x][y].moverEnemigo(-1,0);
			}else if(mov.equals("Abajo")) {
				casillas[x][y].moverEnemigo(1,0);
			}else if(mov.equals("Izquierda")) {
				casillas[x][y].moverEnemigo(0,-1);
			}else if(mov.equals("Derecha")) {
				casillas[x][y].moverEnemigo(0,1);
			}
		}
	}

	public boolean comprobarEnemigosVivos() {
		// TODO Auto-generated method stub
		int i,j;
		boolean vivos = false;
		i=0;
		j=0;
		while (i<getRows() && !vivos) {
			while (j<getCols() && !vivos) {
				if(casillas[i][j].tieneEnemigo()) {
					vivos = true;
					
				}
				j++;
			}
			j=0;
			i++;
		}
		return vivos;
	}

}
