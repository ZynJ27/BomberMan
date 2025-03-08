package bomberman.model;

import java.util.Random;

public class TableroClassic extends Tablero{

	private static TableroClassic miTablero = null;
	
	private TableroClassic() {
		super();
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
	
	public static Tablero getTablero() {
		if(miTablero==null) {
			miTablero = new TableroClassic();
		}
		return miTablero;
	}
}

