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
		for (int i=0;i<getRows();i++) {
			for (int j=0;j<getCols();j++) {
				getCasillas()[i][j]=new Casilla();
			}
		}
		
		//colocar bloques duros en coordenadas impares
		for (int i=1;i<getRows();i+=2) {
			for (int j=1;j<getCols();j+=2) {
				getCasillas()[i][j].setBloque(new BloqueDuro());
			}
		}
		
		//colocar bloques blandos aleatoriamente
		Random r=new Random();
		for (int i=0;i<getRows();i++) {
			for (int j=0;j<getCols();j++) { //random()
				if (getCasillas()[i][j].estaVacio() &&  !esPosicionInicial(i, j) && r.nextDouble()<0.6) {
					getCasillas()[i][j].setBloque(new BloqueBlando());
				}
			}
		}
		
		//colocar BomberMan en (0, 0)
	    this.setBomberMan(new BombermanBlanco(0,0));
	    getCasillas()[0][0].setBomberMan(getBomberMan());

	    // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
	    getCasillas()[0][1].setBloque(null); // Asegurar que (0,1) esté vacía
	    getCasillas()[1][0].setBloque(null); // Asegurar que (1,0) esté vacía
	}
	
	public static TableroClassic getTablero() {
		if(miTablero==null) {
			miTablero = new TableroClassic();
		}
		return miTablero;
	}
	
}
