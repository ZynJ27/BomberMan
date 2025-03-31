package bomberman.model;

import java.util.Random;

public class TableroClassic extends Tablero{
	
	public TableroClassic() {
		super();
	}
	
	public void inicializarTablero() {
		//inicializar todas las casillas
		for (int i=0;i<getRows();i++) {
			for (int j=0;j<getCols();j++) {
				getCasillas()[i][j]=new Casilla(i,j);
			}
		}
		
		//colocar bloques duros en coordenadas impares
		for (int i=1;i<getRows();i+=2) {
			for (int j=1;j<getCols();j+=2) {
				getCasillas()[i][j].setBloque("duro");
			}
		}
		
		//colocar bloques blandos aleatoriamente
		Random r=new Random();
		for (int i=0;i<getRows();i++) {
			for (int j=0;j<getCols();j++) { //random()
				if (getCasillas()[i][j].estaVacio() &&  !esPosicionInicial(i, j) && r.nextDouble()<0.6) {
					getCasillas()[i][j].setBloque("blando");
				}
			}
		}

	    // Asegurarse de que no haya bloques ni enemigos en (0,0), (0,1) y (1,0)
	    getCasillas()[0][1].setBloque(""); // Asegurar que (0,1) esta vacia
	    getCasillas()[1][0].setBloque(""); // Asegurar que (1,0) esta vacia
	}

	@Override
	public String getTipoTablero() {
		return "classic";
	}
	
	
	
}