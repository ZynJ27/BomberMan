package bomberman.model;

import java.util.Random;

public class Tablero {
	private static final int ROWS=11;
	private static final int COLS=17;
	private Casilla[][] tablero;
	private Bomberman bomberMan;
	
	public Tablero(Bomberman bomberman) {
		this.tablero=new Casilla[ROWS][COLS];
		this.bomberMan=bomberman;
		inicializarTableroClassic();	
	}
	
	private void inicializarTableroClassic() {
		//inicializar todas las casillas
		for (int i=0;i<ROWS;i++) {
			for (int j=0;j<COLS;j++) {
				tablero[i][j]=new Casilla();
			}
		}
		
		//colocar bloques duros en coordenadas impares
		for (int i=1;i<ROWS;i+=2) {
			for (int j=1;j<COLS;j+=2) {
				tablero[i][j].setBloque(new BloqueDuro());
			}
		}
		
		//colocar bloques blandos aleatoriamente
		Random r=new Random();
		for (int i=0;i<ROWS;i++) {
			for (int j=0;j<COLS;j++) { //random()
				if (tablero[i][j].estaVacio() &&  !esPosicionInicial(i, j) && r.nextDouble()<0.6) {
					tablero[i][j].setBloque(new BloqueBlando());
				}
			}
		}
		
		//colocar BomberMan en (0, 0)
	    bomberMan = new BombermanBlanco(0,0);
	    tablero[0][0].setBomberMan(bomberMan);

	    // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
	    tablero[0][1].setBloque(null); // Asegurar que (0,1) esté vacía
	    tablero[1][0].setBloque(null); // Asegurar que (1,0) esté vacía
	}

	private boolean esPosicionInicial(int i, int j) {
		return (i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0);
	}

}
