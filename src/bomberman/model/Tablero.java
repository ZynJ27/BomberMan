package bomberman.model;

import java.util.Random;

import bomberman.viewcontroller.JLabel2;

public abstract class Tablero {
	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] casillas;
	private Bomberman bomberMan;
	
	protected Tablero() {
		this.casillas=new Casilla[ROWS][COLS];
		inicializarTableroClassic();	
	}
	
	private void inicializarTableroClassic() {
		//inicializar todas las casillas
		for (int i=0;i<ROWS;i++) {
			for (int j=0;j<COLS;j++) {
				casillas[i][j]=new Casilla(i,j);
			}
		}
		
		//colocar bloques duros en coordenadas impares
		for (int i=1;i<ROWS;i+=2) {
			for (int j=1;j<COLS;j+=2) {
				casillas[i][j].setBloque(new BloqueDuro());
			}
		}
		
		//colocar bloques blandos aleatoriamente
		Random r=new Random();
		for (int i=0;i<ROWS;i++) {
			for (int j=0;j<COLS;j++) { //random()
				if (casillas[i][j].estaVacio() &&  !esPosicionInicial(i, j) && r.nextDouble()<0.6) {
					casillas[i][j].setBloque(new BloqueBlando());
				}
			}
		}
		
		//colocar BomberMan en (0, 0)
	    bomberMan = new BombermanBlanco(0,0);
	    casillas[0][0].setBomberMan(bomberMan);

	    // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
	    casillas[0][1].setBloque(null); // Asegurar que (0,1) esté vacía
	    casillas[1][0].setBloque(null); // Asegurar que (1,0) esté vacía
	}

	private boolean esPosicionInicial(int i, int j) {
		return (i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0);
	}
	
	public void setObserver(JLabel2 label,int pX, int pY) {
		casillas[pX][pY]=new Casilla(pX,pY);
		casillas[pX][pY].addObserver(label);
		
	}
	
	public void moverBomberman(int pX, int pY) {
		int xActual = this.bomberMan.getX();
		int yActual = this.bomberMan.getY();
		if(xActual+pX<0||xActual+pX>=ROWS||yActual+pY<0||yActual+pY>=COLS) {
		}else {
			casillas[xActual][yActual].quitarBomberman();
			casillas[pX][pY].setBomberMan(this.bomberMan);
		}
	}

	public void ponerBomba() {
		// TODO Auto-generated method stub
		int x = this.bomberMan.getX();
		int y = this.bomberMan.getY();
		casillas[x][y].setBomba(new BombaSuper(x,y));
	}

}
