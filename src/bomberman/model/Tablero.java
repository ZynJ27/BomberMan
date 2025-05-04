package bomberman.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero extends Observable{

	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] casillas;
	private String dir="";

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
		Object[] array=Arrays.stream(casillas)
				.flatMap(fila->Arrays.stream(fila))
				.filter(c->c.tieneBomberman() && c.getEstadoBomberman().equals("vivo"))
				.toArray();

		if (array!=null) {
			Casilla c=(Casilla) array[0];
			int nuevoX=c.getX()+pX;
			int nuevoY=c.getY()+pY;
			if(!(nuevoX<0||nuevoX>=getRows()||nuevoY<0||nuevoY>=getCols()||casillas[nuevoX][nuevoY].tieneBloque())) {
				// Establecer direcci�n antes de mover
				if (pX == -1 && pY == 0) {
					setDir("w");
				} else if (pX == 0 && pY == -1) {
					setDir("a");
				} else if (pX == 1 && pY == 0) {
					setDir("s");
				} else if (pX == 0 && pY == 1) {
					setDir("d");
				}
				GestorSonidos.getGestorSonidos().sonido("andarBomberman");
				c.moverBomberman(pX,pY);
			}
		}
	}


	public void ponerBomba() {
		Object[] array=Arrays.stream(casillas)
				.flatMap(fila->Arrays.stream(fila))
				.filter(c->c.tieneBomberman() && c.getEstadoBomberman().equals("vivo"))
				.toArray();

		Casilla c=(Casilla) array[0];
		c.ponerBomba();

	}

	public void explotarBomba(int pX, int pY, int pRadio) {
		boolean arriba=false;
		boolean abajo=false;
		boolean izquierda=false;
		boolean derecha=false;

		// para diagonales
		boolean nw = false;
		boolean ne = false;
		boolean sw = false;
		boolean se = false;
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

		if (pRadio > 0) {
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
		} else {
			for (i = 1; i <= Math.abs(pRadio); i++) {
				// Diagonal superior izquierda-> nw
				if (pX - i >= 0 && pY - i >= 0 && !nw) {
					if (!getCasillas()[pX - i][pY - i].tieneBloqueDuro()) {
						getCasillas()[pX - i][pY - i].setExplosion("explosion");
						getCasillas()[pX - i][pY - i].setBloque("");
					} else {
						nw = true;
					}
				}
				// Diagonal superior derecha-> ne
				if (pX - i >= 0 && pY + i < getCols() && !ne) {
					if (!getCasillas()[pX - i][pY + i].tieneBloqueDuro()) {
						getCasillas()[pX - i][pY + i].setExplosion("explosion");
						getCasillas()[pX - i][pY + i].setBloque("");
					} else {
						ne = true;
					}
				}
				// Diagonal inferior izquierda-> sw
				if (pX + i < getRows() && pY - i >= 0 && !sw) {
					if (!getCasillas()[pX + i][pY - i].tieneBloqueDuro()) {
						getCasillas()[pX + i][pY - i].setExplosion("explosion");
						getCasillas()[pX + i][pY - i].setBloque("");
					} else {
						sw = true;
					}
				}
				// Diagonal inferior derecha-> se
				if (pX + i < getRows() && pY + i < getCols() && !se) {
					if (!getCasillas()[pX + i][pY + i].tieneBloqueDuro()) {
						getCasillas()[pX + i][pY + i].setExplosion("explosion");
						getCasillas()[pX + i][pY + i].setBloque("");
					} else {
						se = true;
					}
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
		Arrays.stream(casillas) //convierte la matriz en un stream de filas
		.flatMap(fila->Arrays.stream(fila)) //stream de casilla para cada fila y combinar en uno
		.forEach(c->c.actualizar());
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
		return Arrays.stream(casillas) //stream<Casilla[]>
				.flatMap(fila->Arrays.stream(fila)) //stream<Casilla>
				.anyMatch(c->c.tieneEnemigo());
	}

	private void setDir(String c)
	{
		this.dir=c;
	}

	public String getDir()
	{
		return this.dir;
	}

	public void cerrarPartida() {
		setChanged();
		Object[] a = new Object[2];
		a[0]=true;
		a[1]=false;
		notifyObservers(a);

	}

	public void volverAlMenu() {
		// TODO Auto-generated method stub
		Arrays.stream(casillas) //stream<Casilla[]>
		.flatMap(fila->Arrays.stream(fila)) //stream<Casilla>
		.forEach(c -> c.pararTimers());
		setChanged();
		Object[] a = new Object[2];
		a[0]=false;
		a[1]=true;
		notifyObservers(a);
	}
}
